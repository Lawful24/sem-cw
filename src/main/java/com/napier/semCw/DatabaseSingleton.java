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
    public void connect(String location, int delay) {
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
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "asd123asd");
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
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Sorts the countries extracted from the database by population count. Descending order
     *
     * @param countries: a list of countries extracted from the database
     */
    public void sortCountriesByPopulation(ArrayList<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
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
        } else {
            System.out.println("Failed to sort list, there was no argument provided.");
        }

    }

    /**
     * Output of all previously retrieved countries.
     *
     * @param countries: a list of countries extracted from the database
     */
    public void printAllCountries(ArrayList<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
            for (Country c : countries) {
                if (c != null) {
                    System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", c.code, c.name, c.continent, c.region, c.population, c.capitalID);
                } else {
                    System.out.println("Missing element!");
                }
            }
        } else {
            System.out.println("Failed to print list, there was no argument provided.");
        }
    }

    /**
     * Output of all previously retrieved countries, but in a given continent.
     *
     * @param countries:     a list of countries extracted from the database
     * @param continentName: the name of the continent to print the countries of
     */
    public void printAllCountriesInContinent(ArrayList<Country> countries, String continentName) {
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
        if (sortedList != null && !sortedList.isEmpty()) {
            if (n <= sortedList.size() && n > 0) {
                System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
                for (int i = 0; i < n; i++) {
                    if (sortedList.get(i) != null) {
                        System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n",
                                sortedList.get(i).code,
                                sortedList.get(i).name,
                                sortedList.get(i).continent,
                                sortedList.get(i).region,
                                sortedList.get(i).population,
                                sortedList.get(i).capitalID);
                    }
                    System.out.println("Missing elements!");
                }
            } else if (n > sortedList.size()) {
                System.out.println("There are only " + sortedList.size() + " cities stored in the database.");
            } else {
                System.out.println("Please enter a valid number.");
            }
        } else {
            System.out.println("Failed to print list, there was no argument provided.");
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
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Output of all previously retrieved cities.
     *
     * @param cities: a list of cities extracted from the database
     */
    public void printAllCities(ArrayList<City> cities) {
        if (cities != null && !cities.isEmpty()) {
            System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", "ID", "Name", "CountryCode", "District", "Population");
            for (City c : cities) {
                if (c != null) {
                    System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", c.getId(), c.getName(), c.getCountryCode(), c.getDistrictName(), c.getPopulation());
                } else {
                    System.out.println("Missing element!");
                }
            }
        } else {
            System.out.println("Failed to print list, there was no argument provided.");
        }
    }

    /**
     * Sorts the cities extracted from the database by population count. Descending order
     *
     * @param cities: a list of cities extracted from the database
     */
    public void sortCitiesByPopulation(ArrayList<City> cities) {
        if (cities != null && !cities.isEmpty()) {
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
        } else {
            System.out.println("Failed to sort list, there was no argument provided.");
        }
    }

    /**
     * Output a given amount of cities, ranked by population count
     *
     * @param cities: a list of cities extracted from the database
     * @param n: the amount of cities the user wishes to print
     */
    public void printTopNPopulatedCities(ArrayList<City> cities, int n) {
        if (cities != null && !cities.isEmpty()) {
            if (n <= cities.size() && n > 0) {
                // Sort the list in case it hasn't been sorted yet
                sortCitiesByPopulation(cities);

                // Print out given amount of records
                System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", "ID", "Name", "CountryCode", "District", "Population");
                for (int i = 0; i < n; i++) {
                    if (cities.get(i) != null) {
                        System.out.printf("%-5s %-35s %-11s %-20s %-10s%n",
                                cities.get(i).getId(),
                                cities.get(i).getName(),
                                cities.get(i).getCountryCode(),
                                cities.get(i).getDistrictName(),
                                cities.get(i).getPopulation());
                    } else {
                        System.out.println("Missing element!");
                    }
                }
            } else if (n > cities.size()) {
                System.out.println("There are only " + cities.size() + " cities stored in the database.");
            } else {
                System.out.println("Please enter a valid number.");
            }
        } else {
            System.out.println("Failed to print list, there was no argument provided.");
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
        if (languages != null && !languages.isEmpty()) {
            System.out.printf("%-12s %-30s %-11s %-10s%n", "Country Code", "Language", "Is Official", "Percentage");
            for (Language l : languages) {
                if (l != null) {
                    System.out.printf("%-12s %-30s %-11s %-10s%n", l.getCountryCode(), l.getLanguage(), l.isOfficial(), l.getPercentage());
                } else {
                    System.out.println("Missing element!");
                }
            }
        } else {
            System.out.println("Failed to print list, there was no argument provided.");
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
    /**
     *  all the cities in a country organised by largest to smallest
     */
    public ArrayList<City> getCitiesInCountryOrganisedByLargest(String countryName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select world.city.`Name`, world.city.ID, world.city.`CountryCode`,world.city.`District`, world.city.`Population` "+
                            " from world.city "+
                            " join world.country on (world.city.`CountryCode`=world.country.`Code`) "+
                            " where world.country.`Name` = '" + countryName +"' "+
                            " order by world.city.`Population` DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city=new City();
                city.setId(rset.getInt("city.ID")) ;
                city.setName(rset.getString("city.Name"));
                city.setCountryCode(rset.getString("city.CountryCode"));
                city.setDistrictName(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country data");
            return null;
        }
    }
    /**
     * all the cities in a region organised by largest to smallest
     */
    public ArrayList<City> getCitiesInRegionOrganisedByLargest(String regionName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select world.city.`Name`, world.city.`ID`, world.city.`CountryCode`,world.city.`District`, world.city.`Population` "+
                            " from world.city "+
                            " join world.country on (world.city.`CountryCode`=world.country.`Code`) "+
                            " where world.country.`Region` = '" + regionName +"' "+
                            " order by world.city.`Population` DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city=new City();
                city.setId(rset.getInt("city.ID")) ;
                city.setName(rset.getString("city.Name"));
                city.setCountryCode(rset.getString("city.CountryCode"));
                city.setDistrictName(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country data");
            return null;
        }
    }
    /**
     * all the cities in a continent organised by largest to smallest
     */
    public ArrayList<City> getCitiesInContinentOrganisedByLargest(String continentName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select world.city.`Name`, world.city.`ID`, world.city.`CountryCode`,world.city.`District`, world.city.`Population` "+
                            " from world.city "+
                            " join world.country on (world.city.`CountryCode`=world.country.`Code`) "+
                            " where world.country.`Continent` = '" + continentName +"' "+
                            " order by world.city.`Population` DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city=new City();
                city.setId(rset.getInt("city.ID")) ;
                city.setName(rset.getString("city.Name"));
                city.setCountryCode(rset.getString("city.CountryCode"));
                city.setDistrictName(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country data");
            return null;
        }
    }
    /**
     * all the capital cities in a region organised by largest to smallest
     */
    public ArrayList<City> getCapitalCitiesInRegionOrganisedByLargest(String regionName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select world.city.`Name`, world.city.`ID`, world.city.`CountryCode`,world.city.`District`, world.city.`Population` "+
                            " from world.city "+
                            " join world.country on (world.city.`CountryCode`=world.country.`Code`) "+
                            " where world.country.`Region` = '" + regionName +"' and "+
                            " world.city.`ID`= world.country.`Capital` "+
                            " order by world.city.`Population` DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city=new City();
                city.setId(rset.getInt("city.ID")) ;
                city.setName(rset.getString("city.Name"));
                city.setCountryCode(rset.getString("city.CountryCode"));
                city.setDistrictName(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country data");
            return null;
        }
    }
    /**
     * top N populated capital cities in a region where N is provided by user
     */
    public ArrayList<City> topNCapitalCitiesInRegion(String regionName, int n){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select world.city.`Name`, city.ID, world.city.`CountryCode`,world.city.`District`, world.city.`Population` "+
                            " from world.city "+
                            " join world.country on (world.city.`CountryCode`=world.country.`Code`) "+
                            " where world.country.`Region` = '" + regionName +"' and "+
                            " world.city.`ID`= world.country.`Capital` "+
                            " order by world.city.`Population` DESC " +
                            " limit " + n;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city=new City();
                city.setId(rset.getInt("city.ID")) ;
                city.setName(rset.getString("city.Name"));
                city.setCountryCode(rset.getString("city.CountryCode"));
                city.setDistrictName(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region data");
            return null;
        }
    }
    /**
     * top N populated capital cities in a continent where N is provided by user
     */
    public ArrayList<City> topNCapitalCitiesInContinent(String continentName, int n){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select world.city.`Name`, city.ID, world.city.`CountryCode`,world.city.`District`, world.city.`Population` "+
                            " from world.city "+
                            " join world.country on (world.city.`CountryCode`=world.country.`Code`) "+
                            " where world.country.`Continent` = '" + continentName +"' and "+
                            " world.city.`ID`= world.country.`Capital` "+
                            " order by world.city.`Population` DESC " +
                            " limit " + n;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city=new City();
                city.setId(rset.getInt("city.ID")) ;
                city.setName(rset.getString("city.Name"));
                city.setCountryCode(rset.getString("city.CountryCode"));
                city.setDistrictName(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region data");
            return null;
        }
    }
    /**
     * top N populated capital cities in the world where N is provided by user
     */
    public ArrayList<City> topNCapitalCitiesInWorld( int n){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select world.city.`Name`, city.ID, world.city.`CountryCode`,world.city.`District`, world.city.`Population` "+
                            " from world.city "+
                            " join world.country on (world.city.`CountryCode`=world.country.`Code`) "+
                            " where world.city.`ID`= world.country.`Capital` "+
                            " order by world.city.`Population` DESC " +
                            " limit " + n;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city=new City();
                city.setId(rset.getInt("city.ID")) ;
                city.setName(rset.getString("city.Name"));
                city.setCountryCode(rset.getString("city.CountryCode"));
                city.setDistrictName(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region data");
            return null;
        }
    }
    /**
     * view the population of people, people living in cities,
     * 	and people not living in cites in each country
     */
    public void peopleICNCinEachCountry(){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " select t.country as Country , t.population as `Population`, " +
                            " p.`people in cities` as " +
                            "`People living in cities`, t.population-`people in cities` "+
                            " as `People not living in cities` "+
                            " from ( "+
                            " select world.country.`Name` as country ,world.country.`Population` "+
                            " as `population`, world.country.`Code` as code "+
                            " from world.country "+
                            " group by world.country.`Code`) as t "+
                            " join "+
                            " ( "+
                            " select sum(world.city.`Population`) as `people in cities`, "+
                            " world.country.`Code` as code "+
                            " from world.city "+
                            " join world.country on (world.city.`CountryCode`=world.country.`Code`) "+
                            " group by world.country.`Code` "+
                            " ) as p on (p.code=t.code) ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            System.out.printf("%-25s %-25s %-25s %-25s%n", "Country", "Population","People living in cities","People not living in cities");
            while (rset.next()) {
                System.out.printf("%-25s %-25s %-25s %-25s%n", rset.getString("Country"), rset.getInt("Population"),rset.getInt("People living in cities"),rset.getInt("People not living in cities"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get data");

        }
    }

   /**
    * @author Petya
    */

    /**
     * Prints the top n populated countries on a continent where n is provided
     *
     * @param continent
     * @param n : number of countries the user wants to print out
     */

    public void printTopNPopulatedCountriesPerContinent(String continent, int n) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country "
                            + "WHERE continent = '" + continent
                            + "' ORDER BY `Population` desc limit " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            String chosenContinent = "";
            int population = 0;
            String countryName = "";
            chosenContinent = rset.getString("country.Continent");
            population = rset.getInt("country.Population");
            countryName = rset.getString("country.Name");

            System.out.printf("%-13s %-44s %-10s %n", "country.Continent", "country.Name", "Population");


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated countries in a continent");
        }
    }

    /**
     * Prints the top n populated cities in the world where n is provided
     *
     * @param n : number of cities the user wants to print out
     */

    public void printTopNPopulatedCitiesInTheWorld(int n) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country "
                            + "ORDER BY `Population` desc limit " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            int population = 0;
            String countryName = "";
            population = rset.getInt("country.Population");
            countryName = rset.getString("country.Name");

            System.out.printf("%-35s %-10s %n", "city.Name", "city.Population");


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated cities in the world");
        }
    }

    /**
     * Prints the top n populated cities on a continent where n is provided
     *
     * @param continent
     * @param n : number of cities the user wants to print out
     */

    public void printTopNPopulatedCitiesPerContinent(String continent, int n) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country JOIN world.city ON (`Code` = `CountryCode`)"
                            + "WHERE continent = '" + continent
                            + "' ORDER BY world.city.Population desc limit " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            String chosenContinent = "";
            int population = 0;
            String countryName = "";
            chosenContinent = rset.getString("country.Continent");
            population = rset.getInt("country.Population");

            System.out.printf("%-13s %-35-n %-10s %n", "city.Continent", "city.Name", "city.Population");


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated cities on a continent");
        }
    }

    /**
     * Prints the top n populated cities in a region where n is provided
     *
     * @param region
     * @param n : number of cities the user wants to print out
     */

    public void printTopNPopulatedCitiesPerRegion(String region, int n) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country JOIN world.city ON (`Code` = `CountryCode`)"
                            + "WHERE `Region` = '" + region
                            + " 'ORDER BY world.city.Population` desc " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            String chosenContinent = "";
            int population = 0;
            String regionName = "";
            chosenContinent = rset.getString("country.Continent");
            population = rset.getInt("city.Population");
            regionName = rset.getString("country.Region");

            System.out.printf("%-25s %-44s %-35s %-10s %n", "country.Region", "country.Name", "city.Name", "city.Population");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated cities in a region");
        }
    }

    /**
     * Prints the top n populated cities in a country where n is provided
     *
     * @param countryName : name of country
     * @param n : number of countries the user wants to print out
     */

    public void printTopNPopulatedCitiesPerCountry(String countryName, int n) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country JOIN world.city ON (`Code` = `CountryCode`) "
                            + "WHERE world.country.Name = '" + countryName
                            + "' ORDER BY world.city.Population` desc limit" + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            String chosenContinent = "";
            int population = 0;
            String chosenCountry = "";
            chosenContinent = rset.getString("country.Continent");
            population = rset.getInt("country.Population");
            chosenCountry = rset.getString("country.Name");

            System.out.printf("%-44s %-10s %-35s %n", "country.Name", "city.Population", "city.Name");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated cities in a country");
        }
    }
}