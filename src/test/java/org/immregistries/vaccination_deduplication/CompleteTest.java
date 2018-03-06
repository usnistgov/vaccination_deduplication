package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * 
 * Extensive test of deterministic, weighted and the hybrid approaches
 */
public class CompleteTest extends TestCase {
    private void compareResultToExpected(ArrayList<LinkedImmunization> result, ArrayList<LinkedImmunization> expected) {
        System.out.println("EXPECTED:");
        for (LinkedImmunization linkedImmunization:expected) {
            System.out.println(linkedImmunization.toString());
        }
        System.out.println("RESULT:");
        for (LinkedImmunization linkedImmunization:result) {
            System.out.println(linkedImmunization.toString());
        }

        assertEquals("The number of LinkedImmunization in the result is different than", expected.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals("The type of this LinkedImmunization is different than",expected.get(i).getType(), result.get(i).getType());
            assertEquals("The number of Immunization in this LinkedImmunization is different than", expected.get(i).size(), result.get(i).size());
            for (int j = 0; j < result.get(i).size(); j++) {
                assertEquals("The Immunization is different than", expected.get(i).get(j), result.get(i).get(j));
            }
        }
    }

    public void testDeduplicateDeterministicPatient1() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient1;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization1);
        sure.add(immunizationLists.immunization2);
        sure.add(immunizationLists.immunization3);

        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);
        different.add(immunizationLists.immunization4);
        different.add(immunizationLists.immunization5);
        different.add(immunizationLists.immunization6);
        different.add(immunizationLists.immunization7);
        different.add(immunizationLists.immunization8);

        expected.add(sure);
        expected.add(different);

        compareResultToExpected(result, expected);
    }

    public void testDeduplicateDeterministicPatient2() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient2;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization9);
        sure.add(immunizationLists.immunization10);
        sure.add(immunizationLists.immunization11);
        sure.add(immunizationLists.immunization12);

        LinkedImmunization sure2 = new LinkedImmunization();
        sure2.setType(LinkedImmunizationType.SURE);
        sure2.add(immunizationLists.immunization14);
        sure2.add(immunizationLists.immunization15);

        LinkedImmunization sure3 = new LinkedImmunization();
        sure3.setType(LinkedImmunizationType.SURE);
        sure3.add(immunizationLists.immunization17);
        sure3.add(immunizationLists.immunization18);

        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);
        different.add(immunizationLists.immunization13);
        different.add(immunizationLists.immunization16);

        expected.add(sure);
        expected.add(sure2);
        expected.add(sure3);
        expected.add(different);

        compareResultToExpected(result, expected);
    }

    public void testDeduplicateDeterministicPatient3() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient3;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization19);
        sure.add(immunizationLists.immunization20);
        sure.add(immunizationLists.immunization21);

        expected.add(sure);

        compareResultToExpected(result, expected);
    }

    public void testDeduplicateWeightedPatient1() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient1;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization1);
        sure.add(immunizationLists.immunization2);
        sure.add(immunizationLists.immunization3);

        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);
        different.add(immunizationLists.immunization4);
        different.add(immunizationLists.immunization5);
        different.add(immunizationLists.immunization6);
        different.add(immunizationLists.immunization7);
        different.add(immunizationLists.immunization8);

        expected.add(sure);
        expected.add(different);

        compareResultToExpected(result, expected);
    }

    public void testDeduplicateWeightedPatient2() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient2;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization9);
        sure.add(immunizationLists.immunization10);
        sure.add(immunizationLists.immunization11);
        sure.add(immunizationLists.immunization12);

        LinkedImmunization sure2 = new LinkedImmunization();
        sure2.setType(LinkedImmunizationType.SURE);
        sure2.add(immunizationLists.immunization14);
        sure2.add(immunizationLists.immunization15);

        LinkedImmunization sure3 = new LinkedImmunization();
        sure3.setType(LinkedImmunizationType.SURE);
        sure3.add(immunizationLists.immunization17);
        sure3.add(immunizationLists.immunization18);

        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);
        different.add(immunizationLists.immunization13);
        different.add(immunizationLists.immunization16);

        expected.add(sure);
        expected.add(sure2);
        expected.add(sure3);
        expected.add(different);

        compareResultToExpected(result, expected);
    }

    public void testDeduplicateWeightedPatient3() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient3;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization19);
        sure.add(immunizationLists.immunization20);
        sure.add(immunizationLists.immunization21);

        expected.add(sure);

        compareResultToExpected(result, expected);
    }

    public void testDeduplicateHybridPatient1() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient1;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateHybrid(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization1);
        sure.add(immunizationLists.immunization2);
        sure.add(immunizationLists.immunization3);

        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);
        different.add(immunizationLists.immunization4);
        different.add(immunizationLists.immunization5);
        different.add(immunizationLists.immunization6);
        different.add(immunizationLists.immunization7);
        different.add(immunizationLists.immunization8);

        expected.add(sure);
        expected.add(different);

        compareResultToExpected(result, expected);
    }

    public void testDeduplicateHybridPatient2() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient2;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateHybrid(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization9);
        sure.add(immunizationLists.immunization10);
        sure.add(immunizationLists.immunization11);
        sure.add(immunizationLists.immunization12);

        LinkedImmunization sure2 = new LinkedImmunization();
        sure2.setType(LinkedImmunizationType.SURE);
        sure2.add(immunizationLists.immunization14);
        sure2.add(immunizationLists.immunization15);

        LinkedImmunization sure3 = new LinkedImmunization();
        sure3.setType(LinkedImmunizationType.SURE);
        sure3.add(immunizationLists.immunization17);
        sure3.add(immunizationLists.immunization18);

        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);
        different.add(immunizationLists.immunization13);
        different.add(immunizationLists.immunization16);

        expected.add(sure);
        expected.add(sure2);
        expected.add(sure3);
        expected.add(different);
        compareResultToExpected(result, expected);
    }

    public void testDeduplicateHybridPatient3() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient3;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateHybrid(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization19);
        sure.add(immunizationLists.immunization20);
        sure.add(immunizationLists.immunization21);

        expected.add(sure);

        compareResultToExpected(result, expected);
    }
}
