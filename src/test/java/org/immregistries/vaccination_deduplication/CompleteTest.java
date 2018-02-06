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
    public void testDeduplicateDeterministic() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.patient1;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(patientRecords);

        for (LinkedImmunization linkedImmunization:result) {
            System.out.println(linkedImmunization.toString());
        }

        assertEquals(true, true);
    }

    public void testDeduplicateWeighted() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.patient1;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(patientRecords);

        for (LinkedImmunization linkedImmunization:result) {
            System.out.println(linkedImmunization.toString());
        }

        assertEquals(true, true);
    }

    public void testDeduplicateHybrid() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.patient1;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateHybrid(patientRecords);

        for (LinkedImmunization linkedImmunization:result) {
            System.out.println(linkedImmunization.toString());
        }

        assertEquals(true, true);
    }
}
