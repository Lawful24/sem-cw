package com.napier.semCw;

/**
 *  @author : Mate Botond Nemeth
 *  @date : 21/02/2022
 *  City class
 */
public class City {

    private int id;

    private String name;

    private Country country;

    private District district;

    private int population;

    /**
     * Constructor for city class
     */
    public City()
    {
        id = 0;
        name = "";
        country = new Country();
        district = new District();
        population = 0;
    }


    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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
     * @return district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * @param district
     */
    public void setDistrict(District district) {
        this.district = district;
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
}
