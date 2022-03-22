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

    /**
     * @Test  getCitiesInCountryOrganisedByLargest Method
     */
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

    /**
     * @Test  getCitiesInRegionOrganisedByLargest Method
     */
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
    /**
     * @Test  getCitiesInContinentOrganisedByLargest Method
     */
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
    /**
     * @Test  getCapitalCitiesInRegionOrganisedByLargest Method
     */
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
    /**
     * @Test  topNCapitalCitiesInRegion Method
     */
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
    /**
     * @Test topNCapitalCitiesInContinent Method
     */
    @Test
    void TesttopNCapitalCitiesInContinent(){
        ArrayList<City> cities = db.topNCapitalCitiesInContinent("Europe",3);
        for (City c : cities) {
            assertTrue(c.getId() >= 0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation() >= 0);
        }
    };
    /**
     * @Test  topNCapitalCitiesInWorld Method
     */
    @Test
    void TesttopNCapitalCitiesInWorld(){
        ArrayList<City> cities = db.topNCapitalCitiesInWorld(3);
        for (City c : cities) {
            assertTrue(c.getId() >= 0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation() >= 0);
        }
    };
    /**
     * @Test  peopleICNCinEachCountry Method
     */
    @Test
    void TestpeopleICNCinEachCountry(){
        db.peopleICNCinEachCountry();
    };

}