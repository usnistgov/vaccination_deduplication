package org.immregistries.vaccination_deduplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class PropertyLoader {

    private HashMap<String, Double> weightedParameters;

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
            weightedParameters.put("dateWindow", stringToDouble(prop.getProperty("dateWindow")));

            weightedParameters.put("weightMaxThreshold", stringToDouble(prop.getProperty("weightMaxThreshold")));
            weightedParameters.put("weightMinThreshold", stringToDouble(prop.getProperty("weightMinThreshold")));

            weightedParameters.put("weightSameSourceHistorical", stringToDouble(prop.getProperty("weightSameSourceHistorical")));
            weightedParameters.put("weightSameSourceAdmin", stringToDouble(prop.getProperty("weightSameSourceAdmin")));
            weightedParameters.put("weightAbsentSource", stringToDouble(prop.getProperty("weightAbsentSource")));
            weightedParameters.put("weightDifferentSource", stringToDouble(prop.getProperty("weightDifferentSource")));

            weightedParameters.put("weightSameLotNumber", stringToDouble(prop.getProperty("weightSameLotNumber")));
            weightedParameters.put("weightDifferentLotNumber", stringToDouble(prop.getProperty("weightDifferentLotNumber")));
            weightedParameters.put("weightAbsentLotNumber", stringToDouble(prop.getProperty("weightAbsentLotNumber")));

            weightedParameters.put("weightSameTradingName", stringToDouble(prop.getProperty("weightSameTradingName")));
            weightedParameters.put("weightDifferentTradingName", stringToDouble(prop.getProperty("weightDifferentTradingName")));
            weightedParameters.put("weightAbsentTradingName", stringToDouble(prop.getProperty("weightAbsentTradingName")));

            weightedParameters.put("weightSameVaccineFamily", stringToDouble(prop.getProperty("weightSameVaccineFamily")));
            weightedParameters.put("weightDifferentVaccineFamily", stringToDouble(prop.getProperty("weightDifferentVaccineFamily")));
            weightedParameters.put("weightAbsentVaccineFamily", stringToDouble(prop.getProperty("weightAbsentVaccineFamily")));

            weightedParameters.put("weightSameOrganisationID", stringToDouble(prop.getProperty("weightSameOrganisationID")));
            weightedParameters.put("weightDifferentOrganisationID", stringToDouble(prop.getProperty("weightDifferentOrganisationID")));
            weightedParameters.put("weightAbsentOrganisationID", stringToDouble(prop.getProperty("weightAbsentOrganisationID")));

            weightedParameters.put("weightDateDifference0", stringToDouble(prop.getProperty("weightDateDifference0")));
            weightedParameters.put("weightDateDifference1", stringToDouble(prop.getProperty("weightDateDifference1")));
            weightedParameters.put("weightDateDifference2", stringToDouble(prop.getProperty("weightDateDifference2")));
            weightedParameters.put("weightDateDifference3", stringToDouble(prop.getProperty("weightDateDifference3")));
            weightedParameters.put("weightDateDifference4", stringToDouble(prop.getProperty("weightDateDifference4")));
            weightedParameters.put("weightDateDifference5", stringToDouble(prop.getProperty("weightDateDifference5")));
            weightedParameters.put("weightDateDifference6AndMore", stringToDouble(prop.getProperty("weightDateDifference6AndMore")));

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

    public void setWeightedParameters(HashMap<String, Double> weightedParameters) {
        this.weightedParameters = weightedParameters;
    }
}
