package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.DeterministicResult;
import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.Immunization.SOURCE;
import org.immregistries.vaccination_deduplication.Result;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
/**
 * Execute Step 2 : Evaluation phase using the Deterministic scoring approach
 *
 */
public class Deterministic {

	public Deterministic() {

	}
	final long dateWindow = 23l ;
	/**
	 * Allows to know if two records have to be deduplicated according to the deterministic approach
	 * 
	 * @param immunization1 and
	 * @param immunization2 are the two record to compare to each other
	 * @return the deterministic scoring outcome which can be "equal", "unsure", or different
	 */
	// TODO change name
	public Result score(Immunization immunization1, Immunization immunization2) {
		DeterministicResult lotNumberResult ; 
		DeterministicResult CVXResult; 
		DeterministicResult tradeNameResult;
		DeterministicResult dateResult; 
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
		organizationIdResult = compareArgument(immunization1.getOrganisationID(), immunization2.getOrganisationID());
		CVXResult = compareArgument(immunization1.getCVX(), immunization2.getCVX());
		lotNumberResult = compareArgument(immunization1.getLotNumber(), immunization2.getLotNumber());
		tradeNameResult = compareArgument(immunization1.getTradeName(), immunization2.getTradeName());
		if ((immunization1.getSource()== null && immunization2.getSource() == null ))
		{
			sourceResult = DeterministicResult.NEITHER;
		}
		else if ((immunization1.getSource() == null ||immunization2.getSource() == null)) 
		{
			sourceResult = DeterministicResult.ONLYONE;
		}
		else if(immunization1.getSource().equals(immunization2.getSource()))
		{
			sourceResult = DeterministicResult.SAME;
		}
		else 
		{
	sourceResult	= DeterministicResult.DIFFERENT;
		}
		return sumResult(immunization1,immunization2,sourceResult,lotNumberResult,CVXResult,organizationIdResult ,tradeNameResult,dateResult);
			

	}
	public DeterministicResult compareArgument(String s1,String s2)
	{		
		DeterministicResult result ; 
		if ((s1==null) && (s2==null))
		{
			result = DeterministicResult.NEITHER;
		}
		else if ((s1==null) || (s2==null) )
		{
			result=DeterministicResult.ONLYONE;
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
	//Step 2 Evaluation phase
	public Result sumResult(Immunization immunization1,Immunization immunization2,DeterministicResult sourceResult,DeterministicResult lotNumberResult,DeterministicResult vaccineResult,DeterministicResult organizationIdResult ,DeterministicResult tradeNameResult,DeterministicResult dateResult)
	{
		Boolean likelyDifferentSource=false;
		Boolean likelyMatchSource=false;

		int likelyMatch = 0; 
		int likelyDifferent = 0 ; 
		int noOutcome = 0; 
		//Lot Numbers rule 
		if (lotNumberResult==DeterministicResult.DIFFERENT)
		{
			likelyDifferent++;
		}
		else {noOutcome++;}	
		//Date
		if(dateResult==DeterministicResult.SAME)
		{
			likelyMatch++;
		}
		else {noOutcome++;}
		//Distintive variables 
		if(lotNumberResult==DeterministicResult.SAME && vaccineResult==DeterministicResult.SAME && tradeNameResult==DeterministicResult.SAME && organizationIdResult==DeterministicResult.SAME)
		{																																																																																																																																																																																																																																																																									{
			likelyMatch++;
		}}
		else if(lotNumberResult == DeterministicResult.ONLYONE && dateResult == DeterministicResult.SAME && vaccineResult == DeterministicResult.DIFFERENT && tradeNameResult == DeterministicResult.NEITHER && organizationIdResult == DeterministicResult.DIFFERENT)
		{
			likelyMatch++;
		}
		else if(lotNumberResult == DeterministicResult.DIFFERENT && dateResult == DeterministicResult.SAME && organizationIdResult == DeterministicResult.SAME )	
		{
			likelyDifferent++;
		}
		else if(lotNumberResult == DeterministicResult.DIFFERENT && dateResult == DeterministicResult.DIFFERENT)
		{
			noOutcome++ ;
		}
		//Different Submitting Providers
		if( organizationIdResult != DeterministicResult.SAME && immunization1.getSource() == SOURCE.SOURCE && immunization2.getSource() == SOURCE.SOURCE) 
		{
			likelyDifferent++; 
			likelyDifferentSource = true ;
		}
		else if(organizationIdResult != DeterministicResult.SAME && sourceResult == DeterministicResult.DIFFERENT)
		{
			likelyMatch++;
			likelyMatchSource = true ; 
		}
		else
		{
			noOutcome++ ;
		}
		
		if(likelyMatch>	likelyDifferent || (likelyMatch == likelyDifferent && likelyDifferentSource == true	))
		{
			return Result.DIFFERENT; 
		}
		else if(likelyDifferent>likelyMatch || (likelyMatch == likelyDifferent && likelyMatchSource == true	))
		{
			return Result.EQUAL;
		}
		else
		{
			return Result.UNSURE;
		}

	}
}


