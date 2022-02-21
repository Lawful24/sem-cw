package com.napier.semCw;

import java.util.ArrayList;

public class District {

    private String name;

    private Country country;

    private ArrayList<City> cities;

    public District()
    {
        name = "";

        country = new Country();

        cities = new ArrayList<City>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
