package com.napier.semCw;

/**
 * Represents a country
 * @Author: Maria Bahna
 * @Date: 19/2/2022
 */
public class Country {

    /**
     * Country code
     */
    public String code;

    /**
     * Country name
     */
    public String name;

    /**
     * Country's continent
     */
    public String continent;

    /**
     * Country region
     */
    public String region;

    /**
     * Country's population
     */
    public int population;

    /**
     * Country's capital
     */
    public int capitalID;

    /**
     * Country language
     */
    public String language;

    // Constructors

    public Country() {
        this.code = "";
        this.name = "";
        this.continent = "";
        this.region = "";
        this.population = 0;
        this.capitalID = 0;
        this.language = "";
    }

    public Country(String code, String name, String continent, String region, int population, int capitalID, String language) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capitalID = capitalID;
        this.language = language;
    }

    /**
     * Getter country code
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter country code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter country name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter country name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter country continent
     * @return continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Setter country continent
     * @param continent
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Getter country region
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Setter country region
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Getter country population
     * @return population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Setter country population
     * @param population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Getter country capital
     * @return capital
     */
    public int getCapitalID() {
        return capitalID;
    }

    /**
     * Setter country capital
     * @param capitalID
     */
    public void setCapitalID(int capitalID) {
        this.capitalID = capitalID;
    }

    /**
     * Getter country language
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Setter country language
      * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Compares the population attribute of two Country objects
     * @param country: another object to compare the current one to
     * @return The code of the country with the larger population.
     */
    public Country comparePopulationTo(Country country) {
        if (this.population > country.population) {
            return this;
        } else {
            return country;
        }
    }

    /**
     * toString method
     * @return string containing all country information
     */
    @Override
    public String toString() {
        return "Country{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", population=" + population +
                ", capital='" + capitalID + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
