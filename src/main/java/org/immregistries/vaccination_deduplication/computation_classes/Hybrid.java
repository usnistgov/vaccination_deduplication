package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.ComparisonResult;

public class Hybrid implements Comparer {
	public Hybrid() {}

	public ComparisonResult score(Immunization immunization1, Immunization immunization2) {
		Deterministic deterministic = new Deterministic();
		ComparisonResult deterministicResult = deterministic.score(immunization1, immunization2);

		Weighted weighted = new Weighted();
		ComparisonResult weightedResult = weighted.score(immunization1, immunization2);

		return this.score(deterministicResult, weightedResult);
	}

	public ComparisonResult score(ComparisonResult deterministicResult,ComparisonResult weightedResult)
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
