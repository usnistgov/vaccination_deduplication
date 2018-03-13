package org.immregistries.vaccination_deduplication;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.immregistries.vaccination_deduplication.computation_classes.StepOne;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.reference.StepOneResult;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.utils.PropertyLoader;

public class StepOneTest extends TestCase {
	public void testIsPotentialDuplicate() throws Exception {

		ImmunizationLists immunizationLists = new ImmunizationLists();

		StepOne stepOne = new StepOne(new PropertyLoader().getParameters());
		StepOneResult result = stepOne.isPotentialDuplicate(immunizationLists.immunization1, immunizationLists.immunization2);

		assertEquals(StepOneResult.POTENTIAL_DUPLICATE, result);
	}

	public void testStepOnePatient1() throws ParseException {
		ImmunizationLists immunizationLists = new ImmunizationLists();
		LinkedImmunization expected = new LinkedImmunization();
		expected.add(immunizationLists.immunization1);
		expected.add(immunizationLists.immunization2);
		expected.add(immunizationLists.immunization3);

		TestPatient(immunizationLists.patient1, expected);
	}

	public void testStepOnePatient2() throws ParseException {
		ImmunizationLists immunizationLists = new ImmunizationLists();
		LinkedImmunization expected = new LinkedImmunization();
		expected.add(immunizationLists.immunization9);
		expected.add(immunizationLists.immunization10);
		expected.add(immunizationLists.immunization11);
		expected.add(immunizationLists.immunization12);
		expected.add(immunizationLists.immunization14);
		expected.add(immunizationLists.immunization15);
		expected.add(immunizationLists.immunization17);
		expected.add(immunizationLists.immunization18);

		TestPatient(immunizationLists.patient2, expected);
	}

	public void testStepOnePatient3() throws ParseException {
		ImmunizationLists immunizationLists = new ImmunizationLists();
		LinkedImmunization expected = new LinkedImmunization();

		immunizationLists.immunization19.addVaccineGroupList("Polio");
		immunizationLists.immunization21.addVaccineGroupList("Polio");

		expected.add(immunizationLists.immunization19);
		expected.add(immunizationLists.immunization20);
		expected.add(immunizationLists.immunization21);

		TestPatient(immunizationLists.patient3, expected);
	}

	public boolean hasPotentialDuplicate(int rank, LinkedImmunization patientRecords) {
		StepOne stepOne = new StepOne(new PropertyLoader().getParameters());
		for (int j = 0; j < patientRecords.size(); j++) {
			if (rank!=j) {
				if (stepOne.isPotentialDuplicate(patientRecords.get(rank), patientRecords.get(j)).equals(StepOneResult.POTENTIAL_DUPLICATE)) {
					return true;
				}
			}
		}
		return false;
	}

	public void compareStepOneResultToExpected(LinkedImmunization expected, LinkedImmunization result) {
		logger.debug(StringUtils.center(" EXPECTED ", 29, '-'));
		logger.debug("\n"+expected);
		logger.debug(StringUtils.center(" RESULT ", 29, '-'));
		logger.debug("\n"+result);

		assertEquals("The number of Immunization in the LinkedImmunization is different than", expected.size(), result.size());
		for (int j = 0; j < result.size(); j++) {
			assertEquals("The Immunization is different than", expected.get(j), result.get(j));
		}
	}

	private static Logger logger = Logger.getLogger(Weighted.class.getName());
	public void TestPatient(LinkedImmunization patientRecords, LinkedImmunization expected) {
		logger.debug(StringUtils.center(" PATIENT RECORDS ", 29, '-'));
		logger.debug("\n"+patientRecords);

		LinkedImmunization stepOneResult = new LinkedImmunization();

		for (int i = 0; i < patientRecords.size(); i++) {
			if (hasPotentialDuplicate(i, patientRecords))
				stepOneResult.add(patientRecords.get(i));
		}

		compareStepOneResultToExpected(expected, stepOneResult);
	}
}
