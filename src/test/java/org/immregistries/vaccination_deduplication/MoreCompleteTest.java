package org.immregistries.vaccination_deduplication;

import java.text.ParseException;
import java.util.ArrayList;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;
import junit.framework.TestCase;
/**
 * 
 * Extensive test of deterministic, weighted and the hybrid approaches
 */
public class MoreCompleteTest extends TestCase {
    private void compareResultToExpected(ArrayList<LinkedImmunization> result, ArrayList<LinkedImmunization> expected) {
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

    public void testDeduplicateDeterministicPatient4() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.patient4;

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(
                patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        LinkedImmunization unsure = new LinkedImmunization();
        unsure.setType(LinkedImmunizationType.UNSURE);
        unsure.add(immunizationLists.immunization22);
        unsure.add(immunizationLists.immunization23);
        unsure.add(immunizationLists.immunization24);

        expected.add(unsure);

        compareResultToExpected(result, expected);
    }

    public void testDeduplicateWeigthedPatient4() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.patient4;

        VaccinationDeduplication vaccinationDeduplication = VaccinationDeduplication.getInstance();
        vaccinationDeduplication.initialize();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(
                patientRecords);

        // test that right number of immunizations are returned
        int resultSize = 0;
        for (LinkedImmunization izResult : result) {
            resultSize += izResult.size();
        }
        assertEquals(patientRecords.size(), resultSize);

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

        compareResultToExpected(result, expected);
    }


}
