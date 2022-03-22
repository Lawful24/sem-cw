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

    /**
     * @Tests for getCitiesInCountryOrganisedByLargest Method
     */
    @Test
    void getCitiesInCountryOrganisedByLargestTestNull(){
        assertEquals(db.getCitiesInCountryOrganisedByLargest(null),null);
    };
    @Test
    void getCitiesInCountryOrganisedByLargestEmptyString(){
        assertEquals(db.getCitiesInCountryOrganisedByLargest(""),null);
    };
    @Test
    void getCitiesInCountryOrganisedByLargestContainsNull(){
        assertNull(db.getCitiesInCountryOrganisedByLargest("W"));
    };

    /**
     * @Tests for getCitiesInRegionOrganisedByLargest Method
     */
    @Test
    void getCitiesInRegionOrganisedByLargestTestNull(){
        assertEquals(db.getCitiesInRegionOrganisedByLargest(null),null);
    };
    @Test
    void getCitiesInRegionOrganisedByLargestEmptyString() {
        assertEquals(db.getCitiesInRegionOrganisedByLargest(""),null);
    }
    @Test
    void getCitiesInRegionOrganisedByLargestContainsNull() {
        assertNull(db.getCitiesInRegionOrganisedByLargest("W"));
    }


    /**
     * @Tests for getCitiesInContinentOrganisedByLargest Method
     */
    @Test
    void getCitiesInContinentOrganisedByLargestNull(){
        assertEquals(db.getCitiesInContinentOrganisedByLargest(null),null);
    };

    @Test
    void getCitiesInContinentOrganisedByLargestEmptyString(){
        assertEquals(db.getCitiesInContinentOrganisedByLargest(""),null);
    };

    @Test
    void getCitiesInContinentOrganisedByLargestContainsNull(){
        assertNull(db.getCitiesInContinentOrganisedByLargest("W"));
    };



    /**
     * @Tests for getCapitalCitiesInRegionOrganisedByLargest Method
     */
    @Test
    void getCapitalCitiesInRegionOrganisedByLargestNull(){
        assertEquals(db.getCapitalCitiesInRegionOrganisedByLargest(null),null);
    };

    @Test
    void getCapitalCitiesInRegionOrganisedByLargestEmptyString(){
        assertEquals(db.getCapitalCitiesInRegionOrganisedByLargest(""),null);
    };

    @Test
    void getCapitalCitiesInRegionOrganisedByLargestContainsNull(){
        assertNull(db.getCapitalCitiesInRegionOrganisedByLargest("W"));
    };


    /**
     * @Tests for topNCapitalCitiesInRegion Method
     */
    @Test
    void topNCapitalCitiesInRegionNull(){
        assertEquals(db.topNCapitalCitiesInRegion(null,4),null);
    };

    @Test
    void topNCapitalCitiesInRegionEmptyString(){
        assertEquals(db.topNCapitalCitiesInRegion("",-3),null);
    };

    @Test
    void topNCapitalCitiesInRegionContainsNull(){
        assertNull(db.topNCapitalCitiesInRegion("W",3));
    };

    @Test
    void topNCapitalCitiesInRegionInvalidNumber(){
        assertNull(db.topNCapitalCitiesInRegion("Eastern Europe",-3));
    };

    /**
     * @Tests for topNCapitalCitiesInContinent Method
     */
    @Test
    void topNCapitalCitiesInContinentNull(){
        assertEquals(db.topNCapitalCitiesInContinent(null,4),null);
    };

    @Test
    void topNCapitalCitiesInContinentEmptyString(){
        assertEquals(db.topNCapitalCitiesInContinent(" ",4),null);
    };

    @Test
    void topNCapitalCitiesInContinentContainsNull(){
        assertNull(db.topNCapitalCitiesInContinent("W",3));
    };

    @Test
    void topNCapitalCitiesInContinentInvalidNumber(){
        assertNull(db.topNCapitalCitiesInContinent("Europe",-3));
    };

    /**
     * @Tests for topNCapitalCitiesInWorld Method
     */
   @Test
    void topNCapitalCitiesInWorldInvalidNumber(){
        assertNull(db.topNCapitalCitiesInWorld(-3));
    };


}