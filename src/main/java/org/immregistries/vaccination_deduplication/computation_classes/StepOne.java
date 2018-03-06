package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.VaccinationDeduplicationParameters;
import org.immregistries.vaccination_deduplication.utils.Matching;

import java.util.List;

/**
 * This class contains all the methods and logic necessary for the first step of the deduplication process.
 */
public class StepOne {
	
	private double dateWindow;

    public StepOne(VaccinationDeduplicationParameters parameters) {
		super();
        this.dateWindow = parameters.getDateWindow();
	}

	/**
	 * This method will compare the administration dates of 2 immunization records.
	 * @param immunization1 The first immunization to compare.
	 * @param immunization2 The second immunization to compare.
	 * @return True if the difference between the 2 is less than the date window set in the config file.
	 */
	public boolean dateWindowMet(Immunization immunization1, Immunization immunization2){
		boolean dateWindowMet = false;
		long duration;
		long date1 = immunization1.getDate().getTime();
		long date2 = immunization2.getDate().getTime();
		duration = (date2 - date1)/86400000; // 1000 ms * 60s * 60min * 24h = 86.400.000 ms = 1 day
		if (duration < 0)
			duration = -duration;
		if (duration <= this.dateWindow)
			dateWindowMet = true;
		return dateWindowMet;
	}

    /**
     * Determines selection phase outcome. Records must be evaluated if they verify 3 different factors : 
     * date window met, same vaccine group and they are not identical vaccination event.
     * 
     * @param immunization1 The first immunization to compare.
     * @param immunization2 The second immunization to compare.
     * @return True if the records pass all tests and thus must be evaluated in step two.
     */
    public boolean isPotentialDuplicate(Immunization immunization1, Immunization immunization2) {
    	// Is the date window met?
    	boolean dateWindowMet = dateWindowMet(immunization1, immunization2);
		
		// Is there at least one vaccine group that is the same between the 2?
		boolean sameVaccineFamily = false;
		if (!(immunization1.getVaccineGroupList()==null || immunization2.getVaccineGroupList()==null ||
                immunization1.getVaccineGroupList().isEmpty() || immunization2.getVaccineGroupList().isEmpty())){
				List<String> immunization1GroupList = immunization1.getVaccineGroupList();
	            List<String> immunization2GroupList = immunization2.getVaccineGroupList();

	        	if (Matching.isThereAMatch(immunization1GroupList, immunization2GroupList)) {
	        		sameVaccineFamily = true;
	        	}
		}

		
		// Are they actually different vaccination events?
		// Check if administrated date, the vaccine type and the provider organization are different.
		boolean notIdenticalVaccinationEvent = false;
		if (immunization1.getDate().equals(immunization2.getDate()) && immunization1.getOrganisationID().equals(immunization2.getOrganisationID()) && immunization1.getCVX().equals(immunization2.getCVX())){
			notIdenticalVaccinationEvent = true;	
		}
		
		return (dateWindowMet && sameVaccineFamily && !notIdenticalVaccinationEvent);
    }
}
