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
    public int code;

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
    public String capital;

    /**
     * Country language
     */
    public String language;

    /**
     * Getter country code
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter country code
     * @param code
     */
    public void setCode(int code) {
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
    public String getCapital() {
        return capital;
    }

    /**
     * Setter country capital
     * @param capital
     */
    public void setCapital(String capital) {
        this.capital = capital;
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
                ", capital='" + capital + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
