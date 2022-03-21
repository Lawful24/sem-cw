package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @Test
    void TestgetCitiesInCountryOrganisedByLargest(){
        ArrayList<City> cities=db.getCitiesInCountryOrganisedByLargest("Romania");
        for( City c : cities ) {
            assertNotNull(c.getId());
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertNotNull(c.getPopulation());
        }
    };

    @Test
    void TestgetCitiesInRegionOrganisedByLargest(){
        ArrayList<City> cities=db.getCitiesInRegionOrganisedByLargest("Eastern Europe");
        for( City c : cities ) {
            assertTrue(c.getId()>=0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation()>=0);
        }
    };
    @Test
    void TestgetCitiesInContinentOrganisedByLargest(){
        ArrayList<City> cities=db.getCitiesInContinentOrganisedByLargest("Europe");
        for( City c : cities ) {
            assertTrue(c.getId()>=0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation()>=0);
        }
    }
    @Test
    void TestgetCapitalCitiesInRegionOrganisedByLargest() {
        ArrayList<City> cities = db.getCapitalCitiesInRegionOrganisedByLargest("Eastern Europe");
        for (City c : cities) {
            assertTrue(c.getId() >= 0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation() >= 0);
        }
    }
    @Test
    void TesttopNCapitalCitiesInRegion() {
        ArrayList<City> cities = db.topNCapitalCitiesInRegion("Eastern Europe",3);
        for (City c : cities) {
            assertTrue(c.getId() >= 0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation() >= 0);
        }
    }
}