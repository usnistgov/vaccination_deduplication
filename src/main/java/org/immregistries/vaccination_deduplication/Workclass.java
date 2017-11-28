package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;

public class Workclass {

    public enum METHOD {WEIGHTED, DETERMINISTIC, COMBO}

    public ArrayList<LinkedImmunization> DeduplicateWeighted(LinkedImmunization patientImmunizationRecords) {
        Weighted weighted = new Weighted();
        Weighted.RESULT result;

        // TODO compare 2 by 2
        int i = 1;
        int j = 2;

        result = weighted.score(patientImmunizationRecords.getImmunizations().get(i), patientImmunizationRecords.getImmunizations().get(j));

        return null;
    }

    public ArrayList<LinkedImmunization> DeduplicateDeterministic(LinkedImmunization patientImmunizationRecords) {
        Deterministic deterministic = new Deterministic();
        Deterministic.RESULT result;

        // TODO compare 2 by 2
        int i = 1;
        int j = 2;

        result = deterministic.score(patientImmunizationRecords.getImmunizations().get(i), patientImmunizationRecords.getImmunizations().get(j));
        return null;
    }

    public ArrayList<LinkedImmunization> DeduplicateCombo(LinkedImmunization patientImmunizationRecords) {
        Weighted.RESULT result1;
        Deterministic.RESULT result2;

        Weighted weighted = new Weighted();
        Deterministic deterministic = new Deterministic();
        // TODO compare 2 by 2
        int i = 1;
        int j = 2;
        result1 = weighted.score(patientImmunizationRecords.getImmunizations().get(i), patientImmunizationRecords.getImmunizations().get(j));
        result2 = deterministic.score(patientImmunizationRecords.getImmunizations().get(i), patientImmunizationRecords.getImmunizations().get(j));

        return null;
    }

    public ArrayList<LinkedImmunization> Deduplicate(LinkedImmunization patientImmunizationRecords, METHOD method) {
        switch (method) {
            case DETERMINISTIC:
                return(DeduplicateDeterministic(patientImmunizationRecords));
            case WEIGHTED:
                return(DeduplicateWeighted(patientImmunizationRecords));
            case COMBO:
                return(DeduplicateCombo(patientImmunizationRecords));
        }
        return null;
    }
}