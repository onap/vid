package org.openecomp.vid.scheduler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(

{ SchedulerRestInterfaceTest.class, org.openecomp.vid.scheduler.RestObjects.TestSuite.class })
public class TestSuite { // nothing
}
