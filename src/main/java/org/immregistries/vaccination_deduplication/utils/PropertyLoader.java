package org.immregistries.vaccination_deduplication.utils;

import org.immregistries.vaccination_deduplication.VaccinationDeduplicationParameters;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


/**
 * This object reads the property file and loads the values. The classes needing these values will then call upon this class to get them.
 */
public class PropertyLoader {

    private static final String DATE_WINDOW = "dateWindow";
    private static final String WEIGHT_MAX_THRESHOLD = "weightMaxThreshold";
    private static final String WEIGHT_MIN_THRESHOLD = "weightMinThreshold";

    private static final String WEIGHT_SAME_SOURCE_HISTORICAL = "weightSameSourceHistorical";
    private static final String WEIGHT_SAME_SOURCE_ADMIN = "weightSameSourceAdmin";
    private static final String WEIGHT_ABSENT_SOURCE = "weightAbsentSource";
    private static final String WEIGHT_DIFFERENT_SOURCE = "weightDifferentSource";

    private static final String WEIGHT_SAME_CVX = "weightSameCVX";
    private static final String WEIGHT_ABSENT_CVX = "weightAbsentCVX";
    private static final String WEIGHT_DIFFERENT_CVX = "weightDifferentCVX";

    private static final String WEIGHT_SAME_MVX = "weightSameMVX";
    private static final String WEIGHT_ABSENT_MVX = "weightAbsentMVX";
    private static final String WEIGHT_DIFFERENT_MVX = "weightDifferentMVX";

    private static final String WEIGHT_SAME_PRODUCT_CODE = "weightSameProductCode";
    private static final String WEIGHT_ABSENT_PRODUCT_CODE = "weightAbsentProductCode";
    private static final String WEIGHT_DIFFERENT_PRODUCT_CODE = "weightDifferentProductCode";

    private static final String WEIGHT_SAME_LOT_NUMBER = "weightSameLotNumber";
    private static final String WEIGHT_DIFFERENT_LOT_NUMBER = "weightDifferentLotNumber";
    private static final String WEIGHT_ABSENT_LOT_NUMBER = "weightAbsentLotNumber";

    private static final String WEIGHT_SAME_ORGANISATION_ID = "weightSameOrganisationID";
    private static final String WEIGHT_DIFFERENT_ORGANISATION_ID = "weightDifferentOrganisationID";
    private static final String WEIGHT_ABSENT_ORGANISATION_ID = "weightAbsentOrganisationID";

    private static final String WEIGHT_DATE_DIFFERENCES = "weightDateDifferences";

    public PropertyLoader() {}

    public VaccinationDeduplicationParameters getParameters() {

        Properties prop = new Properties();
        InputStream input = null;

        VaccinationDeduplicationParameters properties = new VaccinationDeduplicationParameters();

        try {
            input = this.getClass().getResourceAsStream("/config.properties");
            
            // load a properties file
            prop.load(input);

            // get the property values
            

            // for the weighted method
            properties.setDateWindow(stringToDouble(prop.getProperty(DATE_WINDOW)));

            properties.setWeightMaxThreshold(stringToDouble(prop.getProperty(WEIGHT_MAX_THRESHOLD)));
            properties.setWeightMinThreshold(stringToDouble(prop.getProperty(WEIGHT_MIN_THRESHOLD)));

            properties.setWeightSameSourceAdmin(stringToDouble(prop.getProperty(WEIGHT_SAME_SOURCE_ADMIN)));
            properties.setWeightSameSourceHistorical(stringToDouble(prop.getProperty(WEIGHT_SAME_SOURCE_HISTORICAL)));
            properties.setWeightAbsentSource(stringToDouble(prop.getProperty(WEIGHT_ABSENT_SOURCE)));
            properties.setWeightDifferentSource(stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_SOURCE)));

            properties.setWeightSameLotNumber(stringToDouble(prop.getProperty(WEIGHT_SAME_LOT_NUMBER)));
            properties.setWeightAbsentLotNumber(stringToDouble(prop.getProperty(WEIGHT_ABSENT_LOT_NUMBER)));
            properties.setWeightDifferentLotNumber(stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_LOT_NUMBER)));

            properties.setWeightSameCVX(stringToDouble(prop.getProperty(WEIGHT_SAME_CVX)));
            properties.setWeightAbsentCVX(stringToDouble(prop.getProperty(WEIGHT_ABSENT_CVX)));
            properties.setWeightDifferentCVX(stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_CVX)));

            properties.setWeightSameMVX(stringToDouble(prop.getProperty(WEIGHT_SAME_MVX)));
            properties.setWeightAbsentMVX(stringToDouble(prop.getProperty(WEIGHT_ABSENT_MVX)));
            properties.setWeightDifferentMVX(stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_MVX)));

            properties.setWeightSameProductCode(stringToDouble(prop.getProperty(WEIGHT_SAME_PRODUCT_CODE)));
            properties.setWeightAbsentProductCode(stringToDouble(prop.getProperty(WEIGHT_ABSENT_PRODUCT_CODE)));
            properties.setWeightDifferentProductCode(stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_PRODUCT_CODE)));

            properties.setWeightSameOrganisationID(stringToDouble(prop.getProperty(WEIGHT_SAME_ORGANISATION_ID)));
            properties.setWeightAbsentOrganisationID(stringToDouble(prop.getProperty(WEIGHT_ABSENT_ORGANISATION_ID)));
            properties.setWeightDifferentOrganisationID(stringToDouble(prop.getProperty(WEIGHT_DIFFERENT_ORGANISATION_ID)));

            String[] weightDateDifferencesSplitString = prop.getProperty(WEIGHT_DATE_DIFFERENCES).split(",");
            for (String s : weightDateDifferencesSplitString) {
                properties.addWeightDateDifference(stringToDouble(s));
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

        return properties;
    }

    private Double stringToDouble(String input) {
        return Double.parseDouble(input);
    }
}
