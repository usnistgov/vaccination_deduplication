package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.DeterministicResult;
import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.ImmunizationSource;
import org.immregistries.vaccination_deduplication.ComparisonResult;

/**
 * This class contains all the methods necessary to apply the Deterministic method for the second step of the deduplication process.
 */
public class Deterministic implements Comparer {

	public Deterministic() {}

	/**
	 * This method compares two records together to see if they are duplicates.
	 * For this it uses the business rules defined in the guide line document at <a href="http://www.immregistries.org/resources/AIRA-BP_guide_Vaccine_DeDup_120706.pdf">http://www.immregistries.org/resources/AIRA-BP_guide_Vaccine_DeDup_120706.pdf</a>
	 * 
	 * @param immunization1 The first immunization record to compare.
	 * @param immunization2 The second immunization record to compare.
	 * @return The deterministic comparison outcome which can be {@link ComparisonResult#EQUAL}, {@link ComparisonResult#UNSURE}, or {@link ComparisonResult#DIFFERENT}.
	 */
	public ComparisonResult compare(Immunization immunization1, Immunization immunization2) {
		DeterministicResult lotNumberResult ; 
		DeterministicResult dateResult; 
		DeterministicResult cvxResult; 
		DeterministicResult mvxResult;
		DeterministicResult organizationIdResult;
		DeterministicResult sourceResult;

		if(immunization1.getDate().compareTo(immunization2.getDate())==0)
		{
			dateResult = DeterministicResult.SAME;
		}
		else
		{
			dateResult = DeterministicResult.DIFFERENT; 
		}
		organizationIdResult = compareAttribute(immunization1.getOrganisationID(), immunization2.getOrganisationID());
		cvxResult = compareAttribute(immunization1.getCVX(), immunization2.getCVX());
		lotNumberResult = compareAttribute(immunization1.getLotNumber(), immunization2.getLotNumber());
		mvxResult = compareAttribute(immunization1.getMVX(), immunization2.getMVX());

		if ((immunization1.getSource()== null && immunization2.getSource() == null ))
		{
			sourceResult = DeterministicResult.NEITHER;
		}
		else if ((immunization1.getSource() == null ||immunization2.getSource() == null)) 
		{
			sourceResult = DeterministicResult.ONLY_ONE;
		}
		else if(immunization1.getSource().equals(immunization2.getSource()))
		{
			sourceResult = DeterministicResult.SAME;
		}
		else 
		{
			sourceResult = DeterministicResult.DIFFERENT;
		}
		return determineResult(immunization1,immunization2,sourceResult,lotNumberResult,cvxResult,organizationIdResult ,mvxResult,dateResult);
			

	}

	/**
	 * This method compares the 2 values of an attribute from 2 immunization together.
	 * @param s1 The first value.
	 * @param s2 The second value.
	 * @return The result of the comparison which can be {@link DeterministicResult#NEITHER}, {@link DeterministicResult#ONLY_ONE}, {@link DeterministicResult#SAME} OR {@link DeterministicResult#DIFFERENT}
	 */
	public DeterministicResult compareAttribute(String s1,String s2)
	{		
		DeterministicResult result ; 
		if ((s1==null || s1.equals("")) && (s2==null || s2.equals("")))
		{
			result = DeterministicResult.NEITHER;
		}
		else if (s1==null || s1.equals("") || s2==null || s2.equals(""))
		{
			result=DeterministicResult.ONLY_ONE;
		}
		else if(s1.equals(s2)){
			result = DeterministicResult.SAME;
		}
		else 
		{
			result = DeterministicResult.DIFFERENT;
		}
		return result ;

	}

