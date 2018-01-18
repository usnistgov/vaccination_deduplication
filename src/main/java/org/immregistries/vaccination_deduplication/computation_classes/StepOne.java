package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.LinkedImmunization;
import org.immregistries.vaccination_deduplication.PropertyLoader;

import java.util.ArrayList;

/**
 * Execute Step 1 : Selection phase determines if two records compared to each other have to be evaluated or not
 *
 */
public class StepOne {
	
	private double dateWindow;

    public StepOne() {
		super();
		PropertyLoader propertyLoader = PropertyLoader.getInstance();
        this.dateWindow = propertyLoader.getDateWindow();
	}

    /**
     * Determines selection phase outcome. Records must be evaluated if they verify 3 different factors : 
     * date window met,  same vaccine family and Not identical vaccination event.
     * 
     * @param immunization1
     * @param immunization2 are the records to compare to each other
     * @return true if the records must be evaluated or false if the records must not be evaluated
     */
    public boolean selectionPhase(Immunization immunization1, Immunization immunization2) {
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

    public ArrayList<LinkedImmunization> multipleSelection(ArrayList<Immunization> immunizations) {
    	ArrayList<Immunization> immunizationsCopy = immunizations; // We make a copy because we are going to modify it within this method
    	ArrayList<LinkedImmunization> LinkedImmArray = new ArrayList<LinkedImmunization>();
    	for (int i=0; i<immunizations.size(); i++){
    		LinkedImmunization LinkedImm = new LinkedImmunization();
    		LinkedImm.add(immunizations.get(i));
    		for (int j=0; j<immunizationsCopy.size();j++){ // if an immunization i is linked with another immunization j, we check if j date window is met with all the immunization already linked with i
    			if (selectionPhase(immunizations.get(i), immunizationsCopy.get(j))){
    				boolean dateWindowMet = false;
    				for (int k=0; k<LinkedImm.size();k++){
    					if (!immunizationsCopy.get(j).equals(LinkedImm.get(k))){
    						if(dateWindowMet(immunizationsCopy.get(j), LinkedImm.get(k)))
    							dateWindowMet = true;
    					}
    				}
    				if (dateWindowMet)
    					LinkedImm.add(immunizationsCopy.get(j));
    		}
    	}
    		if (LinkedImm.size() > 1){ // If we find at least 2 linked immunizations records, we add it to the LinkedImmunization ArrayList to return 
    			LinkedImmArray.add(LinkedImm);
    			for (Immunization k : LinkedImm) // We remove from the copy all the immunizations whose linked have already been established
    				immunizationsCopy.remove(k);
    			}
    	}
        return LinkedImmArray;
    }
    
    public boolean dateWindowMet(Immunization immunization1, Immunization immunization2){
    	boolean dateWindowMet = false;
        long duration;
    	long date1 = immunization1.getDate().getTime();
    	long date2 = immunization2.getDate().getTime();	
		duration = (date2 - date1)/86400000; // 1000 ms * 60s * 60min * 24h = 86.400.000 ms = 1 day
		if (duration < this.dateWindow)
			dateWindowMet = true;
    return dateWindowMet;
    }
}
