/**
 * @author: László Tárkányi
 * @date: 09/03/2022
 *
 * A class that represents a language from the database.
 */

package com.napier.semCw;

public class Language {
    private String countryCode;
    private String language;
    private boolean isOfficial;
    private double percentage;

    public Language(String countryCode, String language, boolean isOfficial, double percentage) {
        this.countryCode = countryCode;
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    /**
     * Accesses the country code of the language
     * @return countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Accesses the name of the language
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Accesses a binary value that determines whether the language is the country's official language or not
     * @return isOfficial
     */
    public boolean isOfficial() {
        return isOfficial;
    }

    /**
     * Accesses the percentage of the country's population speaking the language
     * @return percentage
     */
    public double getPercentage() {
        return percentage;
    }
}
