package org.immregistries.vaccination_deduplication;

import org.immregistries.vaccination_deduplication.computation_classes.*;
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
public class VaccinationDeduplication {
    static VaccinationDeduplication instance;
    ImmunizationNormalisation immunizationNormalisation;

    private VaccinationDeduplication() {
        this.immunizationNormalisation = ImmunizationNormalisation.getInstance();
    }

    public static VaccinationDeduplication getInstance() {
        if (instance == null)
            instance = new VaccinationDeduplication();
        return instance;
    }

    public void initialize(String codebaseFilePath) throws FileNotFoundException {
        this.immunizationNormalisation.initialize(codebaseFilePath);
    }

    public void initialize() {
        this.immunizationNormalisation.initialize();
    }

    public void refreshCodebase(String codebaseFilePath) throws FileNotFoundException {
        this.immunizationNormalisation.refreshCodebase(codebaseFilePath);
    }


    public boolean lineHas(ArrayList<ComparisonResult> line, ComparisonResult result) {
        for (ComparisonResult r : line)
            if (r.equals(result))
                return true;

        return false;
    }

    public ArrayList<LinkedImmunization> postprocessing(LinkedImmunization toEvaluate, LinkedImmunization notToEvaluate, ArrayList<ArrayList<ComparisonResult>> results) {
        HashMap<Integer, LinkedImmunization> sameGrouped = new HashMap<Integer, LinkedImmunization>();
        ArrayList<LinkedImmunization> unsures = new ArrayList<LinkedImmunization>();

        // first pass to group the ones we are SURE are the same together
        for (int i = 0; i < results.size(); i++) {


            for (int j = 0; j < results.size(); j++) {
                if (results.get(i).get(j).equals(ComparisonResult.EQUAL)) {
                    if(sameGrouped.containsKey(i) && sameGrouped.containsKey(j)) {

                    } else if(sameGrouped.containsKey(i)) {
                        sameGrouped.get(i).add(toEvaluate.get(j));
                        sameGrouped.put(j, sameGrouped.get(i));
                    } else if(sameGrouped.containsKey(j)) {
                        sameGrouped.get(j).add(toEvaluate.get(i));
                        sameGrouped.put(i, sameGrouped.get(j));
                    } else {
                        LinkedImmunization group = new LinkedImmunization();
                        group.setType(LinkedImmunizationType.SURE);
                        group.add(toEvaluate.get(i));
                        group.add(toEvaluate.get(j));
                        sameGrouped.put(i, group);
                        sameGrouped.put(j, group);
                    }
                }
            }
        }

        // second pass to handle the UNSURE and DIFFERENT

        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);

        for (int i = 0; i < results.size()-1; i++) {
            if (!sameGrouped.keySet().contains(i)) {
                if (lineHas(results.get(i), ComparisonResult.UNSURE)) {
                    LinkedImmunization unsure = new LinkedImmunization();
                    unsure.setType(LinkedImmunizationType.UNSURE);
                    unsure.add(toEvaluate.get(i));
                    for (int j = i+1; j < results.size(); j++) {
                        if (results.get(i).get(j) == ComparisonResult.UNSURE)
                            unsure.add(toEvaluate.get(j));
                    }
                    if (unsure.size() > 1)
                        unsures.add(unsure);

                } else {
                    different.add(toEvaluate.get(i));
                    for (int j = i+1; j < results.size(); j++) {
                        if (!sameGrouped.keySet().contains(j) && !different.contains(toEvaluate.get(j))) {
                            different.add(toEvaluate.get(j));
                        }
                    }
                }
            }
        }

        ArrayList<LinkedImmunization> groupedImmunizations = new ArrayList<LinkedImmunization>();

        for (Integer i : sameGrouped.keySet()) {
            if (!groupedImmunizations.contains(sameGrouped.get(i)))
                groupedImmunizations.add(sameGrouped.get(i));
        }

        groupedImmunizations.addAll(unsures);

        different.addAll(notToEvaluate);
        groupedImmunizations.add(different);

        return groupedImmunizations;
    }

    /**
     * Launch the deduplication process using the weighted approach
     * 
     * @param patientImmunizationRecords
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicateWeighted(LinkedImmunization patientImmunizationRecords) {
        return deduplicate(patientImmunizationRecords, DeduplicationMethod.WEIGHTED);
    }

    /**
     * // Launch the deduplication process using the deterministic approach
     * @param patientImmunizationRecords
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicateDeterministic(LinkedImmunization patientImmunizationRecords) {
        return deduplicate(patientImmunizationRecords, DeduplicationMethod.DETERMINISTIC);
    }

    /**
     * // Launch the deduplication process using a combination of the weighted approach and the deterministic approach
     * @param patientImmunizationRecords
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicateHybrid(LinkedImmunization patientImmunizationRecords) {
        return deduplicate(patientImmunizationRecords, DeduplicationMethod.HYBRID);
    }

    /**
     * // Call the deduplication process corresponding to the specified approach
     * @param patientImmunizationRecords
     * @param method
     * @return
     */
    public ArrayList<LinkedImmunization> deduplicate(LinkedImmunization patientImmunizationRecords, DeduplicationMethod method) {
        Comparer comparer;
        switch (method) {
            case DETERMINISTIC:
                comparer = new Deterministic();
                break;
            case WEIGHTED:
                comparer = new Weighted();
                break;
            case HYBRID:
                comparer = new Hybrid();
                break;
            default :
                comparer = new Hybrid();
        }

        immunizationNormalisation.normalizeAllImmunizations(patientImmunizationRecords);

        StepOne stepOne = new StepOne();
        StepOneResult stepOneResult = stepOne.executeStepOne(patientImmunizationRecords);
        LinkedImmunization toEvaluate = stepOneResult.getToEvaluate();

        ArrayList<ArrayList<ComparisonResult>> results;

        results = new ArrayList<ArrayList<ComparisonResult>>(toEvaluate.size());
        for (int i = 0; i < toEvaluate.size(); i++) {
            results.add(new ArrayList<ComparisonResult>());
            for (int j = 0; j < toEvaluate.size(); j++) {
                results.get(i).add(ComparisonResult.TO_BE_DETERMINED);
            }
        }

        for (int i = 0; i < toEvaluate.size()-1; i ++) {
            for (int j = i+1; j < toEvaluate.size(); j ++) {
                ComparisonResult result = comparer.score(toEvaluate.get(i), toEvaluate.get(j));
                results.get(i).set(j, result);
                results.get(j).set(i, result);
            }
        }

        return postprocessing(toEvaluate, stepOneResult.getNotToEvaluate(), results);
    }
}