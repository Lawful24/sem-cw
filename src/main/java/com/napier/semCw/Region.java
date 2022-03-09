/**
 * @author László Tárkányi
 * @date 21/02/2022
 *
 * Represents a region of a continent in the local database.
 * Lackluster implementation, needs improvement.
 *
 * TODO: Figure out what to do with Continent.
 */

package com.napier.semCw;

import java.util.ArrayList;

/**
 * Represents a region of a continent
 */
public class Region {
    /**
     * Host continent
     */
    private enum continent {
        AF, AN, AS, EU, NA, OC, SA
    }

    /**
     * Number of residents
     */
    private int population;

    /**
     * List of all countries within a region
     */
    private ArrayList<Country> countries;

    /**
     * Null constructor
     */
    public Region() {
        //continent = AS; // Asia is the default value in the database
        population = 0;
        countries = new ArrayList<Country>();
    }

    /**
     * Accessors and mutators
     */

    /**
     * Accesses the population attribute of the object
     * @return population
     */
    public int getPopulation() {
        return population;
    }

    /**
     *
     * @param population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Accesses the countries attribute of the object
     * @return countries
     */
    public ArrayList<Country> getCountries() {
        return countries;
    }

    /**
     *
     * @param countries
     */
    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    /**
     * Collects the capital cities located in a region
     * @return List of all capital cities in a region
     */
    public ArrayList<Integer> getCapitalCities() {
        ArrayList<Integer> capitals = new ArrayList<>();

        for (Country c : this.countries) {
            capitals.add(c.getCapitalID());
        }

        return capitals;
    }
}

