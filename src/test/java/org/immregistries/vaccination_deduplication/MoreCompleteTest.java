package org.immregistries.vaccination_deduplication;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

/**
 * 
 * Extensive test of deterministic, weighted and the hybrid approaches
 */
public class MoreCompleteTest extends TestCase{
    private void compareResultToExpected(ArrayList<LinkedImmunization> result, ArrayList<LinkedImmunization> expected, LinkedImmunization patientRecords) {
        System.out.println("PATIENT RECORDS:");
        System.out.println(patientRecords);

        System.out.println("EXPECTED:");
        for (LinkedImmunization linkedImmunization:expected) {
            System.out.println(linkedImmunization.toString());
        }
        System.out.println("RESULT:");
        for (LinkedImmunization linkedImmunization:result) {
            System.out.println(linkedImmunization.toString());
        }

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

    
    public void testDeduplicateDeterministicPatient4a() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient4a() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4a;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.DIFFERENT);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient4b() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient4b() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4b;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.DIFFERENT);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient4c() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient4c() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4c;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.DIFFERENT);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient4d() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient4d() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4d;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.DIFFERENT);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient4e() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient4e() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4e;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.DIFFERENT);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient4f() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient4f() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

        /* Patient to test */
        LinkedImmunization patientRecords = immunizationLists.patient4f;

        /* Expected results */
        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();
        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization22);
        sure.add(immunizationLists.immunization23);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.DIFFERENT);
        unsure.add(immunizationLists.immunization24);

        expected.add(sure);
        expected.add(unsure);

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient5a() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient5a() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient5b() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient5b() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient5c() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient5c() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient5d() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient5d() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateDeterministicPatient5e() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }

    public void testDeduplicateWeightedPatient5e() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }
    
    public void testDeduplicateDeterministicPatient5f() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }
    
    public void testDeduplicateWeightedPatient5f() throws ParseException {

        ImmunizationLists immunizationLists = new ImmunizationLists();

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

        
        compareResultToExpected(result, expected, patientRecords);
    }


    private void print(List<LinkedImmunization> result) {
        for (LinkedImmunization linkedImmunization : result) {
            System.out.println(linkedImmunization.toString());
        }
    }
}
