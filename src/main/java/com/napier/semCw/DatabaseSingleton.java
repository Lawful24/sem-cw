package com.napier.semCw;

import java.sql.*;
import java.util.ArrayList;

/**
 * A collection of all features.
 *
 * @author Maté, Maria, Petya, László
 */
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
     *
     * @param location the address of the database
     * @param delay the amount of milliseconds the thread should wait until attempting to establish the connection
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
        if (countries != null && !countries.isEmpty() && continentName != null && !continentName.isEmpty()) {
            System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
            for (Country c : countries) {
                if (c != null) {
                    if (continentName.equals(c.continent)) {
                        System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", c.code, c.name, c.continent, c.region, c.population, c.capitalID);
                    }
                }
            }
        } else {
            System.out.println("Failed to print list, not enough or no arguments provided.");
        }
    }

    /**
     * Output of all previously retrieved countries, but in a given region.
     *
     * @param countries:  a list of countries extracted from the database
     * @param regionName: the name of the region to print the countries of
     */
    public void printAllCountriesInRegion(ArrayList<Country> countries, String regionName) {
        if (countries != null && !countries.isEmpty() && regionName != null && !regionName.isEmpty()) {
            System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
            for (Country c : countries) {
                if (c != null) {
                    if (regionName.equals(c.region)) {
                        System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", c.code, c.name, c.continent, c.region, c.population, c.capitalID);
                    }
                } else {
                    System.out.println("Missing element!");
                }
            }
        } else {
            System.out.println("Failed to print list, not enough or no arguments provided.");
        }
    }

    /**
     * Output a given amount of countries, ranked by population count
     *
     * @param sortedList: a list of countries extracted from the database, but already sorted by population
     * @param n:          the amount of countries the user wishes to print
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
                    } else {
                        System.out.println("Missing element!");
                    }
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
     * @param n:      the amount of cities the user wishes to print
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
     * @return Country
     */
    public Country printCountryReport(String countryName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country "
                            + "WHERE country.Name = '" + countryName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            while(rset.next()) {

                Country c = new Country();
                c.setCode(rset.getString("country.Code"));
                c.setName(rset.getString("country.Name"));
                c.setContinent(rset.getString("country.Continent"));
                c.setRegion(rset.getString("country.Region"));
                c.setPopulation(rset.getInt("country.Population"));
                c.setCapitalID(rset.getInt("country.Capital"));
                c.setLanguage("");
                System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", "Code", "Name", "Continent", "Region", "Population", "CapitalID");
                System.out.printf("%-4s %-44s %-13s %-25s %-10s %-5s%n", c.code, c.name, c.continent, c.region, c.population, c.capitalID);
                return c;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country data");
            return null;
        }
    }

    /**
     * Prints a report of a city
     *
     * @param cityName: the name of the city
     * @return City
     */
    public City printCityReport(String cityName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.city "
                            + "WHERE city.Name = '" + cityName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()) {
                City c = new City();
                c.setId(rset.getInt("city.ID"));
                c.setName(rset.getString("city.Name"));
                c.setCountryCode(rset.getString("city.Name"));
                c.setDistrictName(rset.getString("city.CountryCode"));
                c.setPopulation(rset.getInt("city.Population"));
                System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", "ID", "Name", "CountryCode", "District", "Population");
                System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", rset.getInt("city.ID"), rset.getString("city.Name"), rset.getString("city.CountryCode"), rset.getString("city.District"), rset.getInt("city.Population"));
                return c;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Prints a report of a capital city
     *
     * @param capitalCityName: the name of the capital city
     * @return City
     */
    public City printCapitalCityReport(String capitalCityName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.city JOIN world.country on (country.Capital = city.ID)"
                            + "WHERE city.Name = '" + capitalCityName + "'" + "AND country.Capital = city.ID";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


                while(rset.next()) {
                    City c = new City();
                    c.setId(rset.getInt("city.ID"));
                    c.setName(rset.getString("city.Name"));
                    c.setCountryCode(rset.getString("city.Name"));
                    c.setDistrictName(rset.getString("city.CountryCode"));
                    c.setPopulation(rset.getInt("city.Population"));
                    System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", "ID", "Name", "CountryCode", "District", "Population");
                    System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", rset.getInt("city.ID"), rset.getString("city.Name"), rset.getString("city.CountryCode"), rset.getString("city.District"), rset.getInt("city.Population"));
                    return c;
                }
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Prints the population of the world
     * @return population of the world
     */
    public int printPopulationOfWorld(){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Population "
                            + "FROM country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            while (rset.next()) {
                population += rset.getInt("country.Population");
            }
            System.out.println("The population of the world is " + population);
            return population;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the world");
            return -1;
        }
    }

    /**
     * Prints the population of a region
     *
     * @param continentName: Name of the region
     * @return population of a region
     */
    public int printPopulationOfContinent(String continentName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Population "
                            + "FROM country "
                            + "WHERE country.Continent = '" + continentName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            while (rset.next()) {
                population += rset.getInt("country.Population");
            }
            if(population != 0){
                System.out.println("The population of the continent is " + population);
                return population;
            }else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the continent");
            return -1;
        }
    }

    /**
     * Prints the population of a region
     *
     * @param regionName: Name of the region
     * @return population of a region
     */
    public int printPopulationOfRegion(String regionName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Population "
                            + "FROM country "
                            + "WHERE country.Region = '" + regionName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            while (rset.next()) {
                population += rset.getInt("country.Population");
            }
            if(population != 0){
                System.out.println("The population of the region is " + population);
                return population;
            }else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the region");
            return -1;
        }
    }

    /**
     * Prints the population of a country
     *
     * @param countryName: Name of the country
     * @return population of a country
     */
    public int printPopulationOfCountry(String countryName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.population "
                            + "FROM country "
                            + "WHERE country.Name = '" + countryName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            while(rset.next()) {
                population += rset.getInt("country.Population");
            }
            if(population != 0){
                System.out.println("The population of the country is " + population);
                return population;
            }else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the country");
            return -1;
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
     * @return population of a city
     */
    public int printPopulationOfCity(String cityName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Population "
                            + "FROM city "
                            + "WHERE city.Name = '" + cityName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            while(rset.next()) {
                population += rset.getInt("city.Population");
            }
            if(population != 0){
                System.out.println("The population of the city is " + population);
                return population;
            }else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the city");
            return -1;
        }
    }

    /**
     * Prints the population of a district
     *
     * @param districtName: Name of the district
     * @return population of a district
     */
    public int printPopulationOfDistrict(String districtName){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Population "
                            + "FROM city "
                            + "WHERE District = '" + districtName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            int population = 0;
            while (rset.next()) {
                population += rset.getInt("city.Population");
            }
            if(population != 0){
                System.out.println("The population of the district is " + population);
                return population;
            }else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the district");
            return -1;
        }
    }
    /**
     * All the cities in a country organised by largest to smallest
     * @param countryName
     * @return list of cities in a country organised by largest
     */
    public ArrayList<City> getCitiesInCountryOrganisedByLargest(String countryName) {
        if (countryName == null || countryName=="") {
            System.out.println("No country name.");
            return null;
        }
        else {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "select world.city.`Name`, world.city.ID, world.city.`CountryCode`,world.city.`District`, world.city.`Population` " +
                                " from world.city " +
                                " join world.country on (world.city.`CountryCode`=world.country.`Code`) " +
                                " where world.country.`Name` = '" + countryName + "' " +
                                " order by world.city.`Population` DESC ";

                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract language information
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next()) {
                    City city = new City();
                    city.setId(rset.getInt("city.ID"));
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
    }

    /**
     * all the cities in a region organised by largest to smallest
     * @param regionName
     * @return  List of cities in a region organised by largest
     */
    public ArrayList<City> getCitiesInRegionOrganisedByLargest(String regionName){
        if (regionName == null || regionName=="") {
            System.out.println("No region name.");
            return null;
        }
        else {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "select world.city.`Name`, world.city.`ID`, world.city.`CountryCode`,world.city.`District`, world.city.`Population` " +
                                " from world.city " +
                                " join world.country on (world.city.`CountryCode`=world.country.`Code`) " +
                                " where world.country.`Region` = '" + regionName + "' " +
                                " order by world.city.`Population` DESC ";

                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract language information
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next()) {
                    City city = new City();
                    city.setId(rset.getInt("city.ID"));
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
    }

    /**
     * all the cities in a continent organised by largest to smallest
     * @param continentName
     * @return list of cities in a continent organised by largest
     */
    public ArrayList<City> getCitiesInContinentOrganisedByLargest(String continentName){
        if (continentName == null || continentName=="") {
            System.out.println("No continent name.");
            return null;
        }
        else {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "select world.city.`Name`, world.city.`ID`, world.city.`CountryCode`,world.city.`District`, world.city.`Population` " +
                                " from world.city " +
                                " join world.country on (world.city.`CountryCode`=world.country.`Code`) " +
                                " where world.country.`Continent` = '" + continentName + "' " +
                                " order by world.city.`Population` DESC ";

                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract language information
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next()) {
                    City city = new City();
                    city.setId(rset.getInt("city.ID"));
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
    }

    /**
     * all the capital cities in a region organised by largest to smallest
     * @param regionName
     * @return  list of capital cities in a region organised by largest
     */
    public ArrayList<City> getCapitalCitiesInRegionOrganisedByLargest(String regionName){
        if (regionName == null || regionName=="") {
            System.out.println("No region name.");
            return null;
        }
        else {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "select world.city.`Name`, world.city.`ID`, world.city.`CountryCode`,world.city.`District`, world.city.`Population` " +
                                " from world.city " +
                                " join world.country on (world.city.`CountryCode`=world.country.`Code`) " +
                                " where world.country.`Region` = '" + regionName + "' and " +
                                " world.city.`ID`= world.country.`Capital` " +
                                " order by world.city.`Population` DESC ";

                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract language information
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next()) {
                    City city = new City();
                    city.setId(rset.getInt("city.ID"));
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
    }

    /**
     * top N populated capital cities in a region where N is provided by user
     * @param regionName
     * @param n  integer given by user
     * @return list of top n populated capital cities in a region
     */
    public ArrayList<City> topNCapitalCitiesInRegion(String regionName, int n){
        if (regionName == null || regionName=="") {
            System.out.println("No region name.");
            return null;
        }
        else if(n<=0){
            System.out.println("No valid N, must be above 0.");
            return null;
        }
        else {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "select world.city.`Name`, city.ID, world.city.`CountryCode`,world.city.`District`, world.city.`Population` " +
                                " from world.city " +
                                " join world.country on (world.city.`CountryCode`=world.country.`Code`) " +
                                " where world.country.`Region` = '" + regionName + "' and " +
                                " world.city.`ID`= world.country.`Capital` " +
                                " order by world.city.`Population` DESC " +
                                " limit " + n;

                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract language information
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next()) {
                    City city = new City();
                    city.setId(rset.getInt("city.ID"));
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
    }

    /**
     * top N populated capital cities in a continent where N is provided by user
     * @param continentName
     * @param n integer given by user
     * @return list of top n populated capital cities in a continent
     */
    public ArrayList<City> topNCapitalCitiesInContinent(String continentName, int n) {
        if (continentName == null || continentName.equals("")) {
            System.out.println("No continent name.");
            return null;
        } else if (n <= 0) {
            System.out.println("No valid N, must be above 0.");
            return null;
        }
        else {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "select world.city.`Name`, city.ID, world.city.`CountryCode`,world.city.`District`, world.city.`Population` " +
                                " from world.city " +
                                " join world.country on (world.city.`CountryCode`=world.country.`Code`) " +
                                " where world.country.`Continent` = '" + continentName + "' and " +
                                " world.city.`ID`= world.country.`Capital` " +
                                " order by world.city.`Population` DESC " +
                                " limit " + n;

                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract language information
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next()) {
                    City city = new City();
                    city.setId(rset.getInt("city.ID"));
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
    }

    /**
     * top N populated capital cities in the world where N is provided by user
     * @param n integer given by user
     * @return list of top n populated capital cities in the world
     */
    public ArrayList<City> topNCapitalCitiesInWorld( int n){
     if (n <= 0) {
        System.out.println("No valid N, must be above 0.");
        return null;
    }
     else {
         try {
             // Create an SQL statement
             Statement stmt = con.createStatement();
             // Create string for SQL statement
             String strSelect =
                     "select world.city.`Name`, city.ID, world.city.`CountryCode`,world.city.`District`, world.city.`Population` " +
                             " from world.city " +
                             " join world.country on (world.city.`CountryCode`=world.country.`Code`) " +
                             " where world.city.`ID`= world.country.`Capital` " +
                             " order by world.city.`Population` DESC " +
                             " limit " + n;

             // Execute SQL statement
             ResultSet rset = stmt.executeQuery(strSelect);
             // Extract language information
             ArrayList<City> cities = new ArrayList<City>();
             while (rset.next()) {
                 City city = new City();
                 city.setId(rset.getInt("city.ID"));
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
                if(rset.getString("Country").isEmpty() || rset.getString("Country")==null) {System.out.println("No country."); }
                else if(rset.getInt("Population")<=0){System.out.println("No Population");}
                    else if(rset.getInt("People living in cities")<=0){System.out.println("No Population living in cities");}
                        else  if(rset.getInt("People not living in cities")<=0) {System.out.println("No Population not living in cities");}
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
     * @return countries
     */

    public ArrayList<Country> printTopNPopulatedCountriesPerContinent(String continent, int n) {

        if(continent == null ||  continent =="")
        {
            System.out.println("Continent input is nonexistent.");
            return null;
        }

        if(n<=0)
        {
            System.out.println("N must be bigger then 0.");
            return null;
        }

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

            //get sql results
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                Country c = new Country();
                c.setContinent(rset.getString("country.Continent"));
                c.setName(rset.getString("country.Name"));
                c.setPopulation(rset.getInt("country.Population"));
                countries.add(c);

            }
            int arraySize = countries.size();

            if(n< arraySize)
            {
                System.out.println("There aren't that many countries, choose a smaller N");
                return null;
            }

            //print results
            System.out.printf("%-13s %-44s %-10s %n", "Continent", "Name", "Population");
            for (Country c : countries)
            {
                System.out.printf("%-13s %-44s %-10s %n", c.getContinent(), c.getName(), c.getPopulation());
            }
            return countries;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated countries in a continent");
            return null;
        }
    }


    /**
     * Prints the top n populated cities in the world where n is provided
     *
     * @param n : number of cities the user wants to print out
     * @return cities
     */

    public ArrayList<City> printTopNPopulatedCitiesInTheWorld(int n) {

        if(n<=0)
        {
            System.out.println("N must be bigger then 0.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.city "
                            + "ORDER BY `Population` desc limit " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<>();
            //get sql results
            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("city.Name"));
                c.setPopulation(rset.getInt("city.Population"));
                cities.add(c);
            }
            int arraySize = cities.size();

            if(n< arraySize)
            {
                System.out.println("There aren't that many cities, choose a smaller N");
                return null;
            }

            //print results
            System.out.printf("%-35s %-10s %n", "city.Name", "city.Population");
            for (City c : cities)
            {
                System.out.printf("%-35s %-10s %n", c.getName(), c.getPopulation());
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated cities in the world");
            return null;
        }
    }


    /**
     * Prints the top n populated cities on a continent where n is provided
     *
     * @param continent
     * @param n : number of cities the user wants to print out
     * @return cities
     */

    public ArrayList<City> printTopNPopulatedCitiesPerContinent(String continent, int n) {

        if(continent == null ||  continent =="")
        {
            System.out.println("Continent input is nonexistent.");
            return null;
        }

        if(n<=0)
        {
            System.out.println("N must be bigger then 0.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT  world.country.continent, world.city.Name, world.city.Population"
                            + " FROM world.country JOIN world.city ON (`Code` = `CountryCode`)"
                            + " WHERE `Continent` = '" + continent
                            + "' ORDER BY world.city.Population desc limit " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<>();

            //get sql results
            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("city.Name"));
                c.setPopulation(rset.getInt("city.Population"));
                cities.add(c);
            }
            int arraySize = cities.size();
            if(n< arraySize)
            {
                System.out.println("There aren't that many cities, choose a smaller N");
                return null;
            }
            //print results
            System.out.printf("%-35s %-10s %n", "Name", "Population");
            for (City c : cities)
            {
                System.out.printf("%-35s %-10s %n", c.getName(), c.getPopulation());
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated cities on a continent");
            return null;
        }
    }

    /**
     * Prints the top n populated cities in a region where n is provided
     *
     * @param regionName : name of region
     * @param n : number of cities the user wants to print out
     * @return cities
     */

    public ArrayList<City> printTopNPopulatedCitiesPerRegion(String regionName, int n) {

        if(regionName == null ||  regionName == "")
        {
            System.out.println("Region input is nonexistent.");
            return null;
        }

        if(n<=0)
        {
            System.out.println("N must be bigger then 0.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country JOIN world.city ON (`Code` = `CountryCode`) "
                            + "WHERE world.country.Region = '" + regionName
                            + "' ORDER BY world.city.Population desc limit " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("city.Name"));
                c.setPopulation(rset.getInt("city.Population"));
                cities.add(c);
            }

            int arraySize = cities.size();
            if(n< arraySize)
            {
                System.out.println("There aren't that many cities, choose a smaller N");
                return null;
            }

            System.out.printf("%-35s %-10s %n", "Name", "Population");
            for (City c : cities)
            {
                System.out.printf("%-35s %-10s %n", c.getName(), c.getPopulation());
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated cities in a region");
            return null;
        }
    }

    /**
     * Prints the top n populated cities in a country where n is provided
     *
     * @param countryName : name of country
     * @param n : number of countries the user wants to print out
     * @return cities
     */

    public ArrayList<City> printTopNPopulatedCitiesPerCountry(String countryName, int n) {

        if(countryName == null ||  countryName =="")
        {
            System.out.println("Country input is nonexistent.");
            return null;
        }

        if(n<=0)
        {
            System.out.println("N must be bigger then 0.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country JOIN world.city ON (`Code` = `CountryCode`) "
                            + "WHERE world.country.Name = '" + countryName
                            + "' ORDER BY world.city.Population desc limit " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<City>();

            //get sql results
            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("city.Name"));
                c.setPopulation(rset.getInt("city.Population"));
                cities.add(c);
            }
            int arraySize = cities.size();
            if(n< arraySize)
            {
                System.out.println("There aren't that many cities, choose a smaller N");
                return null;
            }

            //print results
            System.out.printf("%-35s %-10s %n", "Name", "Population");
            for (City c : cities)
            {
                System.out.printf("%-35s %-10s %n", c.getName(), c.getPopulation());
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated cities in a country");
            return null;
        }
    }

    /**
     * Prints the top n populated countries in a region where n is provided
     *
     * @param regionName
     * @param n : number of countries the user wants to print out
     * @return countries
     */

    public ArrayList<Country> printTopNPopulatedCountriesPerRegion(String regionName, int n) {

        if(regionName == null ||  regionName =="")
        {
            System.out.println("Region input is nonexistent.");
            return null;
        }

        if(n<=0)
        {
            System.out.println("N must be bigger then 0.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country"
                            + " WHERE Region = '" + regionName
                            + "' ORDER BY Population desc limit " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<>();
            //get sql results
            while (rset.next()) {
                Country c = new Country();
                c.setRegion(rset.getString("country.Region"));
                c.setName(rset.getString("country.Name"));
                c.setPopulation(rset.getInt("country.Population"));
                countries.add(c);
            }
            int arraySize = countries.size();
            if(n< arraySize)
            {
                System.out.println("There aren't that many countries, choose a smaller N");
                return null;
            }
            //print results
            System.out.printf("%-25s %-44s %-10s %n", "Region", "Name", "Population");
            for (Country c : countries)
            {
                System.out.printf("%-25s %-44s  %-10s %n", c.getRegion(),c.getName(), c.getPopulation());
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated countries in a region");
            return null;
        }
    }

    /**
     * Prints the top n populated cities in a district where n is provided
     *
     * @param districtName
     * @param n : number of cities the user wants to print out
     * @return cities
     */

    public ArrayList<City> printTopNPopulatedCitiesPerDistrict(String districtName, int n) {

        if(districtName == null || districtName.equals(""))
        {
            System.out.println("District input is nonexistent.");
            return null;
        }

        if(n<=0)
        {
            System.out.println("N must be bigger then 0.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.city "
                            + "WHERE `District` = '" + districtName
                            + "' ORDER BY world.city.Population desc limit " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<>();
            //get sql results
            while (rset.next()) {
                City c = new City();
                c.setDistrictName(rset.getString("city.District"));
                c.setName(rset.getString("city.Name"));
                c.setPopulation(rset.getInt("city.Population"));
                cities.add(c);
            }
            int arraySize = cities.size();
            if(n< arraySize)
            {
                System.out.println("There aren't that many countries, choose a smaller N");
                return null;
            }
            //write sql results
            System.out.printf("%-35s %-20s %-10s %n", "Name", "District", "Population");
            for (City c : cities)
            {
                System.out.printf("%-35s %-20s %-10s %n",c.getName(), c.getDistrictName(), c.getPopulation());
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top n populated cities in a district");
            return null;
        }
    }

    /**
     * Prints all the capital cities organized from largest to smallest based on population
     * return cities
     */

    public ArrayList<City> printCapitalCitiesFromLargestToSmallest() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country JOIN world.city ON (world.country.`Capital` = world.city.`ID`) "
                            + " ORDER BY world.city.`Population` desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<>();
            //get sql results in list
            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("city.Name"));
                c.setPopulation(rset.getInt("city.Population"));
                cities.add(c);

            }
            System.out.printf("%-35s %-10s %n", "Name", "Population");
            for (City c : cities)
            {
                System.out.printf("%-35s %-10s %n",c.getName(), c.getPopulation());
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to list capital cities from largest to smallest");
            return null;
        }
    }

    /**
     * Prints all the capital cities organized from largest to smallest based on population in a given continent
     * @param continent
     * @return cities
     */

    public ArrayList<City> printCapitalCitiesFromLargestToSmallestInAContinent(String continent) {

        if(continent == null ||  continent =="")
        {
            System.out.println("Continent input is nonexistent.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * "
                            + "FROM world.country JOIN world.city ON (world.country.Capital = world.city.ID)"
                            + " WHERE `Continent` = '" + continent
                            + " 'ORDER BY city.Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<>();
            //get results into arraylist
            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("city.Name"));
                c.setPopulation(rset.getInt("city.Population"));
                cities.add(c);

            }
            //print results
            System.out.printf("%-35s %-10s %n", "Name", "Population");
            for (City c : cities)
            {
                System.out.printf("%-35s %-10s %n",c.getName(), c.getPopulation());
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to list capital cities from largest to smallest");
            return null;
        }
    }

    /**
     * Prints all the cities organized from largest to smallest based on population
     * @return cities
     */

    public ArrayList<City> printAllCitiesFromLargestToSmallest() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT world.city.Name, world.city.Population"
                            + " FROM world.city"
                            + " ORDER BY `Population` desc ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cities = new ArrayList<>();
            //get sql results into arraylist
            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("city.Name"));
                c.setPopulation(rset.getInt("city.Population"));
                cities.add(c);

            }
            //print result arraylist
            System.out.printf("%-35s %-10s %n", "Name", "Population");
            for (City c : cities)
            {
                System.out.printf("%-35s %-10s %n",c.getName(), c.getPopulation());
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to list cities from largest to smallest");
            return null;
        }
    }

    /**
     * Prints an ordered list of languages with the number of speakers in the world.
     *
     * @param languages the list of languages to be compared
     */
    public void printNumOfSpeakersFromList(String[] languages) {
        if (languages != null && languages.length > 0) {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Build string for list of countries
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT speakers.Language, ");
                sb.append("(ROUND(SUM((speakers.Percentage * (c.Population / 100))), 0)) AS number_of_speakers, ");
                sb.append("CONCAT(((ROUND(SUM((speakers.Percentage * (c.Population / 100))), 0)) / (SELECT SUM(country.Population) FROM country) * 100), '%') AS percent_of_population ");
                sb.append("FROM country c ");
                sb.append("INNER JOIN ");
                sb.append("(SELECT countrylanguage.CountryCode, countrylanguage.Language, countrylanguage.Percentage ");
                sb.append("FROM countrylanguage ");
                sb.append("WHERE Language = '");
                sb.append(languages[0]);
                sb.append("' ");

                for (int i = 1; i < languages.length; i++) {
                    sb.append("OR Language = '");
                    sb.append(languages[i]);
                    sb.append("' ");
                }
                sb.append(") speakers ");
                sb.append("ON c.Code = speakers.CountryCode ");
                sb.append("GROUP BY speakers.Language ");
                sb.append("ORDER BY number_of_speakers DESC;");
                // Create string for SQL statement
                String strSelect = sb.toString();
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.printf("%-25s %-18s %-31s%n", "Language", "Number of Speakers", "Percentage of Global Population");
                while (rset.next()) {
                    System.out.printf("%-25s %-18s %-31s%n",
                            rset.getString("Language"),
                            rset.getInt("number_of_speakers"),
                            rset.getString("percent_of_population"));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get population details");
            }
        } else {
            System.out.println("Failed to print list, there was no argument provided.");
        }
    }

    /**
     * view the population of people, people living in cities,
     * 	and people not living in cites in each region
     */
    public void peopleICNCinEachRegion(){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " select t.region as Region, t.population as Population, p.`people in cities` as `People living in cities`, t.population-p.`people in cities` as \n" +
                            " `People not living in cities`\n" +
                            " from \n" +
                            " (select world.country.`Region` as region ,sum(world.country.`Population`) as `population`\n" +
                            " from world.country\n" +
                            " group by world.country.`Region`\n" +
                            " ) as t\n" +
                            " join \n" +
                            " (select sum(world.city.`Population`) as `people in cities`, world.country.`Region` as region\n" +
                            " from world.city\n" +
                            " join world.country on (world.city.`CountryCode`=world.country.`Code`)\n" +
                            " group by  world.country.`Region` ) as p\n" +
                            " on (t.region=p.region) ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            System.out.printf("%-25s %-25s %-25s %-25s%n", "Region", "Population","People living in cities","People not living in cities");
            while (rset.next()) {
                if(rset.getString("Region").isEmpty() || rset.getString("Region")==null) {System.out.println("No Region."); }
                else if(rset.getInt("Population")<=0){System.out.println("No Population");}
                else if(rset.getInt("People living in cities")<=0){System.out.println("No Population living in cities");}
                else  if(rset.getInt("People not living in cities")<=0) {System.out.println("No Population not living in cities");}
                System.out.printf("%-25s %-25s %-25s %-25s%n", rset.getString("Region"), rset.getInt("Population"),rset.getInt("People living in cities"),rset.getInt("People not living in cities"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get data");

        }
    }

    /**
     * view the population of people, people living in cities,
     * 	and people not living in cites in each continent
     */
    public void peopleICNCinEachContinent(){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " select t.continent as Continent,t.population as Population,p.`people in cities` as `People living in cities`,t.population-p.`people in cities` as \n" +
                            "`People not living in cities`\n" +
                            "from( \n" +
                            "select world.country.`Continent` as continent ,sum(world.country.`Population`) as `population`\n" +
                            "from world.country\n" +
                            "group by world.country.`Continent`\n" +
                            ") as t\n" +
                            "join \n" +
                            "(select sum(world.city.`Population`) as `people in cities`, world.country.`Continent` \n" +
                            "as continent\n" +
                            "from world.city\n" +
                            "join world.country on (world.city.`CountryCode`=world.country.`Code`)\n" +
                            "group by  world.country.`Continent` ) \n" +
                            "as p\n" +
                            "on (t.continent=p.continent) ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            System.out.printf("%-25s %-25s %-25s %-25s%n", "Continent", "Population","People living in cities","People not living in cities");
            while (rset.next()) {
                if(rset.getString("Continent").isEmpty() || rset.getString("Continent")==null) {System.out.println("No Continent."); }
                else if(rset.getLong("Population")<=0){System.out.println("No Population");}
                else if(rset.getLong("People living in cities")<=0){System.out.println("No Population living in cities");}
                else  if(rset.getLong("People not living in cities")<=0) {System.out.println("No Population not living in cities");}
                System.out.printf("%-25s %-25s %-25s %-25s%n", rset.getString("Continent"), rset.getLong("Population"),rset.getLong("People living in cities"),rset.getLong("People not living in cities"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get data");

        }
    }

    /**
     *
     */
    public void continentPopulationReport(){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " select t.continent as `Continent`,t.population as `Population`,p.`people in cities`/t.population as `percentage of people living in cities`,(t.population - p.`people in cities`)/t.population as `percentage of people not living in cities` "+
                            "from( \n" +
                            "select world.country.Continent as continent ,sum(world.country.Population) as population\n" +
                            "from world.country\n" +
                            "group by world.country.Continent\n" +
                            ") as t\n" +
                            "join \n" +
                            "(select sum(world.city.Population) as `people in cities`, world.country.Continent \n" +
                            "as continent\n" +
                            "from world.city\n" +
                            "join world.country on (world.city.CountryCode=world.country.Code)\n" +
                            "group by  world.country.Continent ) \n" +
                            "as p\n" +
                            "on (t.continent=p.continent) ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            System.out.printf("%-25s %-25s %-25s %-25s%n", "Continent", "Population","Percentage of people living in cities","Percentage of people not living in cities");
            while (rset.next()) {
                if(rset.getString("Continent").isEmpty() || rset.getString("Continent")==null) {System.out.println("No continent."); }
                else if(rset.getLong("Population")<=0){System.out.println("No Population");}
                else if(rset.getDouble("percentage of people living in cities")<=0){System.out.println("No Population living in cities");}
                else  if(rset.getDouble("percentage of people not living in cities")<=0) {System.out.println("No Population not living in cities");}
                System.out.printf("%-25s %-25s %-25s %-25s%n", rset.getString("Continent"), rset.getLong("Population"),rset.getDouble("percentage of people living in cities"),rset.getDouble("percentage of people not living in cities"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get data");

        }
    }



    public void countryPopulationReport(){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " select t.country as `Country` , t.population as `Population`,p.`people in cities`/t.population as `Percentage of people living in cities`, (t.population-`people in cities`)/t.population as `Percentage of people not living in cities` "+
                            " from ( "+
                            " select world.country.Name as country ,world.country.Population "+
                            " as population, world.country.Code as code "+
                            " from world.country "+
                            " group by world.country.Code) as t "+
                            " join "+
                            " ( "+
                            " select sum(world.city.Population) as `people in cities`, "+
                            " world.country.Code as code "+
                            " from world.city "+
                            " join world.country on (world.city.CountryCode=world.country.Code) "+
                            " group by world.country.Code "+
                            " ) as p on (p.code=t.code) ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            System.out.printf("%-25s %-25s %-25s %-25s%n", "Country", "Population","Percentage of people living in cities","Percentage of people not living in cities");
            while (rset.next()) {
                if(rset.getString("Country").isEmpty() || rset.getString("Country")==null) {System.out.println("No country."); }
                else if(rset.getLong("Population")<=0){System.out.println("No Population");}
                else if(rset.getDouble("Percentage of people living in cities")<=0){System.out.println("No Population living in cities");}
                else  if(rset.getDouble("Percentage of people not living in cities")<=0) {System.out.println("No Population not living in cities");}
                System.out.printf("%-25s %-25s %-25s %-25s%n", rset.getString("Country"), rset.getLong("Population"),rset.getDouble("Percentage of people living in cities"),rset.getDouble("Percentage of people not living in cities"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get data");

        }
    }



    public void regionPopulationReport(){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " select t.region as `Region`, t.population as `Population`, p.`people in cities`/t.population as `percentage of people living in cities`, (t.population-p.`people in cities`)/t.population as `percentage of people not living in cities` "+
                            " from \n" +
                            " (select world.country.Region as region ,sum(world.country.Population) as population\n" +
                            " from world.country\n" +
                            " group by world.country.Region\n" +
                            " ) as t\n" +
                            " join \n" +
                            " (select sum(world.city.Population) as `people in cities`, world.country.Region as region\n" +
                            " from world.city\n" +
                            " join world.country on (world.city.CountryCode=world.country.Code)\n" +
                            " group by  world.country.Region ) as p\n" +
                            " on (t.region=p.region) ";
// Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            System.out.printf("%-25s %-25s %-25s %-25s%n", "Region", "Population","Percentage of people living in cities","Percentage of people not living in cities");
            while (rset.next()) {
                if(rset.getString("Region").isEmpty() || rset.getString("Region")==null) {System.out.println("No region."); }
                else if(rset.getLong("Population")<=0){System.out.println("No Population");}
                else if(rset.getDouble("Percentage of people living in cities")<=0){System.out.println("No Population living in cities");}
                else  if(rset.getDouble("Percentage of people not living in cities")<=0) {System.out.println("No Population not living in cities");}
                System.out.printf("%-25s %-25s %-25s %-25s%n", rset.getString("Region"), rset.getLong("Population"),rset.getDouble("percentage of people living in cities"),rset.getDouble("percentage of people not living in cities"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get data");

        }
    }
}