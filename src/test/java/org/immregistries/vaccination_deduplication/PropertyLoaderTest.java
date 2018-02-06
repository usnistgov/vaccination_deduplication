package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyLoaderTest extends TestCase {
    public void obsoleteTestWritePropertyFile() throws Exception {

        URL propertyFileURL = this.getClass().getResource("/config.properties");

        System.out.println(propertyFileURL.toString());

        Properties prop = new Properties();
        OutputStream output = null;
        try {

            output = new FileOutputStream("config.properties");

            prop.setProperty("dateWindow", "23");

            prop.setProperty("weightSameLotNumber", "45");
            prop.setProperty("weightDifferentLotNumber", "-25");
            prop.setProperty("weightAbsentLotNumber", "25");

            prop.setProperty("weightSameVaccineFamily", "50");
            prop.setProperty("weightDifferentVaccineFamily", "5");
            prop.setProperty("weightAbsentVaccineFamily", "15");

            prop.setProperty("weightSameTradingName", "35");
            prop.setProperty("weightDifferentTradingName", "35");
            prop.setProperty("weightAbsentTradingName", "35");

            prop.setProperty("weightSameOrganisationID", "25");
            prop.setProperty("weightDifferentOrganisationID", "10");
            prop.setProperty("weightAbsentOrganisationID", "15");

            prop.setProperty("weightSameSourceAdmin", "-7");
            prop.setProperty("weightSameSourceHistorical", "15");
            prop.setProperty("weightDifferentSource", "60");
            prop.setProperty("weightAbsentSource", "15");

            prop.setProperty("weightMaxThreshold", "0.6");
            prop.setProperty("weightMinThreshold", "0.4");

            prop.setProperty("weightDateDifference0", "80");
            prop.setProperty("weightDateDifference1", "65");
            prop.setProperty("weightDateDifference2", "50");
            prop.setProperty("weightDateDifference3", "43");
            prop.setProperty("weightDateDifference4", "38");
            prop.setProperty("weightDateDifference5", "30");
            prop.setProperty("weightDateDifference6AndMore", "23");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        assertEquals(ComparisonResult.EQUAL, ComparisonResult.EQUAL);
    }

    public void testReadPropertyFile() {
        PropertyLoader pl = PropertyLoader.getInstance();
    }

}

