package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;

/**
 * Execute Step 1 : Selection phase determines if two records compared to each other have to be evaluated or not
 *
 */
public class StepOne {

    private int dateWindow = 23;

    public boolean stepOneEvaluation(Immunization immunization1, Immunization immunization2) {
        return false; 
    }

    public ArrayList<LinkedImmunization> stepOne(ArrayList<Immunization> immunizations) {
        return null;
    }

    public int getDateWindow() {
        return dateWindow;
    }

    public void setDateWindow(int dateWindow) {
        this.dateWindow = dateWindow;
    }
}
