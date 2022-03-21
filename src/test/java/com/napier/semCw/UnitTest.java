package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    static DatabaseSingleton db;

    @BeforeAll
    static void init() {
        db = DatabaseSingleton.getInstance();
        db.connect("localhost:33060", 30000);
    }

    @Test
    void unitTest() {
        assertEquals(5, 5);
    }

    /**
     * @Tests for getCitiesInCountryOrganisedByLargest Method
     */
    @Test
    void getCitiesInCountryOrganisedByLargestTestNull(){
        ArrayList<City> cities=db.getCitiesInCountryOrganisedByLargest(null);
    };
    @Test
    void getCitiesInCountryOrganisedByLargestEmptyString(){
        ArrayList<City> cities=db.getCitiesInCountryOrganisedByLargest("");
    };
    @Test
    void getCitiesInCountryOrganisedByLargestContainsNull(){
        ArrayList<City> cities=db.getCitiesInCountryOrganisedByLargest("A");
    };
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
     * @Tests for getCitiesInRegionOrganisedByLargest Method
     */
    @Test
    void getCitiesInRegionOrganisedByLargestTestNull(){
        ArrayList<City> cities=db.getCitiesInRegionOrganisedByLargest(null);
    };
    @Test
    void getCitiesInRegionOrganisedByLargestEmptyString() {
        ArrayList<City> cities = db.getCitiesInRegionOrganisedByLargest("");
    }
    @Test
    void getCitiesInRegionOrganisedByLargestContainsNull() {
        ArrayList<City> cities = db.getCitiesInRegionOrganisedByLargest("X");
    }
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
     * @Tests for getCitiesInContinentOrganisedByLargest Method
     */
    @Test
    void getCitiesInContinentOrganisedByLargestNull(){
        ArrayList<City> cities=db.getCitiesInContinentOrganisedByLargest(null);
    };

    @Test
    void getCitiesInContinentOrganisedByLargestEmptyString(){
        ArrayList<City> cities=db.getCitiesInContinentOrganisedByLargest("");
    };

    @Test
    void getCitiesInContinentOrganisedByLargestContainsNull(){
        ArrayList<City> cities=db.getCitiesInContinentOrganisedByLargest("Y");
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

    /**
     * @Tests for getCapitalCitiesInRegionOrganisedByLargest Method
     */
    @Test
    void getCapitalCitiesInRegionOrganisedByLargestNull(){
        ArrayList<City> cities=db.getCapitalCitiesInRegionOrganisedByLargest(null);
    };

    @Test
    void getCapitalCitiesInRegionOrganisedByLargestEmptyString(){
        ArrayList<City> cities=db.getCapitalCitiesInRegionOrganisedByLargest("");
    };

    @Test
    void getCapitalCitiesInRegionOrganisedByLargestContainsNull(){
        ArrayList<City> cities=db.getCapitalCitiesInRegionOrganisedByLargest("Y");
    };
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
     * @Tests for topNCapitalCitiesInRegion Method
     */
    @Test
    void topNCapitalCitiesInRegionNull(){
        ArrayList<City> cities = db.topNCapitalCitiesInRegion(null,0);
    };

    @Test
    void topNCapitalCitiesInRegionEmptyString(){
        ArrayList<City> cities = db.topNCapitalCitiesInRegion("",-4);
    };

    @Test
    void topNCapitalCitiesInRegionContainsNull(){
        ArrayList<City> cities = db.topNCapitalCitiesInRegion("X",3);
    };

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
     * @Tests for topNCapitalCitiesInContinent Method
     */
    @Test
    void topNCapitalCitiesInContinent(){};

    /**
     * @Tests for topNCapitalCitiesInWorld Method
     */
    @Test
    void topNCapitalCitiesInWorld(){}

    /**
     * @Tests for peopleICNCinEachCountry Method
     */
    @Test
    void peopleICNCinEachCountry(){};

}