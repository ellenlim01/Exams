package com.ellen.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionTest {
    @Test
    public void test1() {
        assertNotNull(Query.getConn());
    }
}
