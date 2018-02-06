package org.immregistries.vaccination_deduplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


/**
 * This object reads the property file and loads the values. The classes needing these values will then call upon this class to get them.
 */
public class PropertyLoader {

    public static final String DATE_WINDOW = "dateWindow";
    public static final String WEIGHT_MAX_THRESHOLD = "weightMaxThreshold";
    public static final String WEIGHT_MIN_THRESHOLD = "weightMinThreshold";

    public static final String WEIGHT_SAME_SOURCE_HISTORICAL = "weightSameSourceHistorical";
    public static final String WEIGHT_SAME_SOURCE_ADMIN = "weightSameSourceAdmin";
    public static final String WEIGHT_ABSENT_SOURCE = "weightAbsentSource";
    public static final String WEIGHT_DIFFERENT_SOURCE = "weightDifferentSource";

    public static final String WEIGHT_SAME_CVX = "weightSameCVX";
    public static final String WEIGHT_ABSENT_CVX = "weightAbsentCVX";
    public static final String WEIGHT_DIFFERENT_CVX = "weightDifferentCVX";

    public static final String WEIGHT_SAME_MVX = "weightSameMVX";
    public static final String WEIGHT_ABSENT_MVX = "weightAbsentMVX";
    public static final String WEIGHT_DIFFERENT_MVX = "weightDifferentMVX";

    public static final String WEIGHT_SAME_PRODUCT_CODE = "weightSameProductCode";
    public static final String WEIGHT_ABSENT_PRODUCT_CODE = "weightAbsentProductCode";
    public static final String WEIGHT_DIFFERENT_PRODUCT_CODE = "weightDifferentProductCode";

    public static final String WEIGHT_SAME_LOT_NUMBER = "weightSameLotNumber";
    public static final String WEIGHT_DIFFERENT_LOT_NUMBER = "weightDifferentLotNumber";
    public static final String WEIGHT_ABSENT_LOT_NUMBER = "weightAbsentLotNumber";

    public static final String WEIGHT_SAME_ORGANISATION_ID = "weightSameOrganisationID";
    public static final String WEIGHT_DIFFERENT_ORGANISATION_ID = "weightDifferentOrganisationID";
    public static final String WEIGHT_ABSENT_ORGANISATION_ID = "weightAbsentOrganisationID";

    public static final String WEIGHT_DATE_DIFFERENCES = "weightDateDifferences";


    private HashMap<String, Double> weightedParameters;
    private ArrayList<Double> weightDateDifferences;

    private static PropertyLoader instance = null;

    protected PropertyLoader() {
        weightedParameters = new HashMap<String, Double>();
        weightDateDifferences =  new ArrayList<Double>();

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = this.getClass().getResourceAsStream("/config.properties");
            
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

            weightedParameters.put(WEIGHT_SAME_CVX, stringToDouble(prop.getProperty(WEIGHT_SAME_CVX)));
            weightedParameters.put(WEIGHT_DIFFERENT_CVX, stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_CVX)));
            weightedParameters.put(WEIGHT_ABSENT_CVX, stringToDouble(prop.getProperty(WEIGHT_ABSENT_CVX)));

            weightedParameters.put(WEIGHT_SAME_MVX, stringToDouble(prop.getProperty(WEIGHT_SAME_MVX)));
            weightedParameters.put(WEIGHT_DIFFERENT_MVX, stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_MVX)));
            weightedParameters.put(WEIGHT_ABSENT_MVX, stringToDouble(prop.getProperty(WEIGHT_ABSENT_MVX)));

            weightedParameters.put(WEIGHT_SAME_PRODUCT_CODE, stringToDouble(prop.getProperty(WEIGHT_SAME_PRODUCT_CODE)));
            weightedParameters.put(WEIGHT_DIFFERENT_PRODUCT_CODE, stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_PRODUCT_CODE)));
            weightedParameters.put(WEIGHT_ABSENT_PRODUCT_CODE, stringToDouble(prop.getProperty(WEIGHT_ABSENT_PRODUCT_CODE)));

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

    /**
     * This method will instantiate the PropertyLoader singleton when first called then will return the singleton instance.
     * @return The singleton's instance.
     */
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
    
    public void setDateWindow(double dateWindow){
        weightedParameters.put(DATE_WINDOW, dateWindow);
    }
    
    public Double getDateWindow(){
    	return weightedParameters.get(DATE_WINDOW);
    }
}
