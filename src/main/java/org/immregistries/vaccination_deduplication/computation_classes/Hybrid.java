package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Result;

public class Hybrid {
	public Hybrid() {}
	public Result score(Result DeterministicResult,Result WeightedResult)
	{
		if(DeterministicResult==Result.EQUAL&&WeightedResult==Result.EQUAL)
		{
			return Result.EQUAL;
		}
		else if(DeterministicResult==Result.DIFFERENT&&WeightedResult==Result.DIFFERENT)
		{
			return Result.DIFFERENT;
		}
		else return Result.UNSURE;
	}
}
