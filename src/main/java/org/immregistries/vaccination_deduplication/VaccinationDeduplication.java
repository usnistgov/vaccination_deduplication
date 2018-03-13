package org.immregistries.vaccination_deduplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.immregistries.vaccination_deduplication.computation_classes.Comparer;
import org.immregistries.vaccination_deduplication.computation_classes.Deterministic;
import org.immregistries.vaccination_deduplication.computation_classes.Hybrid;
import org.immregistries.vaccination_deduplication.computation_classes.StepOne;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.reference.ComparisonResult;
import org.immregistries.vaccination_deduplication.reference.DeduplicationMethod;
import org.immregistries.vaccination_deduplication.reference.LinkedImmunizationType;
import org.immregistries.vaccination_deduplication.utils.ImmunizationNormalisation;
import org.immregistries.vaccination_deduplication.utils.PropertyLoader;

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
     * This method contains the logic to create, add to and merge groups of immunizations
     *
     * If none of the 2 immunizations are part of a group a new one containing the 2 will be created.
     * If one of the immunizations is already part of a group the other one will be added to it.
     * If the two are already part of groups they will be merged.
     *
     * @param groupedIndexes The indexes of the immunizations grouped together
     * @param index1 The index of the first immunization
     * @param index2 The index of the second immunization
     */
    private void grouping(HashMap<Integer, ArrayList<Integer>> groupedIndexes, int index1, int index2) {
        if(groupedIndexes.containsKey(index1) && groupedIndexes.containsKey(index2)) {
            // merge two groups together
            groupedIndexes.get(index1).addAll(groupedIndexes.get(index2));
            for (Integer key : groupedIndexes.get(index1)) {
                groupedIndexes.put(key, groupedIndexes.get(index1));
            }
        } else if(groupedIndexes.containsKey(index1)) {
            // add index2 to group in index1 and put group in index2
            groupedIndexes.get(index1).add(index2);
            groupedIndexes.put(index2, groupedIndexes.get(index1));
        } else if(groupedIndexes.containsKey(index2)) {
            // add index1 to group in index2 and put group in index1
            groupedIndexes.get(index2).add(index1);
            groupedIndexes.put(index1, groupedIndexes.get(index2));
        } else {
            // create new group and put group in index1 and index2
            ArrayList<Integer> group = new ArrayList<Integer>();
            group.add(index1);
            group.add(index2);
            groupedIndexes.put(index1, group);
            groupedIndexes.put(index2, group);
        }
    }

    /**
     * This method will process the results form the comparison process and return the LinkedImmunization grouping duplicates (or unsures or non duplicates) together.
     * @param toEvaluate This ArrayList contains the Immunizations that have been determined by step one to be potential duplicates.
     * @param results This 2D ArrayList contains the results from the comparisons of the toEvaluate Immunizations.
     * @return An ArrayList of LinkedImmunization containing the final result from the deduplication process.
     */
    public ArrayList<LinkedImmunization> postprocessing(LinkedImmunization toEvaluate, ArrayList<ArrayList<ComparisonResult>> results) {
        // These hashmaps will contain arraylists containing the indexes of Immunizations that have been grouped together
        HashMap<Integer, ArrayList<Integer>> sameGroupedIndexes = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> unsureGroupedIndexes = new HashMap<Integer, ArrayList<Integer>>();

        // first pass to group together the ones we are SURE are the same.
        for (int i = 0; i < results.size()-1; i++) {
            for (int j = i+1; j < results.size(); j++) {
                if (results.get(i).get(j).equals(ComparisonResult.EQUAL)) {
                    grouping(sameGroupedIndexes, i, j);
                }
            }
        }

        // second pass to handle the UNSURE
        for (int i = 0; i < results.size()-1; i++) {
            for (int j = i+1; j < results.size(); j++) {
                // all the immunizations that matched with another will be in the key set
                if (!sameGroupedIndexes.keySet().contains(i) && !sameGroupedIndexes.keySet().contains(j)) {
                    if (results.get(i).get(j).equals(ComparisonResult.UNSURE)) {
                        grouping(unsureGroupedIndexes, i, j);
                    }
                }
            }
        }

        // third pass to handle the DIFFERENT
        LinkedImmunization different = new LinkedImmunization();
        different.setType(LinkedImmunizationType.DIFFERENT);

        for (int i = 0; i < results.size(); i++) {
            // all the immunizations that matched with no other will be in neither the 2 key sets
            if (!sameGroupedIndexes.keySet().contains(i) && !unsureGroupedIndexes.keySet().contains(i)){
                different.add(toEvaluate.get(i));
            }
        }

        // groupedImmunizations will contain all the final linked immunizations
        ArrayList<LinkedImmunization> groupedImmunizations = new ArrayList<LinkedImmunization>();

        // alreadySet will keep track of the immunizations already present in a LinkedImmunization in groupedImmunization
        // this will help prevent adding the same LinkedImmunization more than once.
        ArrayList<Integer> alreadySet = new ArrayList<Integer>();

        // adding the SAME LinkedImmunizations to groupedImmunizations.
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
        // adding the UNSURE LinkedImmunizations to groupedImmunizations.
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

        // adding the DIFFERENT LinkedImmunizations to groupedImmunizations.
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

        ArrayList<ArrayList<ComparisonResult>> results;

        results = new ArrayList<ArrayList<ComparisonResult>>(patientImmunizationRecords.size());
        for (int i = 0; i < patientImmunizationRecords.size(); i++) {
            results.add(new ArrayList<ComparisonResult>());
            for (int j = 0; j < patientImmunizationRecords.size(); j++) {
                results.get(i).add(ComparisonResult.TO_BE_DETERMINED);
            }
        }

        for (int i = 0; i < patientImmunizationRecords.size()-1; i ++) {
            for (int j = i+1; j < patientImmunizationRecords.size(); j ++) {
                switch (stepOne.isPotentialDuplicate(patientImmunizationRecords.get(i), patientImmunizationRecords.get(j))) {
                    case SAME_EVENT:
                        results.get(i).set(j, ComparisonResult.EQUAL);
                        results.get(j).set(i, ComparisonResult.EQUAL);
                        break;
                    case POTENTIAL_DUPLICATE:
                        ComparisonResult result = comparer.compare(patientImmunizationRecords.get(i), patientImmunizationRecords.get(j));
                        results.get(i).set(j, result);
                        results.get(j).set(i, result);
                        break;
                    case NOT_POTENTIAL_DUPLICATE:
                        results.get(i).set(j, ComparisonResult.DIFFERENT);
                        results.get(j).set(i, ComparisonResult.DIFFERENT);
                }
            }
        }

        return postprocessing(patientImmunizationRecords, results);
    }
}