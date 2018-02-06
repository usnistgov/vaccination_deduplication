package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;

import org.immregistries.vaccination_deduplication.computation_classes.StepOne;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import junit.framework.TestCase;

public class StepOneTest extends TestCase {
	public void testIsPotentialDuplicate() throws Exception {

		ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

		StepOne stepOne = new StepOne();
		boolean result = stepOne.isPotentialDuplicate(immunizationLists.immunization1, immunizationLists.immunization2);

		assertEquals(true, result);

	}

	public void testExecuteStepOne() throws Exception {

		ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

		// Test Patient 1
		TestPatient(immunizationLists.patient1);

		// Test Patient 2
		TestPatient(immunizationLists.patient2);

		// Test Patient 3
		TestPatient(immunizationLists.patient3);

	}

	public void TestPatient(LinkedImmunization Patient) {

		LinkedImmunization patientRecords = Patient;

		StepOne stepOne = new StepOne();
		StepOneResult result = stepOne.executeStepOne(patientRecords);

		LinkedImmunization toEvaluate = new LinkedImmunization();
		LinkedImmunization notToEvaluate = new LinkedImmunization();

		// Filling the two arrayLists (expected result)

		for (Immunization Immunization : patientRecords) {
			for (Immunization ImmunizationBis : patientRecords) {
				if (stepOne.isPotentialDuplicate(Immunization, ImmunizationBis)) {
					toEvaluate.add(Immunization);
					toEvaluate.add(ImmunizationBis);
				} else {
					notToEvaluate.add(Immunization);
					notToEvaluate.add(ImmunizationBis);
				}
			}
		}

		compareResults(result.getToEvaluate(), toEvaluate);
		compareResults(result.getNotToEvaluate(), notToEvaluate);

	}

	public void compareResults(ArrayList<Immunization> result, ArrayList<Immunization> expected) {
		for (Immunization Immunization : result) {
			boolean bool = false;
			for (Immunization ImmunizationBis : expected) {
				if (Immunization == ImmunizationBis) {
					bool = true;
				}
			}
			assertEquals(bool, true);
		}
	}

}
