package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.StepOneResult;
import org.immregistries.vaccination_deduplication.utils.Matching;
import org.immregistries.vaccination_deduplication.LinkedImmunization;
import org.immregistries.vaccination_deduplication.PropertyLoader;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Determines selection phase outcome. Records must be evaluated if they verify 3 different factors : 
     * date window met,  same vaccine family and Not identical vaccination event.
     * 
     * @param immunization1
     * @param immunization2 are the records to compare to each other
     * @return true if the records must be evaluated or false if the records must not be evaluated
     */
    public boolean isPotentialDuplicate(Immunization immunization1, Immunization immunization2) {
    	// Date window met ?    	
    	boolean dateWindowMet = dateWindowMet(immunization1, immunization2);
		
		// Same vaccine family ? Check Vaccine Goup
		boolean sameVaccineFamily = false;
		if (!(immunization1.getVaccineGroupList()==null || immunization2.getVaccineGroupList()==null ||
                immunization1.getVaccineGroupList().isEmpty() || immunization2.getVaccineGroupList().isEmpty())){
				List<String> immunization1GroupList = immunization1.getVaccineGroupList();
	            List<String> immunization2GroupList = immunization2.getVaccineGroupList();

	        	if (Matching.isThereAMatch(immunization1GroupList, immunization2GroupList)) {
	        		sameVaccineFamily = true;
	        	}
		}

		
		// Not identical vaccination event ? Check same administrated Date, same vaccine type and same provider organization
		boolean notIdenticalVaccinationEvent = false;
		if (immunization1.getDate().equals(immunization2.getDate()) && immunization1.getOrganisationID().equals(immunization2.getOrganisationID()) && immunization1.getCVX().equals(immunization2.getCVX())){
			notIdenticalVaccinationEvent = true;	
		}
		
		return (dateWindowMet && sameVaccineFamily && !notIdenticalVaccinationEvent);
    }

	public StepOneResult executeStepOne(LinkedImmunization immunizations) {
        LinkedImmunization toEvaluate = new LinkedImmunization();
        LinkedImmunization notToEvaluate = new LinkedImmunization();

        boolean potentialDuplicate = false;

        for (int i = 0; i < immunizations.size(); i++) {
            potentialDuplicate = false;
            for (int j = 0; j < immunizations.size(); j++) {
                if (i != j && // We don't want to compare a record with itself
                        isPotentialDuplicate(immunizations.get(i), immunizations.get(j))) {
                    potentialDuplicate = true;
                }
            }
            if (potentialDuplicate) {
                toEvaluate.add(immunizations.get(i));
            } else {
                notToEvaluate.add(immunizations.get(i));
            }
        }

        return new StepOneResult(toEvaluate, notToEvaluate);
	}
}
