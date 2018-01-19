package org.immregistries.vaccination_deduplication;

import java.io.LineNumberReader;
import java.util.ArrayList;

import org.immregistries.vaccination_deduplication.computation_classes.StepOne;

import junit.framework.TestCase;

public class StepOneTest extends TestCase {
	
	
    public void testSelection() throws Exception {
    	
    	// Init Immunizations
    	
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20161217");   
        immunization1.setCVX("03");
        immunization1.setProductCode("");
        immunization1.setOrganisationID("Mercy Hospital");
        immunization1.setSource(Immunization.SOURCE.HISTORICAL);
        immunization1.setLotNumber("");

        immunization2.setDate("20161218");
        immunization2.setCVX("03");
        immunization2.setProductCode("");
        immunization2.setOrganisationID("Medicare");
        immunization2.setSource(Immunization.SOURCE.HISTORICAL);
        immunization2.setLotNumber("");
        
        StepOne stepOne = new StepOne();
        boolean result = stepOne.selectionPhase(immunization1, immunization2);
        
        assertEquals(true, result);
        
    }
    
    public void testMultipleSelection() throws Exception {
    	
    	// Init immunizations (Patient 1)
    	
    	 Immunization immunization1 = new Immunization();
    	 Immunization immunization2 = new Immunization();
    	 Immunization immunization3 = new Immunization();
    	 Immunization immunization4 = new Immunization();
    	 Immunization immunization5 = new Immunization();
    	 Immunization immunization6 = new Immunization();
    	 Immunization immunization7 = new Immunization();
    	 Immunization immunization8 = new Immunization();
    	 
    	 // Date, CVX and Source are the 3 parameters needed for Step One
    	 // Patient 1
    	 
    	 immunization1.setDate("20161217");   
         immunization1.setCVX("03");
         immunization1.setSource(Immunization.SOURCE.SOURCE);
         
         immunization2.setDate("20161217");   
         immunization2.setCVX("03");
         immunization2.setSource(Immunization.SOURCE.HISTORICAL);
         
         immunization3.setDate("20161218");   
         immunization3.setCVX("03");
         immunization3.setSource(Immunization.SOURCE.HISTORICAL);
         
         immunization4.setDate("20161217");   
         immunization4.setCVX("130");
         immunization4.setSource(Immunization.SOURCE.SOURCE);
         
         immunization5.setDate("20161217");   
         immunization5.setCVX("83");
         immunization5.setSource(Immunization.SOURCE.SOURCE);
         
         immunization6.setDate("20160605");
         immunization6.setCVX("08");
         immunization6.setSource(Immunization.SOURCE.SOURCE);
         
         immunization7.setDate("20160401");   
         immunization7.setCVX("08");
         immunization7.setSource(Immunization.SOURCE.SOURCE);
         
         immunization8.setDate("20151111");   
         immunization8.setCVX("08");
         immunization8.setSource(Immunization.SOURCE.HISTORICAL);	
         
         
         // Add the expected linked immunization to the array which will be 
         //compare with the one built by the StepOne class' multipleSelection method
          
      	LinkedImmunization immunizations = new LinkedImmunization();
      	LinkedImmunization expectedLinkedImmunization = new LinkedImmunization();
      	ArrayList<LinkedImmunization> expectedLinkedArray = new ArrayList<LinkedImmunization>();
      	
      	immunizations.add(immunization2);
      	immunizations.add(immunization3);
      	expectedLinkedImmunization.addAll(immunizations);
      	expectedLinkedArray.add(expectedLinkedImmunization);
      	
      	// filling the immunizations array used by multipleSelection method
      	immunizations.clear();
      	immunizations.add(immunization1);
      	immunizations.add(immunization2);
      	immunizations.add(immunization3);
      	immunizations.add(immunization4);
      	immunizations.add(immunization5);
      	immunizations.add(immunization6);
      	immunizations.add(immunization7);
      	immunizations.add(immunization8);	
      
      	// Test Patient 1
      	
      	StepOne stepOne = new StepOne();
      	ArrayList<LinkedImmunization> result = stepOne.multipleSelection(immunizations);
      	//assertEquals(expectedLinkedArray, result);
      	
      	// Failure debugging code
        for (int i=0; i<result.size();i++){
  		System.out.println((i+1)+"\n");
 		for(int j=0; j<result.get(i).size();j++){
 		System.out.println("Obtained : "+result.get(i).get(j).getCVX());
 		System.out.println(result.get(i).get(j).getDate());
 		System.out.println(result.get(i).get(j).getSource());
 		System.out.println("\n");
 			}
        }
 		
 		for (int k=0; k<expectedLinkedArray.size();k++){
 	  		System.out.println((k+1)+"\n");
 	 		for(int j=0; j<expectedLinkedArray.get(k).size();j++){
 	 		System.out.println("Expected : "+expectedLinkedArray.get(k).get(j).getCVX());
 	 		System.out.println(expectedLinkedArray.get(k).get(j).getDate());
 	 		System.out.println(expectedLinkedArray.get(k).get(j).getSource());
 	 		System.out.println("\n");
 	 		}
 	}
    	
    	// Init immunizations (Patient 2)
    	
    	 Immunization immunization9 = new Immunization();
    	 Immunization immunization10 = new Immunization();
    	 Immunization immunization11 = new Immunization();
    	 Immunization immunization12 = new Immunization();
    	 Immunization immunization13 = new Immunization();
    	 Immunization immunization14 = new Immunization();
    	 Immunization immunization15 = new Immunization();
    	 Immunization immunization16 = new Immunization();
    	 Immunization immunization17 = new Immunization();
    	 Immunization immunization18 = new Immunization();
    	 
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
         immunization18.setSource(Immunization.SOURCE.SOURCE);
         
     	 // Add the expected linked immunization to the array which will be 
         //compare with the one built by the StepOne class' multipleSelection method
         
     	immunizations.clear();
     	expectedLinkedImmunization.clear();
     	expectedLinkedArray.clear();
     	
      	immunizations.add(immunization10);
      	immunizations.add(immunization11);
      	immunizations.add(immunization12);
      	expectedLinkedImmunization.addAll(immunizations);
      	expectedLinkedArray.add(expectedLinkedImmunization);
      	
      	immunizations.clear();
      	immunizations.add(immunization14);
      	immunizations.add(immunization15);
      	expectedLinkedImmunization.addAll(immunizations);
      	expectedLinkedArray.add(expectedLinkedImmunization);
      	
      	immunizations.clear();
      	immunizations.add(immunization17);
      	immunizations.add(immunization18);
      	expectedLinkedImmunization.addAll(immunizations);
      	expectedLinkedArray.add(expectedLinkedImmunization);
      	
      	// filling the immunizations array used by multipleSelection method
      	
      	immunizations.add(immunization9);
      	immunizations.add(immunization10);
      	immunizations.add(immunization11);
      	immunizations.add(immunization12);
      	immunizations.add(immunization13);
      	immunizations.add(immunization14);
      	immunizations.add(immunization15);
      	immunizations.add(immunization16);
      	immunizations.add(immunization17);
      	immunizations.add(immunization18); 
     	
     	// Test patient 2
      	
      	StepOne stepOne2 = new StepOne();
      	ArrayList<LinkedImmunization> result2 = stepOne2.multipleSelection(immunizations);
     	assertEquals(expectedLinkedArray, result2);
     	
        }
    }


