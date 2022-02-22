package com.napier.semCw;

import java.util.ArrayList;

/**
 * @author : Mate Botond Nemeth
 * @date : 21/02/2022
 * District class
 */
public class District {

    private String name;

    private Country country;

    private ArrayList<City> cities;

    /**
     * Constructor for the District class
     */
    public District()
    {
        name = "";

        country = new Country();

        cities = new ArrayList<City>();
    }


    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @return cities
     */
    public ArrayList<City> getCities() {
        return cities;
    }

    /**
     * @param cities
     */
    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
