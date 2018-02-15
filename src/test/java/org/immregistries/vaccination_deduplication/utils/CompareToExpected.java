package org.immregistries.vaccination_deduplication.utils;

import org.immregistries.vaccination_deduplication.LinkedImmunization;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CompareToExpected {
    public static void compareResultToExpected(ArrayList<LinkedImmunization> result, ArrayList<LinkedImmunization> expected) {

        System.out.println("EXPECTED :");
        for (LinkedImmunization linkedImmunization :expected) {
            System.out.println(linkedImmunization.toString());
        }

        System.out.println("RESULT :");
        for (LinkedImmunization linkedImmunization :result) {
            System.out.println(linkedImmunization.toString());
        }

        // test that right number of immunizations are returned
        int expectedSize = 0;
        for (LinkedImmunization izExpected : expected) {
            expectedSize += izExpected.size();
        }
        int resultSize = 0;
        for (LinkedImmunization izResult : result) {
            resultSize += izResult.size();
        }
        assertEquals("Input and output size mismatch : ", expectedSize,
                resultSize);

        assertEquals("The number of LinkedImmunization in the result is different than", expected.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals("The type of this LinkedImmunization is different than",expected.get(i).getType(), result.get(i).getType());
            assertEquals("The number of Immunization in this LinkedImmunization is different than", expected.get(i).size(), result.get(i).size());
            for (int j = 0; j < result.get(i).size(); j++) {
                assertEquals("The Immunization is different than", expected.get(i).get(j), result.get(i).get(j));
            }
        }
    }
}
