/*
 * ============LICENSE_START==========================================
 * ONAP Portal SDK
 * ===================================================================
 * Copyright © 2017 AT&T Intellectual Property. All rights reserved.
 * ===================================================================
 *
 * Unless otherwise specified, all software contained herein is licensed
 * under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Unless otherwise specified, all documentation contained herein is licensed
 * under the Creative Commons License, Attribution 4.0 Intl. (the "License");
 * you may not use this documentation except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             https://creativecommons.org/licenses/by/4.0/
 *
 * Unless required by applicable law or agreed to in writing, documentation
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ============LICENSE_END============================================
 *
 * ECOMP is a trademark and service mark of AT&T Intellectual Property.
 */
package org.onap.portalapp.conf;

import org.onap.vid.controllers.ChangeManagementController;
import org.onap.vid.controllers.MaintenanceController;
import org.onap.vid.controllers.RoleGeneratorController;
import org.onap.portalsdk.core.conf.AppInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.TimeZone;

public class ExternalAppInitializer extends AppInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return super.getRootConfigClasses();
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class<?> appConfigClass = ExternalAppConfig.class;
		// Show something on stdout to indicate the app is starting.
		System.out.println("ExternalAppInitializer: servlet configuration class is " + appConfigClass.getName());
		return new Class[] { appConfigClass };
	}

	/*
	 * URL request will direct to the Spring dispatcher for processing
	 */
	@Override
	protected String[] getServletMappings() {
		return super.getServletMappings();
	}

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		super.onStartup(servletContext);
//		setDefaultTimeZoneToUTC();
//		servletContext.addFilter("requestFromLocalhost", LocalhostFilter.class)
//				.addMappingForUrlPatterns(null, false,
//						String.format("/%s/%s/*", ChangeManagementController.CHANGE_MANAGEMENT, ChangeManagementController.VNF_WORKFLOW_RELATION),
//						String.format("/%s/*", RoleGeneratorController.GENERATE_ROLE_SCRIPT),
//						String.format("/%s/*", MaintenanceController.MAINTENANCE));
//	}

	//set time zone to UTC so Dates would be written to DB in UTC timezone
	private void setDefaultTimeZoneToUTC() {
		System.setProperty("user.timezone", "UTC");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC")); //since TimeZone cache previous user.timezone
	}
}
