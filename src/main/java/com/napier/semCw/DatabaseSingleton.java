package com.napier.semCw;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseSingleton {
    private static DatabaseSingleton instance;

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    private DatabaseSingleton() {
    }


    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }

        return instance;
    }

    /* Methods */

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "asd123asd");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Gets all current countries
     *
     * @return A list of all current countries in the world, or null in case of an error.
     */
    public ArrayList<Country> getAllCountriesFromDatabase() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country();
                c.code = rset.getString("country.Code");
                c.name = rset.getString("country.Name");
                c.continent = rset.getString("country.Continent");
                c.region = rset.getString("country.Region");
                c.population = rset.getInt("country.Population");
                c.capitalID = rset.getInt("country.Capital");
                c.language = "";
                countries.add(c);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * Sorts the countries extracted from the database by population count. Descending order
     *
     * @param countries: a list of countries extracted from the database
     */
    public void sortCountriesByPopulation(ArrayList<Country> countries) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            // Reverse bubble sort
            for (int i = countries.size() - 1; i > 0; i--) {
                if (countries.get(i).code.equals(countries.get(i).comparePopulationTo(countries.get(i - 1)).code)) {
                    countries.set(i - 1, countries.set(i, countries.get(i - 1))); // switch adjacent elements
                    sorted = false; // flip flag if an element was modified
                }
            }
        }
    }

    /**
     * Output of all previously retrieved countries.
     *
     * @param countries: a list of countries extracted from the database
     */
    public void printAllCountries(ArrayList<Country> countries) {
        System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
        for (Country c : countries) {
            System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", c.code, c.name, c.continent, c.region, c.population, c.capitalID);
        }
    }

    /**
     * Output of all previously retrieved countries, but in a given continent.
     *
     * @param countries:     a list of countries extracted from the database
     * @param continentName: the name of the continent to print the countries of
     */
    public void printAllCountries(ArrayList<Country> countries, String continentName) {
        System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
        for (Country c : countries) {
            if (continentName.equals(c.continent)) {
                System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", c.code, c.name, c.continent, c.region, c.population, c.capitalID);
            }
        }
    }

    /**
     * Output of all previously retrieved countries, but in a given region.
     *
     * @param countries:  a list of countries extracted from the database
     * @param regionName: the name of the region to print the countries of
     */
    public void printAllCountriesInRegion(ArrayList<Country> countries, String regionName) {
        System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
        for (Country c : countries) {
            if (regionName.equals(c.region)) {
                System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", c.code, c.name, c.continent, c.region, c.population, c.capitalID);
            }
        }
    }

    /**
     * Output a given amount of countries, ranked by population count
     *
     * @param sortedList: a list of countries extracted from the database, but already sorted by population
     * @param n: the amount of countries the user wishes to print
     */
    public void printTopNPopulatedCountries(ArrayList<Country> sortedList, int n) {
        System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n",
                    sortedList.get(i).code,
                    sortedList.get(i).name,
                    sortedList.get(i).continent,
                    sortedList.get(i).region,
                    sortedList.get(i).population,
                    sortedList.get(i).capitalID);
        }
    }

    /**
     * Gets all current cities
     *
     * @return A list of all current cities in the world, or null in case of an error.
     */
    public ArrayList<City> getAllCitiesFromDatabase() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM city";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(); // id, name, country, district, population
                c.setId(rset.getInt("city.ID"));
                c.setName(rset.getString("city.Name"));
                c.setCountryCode(rset.getString("city.CountryCode"));
                c.setDistrictName(rset.getString("city.District"));
                c.setPopulation(rset.getInt("city.Population"));
                cities.add(c);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * Output of all previously retrieved cities.
     *
     * @param cities: a list of cities extracted from the database
     */
    public void printAllCities(ArrayList<City> cities) {
        System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", "ID", "Name", "CountryCode", "District", "Population");
        for (City c : cities) {
            System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", c.getId(), c.getName(), c.getCountryCode(), c.getDistrictName(), c.getPopulation());
        }
    }

    /**
     * Sorts the cities extracted from the database by population count. Descending order
     *
     * @param cities: a list of cities extracted from the database
     */
    public void sortCitiesByPopulation(ArrayList<City> cities) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            // Reverse bubble sort
            for (int i = cities.size() - 1; i > 0; i--) {
                if (cities.get(i).getId() == cities.get(i).comparePopulationTo(cities.get(i - 1)).getId()) {
                    cities.set(i - 1, cities.set(i, cities.get(i - 1))); // switch adjacent elements
                    sorted = false; // flip flag if an element was modified
                }
            }
        }
    }

    /**
     * Output a given amount of cities, ranked by population count
     *
     * @param cities: a list of cities extracted from the database
     * @param n: the amount of cities the user wishes to print
     */
    public void printTopNPopulatedCities(ArrayList<City> cities, int n) {
        if (n <= cities.size() && n > 0) {
            // Sort the list in case it hasn't been sorted yet
            sortCitiesByPopulation(cities);

            // Print out given amount of records
            System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", "ID", "Name", "CountryCode", "District", "Population");
            for (int i = 0; i < n; i++) {
                System.out.printf("%-5s %-35s %-11s %-20s %-10s%n",
                        cities.get(i).getId(),
                        cities.get(i).getName(),
                        cities.get(i).getCountryCode(),
                        cities.get(i).getDistrictName(),
                        cities.get(i).getPopulation());
            }
        } else if (n > cities.size()) {
            System.out.println("There are only " + cities.size() + " cities stored in the database.");
        } else {
            System.out.println("Please enter a valid number.");
        }
    }


    /**
     * Prints a report of a country
     *
     * @param countryName: the name of the country
     */
    public void printCountryReport(String countryName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM country"
                            + "WHERE Name = " + countryName;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
            System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", rset.getString("country.Code"), rset.getString("country.Name"), rset.getString("country.Continent"), rset.getString("country.Region"), rset.getInt("country.Population"), rset.getInt("country.Capital"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
        }
    }

    /**
     * Prints a report of a city
     *
     * @param cityName: the name of the city
     */
    public void printCityReport(String cityName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM city"
                            + "WHERE Name = " + cityName;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", "ID", "Name", "CountryCode", "District", "Population");
            System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", rset.getInt("city.ID"), rset.getString("city.Name"), rset.getString("city.CountryCode"), rset.getString("city.District"), rset.getInt("city.Population"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
    }

    /**
     * Prints a report of a capital city
     *
     * @param capitalCityName: the name of the capital city
     */
    public void printCapitalCityReport(String capitalCityName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM city"
                            + "WHERE Name = " + capitalCityName;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            String strSelect2 =
                    "SELECT * "
                            + "FROM country"
                            + "WHERE Capital = " + rset.getInt("city.ID");
            // Execute SQL statement
            ResultSet rset2 = stmt.executeQuery(strSelect);
            if(!rset2.getString("country.Name").isEmpty()) {
                System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", "ID", "Name", "CountryCode", "District", "Population");
                System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", rset.getInt("city.ID"), rset.getString("city.Name"), rset.getString("city.CountryCode"), rset.getString("city.District"), rset.getInt("city.Population"));
            }else{
                System.out.println("Not capital");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
    }

    /**
     * Prints the population of the world
     */
    public void printPopulationOfWorld(){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            while (rset.next()) {
                population += rset.getInt("country.Population");
            }
            System.out.println("The population of the world is " + population);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the world");
        }
    }

    /**
     * Prints the population of a region
     *
     * @param regionName: Name of the region
     */
    public void printPopulationOfRegion(String regionName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM country"
                            + "WHERE Region = " + regionName;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            while (rset.next()) {
                population += rset.getInt("country.Population");
            }
            System.out.println("The population of the region is " + population);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the region");
        }
    }

    /**
     * Prints the population of a country
     *
     * @param countryName: Name of the country
     */
    public void printPopulationOfCountry(String countryName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM country"
                            + "WHERE Name = " + countryName;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            population += rset.getInt("country.Population");
            System.out.println("The population of the region is " + population);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the country");
        }
    }

    /**
     * Gets all current languages
     *
     * @return A list of all current languages in the world, or null in case of an error.
     */
    public ArrayList<Language> getAllLanguagesFromDatabase() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM countrylanguage";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<Language> languages = new ArrayList<Language>();
            while (rset.next()) {
                languages.add(new Language(
                        rset.getString("countrylanguage.CountryCode"),
                        rset.getString("countrylanguage.Language"),
                        rset.getString("countrylanguage.IsOfficial").equals("T"),
                        rset.getDouble("countrylanguage.Percentage")
                ));
            }
            return languages;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language data");
            return null;
        }
    }

    /**
     * Output of all previously retrieved languages.
     *
     * @param languages: a list of languages extracted from the database
     */
    public void printAllLanguages(ArrayList<Language> languages) {
        System.out.printf("%-12s %-30s %-11s %-10s%n", "Country Code", "Language", "Is Official", "Percentage");
        for (Language l : languages) {
            System.out.printf("%-12s %-30s %-11s %-10s%n", l.getCountryCode(), l.getLanguage(), l.isOfficial(), l.getPercentage());
        }
    }

    /**
     * Prints the population of a city
     *
     * @param cityName: Name of the city
     */
    public void printPopulationOfCity(String cityName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM city"
                            + "WHERE Name = " + cityName;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            population += rset.getInt("city.Population");
            System.out.println("The population of the city is " + population);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the city");
        }
    }

    /**
     * Prints the population of a district
     *
     * @param districtName: Name of the district
     */
    public void printPopulationOfDistrict(String districtName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM city"
                            + "WHERE District = " + districtName;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            while (rset.next()) {
                population += rset.getInt("city.Population");
            }
            System.out.println("The population of the district is " + population);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the district");
        }
    }
}
