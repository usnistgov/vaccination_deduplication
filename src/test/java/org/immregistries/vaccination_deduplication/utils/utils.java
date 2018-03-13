package org.immregistries.vaccination_deduplication.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.immregistries.vaccination_deduplication.LinkedImmunization;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class utils {
    private static Logger logger = Logger.getLogger(Weighted.class.getName());

    public static void compareResultToExpected(ArrayList<LinkedImmunization> result, ArrayList<LinkedImmunization> expected, LinkedImmunization patientRecords) {
        logger.debug(StringUtils.center(" PATIENT RECORDS ", 29, '-'));
        logger.debug("\n"+patientRecords.toString());

        logger.debug(StringUtils.center(" EXPECTED ", 29, '-'));
        for (LinkedImmunization linkedImmunization:expected) {
            logger.debug("\n"+linkedImmunization.toString());
        }

        logger.debug(StringUtils.center(" RESULT ", 29, '-'));
        for (LinkedImmunization linkedImmunization:result) {
            logger.debug("\n"+linkedImmunization.toString());
        }

        /* Test that the correct number of immunizations are returned */
        int resultSize = 0;
        for (LinkedImmunization izResult : result) {
            resultSize += izResult.size();
        }

        /*
         * Test that the number of input records matches the number of output
         * records
         */
        assertEquals("Input and output size mismatch : ", patientRecords.size(),
                resultSize);

        /* Test that the result has the same size as the expected */
        assertEquals("The number of LinkedImmunization in the result is different than expected.", expected.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            /* Test that the type is the same */
            assertEquals("The type of this LinkedImmunization is different than expected.",expected.get(i).getType(), result.get(i).getType());

            /* Test that the list has the expected size */
            assertEquals("The number of Immunization in this LinkedImmunization is different than expected.", expected.get(i).size(), result.get(i).size());

            /* Test that each expected result is present in the result */
            for (int j = 0; j < result.get(i).size(); j++) {
                assertEquals("One of the expected values is not present in the result.", true, result.get(i).contains(expected.get(i).get(j)));
            }
        }
    }
}
