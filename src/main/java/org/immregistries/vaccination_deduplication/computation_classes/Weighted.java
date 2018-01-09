package org.immregistries.vaccination_deduplication.computation_classes;


import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.PropertyLoader;
import org.immregistries.vaccination_deduplication.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
        int score=0;
    	
    	// Lot Number
        if (!(immunization1.getLotNumber().isEmpty() || immunization2.getLotNumber().isEmpty())){
        	if (immunization1.getLotNumber().equals(immunization2.getLotNumber())) {score+=parameters.get(PropertyLoader.WEIGHT_SAME_LOT_NUMBER);} // Present and same
        	else {score+=parameters.get(PropertyLoader.WEIGHT_DIFFERENT_LOT_NUMBER);} // Present and different
        }
        else{score+=parameters.get(PropertyLoader.WEIGHT_ABSENT_LOT_NUMBER);} // Absent one or both

        // Date administered
        int dateDifferenceInDays = (int) (immunization1.getDate().getTime() - immunization2.getDate().getTime() / (24*60*60*1000));
        if (dateDifferenceInDays > dateDifferenceWeight.size()) {
            dateDifferenceInDays = dateDifferenceWeight.size();
        }
        score+=dateDifferenceWeight.get(dateDifferenceInDays-1);

        // Vaccine Type
        if (!(immunization1.getVaccineCode().isEmpty() || immunization2.getVaccineCode().isEmpty())){
        	if (immunization1.getVaccineCode().equals(immunization2.getVaccineCode())) {score+=parameters.get(PropertyLoader.WEIGHT_SAME_VACCINE_FAMILY);}
        	else {score+=parameters.get(PropertyLoader.WEIGHT_DIFFERENT_VACCINE_FAMILY);}
        }
        else{score+=parameters.get(PropertyLoader.WEIGHT_ABSENT_VACCINE_FAMILY);}
        
        // Provider Organization
        if (!(immunization1.getOrganisationID().isEmpty() || immunization2.getOrganisationID().isEmpty())){
        	if (immunization1.getOrganisationID().equals(immunization2.getOrganisationID())) {score+=parameters.get(PropertyLoader.WEIGHT_SAME_ORGANISATION_ID);}
        	else {score+=parameters.get(PropertyLoader.WEIGHT_DIFFERENT_ORGANISATION_ID);}
        }
        else{score+=parameters.get(PropertyLoader.WEIGHT_ABSENT_ORGANISATION_ID);}

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
        for (int i = 0; i < array.length; i++)
            if (array[i] < min)
                min = array[i];
        return min;
    }

    private double getMaximum(double[] array) {
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++)
            if (array[i] > max)
                max = array[i];
        return max;
    }

    private double getMinimum(ArrayList<Double> array) {
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < array.size(); i++)
            if (array.get(i) < min)
                min = array.get(i);
        return min;
    }

    private double getMaximum(ArrayList<Double> array) {
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < array.size(); i++)
            if (array.get(i) > max)
                max = array.get(i);
        return max;
    }

    public void updateSminAndSmax() {
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
