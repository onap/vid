/*-
 * ============LICENSE_START=======================================================
 * VID
 * ================================================================================
 * Copyright (C) 2017 - 2019 AT&T Intellectual Property. All rights reserved.
 * Modifications Copyright (C) 2018 - 2019 Nokia. All rights reserved.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */

package org.onap.vid.roles;


import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.onap.portalsdk.core.util.SystemProperties;
import org.onap.vid.properties.Features;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.togglz.core.manager.FeatureManager;

@Component
public class RoleValidatorFactory {
    private final FeatureManager featureManager;

    @Autowired
    public RoleValidatorFactory(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }


    public RoleValidator by(List<Role> roles) {
        final boolean disableRoles = StringUtils
            .equals(SystemProperties.getProperty("role_management_activated"), "false");
        return by(roles, disableRoles);
    }

    public RoleValidator by(List<Role> roles, boolean disableRoles) {

        if(disableRoles) {
            return new AlwaysValidRoleValidator();
        }
        else if (featureManager.isActive(Features.FLAG_2006_USER_PERMISSIONS_BY_OWNING_ENTITY)){
            return new RoleValidatorsComposer(
                new RoleValidatorBySubscriberAndServiceType(roles),
                new RoleValidatorByOwningEntity()
            );
        }
        else {
            return new RoleValidatorBySubscriberAndServiceType(roles);
        }
    }
}
