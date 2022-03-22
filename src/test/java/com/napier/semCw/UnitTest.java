package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    static DatabaseSingleton db;

    @BeforeAll
    static void init() {
        db = DatabaseSingleton.getInstance();
        db.connect("localhost:33060", 5000);
    }

    @Test
    void unitTest() {
        assertEquals(5, 5);
    }

    @Test
    void countryReportTest2() {
        assertNull(db.printCountryReport(null));
    }

    @Test
    void cityReportTest2() {
        assertNull(db.printCityReport(null));
    }

    @Test
    void capitalCityReportTest2() {
        assertNull(db.printCapitalCityReport(null));
    }
}