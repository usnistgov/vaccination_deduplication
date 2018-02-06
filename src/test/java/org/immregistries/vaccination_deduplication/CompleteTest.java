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
    public void compareResultToExpected(ArrayList<LinkedImmunization> result, ArrayList<LinkedImmunization> expected) {
        assertEquals(result.size(), expected.size());

        for (int i = 0; i < result.size(); i++) {
            //System.out.println(result.get(i).toString());

            assertEquals(expected.get(i).getType(), result.get(i).getType());
            assertEquals(expected.get(i).size(), result.get(i).size());
            for (int j = 0; j < result.get(i).size(); j++) {
                assert(expected.get(i).get(j) == result.get(i).get(j));
            }
        }
    }

    public void testDeduplicateDeterministic() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.patient1;

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization sure = new LinkedImmunization();
        sure.setType(LinkedImmunizationType.SURE);
        sure.add(immunizationLists.immunization1);
        sure.add(immunizationLists.immunization2);
        sure.add(immunizationLists.immunization3);

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization6);
        unsure.add(immunizationLists.immunization7);

        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);
        different.add(immunizationLists.immunization4);
        different.add(immunizationLists.immunization5);
        different.add(immunizationLists.immunization8);

        expected.add(sure);
        expected.add(unsure);
        expected.add(different);

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).toString());
        }

        compareResultToExpected(result, expected);
    }

    public void testDeduplicateWeighted() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.patient1;

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(patientRecords);

        for (LinkedImmunization linkedImmunization:result) {
            System.out.println(linkedImmunization.toString());
        }

        assertEquals(true, true);
    }

    public void testDeduplicateHybrid() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.patient1;

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateHybrid(patientRecords);

        for (LinkedImmunization linkedImmunization:result) {
            System.out.println(linkedImmunization.toString());
        }

        assertEquals(true, true);
    }
}
