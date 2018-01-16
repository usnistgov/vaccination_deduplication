package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.LinkedImmunization;
import java.util.ArrayList;

/**
 * Execute Step 1 : Selection phase determines if two records compared to each other have to be evaluated or not
 *
 */
public class StepOne {

    public StepOne() {
		super();
	}

	private long dateWindow = 23;

    /**
     * Determines selection phase outcome. Records must be evaluated if they verify 3 different factors : 
     * date window met,  same vaccine family and Not identical vaccination event.
     * 
     * @param immunization1
     * @param immunization2 are the records to compare to each other
     * @return true if the records must be evaluated or false if the records must not be evaluated
     */
    public boolean stepOneEvaluation(Immunization immunization1, Immunization immunization2) {
    	// Date window met ?    	
    	boolean dateWindowMet = dateWindowMet(immunization1, immunization2);
		
		// Same vaccine family ?
		boolean sameVaccineFamily = false;
		if (immunization1.getCVX().equals(immunization2.getCVX()))
			sameVaccineFamily = true;
		
		// Not identical vaccination event ?
		boolean notIdenticalVaccinationEvent = false;
		if (immunization1.getSource().equals(immunization2.getSource()))
			notIdenticalVaccinationEvent = true;		
		
		return (dateWindowMet && sameVaccineFamily && notIdenticalVaccinationEvent);
    }

    public ArrayList<LinkedImmunization> stepOne(ArrayList<Immunization> immunizations) {
    	ArrayList<Immunization> immunizationsCopy = immunizations; // We make a copy because we are going to modify it within this method
    	ArrayList<LinkedImmunization> LinkedImmArray = new ArrayList<LinkedImmunization>();
    	for (Immunization i : immunizationsCopy){
    		LinkedImmunization LinkedImm = new LinkedImmunization();
			LinkedImm.addImmunization(i);
    		for (Immunization j : immunizationsCopy){ // if an immunization i is linked with another j, we check if j date window is met with all the immunization already linked with i
    			if (stepOneEvaluation(i, j)){
    				boolean dateWindowMet = true;
    				for (Immunization k : LinkedImm){
    					if(!(dateWindowMet(j,k)))
    							dateWindowMet = false;
    				}
    				if (dateWindowMet)
    					LinkedImm.addImmunization(j);
    		}
    	}
    		if (LinkedImm.getSize() > 1){ // If we find at least 2 linked immunizations records, we add it to the LinkedImmunization ArrayList to return 
    			LinkedImmArray.add(LinkedImm);
    			for (Immunization k : LinkedImm) // We remove from the copy all the immunizations whose linked have already been established
    				immunizationsCopy.remove(k);
    			}
    	}
        return LinkedImmArray;
    }

    public long getDateWindow() {
        return dateWindow;
    }

    public void setDateWindow(int dateWindow) {
        this.dateWindow = dateWindow;
    }
    
    public boolean dateWindowMet(Immunization immunization1, Immunization immunization2){
    	boolean dateWindowMet = false;
        long duration;
    	long date1 = immunization1.getDate().getTime();
    	long date2 = immunization2.getDate().getTime();	
		duration = (date2 - date1)/86400000; // 1000 ms * 60s * 60min * 24h = 86.400.000 ms = 1 day
		if (duration < getDateWindow())
			dateWindowMet = true;
    return dateWindowMet;
    }
}
