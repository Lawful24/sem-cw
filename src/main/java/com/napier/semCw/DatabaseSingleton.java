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
     * @param n:          the amount of countries the user wishes to print
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
        if (cities == null) {
            System.out.println("No cities");
        } else {
            System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", "ID", "Name", "CountryCode", "District", "Population");
            for (City c : cities) {
                if (c == null)
                    continue;
                System.out.printf("%-5s %-35s %-11s %-20s %-10s%n", c.getId(), c.getName(), c.getCountryCode(), c.getDistrictName(), c.getPopulation());
            }
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
     * @param n:      the amount of cities the user wishes to print
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
        System.out.printf("%-12s %-30s %-11s %-10s%n", "Country Code", "Language", "Is Official", "Percentage");
        for (Language l : languages) {
            System.out.printf("%-12s %-30s %-11s %-10s%n", l.getCountryCode(), l.getLanguage(), l.isOfficial(), l.getPercentage());
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
        if (continentName == null || continentName == "") {
            System.out.println("No region name.");
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