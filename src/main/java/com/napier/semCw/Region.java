/**
 * Author: László Tárkányi
 * Last modified: 21/02/2022
 *
 * Represents a region of a continent in the local database.
 * Lackluster implementation, needs improvement.
 *
 * TODO: Other classes used are yet to be connected.
 */

package com.napier.semCw;

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
    private List<Country> countries;

    /**
     * Null constructor
     */
    public Region() {
        continent = AS; // Asia is the default value in the database
        population = 0;
        countries = new List<Country>();
    }

    /**
     * Accessors and mutators
     */

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    /**
     *
     * @return List of all cities in a region
     */
    public ArrayList<City> getCities() {
        ArrayList<City> cities = new List<City>();

        for (Country c : this.countries) {
            for (City city : c.getCities()) {
                cities.add(city);
            }
        }

        return cities;
    }

    /**
     *
     * @return List of all capital cities in a region
     */
    public ArrayList<City> getCapitalCities() {
        ArrayList<City> capitals = new List<City>();

        for (Country c : this.countries) {
            capitals.add(c.getCapital());
        }

        return capitals;
    }
}
