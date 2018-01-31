package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import java.text.ParseException;
import java.util.ArrayList;

public class CompleteTest extends TestCase {
    public void testDeduplicateDeterministic() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.getPatient1();

        Workclass workclass = new Workclass();
        ArrayList<LinkedImmunization> result = workclass.deduplicateDeterministic(patientRecords);

        for (LinkedImmunization linkedImmunization:result) {
            System.out.println(linkedImmunization.toString());
        }

        assertEquals(true, true);
    }

    public void testDeduplicateWeighted() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.getPatient1();

        Workclass workclass = new Workclass();
        ArrayList<LinkedImmunization> result = workclass.deduplicateWeighted(patientRecords);

        assertEquals(true, true);
    }

    public void testDeduplicateHybrid() throws ParseException {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        LinkedImmunization patientRecords = immunizationLists.getPatient1();

        Workclass workclass = new Workclass();
        ArrayList<LinkedImmunization> result = workclass.deduplicateHybrid(patientRecords);

        assertEquals(true, true);
    }
}
