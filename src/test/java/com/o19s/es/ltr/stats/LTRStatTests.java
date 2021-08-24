package com.o19s.es.ltr.stats;

import org.opensearch.test.OpenSearchTestCase;

public class LTRStatTests extends OpenSearchTestCase {
    public void testIsClusterLevel() {
        LTRStat stat1 = new LTRStat(true, () -> "test");
        assertTrue(stat1.isClusterLevel());

        LTRStat stat2 = new LTRStat(false, () -> "test");
        assertFalse(stat2.isClusterLevel());
    }

    public void testGetValue() {
        LTRStat stat2 = new LTRStat(false, () -> "test");
        assertEquals("test", stat2.getStatValue());
    }
}
