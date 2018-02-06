package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.ComparisonResult;

/**
 * 
 * Hybrid method, uses Deterministic and Weighted classes
 * Uses a majority vote between deterministic and weighted in order to give another result
 */
public class Hybrid implements Comparer {
	public Hybrid() {}

	public ComparisonResult compare(Immunization immunization1, Immunization immunization2) {
		Deterministic deterministic = new Deterministic();
		ComparisonResult deterministicResult = deterministic.compare(immunization1, immunization2);

		Weighted weighted = new Weighted();
		ComparisonResult weightedResult = weighted.compare(immunization1, immunization2);

		return this.compare(deterministicResult, weightedResult);
	}

	public ComparisonResult compare(ComparisonResult deterministicResult,ComparisonResult weightedResult)
	{
		if(deterministicResult==ComparisonResult.EQUAL&&weightedResult==ComparisonResult.EQUAL)
		{
			return ComparisonResult.EQUAL;
		}
		else if(deterministicResult==ComparisonResult.DIFFERENT&&weightedResult==ComparisonResult.DIFFERENT)
		{
			return ComparisonResult.DIFFERENT;
		}
		else return ComparisonResult.UNSURE;
	}
}
