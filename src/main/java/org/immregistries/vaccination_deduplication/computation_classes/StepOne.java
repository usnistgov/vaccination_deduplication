package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.LinkedImmunization;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Execute Step 1 : Selection phase determines if two records compared to each other have to be evaluated or not
 *
 */
public class StepOne {

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
    	boolean dateWindowMet = false;
        long duration;
    	LocalDate date1 = immunization1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date2 = immunization2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		duration = ChronoUnit.DAYS.between(date1,date2);
		if (duration < getDateWindow())
			dateWindowMet = true;
		
		// Same vaccine family ?
		boolean sameVaccineFamily = false;
		if (immunization1.getVaccineCode().equals(immunization2.getVaccineCode()))
			sameVaccineFamily = true;
		
		// Not identical vaccination event ?
		boolean notIdenticalVaccinationEvent = false;
		if (immunization1.getSource().equals(immunization2.getSource()))
			notIdenticalVaccinationEvent = true;		
		
		return (dateWindowMet && sameVaccineFamily && notIdenticalVaccinationEvent);
    }

    public ArrayList<LinkedImmunization> stepOne(ArrayList<Immunization> immunizations) {
        return null;
    }

    public long getDateWindow() {
        return dateWindow;
    }

    public void setDateWindow(int dateWindow) {
        this.dateWindow = dateWindow;
    }
}
