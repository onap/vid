package org.onap.vid.policy;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.onap.vid.policy.rest.RequestDetails;

public class PolicyRestInterfaceTest {

    private PolicyRestInterface createTestSubject() {
        return new PolicyRestInterface();
    }

    @Test
    public void testLogRequest() throws Exception {
        PolicyRestInterface testSubject;
        RequestDetails r = null;

        // default test
        testSubject = createTestSubject();
        testSubject.logRequest(r);
    }

    /*@Test
    public void testInitRestClient() throws Exception {
        PolicyRestInterface testSubject;

        // default test
        testSubject = createTestSubject();
        testSubject.initRestClient();
    }*/

}