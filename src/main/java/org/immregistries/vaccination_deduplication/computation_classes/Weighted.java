package org.immregistries.vaccination_deduplication.computation_classes;


import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.PropertyLoader;
import org.immregistries.vaccination_deduplication.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.immregistries.vaccination_deduplication.utils.Matching.compareForWeighted;
import static org.immregistries.vaccination_deduplication.utils.Matching.isThereAMatch;

/**
 * Execute Step 2 : Evaluation phase using the weighted scoring approach
 *
 */
public class Weighted {

    // Thresholds allow to take a decision. They are compared to the weighted score
    private HashMap<String, Double> parameters;
    private ArrayList<Double> dateDifferenceWeight;

    private double Smin;
    private double Smax;

    // Constructor
    public Weighted() {
        PropertyLoader propertyLoader = PropertyLoader.getInstance();
        this.parameters = propertyLoader.getWeightedParameters();
        this.dateDifferenceWeight = propertyLoader.getWeightDateDifferences();

        updateSminAndSmax();
    }

	/**
	 * Allows to know if two records have to be deduplicated according to the weighted approach
	 * 
	 * @param immunization1 and immunization2 are the two record to compare to each other
	 * @param minThreshold and maxThreshold correspond to the weight scores used to determine the outcome
	 * @return the weighted scoring outcome which can be "equal" (case : maxThreshold &lt; score), "unsure" (case : minThreshold &lt; score &lt; maxThreshold), or different (case : score &lt; minThreshold)
	 */
	// TODO change name
    public Result score(Immunization immunization1, Immunization immunization2, double minThreshold, double maxThreshold) {
        double score=0;

        // TODO add CVX, MVX, ProductCode

    	// Lot Number
        score += compareForWeighted(
                immunization1.getLotNumber(),
                immunization2.getLotNumber(),
                parameters.get(PropertyLoader.WEIGHT_SAME_LOT_NUMBER),
                parameters.get(PropertyLoader.WEIGHT_ABSENT_LOT_NUMBER),
                parameters.get(PropertyLoader.WEIGHT_DIFFERENT_LOT_NUMBER)
        );

        // Date administered
        int dateDifferenceInDays = (int) ((immunization1.getDate().getTime() - immunization2.getDate().getTime()) / (24*60*60*1000));
        System.out.println(dateDifferenceInDays);
        if (dateDifferenceInDays > dateDifferenceWeight.size()) {
            dateDifferenceInDays = dateDifferenceWeight.size();
        }
        System.out.println(dateDifferenceInDays);
        score+=dateDifferenceWeight.get(dateDifferenceInDays-1);

        // Vaccine Group
        if (!(immunization1.getVaccineGroupList().isEmpty() || immunization2.getVaccineGroupList().isEmpty())){
            List<String> immunization1GroupList = immunization1.getVaccineGroupList();
            List<String> immunization2GroupList = immunization2.getVaccineGroupList();

        	if (isThereAMatch(immunization1GroupList, immunization2GroupList)) {
        	    score+=parameters.get(PropertyLoader.WEIGHT_SAME_VACCINE_FAMILY);
        	} else {
        	    score+=parameters.get(PropertyLoader.WEIGHT_DIFFERENT_VACCINE_FAMILY);
        	}
        }
        else{score+=parameters.get(PropertyLoader.WEIGHT_ABSENT_VACCINE_FAMILY);}
        
        // Provider Organization
        score += compareForWeighted(
                immunization1.getOrganisationID(),
                immunization2.getOrganisationID(),
                parameters.get(PropertyLoader.WEIGHT_SAME_ORGANISATION_ID),
                parameters.get(PropertyLoader.WEIGHT_ABSENT_ORGANISATION_ID),
                parameters.get(PropertyLoader.WEIGHT_DIFFERENT_ORGANISATION_ID));

        // Source
        if (!(immunization1.getSource() == null && immunization2.getSource() == null)){
            if (immunization1.getSource() == Immunization.SOURCE.SOURCE && immunization2.getSource() == Immunization.SOURCE.SOURCE) {
                score+=parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_ADMIN);
            } else if (immunization1.getSource() == Immunization.SOURCE.HISTORICAL && immunization2.getSource() == Immunization.SOURCE.HISTORICAL) {
                score+=parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_HISTORICAL);
            } else {
                score+=parameters.get(PropertyLoader.WEIGHT_DIFFERENT_SOURCE);
            }
        }
        else{score+=parameters.get(PropertyLoader.WEIGHT_ABSENT_SOURCE);}


        double balancedScore = (score - this.Smin) / this.Smax;

        if (balancedScore > maxThreshold) {
            return Result.EQUAL;
        } else if (balancedScore < minThreshold) {
            return Result.DIFFERENT;
        } else {
            return Result.UNSURE;
        }
    }

	/**
	 * Allows to know if two records have to be deduplicated according to the weighted approach using default thresholds if they aren't specified
	 * 
	 * @param immunization1 and immunization2 are the two record to compare to each other
	 * @return call the score method with default thresholds
	 */
    public Result score(Immunization immunization1, Immunization immunization2) {
        return score(immunization1, immunization2, parameters.get(PropertyLoader.WEIGHT_MIN_THRESHOLD), parameters.get(PropertyLoader.WEIGHT_MAX_THRESHOLD));
    }

    private double getMinimum(double[] array) {
        double min = Integer.MAX_VALUE;
        for (double anArray : array)
            if (anArray < min)
                min = anArray;
        return min;
    }

    private double getMaximum(double[] array) {
        double max = Integer.MIN_VALUE;
        for (double anArray : array)
            if (anArray > max)
                max = anArray;
        return max;
    }

    private double getMinimum(ArrayList<Double> array) {
        double min = Integer.MAX_VALUE;
        for (Double anArray : array)
            if (anArray < min)
                min = anArray;
        return min;
    }

    private double getMaximum(ArrayList<Double> array) {
        double max = Integer.MIN_VALUE;
        for (Double anArray : array)
            if (anArray > max)
                max = anArray;
        return max;
    }

    private void updateSminAndSmax() {
        double[] lotNumberWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_LOT_NUMBER),parameters.get(PropertyLoader.WEIGHT_DIFFERENT_LOT_NUMBER),parameters.get(PropertyLoader.WEIGHT_ABSENT_LOT_NUMBER)};
        double[] vaccineFamilyWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_VACCINE_FAMILY),parameters.get(PropertyLoader.WEIGHT_DIFFERENT_VACCINE_FAMILY),parameters.get(PropertyLoader.WEIGHT_ABSENT_VACCINE_FAMILY)};
        double[] organisationIdWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_ORGANISATION_ID),parameters.get(PropertyLoader.WEIGHT_DIFFERENT_ORGANISATION_ID),parameters.get(PropertyLoader.WEIGHT_ABSENT_ORGANISATION_ID)};
        double[] sourceWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_ADMIN),parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_HISTORICAL),parameters.get(PropertyLoader.WEIGHT_DIFFERENT_SOURCE),parameters.get(PropertyLoader.WEIGHT_ABSENT_SOURCE)};


        this.Smin =
                getMinimum(lotNumberWeight) +
                getMinimum(dateDifferenceWeight) +
                getMinimum(vaccineFamilyWeight) +
                getMinimum(organisationIdWeight) +
                getMinimum(sourceWeight);
        this.Smax =
                getMaximum(lotNumberWeight) +
                getMaximum(dateDifferenceWeight) +
                getMaximum(vaccineFamilyWeight) +
                getMaximum(organisationIdWeight ) +
                getMaximum(sourceWeight);
    }
}
