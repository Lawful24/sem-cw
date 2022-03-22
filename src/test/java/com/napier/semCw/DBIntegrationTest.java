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



}
