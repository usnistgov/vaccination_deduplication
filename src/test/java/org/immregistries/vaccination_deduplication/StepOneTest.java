package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;

import org.immregistries.vaccination_deduplication.computation_classes.StepOne;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import junit.framework.TestCase;

public class StepOneTest extends TestCase {
	public LinkedImmunization patient1;
	public LinkedImmunization patient2;
	public LinkedImmunization patient3;

	Immunization immunization1;
	Immunization immunization2;
	Immunization immunization3;
	Immunization immunization4;
	Immunization immunization5;
	Immunization immunization6;
	Immunization immunization7;
	Immunization immunization8;

	Immunization immunization9;
	Immunization immunization10;
	Immunization immunization11;
	Immunization immunization12;
	Immunization immunization13;
	Immunization immunization14;
	Immunization immunization15;
	Immunization immunization16;
	Immunization immunization17;
	Immunization immunization18;

	Immunization immunization19;
	Immunization immunization20;
	Immunization immunization21;

	public void testIsPotentialDuplicate() throws Exception {

		ImmunizationLists immunizationLists = ImmunizationLists.getInstance();
		LinkedImmunization patientRecords = immunizationLists.patient1;

		immunization1 = patientRecords.get(0);
		immunization2 = patientRecords.get(1);

		StepOne stepOne = new StepOne();
		boolean result = stepOne.isPotentialDuplicate(immunization1, immunization2);

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
