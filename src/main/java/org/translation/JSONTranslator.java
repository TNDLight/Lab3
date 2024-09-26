package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    private final Map<String, Map<String, String>> languageMap = new HashMap<>();
    private final ArrayList<String> countries = new ArrayList<>();
    private final String alpha3 = "alpha3";
    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */

    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            for (Object line : jsonArray) {
                JSONObject jsonObject = (JSONObject) line;
                countries.add(jsonObject.getString(alpha3));
                Map<String, String> languages = new HashMap<>();
                for (String key : jsonObject.keySet()) {
                    if (!"id".equals(key) && !"alpha2".equals(key) && !"alpha3".equals(key)) {
                        languages.put(key, jsonObject.getString(key));
                    }
                }
                languageMap.put(jsonObject.getString(alpha3), languages);

            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {

        List<String> countryTranslation = new ArrayList<>();
        for (String key: languageMap.keySet()) {
            if (key.equals(country)) {
                countryTranslation.addAll(languageMap.get(key).keySet());
            }
        }
        return countryTranslation;
    }

    @Override
    public List<String> getCountries() {
        return countries;
    }

    @Override
    public String translate(String country, String language) {
        String countryName = "";

        if (!countries.contains(country) || !getCountryLanguages(country).contains(language)) {
            return null;
        }
        for (String c : languageMap.keySet()) {
            if (country.equals(c)) {
                for (Map.Entry<String, String> result : languageMap.get(c).entrySet()) {
                    String code = result.getKey();
                    if (code.equals(language)) {
                        countryName = result.getValue();
                    }
                }
            }
        }
        return countryName;
    }

}
