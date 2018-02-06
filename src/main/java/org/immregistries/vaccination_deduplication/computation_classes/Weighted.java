package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.ImmunizationSource;
import org.immregistries.vaccination_deduplication.PropertyLoader;
import org.immregistries.vaccination_deduplication.ComparisonResult;

import java.util.ArrayList;
import java.util.HashMap;

import static org.immregistries.vaccination_deduplication.utils.Matching.compareForWeighted;

/**
 * Execute Step 2 : Evaluation phase using the weighted scoring approach
 *
 */
public class Weighted implements Comparer {

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
    public ComparisonResult score(Immunization immunization1, Immunization immunization2, double minThreshold, double maxThreshold) {
        double score=0;

        // CVX
        score += compareForWeighted(
                immunization1.getCVX(),
                immunization2.getCVX(),
                parameters.get(PropertyLoader.WEIGHT_SAME_CVX),
                parameters.get(PropertyLoader.WEIGHT_ABSENT_CVX),
                parameters.get(PropertyLoader.WEIGHT_DIFFERENT_CVX)
        );

        // MVX
        score += compareForWeighted(
                immunization1.getMVX(),
                immunization2.getMVX(),
                parameters.get(PropertyLoader.WEIGHT_SAME_MVX),
                parameters.get(PropertyLoader.WEIGHT_ABSENT_MVX),
                parameters.get(PropertyLoader.WEIGHT_DIFFERENT_MVX)
        );

        // Product Code
        score += compareForWeighted(
                immunization1.getProductCode(),
                immunization2.getProductCode(),
                parameters.get(PropertyLoader.WEIGHT_SAME_PRODUCT_CODE),
                parameters.get(PropertyLoader.WEIGHT_ABSENT_PRODUCT_CODE),
                parameters.get(PropertyLoader.WEIGHT_DIFFERENT_PRODUCT_CODE)
        );

    	// Lot Number
        score += compareForWeighted(
                immunization1.getLotNumber(),
                immunization2.getLotNumber(),
                parameters.get(PropertyLoader.WEIGHT_SAME_LOT_NUMBER),
                parameters.get(PropertyLoader.WEIGHT_ABSENT_LOT_NUMBER),
                parameters.get(PropertyLoader.WEIGHT_DIFFERENT_LOT_NUMBER)
        );

        // Date administered
        // TODO review calculations
        int dateDifferenceInDays = (int) ((immunization1.getDate().getTime() - immunization2.getDate().getTime()) / (24*60*60*1000));
        if (dateDifferenceInDays >= dateDifferenceWeight.size()) {
            dateDifferenceInDays = dateDifferenceWeight.size()-1;
        }
        if (dateDifferenceInDays < 0) {
            dateDifferenceInDays = 0;
        }
        score+=dateDifferenceWeight.get(dateDifferenceInDays);
        
        // Provider Organization
        score += compareForWeighted(
                immunization1.getOrganisationID(),
                immunization2.getOrganisationID(),
                parameters.get(PropertyLoader.WEIGHT_SAME_ORGANISATION_ID),
                parameters.get(PropertyLoader.WEIGHT_ABSENT_ORGANISATION_ID),
                parameters.get(PropertyLoader.WEIGHT_DIFFERENT_ORGANISATION_ID));

        // Source
        if (!(immunization1.getSource() == null && immunization2.getSource() == null)){
            if (immunization1.getSource() == ImmunizationSource.SOURCE && immunization2.getSource() == ImmunizationSource.SOURCE) {
                score+=parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_ADMIN);
            } else if (immunization1.getSource() == ImmunizationSource.HISTORICAL && immunization2.getSource() == ImmunizationSource.HISTORICAL) {
                score+=parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_HISTORICAL);
            } else {
                score+=parameters.get(PropertyLoader.WEIGHT_DIFFERENT_SOURCE);
            }
        }
        else{score+=parameters.get(PropertyLoader.WEIGHT_ABSENT_SOURCE);}


        double balancedScore = (score - this.Smin) / this.Smax;

        if (balancedScore > maxThreshold) {
            return ComparisonResult.EQUAL;
        } else if (balancedScore < minThreshold) {
            return ComparisonResult.DIFFERENT;
        } else {
            return ComparisonResult.UNSURE;
        }
    }

	/**
	 * Allows to know if two records have to be deduplicated according to the weighted approach using default thresholds if they aren't specified
	 * 
	 * @param immunization1 and immunization2 are the two record to compare to each other
	 * @return call the score method with default thresholds
	 */
    public ComparisonResult score(Immunization immunization1, Immunization immunization2) {
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
        double[] cvxWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_CVX),parameters.get(PropertyLoader.WEIGHT_DIFFERENT_CVX),parameters.get(PropertyLoader.WEIGHT_ABSENT_CVX)};
        double[] mvxWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_MVX),parameters.get(PropertyLoader.WEIGHT_DIFFERENT_MVX),parameters.get(PropertyLoader.WEIGHT_ABSENT_MVX)};
        double[] productCodeWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_PRODUCT_CODE),parameters.get(PropertyLoader.WEIGHT_DIFFERENT_PRODUCT_CODE),parameters.get(PropertyLoader.WEIGHT_ABSENT_PRODUCT_CODE)};
        double[] organisationIdWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_ORGANISATION_ID),parameters.get(PropertyLoader.WEIGHT_DIFFERENT_ORGANISATION_ID),parameters.get(PropertyLoader.WEIGHT_ABSENT_ORGANISATION_ID)};
        double[] sourceWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_ADMIN),parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_HISTORICAL),parameters.get(PropertyLoader.WEIGHT_DIFFERENT_SOURCE),parameters.get(PropertyLoader.WEIGHT_ABSENT_SOURCE)};


        this.Smin =
                getMinimum(lotNumberWeight) +
                getMinimum(dateDifferenceWeight) +
                getMinimum(cvxWeight) +
                getMinimum(mvxWeight) +
                getMinimum(productCodeWeight) +
                getMinimum(organisationIdWeight) +
                getMinimum(sourceWeight);
        this.Smax =
                getMaximum(lotNumberWeight) +
                getMaximum(dateDifferenceWeight) +
                getMaximum(cvxWeight) +
                getMaximum(mvxWeight) +
                getMaximum(productCodeWeight) +
                getMaximum(organisationIdWeight ) +
                getMaximum(sourceWeight);
    }
}
