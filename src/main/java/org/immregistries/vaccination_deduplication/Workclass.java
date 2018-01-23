package org.immregistries.vaccination_deduplication;

import org.immregistries.vaccination_deduplication.computation_classes.Deterministic;
import org.immregistries.vaccination_deduplication.computation_classes.StepOne;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.utils.ImmunizationNormalisation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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


    public boolean lineHas(ArrayList<Result> line, Result result) {
        for (Result r : line)
            if (r.equals(result))
                return true;

        return false;
    }

    /**
     * Launch the deduplication process using the weighted approach
     * 
     * @param patientImmunizationRecords
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicateWeighted(LinkedImmunization patientImmunizationRecords) {
        immunizationNormalisation.normalizeAllImmunizations(patientImmunizationRecords);

        StepOne stepOne = new StepOne();
        StepOneResult stepOneResult = stepOne.executeStepOne(patientImmunizationRecords);
        LinkedImmunization toEvaluate = stepOneResult.getToEvaluate();

        Weighted weighted = new Weighted();
        ArrayList<ArrayList<Result>> Results;

        HashMap<Integer, LinkedImmunization> groups = new HashMap<Integer, LinkedImmunization>();

        ArrayList<Result> R = new ArrayList<Result>(Collections.nCopies(toEvaluate.size(),  Result.TO_BE_DETERMINED));
        Results = new ArrayList<ArrayList<Result>>(Collections.nCopies(toEvaluate.size(),  R));

        for (int i = 0; i < toEvaluate.size(); i ++) {
            for (int j = i; j < toEvaluate.size(); j ++) {
                Result result = weighted.score(toEvaluate.get(i), toEvaluate.get(j));
                Results.get(i).set(j, result);

                if (result.equals(Result.EQUAL)) {
                    if(groups.containsKey(i)) {
                        groups.get(i).add(toEvaluate.get(j));
                    } else if(groups.containsKey(j)) {
                        groups.get(j).add(toEvaluate.get(i));
                    } else {
                        LinkedImmunization group = new LinkedImmunization();
                        group.add(toEvaluate.get(i));
                        group.add(toEvaluate.get(j));
                        groups.put(i, group);
                        groups.put(j, group);
                    }
                }
            }
        }

        ArrayList<LinkedImmunization> GroupedImmunizations = new ArrayList<LinkedImmunization>();


        for (Integer key:groups.keySet()) {
            if (!GroupedImmunizations.contains(groups.get(key))) {
                GroupedImmunizations.add(groups.get(key));
            }
        }

        boolean contains = false;
        LinkedImmunization different = new LinkedImmunization();
        for (int i = 0; i < toEvaluate.size(); i ++) {
            contains = false;
            for (Integer j:groups.keySet()) {
                if (i == j)
                    contains = true;
            }

            if (!contains) { // leftovers
                if (lineHas(Results.get(i), Result.UNSURE)) {
                    ArrayList<Result> line = Results.get(i);
                    LinkedImmunization group = new LinkedImmunization();
                    group.add(toEvaluate.get(i));
                    for (int j = 0; j < toEvaluate.size(); j++) {
                        if (line.get(j).equals(Result.UNSURE)) {
                            group.add(toEvaluate.get(j));
                        }
                    }
                    GroupedImmunizations.add(group);
                } else if(!lineHas(Results.get(i), Result.EQUAL)) {
                    different.add(toEvaluate.get(i));
                }
            }
        }

        GroupedImmunizations.add(different);


        return GroupedImmunizations;
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

        result = deterministic.score(patientImmunizationRecords.get(i), patientImmunizationRecords.get(j));
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
        result1 = weighted.score(patientImmunizationRecords.get(i), patientImmunizationRecords.get(j));
        result2 = deterministic.score(patientImmunizationRecords.get(i), patientImmunizationRecords.get(j));

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