package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.reference.LinkedImmunizationType;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import java.text.ParseException;
import java.util.ArrayList;

import static org.immregistries.vaccination_deduplication.utils.utils.compareResultToExpected;

public class OtherTests extends TestCase {
    public void testDeduplicateDeterministicPatient2WithIdentical() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient2;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();


        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateDeterministic(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        immunizationLists.immunization15.setOrganisationID("Dr Murphey");
        immunizationLists.immunization18.setOrganisationID("Dr Murphey");

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

    public void testDeduplicateWeightedPatient2WithIdentical() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient2;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();


        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateWeighted(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        immunizationLists.immunization15.setOrganisationID("Dr Murphey");
        immunizationLists.immunization18.setOrganisationID("Dr Murphey");

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

    public void testDeduplicateHybridPatient2WithIdentical() throws ParseException {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        LinkedImmunization patientRecords = immunizationLists.patient2;

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();

        ArrayList<LinkedImmunization> result = vaccinationDeduplication.deduplicateHybrid(patientRecords);

        ArrayList<LinkedImmunization> expected = new ArrayList<LinkedImmunization>();

        immunizationLists.immunization15.setOrganisationID("Dr Murphey");
        immunizationLists.immunization18.setOrganisationID("Dr Murphey");

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

}
