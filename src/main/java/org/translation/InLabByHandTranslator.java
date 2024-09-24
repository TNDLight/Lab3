package org.translation;

import java.util.ArrayList;
import java.util.List;

// Extra Task: if your group has extra time, you can add support for another country code in this class.

/**
 * An implementation of the Translator interface which translates
 * the country code "can" to several languages.
 */
public class InLabByHandTranslator implements Translator {
    /**
     * Returns the language abbreviations for all languages whose translations are
     * available for the given country.
     *
     * @param country the country
     * @return list of language abbreviations which are available for this country
     */

    public static final String CANADA = "can";
    public static final String GERMAN = "de";
    public static final String ENGLISH = "en";
    public static final String CHINESE = "zh";
    public static final String SPANISH = "es";
    public static final String JAPANESE = "ja";

    @Override
    public List<String> getCountryLanguages(String country) {
        if (CANADA.equals(country)) {
            return new ArrayList<>(List.of(GERMAN, ENGLISH, CHINESE, SPANISH, JAPANESE));
        }
        return new ArrayList<>();
    }

    /**
     * Returns the country abbreviations for all countries whose translations are
     * available from this Translator.
     *
     * @return list of country abbreviations for which we have translations available
     */
    @Override
    public List<String> getCountries() {
        return new ArrayList<>(List.of(CANADA));
    }

    /**
     * Returns the name of the country based on the specified country abbreviation and language abbreviation.
     *
     * @param country  the country
     * @param language the language
     * @return the name of the country in the given language or null if no translation is available
     */
    @Override
    public String translate(String country, String language) {
        String countryName = "";

        if (!CANADA.equals(country) && !GERMAN.equals(language) && !ENGLISH.equals(language)
                && !CHINESE.equals(language) && !SPANISH.equals(language) && !JAPANESE.equals(language)) {
            return null;
        }
        if (GERMAN.equals(language)) {
            countryName = "Kanada";
        }
        else if (ENGLISH.equals(language)) {
            countryName = "Canada";
        }
        else if (CHINESE.equals(language)) {
            countryName = "加拿大";
        }
        else if (SPANISH.equals(language)) {
            countryName = "Canadá";
        }
        else if (JAPANESE.equals(language)) {
            countryName = "カナダ";
        }
        return countryName;
    }
}
