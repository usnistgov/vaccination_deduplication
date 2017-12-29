package org.immregistries.vaccination_deduplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class PropertyLoader {

    public static final String DATE_WINDOW = "dateWindow";
    public static final String WEIGHT_MAX_THRESHOLD = "weightMaxThreshold";
    public static final String WEIGHT_MIN_THRESHOLD = "weightMinThreshold";
    public static final String WEIGHT_SAME_SOURCE_HISTORICAL = "weightSameSourceHistorical";
    public static final String WEIGHT_SAME_SOURCE_ADMIN = "weightSameSourceAdmin";
    public static final String WEIGHT_ABSENT_SOURCE = "weightAbsentSource";
    public static final String WEIGHT_DIFFERENT_SOURCE = "weightDifferentSource";
    public static final String WEIGHT_SAME_LOT_NUMBER = "weightSameLotNumber";
    public static final String WEIGHT_DIFFERENT_LOT_NUMBER = "weightDifferentLotNumber";
    public static final String WEIGHT_ABSENT_LOT_NUMBER = "weightAbsentLotNumber";
    public static final String WEIGHT_SAME_TRADING_NAME = "weightSameTradingName";
    public static final String WEIGHT_DIFFERENT_TRADING_NAME = "weightDifferentTradingName";
    public static final String WEIGHT_ABSENT_TRADING_NAME = "weightAbsentTradingName";
    public static final String WEIGHT_SAME_VACCINE_FAMILY = "weightSameVaccineFamily";
    public static final String WEIGHT_DIFFERENT_VACCINE_FAMILY = "weightDifferentVaccineFamily";
    public static final String WEIGHT_ABSENT_VACCINE_FAMILY = "weightAbsentVaccineFamily";
    public static final String WEIGHT_SAME_ORGANISATION_ID = "weightSameOrganisationID";
    public static final String WEIGHT_DIFFERENT_ORGANISATION_ID = "weightDifferentOrganisationID";
    public static final String WEIGHT_ABSENT_ORGANISATION_ID = "weightAbsentOrganisationID";
    public static final String WEIGHT_DATE_DIFFERENCES = "weightDateDifferences";

    private HashMap<String, Double> weightedParameters;
    private ArrayList<Double> weightDateDifferences;

    private static PropertyLoader instance = null;

    protected PropertyLoader() {
        Properties prop = new Properties();
        InputStream input = null;

        URL fileUrl = this.getClass().getResource("/config.properties");

        try {
            input = new FileInputStream(fileUrl.toString());
            // load a properties file
            prop.load(input);

            // get the property values

            // for the weighted method
            weightedParameters.put(DATE_WINDOW, stringToDouble(prop.getProperty(DATE_WINDOW)));

            weightedParameters.put(WEIGHT_MAX_THRESHOLD, stringToDouble(prop.getProperty(WEIGHT_MAX_THRESHOLD)));
            weightedParameters.put(WEIGHT_MIN_THRESHOLD, stringToDouble(prop.getProperty(WEIGHT_MIN_THRESHOLD)));

            weightedParameters.put(WEIGHT_SAME_SOURCE_HISTORICAL, stringToDouble(prop.getProperty(WEIGHT_SAME_SOURCE_HISTORICAL)));
            weightedParameters.put(WEIGHT_SAME_SOURCE_ADMIN, stringToDouble(prop.getProperty(WEIGHT_SAME_SOURCE_ADMIN)));
            weightedParameters.put(WEIGHT_ABSENT_SOURCE, stringToDouble(prop.getProperty(WEIGHT_ABSENT_SOURCE)));
            weightedParameters.put(WEIGHT_DIFFERENT_SOURCE, stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_SOURCE)));

            weightedParameters.put(WEIGHT_SAME_LOT_NUMBER, stringToDouble(prop.getProperty(WEIGHT_SAME_LOT_NUMBER)));
            weightedParameters.put(WEIGHT_DIFFERENT_LOT_NUMBER, stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_LOT_NUMBER)));
            weightedParameters.put(WEIGHT_ABSENT_LOT_NUMBER, stringToDouble(prop.getProperty(WEIGHT_ABSENT_LOT_NUMBER)));

            weightedParameters.put(WEIGHT_SAME_TRADING_NAME, stringToDouble(prop.getProperty(WEIGHT_SAME_TRADING_NAME)));
            weightedParameters.put(WEIGHT_DIFFERENT_TRADING_NAME, stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_TRADING_NAME)));
            weightedParameters.put(WEIGHT_ABSENT_TRADING_NAME, stringToDouble(prop.getProperty(WEIGHT_ABSENT_TRADING_NAME)));

            weightedParameters.put(WEIGHT_SAME_VACCINE_FAMILY, stringToDouble(prop.getProperty(WEIGHT_SAME_VACCINE_FAMILY)));
            weightedParameters.put(WEIGHT_DIFFERENT_VACCINE_FAMILY, stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_VACCINE_FAMILY)));
            weightedParameters.put(WEIGHT_ABSENT_VACCINE_FAMILY, stringToDouble(prop.getProperty(WEIGHT_ABSENT_VACCINE_FAMILY)));

            weightedParameters.put(WEIGHT_SAME_ORGANISATION_ID, stringToDouble(prop.getProperty(WEIGHT_SAME_ORGANISATION_ID)));
            weightedParameters.put(WEIGHT_DIFFERENT_ORGANISATION_ID, stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_ORGANISATION_ID)));
            weightedParameters.put(WEIGHT_ABSENT_ORGANISATION_ID, stringToDouble(prop.getProperty(WEIGHT_ABSENT_ORGANISATION_ID)));

            String[] weightDateDifferencesSplitString = prop.getProperty(WEIGHT_DATE_DIFFERENCES).split(",");
            for (String s : weightDateDifferencesSplitString) {
                weightDateDifferences.add(Double.parseDouble(s));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static PropertyLoader getInstance() {
        if(instance == null) {
            instance = new PropertyLoader();
        }
        return instance;
    }

    private Double stringToDouble(String input) {
        return Double.parseDouble(input);
    }

    public HashMap<String, Double> getWeightedParameters() {
        return weightedParameters;
    }

    public ArrayList<Double> getWeightDateDifferences() {
        return weightDateDifferences;
    }

    public void setWeightDateDifferences(ArrayList<Double> weightDateDifferences) {
        this.weightDateDifferences = weightDateDifferences;
    }
}
