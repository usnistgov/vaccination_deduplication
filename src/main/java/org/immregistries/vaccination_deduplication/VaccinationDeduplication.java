package org.immregistries.vaccination_deduplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.immregistries.vaccination_deduplication.computation_classes.Comparer;
import org.immregistries.vaccination_deduplication.computation_classes.Deterministic;
import org.immregistries.vaccination_deduplication.computation_classes.Hybrid;
import org.immregistries.vaccination_deduplication.computation_classes.StepOne;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.utils.ImmunizationNormalisation;

/**
 * This class is the entry point for the API. It contains the main logic for the deduplication process.
 */
public class VaccinationDeduplication {
    private ImmunizationNormalisation immunizationNormalisation;

    /**
     * This constructor will initialize the class for the Immunization Normalisation process.
     * It will use the codebase file present in the codebase client jar.
     */
    public VaccinationDeduplication() {
        this.immunizationNormalisation = new ImmunizationNormalisation();
    }

    /**
     * This constructor will initialize the class for the Immunization Normalisation process.
     * It will use the codebase file at the given path.
     *
     * @param codebaseFilePath The path to the codebase file.
     * @throws FileNotFoundException Throws exception if the codebase file is not found.
     */
    public VaccinationDeduplication(String codebaseFilePath) throws FileNotFoundException {
        this.immunizationNormalisation = new ImmunizationNormalisation(codebaseFilePath);
    }

    /**
     * This method will change the codebase file used by the Immunization Normalisation process.
     * @param codebaseFilePath The path to the codebase file.
     * @throws FileNotFoundException Throws exception if the codebase file is not found.
     */
    public void refreshCodebase(String codebaseFilePath) throws FileNotFoundException {
        this.immunizationNormalisation.refreshCodebase(codebaseFilePath);
    }

    /**
     * This method will process the results form the comparison process and return the LinkedImmunization grouping duplicates (or unsures or non duplicates) together.
     * @param toEvaluate This ArrayList contains the Immunizations that have been determined by step one to be potential duplicates.
     * @param results This 2D ArrayList contains the results from the comparisons of the toEvaluate Immunizations.
     * @return An ArrayList of LinkedImmunization containing the final result from the deduplication process.
     */
    public ArrayList<LinkedImmunization> postprocessing(LinkedImmunization toEvaluate, ArrayList<ArrayList<ComparisonResult>> results) {

        for (ArrayList<ComparisonResult> line : results) {
            for (ComparisonResult cr : line) {
                System.out.print(cr + "\t");
            }
            System.out.println();
        }

        // These hashmaps will contain arraylists containing the indexes of Immunizations

        HashMap<Integer, ArrayList<Integer>> sameGroupedIndexes = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> unsureGroupedIndexes = new HashMap<Integer, ArrayList<Integer>>();

        // first pass to group the ones we are SURE are the same together
        for (int i = 0; i < results.size()-1; i++) {
            for (int j = i+1; j < results.size(); j++) {
                if (results.get(i).get(j).equals(ComparisonResult.EQUAL)) {
                    if(sameGroupedIndexes.containsKey(i) && sameGroupedIndexes.containsKey(j)) {
                        // merge two groups together
                        sameGroupedIndexes.get(i).addAll(sameGroupedIndexes.get(j));
                        for (Integer key : sameGroupedIndexes.get(i)) {
                            sameGroupedIndexes.put(key, sameGroupedIndexes.get(i));
                        }
                    } else if(sameGroupedIndexes.containsKey(i)) {
                        // add j to group in i and put group in j
                        sameGroupedIndexes.get(i).add(j);
                        sameGroupedIndexes.put(j, sameGroupedIndexes.get(i));
                    } else if(sameGroupedIndexes.containsKey(j)) {
                        // add i to group in j and put group in i
                        sameGroupedIndexes.get(j).add(i);
                        sameGroupedIndexes.put(i, sameGroupedIndexes.get(j));
                    } else {
                        // create new group and put group in i and j
                        ArrayList<Integer> group = new ArrayList<Integer>();
                        group.add(i);
                        group.add(j);
                        sameGroupedIndexes.put(i, group);
                        sameGroupedIndexes.put(j, group);
                    }
                }
            }
        }

        // second pass to handle the UNSURE
        for (int i = 0; i < results.size()-1; i++) {
            for (int j = i+1; j < results.size(); j++) {
                if (!sameGroupedIndexes.keySet().contains(i) && !sameGroupedIndexes.keySet().contains(j)) {
                    if (results.get(i).get(j).equals(ComparisonResult.UNSURE)) {
                        if(unsureGroupedIndexes.containsKey(i) && unsureGroupedIndexes.containsKey(j)) {
                            unsureGroupedIndexes.get(i).addAll(unsureGroupedIndexes.get(j));
                            for (Integer key : unsureGroupedIndexes.get(i)) {
                                unsureGroupedIndexes.put(key, unsureGroupedIndexes.get(i));
                            }
                        } else if(unsureGroupedIndexes.containsKey(i)) {
                            unsureGroupedIndexes.get(i).add(j);
                            unsureGroupedIndexes.put(j, unsureGroupedIndexes.get(i));
                        } else if(unsureGroupedIndexes.containsKey(j)) {
                            unsureGroupedIndexes.get(j).add(i);
                            unsureGroupedIndexes.put(i, unsureGroupedIndexes.get(j));
                        } else {
                            ArrayList<Integer> group = new ArrayList<Integer>();
                            group.add(i);
                            group.add(j);
                            unsureGroupedIndexes.put(i, group);
                            unsureGroupedIndexes.put(j, group);
                        }
                    }
                }
            }
        }

        // third pass to handle the DIFFERENT
        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);

