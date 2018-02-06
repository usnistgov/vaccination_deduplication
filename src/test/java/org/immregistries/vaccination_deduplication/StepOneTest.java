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
<<<<<<< HEAD
=======
	

    public void testIsPotentialDuplicate() throws Exception {
    /*
    	// Init Immunizations
    	
        immunization1 = new Immunization();
        immunization2 = new Immunization();
>>>>>>> branch 'master' of https://github.com/nathanbunker/vaccination_deduplication.git

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

<<<<<<< HEAD
		ImmunizationLists immunizationLists = ImmunizationLists.getInstance();
=======
         immunization8.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
         immunization8.setOrganisationID("Dr Murphey");
         immunization8.setDate("20151111");
         immunization8.setCVX("08");
         immunization8.setSource(Immunization.SOURCE.HISTORICAL);
         
         
         // Add the expected linked immunization to the array which will be 
         //compare with the one built by the StepOne class' multipleSelection method
      
         patient1.add(immunization1);
         patient1.add(immunization2);
         patient1.add(immunization3);
         patient1.add(immunization4);
         patient1.add(immunization5);
         patient1.add(immunization6);
         patient1.add(immunization7);
         patient1.add(immunization8);	
      
      	// Test Patient 1
      	
        StepOne stepOne = new StepOne();
        StepOneResult result = stepOne.executeStepOne(patient1);
        
        StepOneResult expectedResult = new StepOneResult(); 
        expectedResult.setToEvaluate(new LinkedImmunization());
      	assertEquals(expectedResult, result);
        
         //assertEquals(true, true);
         */
      	
    	/*// Init immunizations (Patient 2)
    	
    	immunization9 = new Immunization();
    	immunization10 = new Immunization();
    	immunization11 = new Immunization();
    	immunization12 = new Immunization();
    	immunization13 = new Immunization();
    	immunization14 = new Immunization();
    	immunization15 = new Immunization();
    	immunization16 = new Immunization();
    	immunization17 = new Immunization();
    	immunization18 = new Immunization();
    	 
    	 // Patient 2
     	
    	 immunization9.setDate("20161217");   
         immunization9.setCVX("110");
         immunization9.setSource(Immunization.SOURCE.SOURCE);
         
         immunization10.setDate("20161217");   
         immunization10.setCVX("20");
         immunization10.setSource(Immunization.SOURCE.HISTORICAL);
         
         immunization11.setDate("20161217");   
         immunization11.setCVX("08");
         immunization11.setSource(Immunization.SOURCE.HISTORICAL);
         
         immunization12.setDate("20161217");   
         immunization12.setCVX("10");
         immunization12.setSource(Immunization.SOURCE.HISTORICAL);
         
         immunization13.setDate("20161217");   
         immunization13.setCVX("03");
         immunization13.setSource(Immunization.SOURCE.HISTORICAL);
         
         immunization14.setDate("20160605");   
         immunization14.setCVX("08");
         immunization14.setSource(Immunization.SOURCE.SOURCE);
         
         immunization15.setDate("20160605");   
         immunization15.setCVX("08");
         immunization15.setSource(Immunization.SOURCE.SOURCE);
         
         immunization16.setDate("20160401");   
         immunization16.setCVX("08");
         immunization16.setSource(Immunization.SOURCE.HISTORICAL);
         
         immunization17.setDate("20160201");   
         immunization17.setCVX("08");
         immunization17.setSource(Immunization.SOURCE.SOURCE);
         
         immunization18.setDate("20160201");   
         immunization18.setCVX("08");
         immunization18.setSource(Immunization.SOURCE.SOURCE);*/
         
>>>>>>> branch 'master' of https://github.com/nathanbunker/vaccination_deduplication.git

<<<<<<< HEAD
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
=======
    }

>>>>>>> branch 'master' of https://github.com/nathanbunker/vaccination_deduplication.git
}
