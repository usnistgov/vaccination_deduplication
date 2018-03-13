package org.immregistries.vaccination_deduplication;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.reference.LinkedImmunizationType;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import static org.immregistries.vaccination_deduplication.utils.utils.compareResultToExpected;

/**
 * 
 * Extensive test of deterministic, weighted and the hybrid approaches
 */
public class MoreCompleteTest extends TestCase{

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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

        VaccinationDeduplication vaccinationDeduplication = new VaccinationDeduplication();
        

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
