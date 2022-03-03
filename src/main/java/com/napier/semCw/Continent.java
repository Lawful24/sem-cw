/**
 * @author : Peter Stefan
 * @date : 2022.02.21
 * Continent class
 */
package com.napier.semCw;

import java.util.ArrayList;
import java.util.List;

public class Continent {

    /**
     * Name of continent
     */
    private String name;

    /**
     * List of countries on a continent
     */
    private ArrayList<Country> countries;

    /**
     * List of regions on a continent
     */
    private ArrayList<Region> regions;

    /**
     * Number of residents
     */
    private int population;

    /**
     * Constructor
     */
    public Continent(){
        population = 0;
        countries = new ArrayList<Country>();
        name = "";
    }

    /**
     * @return population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return countries
     */
    public ArrayList<Country> getCountries() {
        return countries;
    }

    /**
     * @param countries
     */
    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    /**
     * @return regions
     */
    public ArrayList<Region> getRegions() {
        return regions;
    }

    /**
     * @param regions
     */
    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return name
     */
    public void setName(String name) {
        this.name = name;
    }
}