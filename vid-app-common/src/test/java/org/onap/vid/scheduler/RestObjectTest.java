package org.onap.vid.scheduler;

import org.junit.Test;

public class RestObjectTest {

    private RestObject createTestSubject() {
        return new RestObject();
    }

    @Test
    public void testSet() throws Exception {
        RestObject testSubject;

        // default test
        testSubject = createTestSubject();
        testSubject.set(null);
    }

    @Test
    public void testGet() throws Exception {
        RestObject testSubject;

        // default test
        testSubject = createTestSubject();
        testSubject.get();
    }

    @Test
    public void testSetStatusCode() throws Exception {
        RestObject testSubject;
        int v = 0;

        // default test
        testSubject = createTestSubject();
        testSubject.setStatusCode(v);
    }

    @Test
    public void testGetStatusCode() throws Exception {
        RestObject testSubject;
        int result;

        // default test
        testSubject = createTestSubject();
        result = testSubject.getStatusCode();
    }

    @Test
    public void testSetUUID() throws Exception {
        RestObject testSubject;
        String uuid = "";

        // default test
        testSubject = createTestSubject();
        testSubject.setUUID(uuid);
    }

    @Test
    public void testGetUUID() throws Exception {
        RestObject testSubject;
        String result;

        // default test
        testSubject = createTestSubject();
        result = testSubject.getUUID();
    }
}