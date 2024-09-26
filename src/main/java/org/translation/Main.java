package org.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {
        Translator translator = new JSONTranslator();

        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        String q = "quit";
        while (true) {
            String country = promptForCountry(translator);

            if (q.equals(country)) {
                break;
            }
            CountryCodeConverter converter = new CountryCodeConverter();
            String countryCode = converter.fromCountry(country);

            String language = promptForLanguage(translator, countryCode);
            if (q.equals(language)) {
                break;
            }
            LanguageCodeConverter languageConverter = new LanguageCodeConverter();
            String languageCode = languageConverter.fromLanguage(language);

            System.out.println(country + " in " + language + " is " + translator.translate(countryCode, languageCode));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner s = new Scanner(System.in);
            String textTyped = s.nextLine();

            if (q.equals(textTyped)) {
                break;
            }
        }
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator) {
        List<String> countries = translator.getCountries();
        List<String> fullCountryName = new ArrayList<>();
        CountryCodeConverter converter = new CountryCodeConverter();

        for (String country: countries) {
            String fullCountry = converter.fromCountryCode(country);
            fullCountryName.add(fullCountry);
        }
        Collections.sort(fullCountryName);
        for (int i = 0; i < fullCountryName.size(); i++) {
            System.out.println(fullCountryName.get(i));
        }
        System.out.println("select a country from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();

    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String country) {

        List<String> languagesCode = translator.getCountryLanguages(country);
        List<String> fullLanguagesCode = new ArrayList<>();
        LanguageCodeConverter converter = new LanguageCodeConverter();

        for (String language: languagesCode) {
            String fullLanguage = converter.fromLanguageCode(language);
            fullLanguagesCode.add(fullLanguage);
        }
        Collections.sort(fullLanguagesCode);

        for (int i = 0; i < fullLanguagesCode.size(); i++) {
            System.out.println(fullLanguagesCode.get(i));
        }
        System.out.println("select a language from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
