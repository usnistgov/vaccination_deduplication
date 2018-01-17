package org.immregistries.vaccination_deduplication;

import org.immregistries.vaccination_deduplication.computation_classes.Deterministic;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.utils.ImmunizationNormalisation;

import java.io.*;
import java.util.ArrayList;

/**
 * 
 * Launch the deduplication process according to the evaluation method chosen
 * which can be the weighted approach, the deterministic approach or a combination of both
 *
 */
// TODO change name
public class Workclass {
    ImmunizationNormalisation immunizationNormalisation;

    public Workclass(String codebaseFilePath) throws FileNotFoundException {
        this.immunizationNormalisation = ImmunizationNormalisation.getInstance();
        this.immunizationNormalisation.initialize(codebaseFilePath);
    }

    public Workclass() {
        this.immunizationNormalisation = ImmunizationNormalisation.getInstance();
        this.immunizationNormalisation.initialize();
    }

    public void refreshCodebase(String codebaseFilePath) throws FileNotFoundException {
        this.immunizationNormalisation.refreshCodebase(codebaseFilePath);
    }

    /**
     * Launch the deduplication process using the weighted approach
     * 
     * @param patientImmunizationRecords
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicateWeighted(LinkedImmunization patientImmunizationRecords) {
        immunizationNormalisation.normalizeAllImmunizations(patientImmunizationRecords);
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
        immunizationNormalisation.normalizeAllImmunizations(patientImmunizationRecords);
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
        immunizationNormalisation.normalizeAllImmunizations(patientImmunizationRecords);
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
    public ArrayList<LinkedImmunization> deduplicate(LinkedImmunization patientImmunizationRecords, DeduplicationMethod method) {
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