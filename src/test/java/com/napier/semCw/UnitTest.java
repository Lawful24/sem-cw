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
        ArrayList<City> cities = db.topNCapitalCitiesInRegion("X",-3);
    };



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