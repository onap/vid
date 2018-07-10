package org.onap.vid.job.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.onap.vid.aai.exceptions.InvalidAAIResponseException;
import org.onap.vid.changeManagement.RequestDetailsWrapper;
import org.onap.vid.exceptions.MaxRetriesException;
import org.onap.vid.job.Job;
import org.onap.vid.job.JobCommand;
import org.onap.vid.job.NextCommand;
import org.onap.vid.model.RequestReferencesContainer;
import org.onap.vid.model.serviceInstantiation.ServiceInstantiation;
import org.onap.vid.mso.RestMsoImplementation;
import org.onap.vid.mso.RestObject;
import org.onap.vid.mso.model.ServiceInstantiationRequestDetails;
import org.onap.vid.services.AsyncInstantiationBusinessLogic;
import org.onap.vid.services.AuditService;
import org.onap.portalsdk.core.logging.logic.EELFLoggerDelegate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ServiceInstantiationCommand implements JobCommand {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final EELFLoggerDelegate LOGGER = EELFLoggerDelegate.getLogger(ServiceInstantiationCommand.class);

    @Inject
    private AsyncInstantiationBusinessLogic asyncInstantiationBL;

    @Inject
    private AuditService auditService;

    @Inject
    private RestMsoImplementation restMso;

    private UUID uuid;
    private ServiceInstantiation serviceInstantiationRequest;
    private String userId;

    public ServiceInstantiationCommand() {
    }

    public ServiceInstantiationCommand(UUID uuid, ServiceInstantiation serviceInstantiationRequest, String userId) {
        init(uuid, serviceInstantiationRequest, userId);
    }

    @Override
    public NextCommand call() {
        RequestDetailsWrapper<ServiceInstantiationRequestDetails> requestDetailsWrapper ;
        try {
            requestDetailsWrapper = asyncInstantiationBL.generateServiceInstantiationRequest(
                    uuid, serviceInstantiationRequest, userId
            );
        }

        //Aai return bad response while checking names uniqueness
        catch (InvalidAAIResponseException exception) {
            LOGGER.error("Failed to check name uniqueness in AAI. VID will try again later", exception);
            //put the job in_progress so we will keep trying to check name uniqueness in AAI
            //And then send the request to MSO
            return new NextCommand(Job.JobStatus.IN_PROGRESS, this);
        }

        //Vid reached to max retries while trying to find unique name in AAI
        catch (MaxRetriesException exception) {
            LOGGER.error("Failed to find unused name in AAI. Set the job to FAILED ", exception);
            return handleCommandFailed();
        }

        String path = asyncInstantiationBL.getServiceInstantiationPath(serviceInstantiationRequest);

        RestObject<RequestReferencesContainer> msoResponse = restMso.PostForObject(requestDetailsWrapper, "",
                path, RequestReferencesContainer.class);

        if (msoResponse.getStatusCode() >= 200 && msoResponse.getStatusCode() < 400) {
            final Job.JobStatus jobStatus = Job.JobStatus.IN_PROGRESS;
            final String requestId = msoResponse.get().getRequestReferences().getRequestId();
            final String instanceId = msoResponse.get().getRequestReferences().getInstanceId();
            asyncInstantiationBL.auditVidStatus(uuid, jobStatus);
            setInitialRequestAuditStatusFromMso(requestId);
            asyncInstantiationBL.updateServiceInfo(uuid, x-> {
                x.setJobStatus(jobStatus);
                x.setServiceInstanceId(instanceId);
            });

            return new NextCommand(jobStatus, new InProgressStatusCommand(uuid, requestId));
        } else {
            auditService.setFailedAuditStatusFromMso(uuid,null, msoResponse.getStatusCode(),msoResponse.getRaw());
            return handleCommandFailed();
        }

    }

    private void setInitialRequestAuditStatusFromMso(String requestId){
        final String initialMsoRequestStatus = "REQUESTED";
        asyncInstantiationBL.auditMsoStatus(uuid,initialMsoRequestStatus,requestId,null);
    }

    protected NextCommand handleCommandFailed() {
        asyncInstantiationBL.handleFailedInstantiation(uuid);
        return new NextCommand(Job.JobStatus.FAILED);
    }

    @Override
    public ServiceInstantiationCommand init(UUID jobUuid, Map<String, Object> data) {
        final Object request = data.get("request");

        return init(
                jobUuid,
                OBJECT_MAPPER.convertValue(request, ServiceInstantiation.class),
                (String) data.get("userId")
        );
    }

    private ServiceInstantiationCommand init(UUID jobUuid, ServiceInstantiation serviceInstantiationRequest, String userId) {
        this.uuid = jobUuid;
        this.serviceInstantiationRequest = serviceInstantiationRequest;
        this.userId = userId;

        return this;
    }

    @Override
    public Map<String, Object> getData() {
        return ImmutableMap.of(
                "uuid", uuid,
                "request", serviceInstantiationRequest,
                "userId", userId
        );
    }
}
