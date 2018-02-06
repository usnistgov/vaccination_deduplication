package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;
import java.util.Arrays;

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
    /*
    	// Init Immunizations
    	
        immunization1 = new Immunization();
        immunization2 = new Immunization();

        immunization1.setDate("20161217");   
        immunization1.setCVX("03");
		immunization1.setVaccineGroupList(new ArrayList<String>(Arrays.asList("MMR")));
        immunization1.setProductCode("");
        immunization1.setOrganisationID("Mercy Hospital");
        immunization1.setSource(ImmunizationSource.HISTORICAL);
        immunization1.setLotNumber("");

        immunization2.setDate("20161218");
        immunization2.setCVX("03");
        immunization2.setVaccineGroupList(new ArrayList<String>(Arrays.asList("MMR")));
        immunization2.setProductCode("");
        immunization2.setOrganisationID("Medicare");
        immunization2.setSource(ImmunizationSource.HISTORICAL);
        immunization2.setLotNumber("");
        
        StepOne stepOne = new StepOne();
        boolean result = stepOne.isPotentialDuplicate(immunization1, immunization2);
        
        assertEquals(true, result);
        
    }
    
   public void testExecuteStepOne() throws Exception {
	   
	   // Init patients
	   patient1 = new LinkedImmunization();
       patient2 = new LinkedImmunization();
       patient3 = new LinkedImmunization();
	  
       // Init immunizations (Patient 1)
       immunization1 = new Immunization();
       immunization2 = new Immunization();
       immunization3 = new Immunization();
       immunization4 = new Immunization();
       immunization5 = new Immunization();
       immunization6 = new Immunization();
       immunization7 = new Immunization();
       immunization8 = new Immunization();
    	 
    	// Instanciate the immunizations with real parameters
    	 
    	// Patient 1
         
         immunization1.setVaccineGroupList(new ArrayList<String>(Arrays.asList("MMR")));
         immunization1.setOrganisationID("Dr Murphey");
         immunization1.setDate("20161217");
         immunization1.setCVX("03");
         immunization1.setSource(Immunization.SOURCE.SOURCE);
         
         immunization2.setVaccineGroupList(new ArrayList<String>(Arrays.asList("MMR")));
         immunization2.setOrganisationID("Mercy Hospital");
         immunization2.setDate("20161217");
         immunization2.setCVX("03");
         immunization2.setSource(Immunization.SOURCE.HISTORICAL);

         immunization3.setVaccineGroupList(new ArrayList<String>(Arrays.asList("MMR")));
         immunization3.setOrganisationID("Medicare");
         immunization3.setDate("20161218");
         immunization3.setCVX("03");
         immunization3.setSource(Immunization.SOURCE.HISTORICAL);

         immunization4.setVaccineGroupList(new ArrayList<String>(Arrays.asList("DTaP", "IPV")));
         immunization4.setOrganisationID("Dr Murphey");
         immunization4.setDate("20161217");
         immunization4.setCVX("130");
         immunization4.setSource(Immunization.SOURCE.SOURCE);

         immunization5.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep A")));
         immunization5.setOrganisationID("Dr Murphey");
         immunization5.setDate("20161217");
         immunization5.setCVX("83");
         immunization5.setSource(Immunization.SOURCE.SOURCE);

         immunization6.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
         immunization6.setOrganisationID("Dr Murphey");
         immunization6.setDate("20160605");
         immunization6.setCVX("08");
         immunization6.setSource(Immunization.SOURCE.SOURCE);

         immunization7.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
         immunization7.setOrganisationID("Dr Murphey");
         immunization7.setDate("20160401");
         immunization7.setCVX("08");
         immunization7.setSource(Immunization.SOURCE.SOURCE);

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
         

    }

}


