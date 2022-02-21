package com.napier.semCw;

import java.util.List;

public class District {

    private String name;

    private Country country;

    private List<City> cities;

    public District()
    {
        name = "";

        country = new Country();

        cities = new List<City>();
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
