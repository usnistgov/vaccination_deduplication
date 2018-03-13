package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.reference.LinkedImmunizationType;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import java.text.ParseException;
import java.util.ArrayList;

import static org.immregistries.vaccination_deduplication.utils.utils.compareResultToExpected;

/**
 * 
 * Extensive test of deterministic, weighted and the hybrid approaches
 */
public class CompleteTest extends TestCase {
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

        compareResultToExpected(result, expected, patientRecords);
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

        compareResultToExpected(result, expected, patientRecords);
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

        compareResultToExpected(result, expected, patientRecords);
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

        compareResultToExpected(result, expected, patientRecords);
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

        compareResultToExpected(result, expected, patientRecords);
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

        compareResultToExpected(result, expected, patientRecords);
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

        compareResultToExpected(result, expected, patientRecords);
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
        compareResultToExpected(result, expected, patientRecords);
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

        compareResultToExpected(result, expected, patientRecords);
    }
}
