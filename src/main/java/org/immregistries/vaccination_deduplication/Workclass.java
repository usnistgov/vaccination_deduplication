package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;

/**
 * Created by Ansel on 07/11/2017.
 */
public class Workclass {

    public final static String METHOD_WEIGHTED = "WEIGHTED";
    public final static String METHOD_DETERMINISTIC = "DETERMINISTIC";
    public final static String METHOD_COMBO = "COMBO";

    public ArrayList<Linkage> DeduplicateWeighted(Linkage patientImmunizationRecords) {
        Weighted weighted = new Weighted();
        int result;

        // TODO compare 2 by 2
        int i = 1;
        int j = 2;

        result = weighted.score(patientImmunizationRecords.getItems().get(i).getResource(), patientImmunizationRecords.getItems().get(j).getResource());

        return null;
    }

    public ArrayList<Linkage> DeduplicateDeterministic(Linkage patientImmunizationRecords) {
        Deterministic deterministic = new Deterministic();
        int result;

        // TODO compare 2 by 2
        int i = 1;
        int j = 2;

        result = deterministic.score(patientImmunizationRecords.getItems().get(i).getResource(), patientImmunizationRecords.getItems().get(j).getResource());
        return null;
    }

    public ArrayList<Linkage> DeduplicateCombo(Linkage patientImmunizationRecords) {
        int result1;
        int result2;

        Weighted weighted = new Weighted();
        Deterministic deterministic = new Deterministic();
        // TODO compare 2 by 2
        int i = 1;
        int j = 2;
        result1 = weighted.score(patientImmunizationRecords.getItems().get(i).getResource(), patientImmunizationRecords.getItems().get(j).getResource());
        result2 = deterministic.score(patientImmunizationRecords.getItems().get(i).getResource(), patientImmunizationRecords.getItems().get(j).getResource());

        return null;
    }

    public ArrayList<Linkage> Deduplicate(Linkage patientImmunizationRecords, String method) {
        if (method.equals(METHOD_WEIGHTED)) {
            return(DeduplicateWeighted(patientImmunizationRecords));
        } else if (method.equals(METHOD_DETERMINISTIC)) {
            return(DeduplicateDeterministic(patientImmunizationRecords));
        } else if (method.equals(METHOD_COMBO)) {
            return(DeduplicateCombo(patientImmunizationRecords));
        }
        return null;
    }
}