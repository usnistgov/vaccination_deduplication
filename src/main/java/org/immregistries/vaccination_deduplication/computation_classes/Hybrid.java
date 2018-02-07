package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.ComparisonResult;

/**
 * This class contains all the methods necessary to apply the hybrid method for the second step of the deduplication process.
 * The hybrid method takes the result of the Deterministic method and the result of the Weighted method and combines them into one result.
 */
public class Hybrid implements Comparer {
	public Hybrid() {}

	/**
	 * This method calls upon the Deterministic and Weighted comparison classes and methods then combines the 2 results together.
	 * @param immunization1 The first immunization record to compare.
	 * @param immunization2 The second immunization record to compare.
	 * @return The combined result from the Deterministic method and the Weighted method.
	 */
	public ComparisonResult compare(Immunization immunization1, Immunization immunization2) {
		Deterministic deterministic = new Deterministic();
		ComparisonResult deterministicResult = deterministic.compare(immunization1, immunization2);

		Weighted weighted = new Weighted();
		ComparisonResult weightedResult = weighted.compare(immunization1, immunization2);

		return this.compare(deterministicResult, weightedResult);
	}

	/**
	 * This method takes the results for the Deterministic method and from the Weighted method and combines them into one result.
	 * @param deterministicResult The result from the Deterministic method.
	 * @param weightedResult The result from the Weighted method.
	 * @return The combined result from the Deterministic method and the Weighted method.
	 */
	public ComparisonResult compare(ComparisonResult deterministicResult,ComparisonResult weightedResult) {
		int vote = 0;
		if (deterministicResult==ComparisonResult.EQUAL)
			vote += 1;

		if (weightedResult==ComparisonResult.EQUAL)
			vote += 1;

		if (deterministicResult==ComparisonResult.DIFFERENT)
			vote -= 1;

		if (weightedResult==ComparisonResult.DIFFERENT)
			vote -= 1;

		if (vote > 0) {
			return ComparisonResult.EQUAL;
		} else if( vote < 0) {
			return ComparisonResult.DIFFERENT;
		} else {
			return ComparisonResult.UNSURE;
		}
	}
}
