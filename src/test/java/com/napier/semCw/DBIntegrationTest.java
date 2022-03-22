package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DBIntegrationTest {
    static DatabaseSingleton db;

    @BeforeAll
    static void init() {
        db = DatabaseSingleton.getInstance();
        db.connect("localhost:33060", 5000);
    }

    //issue #7
    @Test
    void printTopNPopulatedCountriesPerContinentTestNullContinent() {
        assertEquals(db.printTopNPopulatedCountriesPerContinent(null, 4),null);
    }

    @Test
    void printTopNPopulatedCountriesPerContinentTestEmptyContinent() {
        assertEquals(db.printTopNPopulatedCountriesPerContinent("", 4),null);
    }

    /*

    @Test
    void printTopNPopulatedCountriesPerContinentTestInvalidContinent() {
        assertNull(db.printTopNPopulatedCountriesPerContinent("England", 5));
    }

     */

    @Test
    void printTopNPopulatedCountriesPerContinentTestNIsZero() {
        assertEquals(db.printTopNPopulatedCountriesPerContinent("Europe", 0),null);
    }

    @Test
    void printTopNPopulatedCountriesPerContinentTestNIsNegative() {
        assertEquals(db.printTopNPopulatedCountriesPerContinent("Europe", -1),null);
    }

    //issue #6
    @Test
    void printTopNPopulatedCitiesInTheWorldTestNIsZero() {
        assertEquals(db.printTopNPopulatedCitiesInTheWorld(0),null);
    }

    @Test
    void printTopNPopulatedCitiesInTheWorldTestNIsNegative() {
        assertEquals(db.printTopNPopulatedCitiesInTheWorld(-1),null);
    }

    //issue #8
    @Test
    void printTopNPopulatedCitiesPerContinentTestContinentIsNull() {
        assertEquals(db.printTopNPopulatedCitiesPerContinent(null, 2),null);
    }

    @Test
    void printTopNPopulatedCitiesPerContinentTestContinentIsEmpty() {
        assertEquals(db.printTopNPopulatedCitiesPerContinent("", 2),null);
    }

    @Test
    void printTopNPopulatedCitiesPerContinentTestNIsZero() {
        assertEquals(db.printTopNPopulatedCitiesPerContinent("Asia", 0),null);
    }

    @Test
    void printTopNPopulatedCitiesPerContinentTestNIsNegative() {
        assertEquals(db.printTopNPopulatedCitiesPerContinent("Asia", -3),null);
    }

    //issue #9
    @Test
    void printTopNPopulatedCitiesPerRegionTestRegionIsNull() {
        assertEquals(db.printTopNPopulatedCitiesPerRegion(null, 5),null);
    }

    @Test
    void printTopNPopulatedCitiesPerRegionTestRegionIsEmpty() {
        assertEquals(db.printTopNPopulatedCitiesPerRegion("", 5),null);
    }

    @Test
    void printTopNPopulatedCitiesPerRegionTestNIsZero() {
        assertEquals(db.printTopNPopulatedCitiesPerRegion("Central America", 0),null);
    }

    @Test
    void printTopNPopulatedCitiesPerRegionTestNIsNegative() {
        assertEquals(db.printTopNPopulatedCitiesPerRegion("Central America", -3),null);
    }

    //issue #10
    @Test
    void printTopNPopulatedCitiesPerCountryTestCountryIsNull() {
        assertEquals(db.printTopNPopulatedCitiesPerCountry(null, 11),null);
    }

    @Test
    void printTopNPopulatedCitiesPerCountryTestCountryIsEmpty() {
        assertEquals(db.printTopNPopulatedCitiesPerCountry("", 11),null);
    }

    @Test
    void printTopNPopulatedCitiesPerCountryTestNIsZero() {
        assertEquals(db.printTopNPopulatedCitiesPerCountry("Honduras", 0),null);
    }

    @Test
    void printTopNPopulatedCitiesPerCountryTestNIsNegative() {
        assertEquals(db.printTopNPopulatedCitiesPerCountry("Honduras", -1),null);
    }














    
    //issue #11
    @Test
    void printTopNPopulatedCountriesPerRegionTestRegionIsNull() {
        assertEquals(db.printTopNPopulatedCitiesPerRegion(null, 5),null);
    }

    @Test
    void printTopNPopulatedCountriesPerRegionTestRegionIsEmpty() {
        assertEquals(db.printTopNPopulatedCitiesPerRegion("", 5),null);
    }

    @Test
    void printTopNPopulatedCountriesPerRegionTestNIsZero() {
        assertEquals(db.printTopNPopulatedCitiesPerRegion("Central America", 0),null);
    }

    @Test
    void printTopNPopulatedCountriesPerRegionTestNIsNegative() {
        assertEquals(db.printTopNPopulatedCitiesPerRegion("Central America", -3),null);
    }


}