        for (int i = 0; i < results.size(); i++) {
            if (!sameGroupedIndexes.keySet().contains(i) && !unsureGroupedIndexes.keySet().contains(i)){
                different.add(toEvaluate.get(i));
            }
        }

        ArrayList<LinkedImmunization> groupedImmunizations = new ArrayList<LinkedImmunization>();

        ArrayList<Integer> alreadySet = new ArrayList<Integer>();
        for (Integer i : sameGroupedIndexes.keySet()) {
            LinkedImmunization linkedImmunization = new LinkedImmunization();
            linkedImmunization.setType(LinkedImmunizationType.SURE);
            for (Integer index : sameGroupedIndexes.get(i)) {
                if (!alreadySet.contains(index)) {
                    linkedImmunization.add(toEvaluate.get(index));
                    alreadySet.add(index);
                }
            }
            if (linkedImmunization.size() > 0)
                groupedImmunizations.add(linkedImmunization);
        }
        alreadySet.clear();
        for (Integer i : unsureGroupedIndexes.keySet()) {
            LinkedImmunization linkedImmunization = new LinkedImmunization();
            linkedImmunization.setType(LinkedImmunizationType.UNSURE);
            for (Integer index : unsureGroupedIndexes.get(i)) {
                if (!alreadySet.contains(index)) {
                    linkedImmunization.add(toEvaluate.get(index));
                    alreadySet.add(index);
                }
            }
            if (linkedImmunization.size() > 0)
                groupedImmunizations.add(linkedImmunization);
        }


        if (different.size()>0)
            groupedImmunizations.add(different);

        return groupedImmunizations;
    }

    /**
     * This method will launch the deduplication process using the weighted approach.
     * @param patientImmunizationRecords A LinkedImmunization object containing all the immunization records for a patient.
     * @return An ArrayList of LinkedImmunization containing the final result from the deduplication process.
     */
    public ArrayList<LinkedImmunization> deduplicateWeighted(LinkedImmunization patientImmunizationRecords) {
        return deduplicate(patientImmunizationRecords, DeduplicationMethod.WEIGHTED);
    }

    /**
     * This method will launch the deduplication process using the deterministic approach
     * @param patientImmunizationRecords A LinkedImmunization object containing all the immunization records for a patient.
     * @return An ArrayList of LinkedImmunization containing the final result from the deduplication process.
     */
    public ArrayList<LinkedImmunization> deduplicateDeterministic(LinkedImmunization patientImmunizationRecords) {
        return deduplicate(patientImmunizationRecords, DeduplicationMethod.DETERMINISTIC);
    }

    /**
     * This method will launch the deduplication process using a hybrid of the weighted approach and the deterministic approach.
     * @param patientImmunizationRecords A LinkedImmunization object containing all the immunization records for a patient.
     * @return An ArrayList of LinkedImmunization containing the final result from the deduplication process.
     */
    public ArrayList<LinkedImmunization> deduplicateHybrid(LinkedImmunization patientImmunizationRecords) {
        return deduplicate(patientImmunizationRecords, DeduplicationMethod.HYBRID);
    }

    /**
     * This method will call all the methods necessary to the deduplication process.
     * It can also be called directly instead of using the specific methods.
     * @param patientImmunizationRecords A LinkedImmunization object containing all the immunization records for a patient.
     * @param method The method to use for the deduplication process.
     * @return An ArrayList of LinkedImmunization containing the final result from the deduplication process.
     */
    public ArrayList<LinkedImmunization> deduplicate(LinkedImmunization patientImmunizationRecords, DeduplicationMethod method) {
        // Load parameters
        PropertyLoader propertyLoader = new PropertyLoader();
        VaccinationDeduplicationParameters parameters = propertyLoader.getParameters();

        Comparer comparer;
        switch (method) {
            case DETERMINISTIC:
                comparer = new Deterministic();
                break;
            case WEIGHTED:
                comparer = new Weighted(parameters);
                break;
            case HYBRID:
                comparer = new Hybrid(parameters);
                break;
            default :
                comparer = new Hybrid(parameters);
        }

        immunizationNormalisation.normalizeAllImmunizations(patientImmunizationRecords);

        StepOne stepOne = new StepOne(parameters);
        LinkedImmunization toEvaluate = patientImmunizationRecords;

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
                if (stepOne.isPotentialDuplicate(toEvaluate.get(i), toEvaluate.get(j))) {
                    ComparisonResult result = comparer.compare(toEvaluate.get(i), toEvaluate.get(j));
                    results.get(i).set(j, result);
                    results.get(j).set(i, result);
                } else {
                    results.get(i).set(j, ComparisonResult.DIFFERENT);
                    results.get(j).set(i, ComparisonResult.DIFFERENT);
                }
            }
        }

        return postprocessing(toEvaluate, results);
    }
}