	/**
	 * This method combines the results from all the attribute comparisons and some information from the 2 immunization records.
	 * Using this it determines the final result for the deterministic method.
	 * @param immunization1 The first immunization record to compare.
	 * @param immunization2 The second immunization record to compare.
	 * @param sourceResult The result from the comparison of the sources.
	 * @param lotNumberResult The result from the comparison of the lot numbers.
	 * @param cvxResult The result from the comparison of the CVXs.
	 * @param organizationIdResult The result from the comparison of the organization IDs.
	 * @param tradeNameResult The result from the comparison of the trade names.
	 * @param dateResult The result from the comparison of the administration dates
	 * @return The deterministic comparison outcome which can be {@link ComparisonResult#EQUAL}, {@link ComparisonResult#UNSURE}, or {@link ComparisonResult#DIFFERENT}.
	 */
	public ComparisonResult determineResult(
			Immunization immunization1,
			Immunization immunization2,
			DeterministicResult sourceResult,
			DeterministicResult lotNumberResult,
			DeterministicResult cvxResult,
			DeterministicResult organizationIdResult,
			DeterministicResult tradeNameResult,
			DeterministicResult dateResult) {

		Boolean likelyDifferentSource=false;
		Boolean likelyMatchSource=false;

		int likelyMatch = 0; 
		int likelyDifferent = 0 ; 
		/*
			Lot Numbers rule
			If vaccine lot numbers are different in evaluated records,
			these records are most likely to be different.
		*/
		if (lotNumberResult==DeterministicResult.DIFFERENT)
		{
			likelyDifferent++;
		}
		else {}

		/*
			Date rule
			If vaccination  encounter dates are the same in evaluated records,
			these records are most likely to be duplicates.
		*/
		if(dateResult==DeterministicResult.SAME)
		{
			likelyMatch++;
		}
		else {}

		/*
			Distinctive combinations of variables should be considered for the evaluation of candidates records.
		*/
		if(lotNumberResult==DeterministicResult.DIFFERENT && cvxResult==DeterministicResult.SAME && tradeNameResult==DeterministicResult.SAME && organizationIdResult==DeterministicResult.SAME)
		{																																																																																																																																																																																																																																																																									{
			likelyMatch++;
		}}
		else if(lotNumberResult == DeterministicResult.ONLY_ONE && dateResult == DeterministicResult.SAME && cvxResult == DeterministicResult.DIFFERENT && tradeNameResult == DeterministicResult.NEITHER && organizationIdResult == DeterministicResult.DIFFERENT)
		{
			likelyMatch++;
		}
		else if(lotNumberResult == DeterministicResult.DIFFERENT && dateResult == DeterministicResult.SAME && organizationIdResult == DeterministicResult.SAME )	
		{
			likelyDifferent++;
		}
		else if(lotNumberResult == DeterministicResult.DIFFERENT && dateResult == DeterministicResult.DIFFERENT)
		{
		}

		/*
		 	If Record Source Types are "Administered" in evaluated records and are from
		 	different providers, these records are most likely to be different (not duplicates).
		 	If Record Source Type is "Administered" in one record and "Historical" in another
		 	record and vaccination dates are close(P11),
		 	these records are most likely to be duplicates.
		*/
		if( organizationIdResult != DeterministicResult.SAME && immunization1.getSource() == ImmunizationSource.SOURCE && immunization2.getSource() == ImmunizationSource.SOURCE)
		{
			likelyDifferent++; 
			likelyDifferentSource = true;
		}
		else if(organizationIdResult != DeterministicResult.SAME && sourceResult == DeterministicResult.DIFFERENT)
		{
			likelyMatch++;
			likelyMatchSource = true;
		}
		else
		{
		}
		
		if(likelyMatch>	likelyDifferent || (likelyMatch == likelyDifferent && likelyDifferentSource))
		{
			return ComparisonResult.EQUAL;
		}
		else if(likelyDifferent>likelyMatch || (likelyMatch == likelyDifferent && likelyMatchSource))
		{
			return ComparisonResult.DIFFERENT;
		}
		else
		{
			return ComparisonResult.UNSURE;
		}

	}
}


