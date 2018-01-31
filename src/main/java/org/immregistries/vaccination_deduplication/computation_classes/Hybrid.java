package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.Result;

public class Hybrid implements Comparer {
	public Hybrid() {}

	public Result score(Immunization immunization1, Immunization immunization2) {
		Deterministic deterministic = new Deterministic();
		Result deterministicResult = deterministic.score(immunization1, immunization2);

		Weighted weighted = new Weighted();
		Result weightedResult = weighted.score(immunization1, immunization2);

		return this.score(deterministicResult, weightedResult);
	}

	public Result score(Result deterministicResult,Result weightedResult)
	{
		if(deterministicResult==Result.EQUAL&&weightedResult==Result.EQUAL)
		{
			return Result.EQUAL;
		}
		else if(deterministicResult==Result.DIFFERENT&&weightedResult==Result.DIFFERENT)
		{
			return Result.DIFFERENT;
		}
		else return Result.UNSURE;
	}
}
