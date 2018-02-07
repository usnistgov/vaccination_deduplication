package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.ComparisonResult;
import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.ImmunizationSource;
import org.immregistries.vaccination_deduplication.PropertyLoader;

import java.util.ArrayList;
import java.util.HashMap;

import static org.immregistries.vaccination_deduplication.utils.Matching.compareForWeighted;

/**
 * This class contains all the methods necessary to apply the Weighted method for the second step of the deduplication process.
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
     * This method compares two records together to see if they are duplicates.
     * For this it will use the weights defined in the config file.
     *
     * @param immunization1 The first immunization record to compare.
     * @param immunization2 The second immunization record to compare.
     * @return The deterministic comparison outcome which can be {@link ComparisonResult#EQUAL}, {@link ComparisonResult#UNSURE}, or {@link ComparisonResult#DIFFERENT}.
     */
    public ComparisonResult compare(Immunization immunization1, Immunization immunization2) {
        double score = 0;

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
        int dateDifferenceInDays = (int) ((immunization1.getDate().getTime() - immunization2.getDate().getTime()) / (24 * 60 * 60 * 1000));
        if (dateDifferenceInDays < 0) {
            dateDifferenceInDays = -dateDifferenceInDays;
        }
        if (dateDifferenceInDays >= dateDifferenceWeight.size()) {
            dateDifferenceInDays = dateDifferenceWeight.size() - 1;
        }
        score += dateDifferenceWeight.get(dateDifferenceInDays);

        // Provider Organization
        score += compareForWeighted(
                immunization1.getOrganisationID(),
                immunization2.getOrganisationID(),
                parameters.get(PropertyLoader.WEIGHT_SAME_ORGANISATION_ID),
                parameters.get(PropertyLoader.WEIGHT_ABSENT_ORGANISATION_ID),
                parameters.get(PropertyLoader.WEIGHT_DIFFERENT_ORGANISATION_ID));

        // Source
        if (!(immunization1.getSource() == null && immunization2.getSource() == null)) {
            if (immunization1.getSource() == ImmunizationSource.SOURCE && immunization2.getSource() == ImmunizationSource.SOURCE) {
                score += parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_ADMIN);
            } else if (immunization1.getSource() == ImmunizationSource.HISTORICAL && immunization2.getSource() == ImmunizationSource.HISTORICAL) {
                score += parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_HISTORICAL);
            } else {
                score += parameters.get(PropertyLoader.WEIGHT_DIFFERENT_SOURCE);
            }
        } else {
            score += parameters.get(PropertyLoader.WEIGHT_ABSENT_SOURCE);
        }

        double balancedScore = (score - this.Smin) / this.Smax;

        if (balancedScore > parameters.get(PropertyLoader.WEIGHT_MAX_THRESHOLD)) {
            return ComparisonResult.EQUAL;
        } else if (balancedScore < parameters.get(PropertyLoader.WEIGHT_MIN_THRESHOLD)) {
            return ComparisonResult.DIFFERENT;
        } else {
            return ComparisonResult.UNSURE;
        }
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

    /**
     * This method will update Smin and Smax, being respectively the minimal score and the maximal score.
     * For this it will sum all the possible lowest weights together and all the possible maximum weights together.
     *
     * Smin and Smax are then used to decide how a comparison score relates to the max and min thresholds.
     */
    private void updateSminAndSmax() {
        double[] lotNumberWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_LOT_NUMBER), parameters.get(PropertyLoader.WEIGHT_DIFFERENT_LOT_NUMBER), parameters.get(PropertyLoader.WEIGHT_ABSENT_LOT_NUMBER)};
        double[] cvxWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_CVX), parameters.get(PropertyLoader.WEIGHT_DIFFERENT_CVX), parameters.get(PropertyLoader.WEIGHT_ABSENT_CVX)};
        double[] mvxWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_MVX), parameters.get(PropertyLoader.WEIGHT_DIFFERENT_MVX), parameters.get(PropertyLoader.WEIGHT_ABSENT_MVX)};
        double[] productCodeWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_PRODUCT_CODE), parameters.get(PropertyLoader.WEIGHT_DIFFERENT_PRODUCT_CODE), parameters.get(PropertyLoader.WEIGHT_ABSENT_PRODUCT_CODE)};
        double[] organisationIdWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_ORGANISATION_ID), parameters.get(PropertyLoader.WEIGHT_DIFFERENT_ORGANISATION_ID), parameters.get(PropertyLoader.WEIGHT_ABSENT_ORGANISATION_ID)};
        double[] sourceWeight = new double[]{parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_ADMIN), parameters.get(PropertyLoader.WEIGHT_SAME_SOURCE_HISTORICAL), parameters.get(PropertyLoader.WEIGHT_DIFFERENT_SOURCE), parameters.get(PropertyLoader.WEIGHT_ABSENT_SOURCE)};


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
                        getMaximum(organisationIdWeight) +
                        getMaximum(sourceWeight);
    }
}
