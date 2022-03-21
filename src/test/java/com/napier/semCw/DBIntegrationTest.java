package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBIntegrationTest {
    static DatabaseSingleton db;

    @BeforeAll
    static void init()
    {
        db = DatabaseSingleton.getInstance();
        db.connect("localhost:33060", 30000);
    }

    @Test
    void testAssertEquals() {
        assertEquals(5, 5);
    }
}