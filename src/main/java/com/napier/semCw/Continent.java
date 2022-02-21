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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}