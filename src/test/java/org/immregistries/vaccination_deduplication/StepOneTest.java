package org.immregistries.vaccination_deduplication;

import org.immregistries.vaccination_deduplication.computation_classes.StepOne;

import junit.framework.TestCase;

public class StepOneTest extends TestCase {
    public void testSelection() throws Exception {
    	
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number");
        immunization1.setCVX("VCX");
        immunization1.setSource(Immunization.SOURCE.SOURCE);

        immunization2.setDate("20171225");
        immunization2.setLotNumber("test lot number");
        immunization2.setCVX("VCX");
        immunization2.setSource(Immunization.SOURCE.ALTERNATE);
        
        StepOne stepOne = new StepOne();
        stepOne.stepOneEvaluation(immunization1, immunization2);
        
        

    }

}