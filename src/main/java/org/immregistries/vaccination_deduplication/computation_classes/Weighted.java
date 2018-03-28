package org.immregistries.vaccination_deduplication.computation_classes;

import static org.immregistries.vaccination_deduplication.utils.Matching.compareForWeighted;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.VaccinationDeduplicationParameters;
import org.immregistries.vaccination_deduplication.reference.ComparisonResult;
import org.immregistries.vaccination_deduplication.reference.ImmunizationSource;

/**
 * This class contains all the methods necessary to apply the Weighted method for the second step of the deduplication process.
 */
public class Weighted implements Comparer {

    private static Logger logger = Logger.getLogger(Weighted.class.getName());

    private VaccinationDeduplicationParameters parameters;

    private double Smin;
    private double Smax;

    // Constructor
    public Weighted(VaccinationDeduplicationParameters parameters) {
        this.parameters = parameters;

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

        double score = getScore(immunization1, immunization2);

        double balancedScore = getBalancedScore(score);

        if (balancedScore > parameters.getWeightMaxThreshold()) {
            return ComparisonResult.EQUAL;
        } else if (balancedScore < parameters.getWeightMinThreshold()) {
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
        double[] lotNumberWeight = new double[]{parameters.getWeightSameLotNumber(), parameters.getWeightDifferentLotNumber(), parameters.getWeightAbsentLotNumber()};
        double[] cvxWeight = new double[]{parameters.getWeightSameCVX(), parameters.getWeightDifferentCVX(), parameters.getWeightAbsentCVX()};
        //double[] mvxWeight = new double[]{parameters.getWeightSameMVX(), parameters.getWeightDifferentMVX(), parameters.getWeightAbsentMVX()};
        double[] productCodeWeight = new double[]{parameters.getWeightSameProductCode(), parameters.getWeightDifferentProductCode(), parameters.getWeightAbsentProductCode()};
        double[] organisationIdWeight = new double[]{parameters.getWeightSameOrganisationID(), parameters.getWeightDifferentOrganisationID(), parameters.getWeightAbsentOrganisationID()};
        double[] sourceWeight = new double[]{parameters.getWeightSameSourceAdmin(), parameters.getWeightSameSourceHistorical(), parameters.getWeightDifferentSource(), parameters.getWeightAbsentSource()};


        this.Smin =
                getMinimum(lotNumberWeight) +
                        getMinimum(parameters.getWeightDateDifference()) +
                        getMinimum(cvxWeight) +
                        // getMinimum(mvxWeight) +
                        getMinimum(productCodeWeight) +
                        getMinimum(organisationIdWeight) +
                        getMinimum(sourceWeight);
        this.Smax =
                getMaximum(lotNumberWeight) +
                        getMaximum(parameters.getWeightDateDifference()) +
                        getMaximum(cvxWeight) +
                        // getMaximum(mvxWeight) +
                        getMaximum(productCodeWeight) +
                        getMaximum(organisationIdWeight) +
                        getMaximum(sourceWeight);
    }

    public double getScore(Immunization immunization1,
            Immunization immunization2) {

        logger.debug(StringUtils.center(" Start score calculation ", 36, '-'));

        double score = 0;

        // CVX
        double weight = compareForWeighted(immunization1.getCVX(),
                immunization2.getCVX(),
                parameters.getWeightSameCVX(),
                parameters.getWeightAbsentCVX(),
                parameters.getWeightDifferentCVX());
        score += weight;
        logger.debug(
                StringUtils.rightPad("[Vaccine type / CVX]", 30)
                        + StringUtils.leftPad(Double.toString(weight), 6));

        /*
         * // MVX score += compareForWeighted( immunization1.getMVX(),
         * immunization2.getMVX(),
         * parameters.get(PropertyLoader.WEIGHT_SAME_MVX),
         * parameters.get(PropertyLoader.WEIGHT_ABSENT_MVX),
         * parameters.get(PropertyLoader.WEIGHT_DIFFERENT_MVX) );
         */

        // Product Code
        weight = compareForWeighted(immunization1.getProductCode(),
                immunization2.getProductCode(),
                parameters.getWeightSameProductCode(),
                parameters.getWeightAbsentProductCode(),
                parameters.getWeightDifferentProductCode());
        score += weight;
        logger.debug(StringUtils.rightPad("[Trade Name / Product Code]", 30)
                + StringUtils.leftPad(Double.toString(weight), 6));

        // Lot Number
        weight = compareForWeighted(immunization1.getLotNumber(),
                immunization2.getLotNumber(),
                parameters.getWeightSameLotNumber(),
                parameters.getWeightAbsentLotNumber(),
                parameters.getWeightDifferentLotNumber());
        score += weight;
        logger.debug(StringUtils.rightPad("[Lot Number]", 30)
                + StringUtils.leftPad(Double.toString(weight), 6));

        // Date administered
        int dateDifferenceInDays = (int) ((immunization1.getDate().getTime()
                - immunization2.getDate().getTime()) / (24 * 60 * 60 * 1000));
        if (dateDifferenceInDays < 0) {
            dateDifferenceInDays = -dateDifferenceInDays;
        }
        if (dateDifferenceInDays >= parameters.getWeightDateDifference().size()) {
            dateDifferenceInDays = parameters.getWeightDateDifference().size() - 1;
        }
        weight = parameters.getWeightDateDifference().get(dateDifferenceInDays);
        score += weight;
        logger.debug(
                StringUtils.rightPad("[Date administered]", 30)
                        + StringUtils.leftPad(Double.toString(weight), 6));

        // Provider Organization
        weight = compareForWeighted(immunization1.getOrganisationID(),
                immunization2.getOrganisationID(),
                parameters.getWeightSameOrganisationID(),
                parameters.getWeightAbsentOrganisationID(),
                parameters.getWeightDifferentOrganisationID());
        score += weight;
        logger.debug(
                StringUtils.rightPad("[Provider Organization]", 30)
                        + StringUtils.leftPad(Double.toString(weight), 6));

        // Source
		if (immunization1.getSource() == null || immunization2.getSource() == null) {
            weight = parameters.getWeightAbsentSource();
        } else {
			if (immunization1.getSource() == ImmunizationSource.SOURCE
					&& immunization2.getSource() == ImmunizationSource.SOURCE) {
				weight = parameters.getWeightSameSourceAdmin();
			} else if (immunization1.getSource() == ImmunizationSource.HISTORICAL
					&& immunization2.getSource() == ImmunizationSource.HISTORICAL) {
				weight = parameters.getWeightSameSourceHistorical();
			} else {
				weight = parameters.getWeightDifferentSource();
			}
		}
        score += weight;
        logger.debug(StringUtils.rightPad("[Source]", 30)
                + StringUtils.leftPad(Double.toString(weight), 6));

        logger.debug(StringUtils.rightPad("[Score]", 30)
                + StringUtils.leftPad(Double.toString(score), 6));
        logger.debug(StringUtils.repeat('-', 36));
        return score;
    }

    public double getBalancedScore(double score) {
		double balancedScore = (score - this.Smin) / (this.Smax - this.Smin);
		double roundValue = Math.round(balancedScore * 1000.0) / 1000.0;
		logger.debug(
				StringUtils.rightPad("[Balanced Score]", 30) + StringUtils.leftPad(Double.toString(roundValue), 6));
		logger.debug(StringUtils.repeat('-', 36));
		return balancedScore;
    }
}
