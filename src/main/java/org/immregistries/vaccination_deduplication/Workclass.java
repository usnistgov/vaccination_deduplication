package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;

/**
 * 
 * Launch the deduplication process according to the evaluation method chosen
 * which can be the weighted approach, the deterministic approach or a combination of both
 *
 */
public class Workclass {

	// Methods which can be used to launch the deduplication process
    public enum METHOD {WEIGHTED, DETERMINISTIC, COMBO}

    /**
     * Launch the deduplication process using the weighted approach
     * 
     * @param patientImmunizationRecords
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicateWeighted(LinkedImmunization patientImmunizationRecords) {
        Weighted weighted = new Weighted();
        Result result;

        // TODO compare 2 by 2
        int i = 1;
        int j = 2;

        result = weighted.score(patientImmunizationRecords.getImmunizations().get(i), patientImmunizationRecords.getImmunizations().get(j));

        return null;
    }

    /**
     * // Launch the deduplication process using the deterministic approach
     * @param patientImmunizationRecords
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicateDeterministic(LinkedImmunization patientImmunizationRecords) {
        Deterministic deterministic = new Deterministic();
        Result result;

        // TODO compare 2 by 2
        int i = 1;
        int j = 2;

        result = deterministic.score(patientImmunizationRecords.getImmunizations().get(i), patientImmunizationRecords.getImmunizations().get(j));
        return null;
    }

    /**
     * // Launch the deduplication process using a combination of the weighted approach and the deterministic approach
     * @param patientImmunizationRecords
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicateCombo(LinkedImmunization patientImmunizationRecords) {
        Result result1;
        Result result2;

        Weighted weighted = new Weighted();
        Deterministic deterministic = new Deterministic();
        // TODO compare 2 by 2
        int i = 1;
        int j = 2;
        result1 = weighted.score(patientImmunizationRecords.getImmunizations().get(i), patientImmunizationRecords.getImmunizations().get(j));
        result2 = deterministic.score(patientImmunizationRecords.getImmunizations().get(i), patientImmunizationRecords.getImmunizations().get(j));

        return null;
    }

    /**
     * // Call the deduplication process corresponding to the specified approach
     * @param patientImmunizationRecords
     * @param method
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicate(LinkedImmunization patientImmunizationRecords, METHOD method) {
        switch (method) {
            case DETERMINISTIC:
                return(deduplicateDeterministic(patientImmunizationRecords));
            case WEIGHTED:
                return(deduplicateWeighted(patientImmunizationRecords));
            case COMBO:
                return(deduplicateCombo(patientImmunizationRecords));
        }
        return null;
    }
}