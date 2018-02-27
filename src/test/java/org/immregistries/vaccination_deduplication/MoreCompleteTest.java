package org.immregistries.vaccination_deduplication;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;
import org.junit.Test;
/**
 * 
 * Extensive test of deterministic, weighted and the hybrid approaches
 */

public class MoreCompleteTest {
    private void compareResultToExpected(ArrayList<LinkedImmunization> result, ArrayList<LinkedImmunization> expected) {

        /* Test that the result has the same size as the expected */
        assertEquals("The number of LinkedImmunization in the result is different than", expected.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            /* Test that the type is the same */
            assertEquals("The type of this LinkedImmunization is different than",expected.get(i).getType(), result.get(i).getType());

            /* Test that the list has the expected size */
            assertEquals("The number of Immunization in this LinkedImmunization is different than", expected.get(i).size(), result.get(i).size());

            /* Test that each expected result is present in the result */
            for (int j = 0; j < result.get(i).size(); j++) {
                assertEquals(true,
                        result.get(i).contains(expected.get(i).get(j)));
            }
        }
    }

    @Test
    public void testDeduplicateDeterministicPatient4a() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4a;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization22);
        unsure.add(immunizationLists.immunization23);
        unsure.add(immunizationLists.immunization24);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient4a() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4a;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);

    }

    @Test
    public void testDeduplicateDeterministicPatient4b() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4b;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization22);
        unsure.add(immunizationLists.immunization23);
        unsure.add(immunizationLists.immunization24);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient4b() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4b;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);

    }

    @Test
    public void testDeduplicateDeterministicPatient4c() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4c;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization22);
        unsure.add(immunizationLists.immunization23);
        unsure.add(immunizationLists.immunization24);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient4c() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4c;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateDeterministicPatient4d() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4d;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization22);
        unsure.add(immunizationLists.immunization23);
        unsure.add(immunizationLists.immunization24);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient4d() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4d;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);

    }

    @Test
    public void testDeduplicateDeterministicPatient4e() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4e;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization22);
        unsure.add(immunizationLists.immunization23);
        unsure.add(immunizationLists.immunization24);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient4e() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4e;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);

    }

    @Test
    public void testDeduplicateDeterministicPatient4f() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4f;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization22);
        unsure.add(immunizationLists.immunization23);
        unsure.add(immunizationLists.immunization24);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient4f() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4f;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateDeterministicPatient5a() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5a;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization25);
        unsure.add(immunizationLists.immunization26);
        unsure.add(immunizationLists.immunization27);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient5a() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5a;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization25);
        sure.add(immunizationLists.immunization26);
        sure.add(immunizationLists.immunization27);

        expected.add(sure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateDeterministicPatient5b() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5b;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization25);
        unsure.add(immunizationLists.immunization26);
        unsure.add(immunizationLists.immunization27);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient5b() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5b;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization25);
        sure.add(immunizationLists.immunization26);
        sure.add(immunizationLists.immunization27);

        expected.add(sure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateDeterministicPatient5c() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5c;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization25);
        unsure.add(immunizationLists.immunization26);
        unsure.add(immunizationLists.immunization27);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient5c() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5c;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization25);
        sure.add(immunizationLists.immunization26);
        sure.add(immunizationLists.immunization27);

        expected.add(sure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateDeterministicPatient5d() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5d;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization25);
        unsure.add(immunizationLists.immunization26);
        unsure.add(immunizationLists.immunization27);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient5d() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5d;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization25);
        sure.add(immunizationLists.immunization26);
        sure.add(immunizationLists.immunization27);

        expected.add(sure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateDeterministicPatient5e() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5e;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization25);
        unsure.add(immunizationLists.immunization26);
        unsure.add(immunizationLists.immunization27);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient5e() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5e;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization25);
        sure.add(immunizationLists.immunization26);
        sure.add(immunizationLists.immunization27);

        expected.add(sure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateDeterministicPatient5f() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5f;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization25);
        unsure.add(immunizationLists.immunization26);
        unsure.add(immunizationLists.immunization27);

        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }

    @Test
    public void testDeduplicateWeigthedPatient5f() throws ParseException {

        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient5f;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization25);
        sure.add(immunizationLists.immunization26);
        sure.add(immunizationLists.immunization27);

        expected.add(sure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        /* Test that right number of immunizations are returned */
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
        compareResultToExpected(result, expected);
    }


    private void print(List<LinkedImmunization> result) {
        for (LinkedImmunization linkedImmunization : result) {
            System.out.println(linkedImmunization.toString());
        }
    }
}
