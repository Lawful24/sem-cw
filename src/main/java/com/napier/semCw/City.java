package com.napier.semCw;

/**
 *  @author : Mate Botond Nemeth
 *  @date : 21/02/2022
 *  City class
 */
public class City {

    private int id;

    private String name;

    private String countryCode;

    private String districtName;

    private int population;

    /**
     * Constructor for city class
     */
    public City()
    {
        id = 0;
        name = "";
        countryCode = "";
        districtName = "";
        population = 0;
    }

    public City(int id, String name, String countryCode, String districtName, int population)
    {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.districtName = districtName;
        this.population = population;
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
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return district
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * @param districtName
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
     * Compares the population attribute of two City objects
     *
     * @param city: another object to compare the current one to
     * @return The ID of the city with the larger population.
     */
    public City comparePopulationTo(City city) {
        if (this.population > city.population) {
            return this;
        } else {
            return city;
        }
    }
}
