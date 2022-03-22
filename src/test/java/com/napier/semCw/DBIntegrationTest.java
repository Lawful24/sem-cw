package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DBIntegrationTest {
    static DatabaseSingleton db;

    @BeforeAll
    static void init()
    {
        db = DatabaseSingleton.getInstance();
        db.connect("localhost:33060", 5000);
    }

    @Test
    void testAssertEquals() {
        assertEquals(5, 5);
    }

    @Test
    void countryReportTest1() {
        String countryName = "Hungary";
        assertNotNull(db.printCountryReport(countryName));
    }

    @Test
    void countryReportTest3() {
        String countryName = "Kosovo";
        assertNull(db.printCountryReport(countryName));
    }

    @Test
    void cityReportTest1() {
        String cityName = "Budapest";
        assertNotNull(db.printCityReport(cityName));
    }

    @Test
    void cityReportTest2() {
        String cityName = "Hajduszoboszlo";
        assertNull(db.printCityReport(cityName));
    }

    @Test
    void capitalCityReportTest1() {
        String cityName = "Budapest";
        assertNotNull(db.printCapitalCityReport(cityName));
    }

    @Test
    void capitalCityReportTest2() {
        String cityName = "Hajduszoboszlo";
        assertNull(db.printCapitalCityReport(cityName));
    }

    @Test
    void worldPopulationTest(){
        assertTrue(db.printPopulationOfWorld() > 0);
    }

    @Test
    void regionPopulationTest1(){
        assertTrue(db.printPopulationOfRegion("Eastern Africa") > 0);
    }

    @Test
    void regionPopulationTest2(){
        assertTrue(db.printPopulationOfRegion("xd") == -1);
    }

    @Test
    void countryPopulationTest1(){
        assertTrue(db.printPopulationOfCountry("Hungary") > 0);
    }

    @Test
    void countryPopulationTest2(){
        assertTrue(db.printPopulationOfCountry("xd") == -1);
    }

    @Test
    void cityPopulationTest1(){
        assertTrue(db.printPopulationOfCity("Gyor") > 0);
    }

    @Test
    void cityPopulationTest2(){
        assertTrue(db.printPopulationOfCity("xd") == -1);
    }

    @Test
    void districtPopulationTest1(){
        assertTrue(db.printPopulationOfDistrict("Gyor-Moson-Sopron") > 0);
    }

    @Test
    void districtPopulationTest2(){
        assertTrue(db.printPopulationOfDistrict("xd") == -1);
    }

    @Test
    void continentPopulationTest1(){
        assertTrue(db.printPopulationOfContinent("Africa") > 0);
    }

    @Test
    void continentPopulationTest2(){
        assertTrue(db.printPopulationOfContinent("xd") == -1);
    }
}