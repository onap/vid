package org.openecomp.vid.policy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(

{ RestObjectTest.class, PolicyResponseWrapperTest.class, PolicyRestIntTest.class, PolicyUtilTest.class,
		PolicyRestInterfaceTest.class, org.openecomp.vid.policy.rest.TestSuite.class })
public class TestSuite { // nothing
}
