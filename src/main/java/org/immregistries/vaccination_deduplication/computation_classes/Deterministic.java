package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
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
	
	/**
	 * Allows to know if two records have to be deduplicated according to the deterministic approach
	 * 
	 * @param immunization1 and
	 * @param immunization2 are the two record to compare to each other
	 * @return the deterministic scoring outcome which can be "equal", "unsure", or different
	 */
	// TODO change name
	public Result score(Immunization immunization1, Immunization immunization2) {
		Result lotresult ; 
		long duration ; 
		Result vaccineresult; 
		Result vaccinetyperesult;
		Result tradenameresult;
		if (immunization1.getLotNumber().equals(immunization2.getLotNumber()))
		{
			lotresult = Result.EQUAL;
		}
		else if (immunization1.getLotNumber()==null && immunization2.getLotNumber()==null)
		{
			lotresult=Result.DIFFERENT;
		}
		else if (immunization1.getLotNumber()==null && immunization2.getLotNumber()!=null)
		{
			lotresult=Result.UNSURE;
		}
		else if (immunization1.getLotNumber()!=null && immunization2.getLotNumber()==null)
		{
			lotresult=Result.UNSURE;
		}
		LocalDate date1 = immunization1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date2 = immunization2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		duration= ChronoUnit.DAYS.between(date1,date2);
		if (immunization1.getVaccineCode().equals(immunization2.getVaccineCode()))
		{
			vaccineresult=Result.EQUAL;
		}
		else if (immunization1.getVaccineCode()==null && immunization2.getVaccineCode()==null)
		{
			vaccineresult=Result.DIFFERENT;
		}
		else if (immunization1.getVaccineCode()==null && immunization2.getVaccineCode()!=null)
		{
			vaccineresult=Result.UNSURE;
		}
		else if (immunization1.getVaccineCode()!=null && immunization2.getVaccineCode()==null)
		{
			vaccineresult=Result.UNSURE;
		}
		
		
		// 
		if (immunization1.getVaccineType().equals(immunization2.getVaccineType()))
		{
			vaccinetyperesult=Result.EQUAL;
		}
		else if (immunization1.getVaccineType()==null && immunization2.getVaccineType()==null)
		{
			vaccinetyperesult=Result.DIFFERENT;
		}
		else if (immunization1.getVaccineType()==null && immunization2.getVaccineType()!=null)
		{
			vaccinetyperesult=Result.UNSURE;
		}
		else if (immunization1.getVaccineType()!=null && immunization2.getVaccineType()==null)
		{
			vaccinetyperesult=Result.UNSURE;
		}
		
		if (immunization1.getVaccineType().equals(immunization2.getVaccineType()))
		{
			vaccinetyperesult=Result.EQUAL;
		}
		else if (immunization1.getVaccineType()==null && immunization2.getVaccineType()==null)
		{
			vaccinetyperesult=Result.DIFFERENT;
		}
		else if (immunization1.getVaccineType()==null && immunization2.getVaccineType()!=null)
		{
			vaccinetyperesult=Result.UNSURE;
		}
		else if (immunization1.getVaccineType()!=null && immunization2.getVaccineType()==null)
		{
			vaccinetyperesult=Result.UNSURE;
		}
		
		if (immunization1.getVaccineType().equals(immunization2.getVaccineType()))
		{
			vaccinetyperesult=Result.EQUAL;
		}
		else if (immunization1.getVaccineType()==null && immunization2.getVaccineType()==null)
		{
			vaccinetyperesult=Result.DIFFERENT;
		}
		else if (immunization1.getVaccineType()==null && immunization2.getVaccineType()!=null)
		{
			vaccinetyperesult=Result.UNSURE;
		}
		else if (immunization1.getVaccineType()!=null && immunization2.getVaccineType()==null)
		{
			vaccinetyperesult=Result.UNSURE;
		}
		
		
		if (immunization1.getTradeName().equals(immunization2.getTradeName()))
		{
			vaccinetyperesult=Result.EQUAL;
		}
		else if (immunization1.getTradeName()==null && immunization2.getTradeName()==null)
		{
			vaccinetyperesult=Result.DIFFERENT;
		}
		else if (immunization1.getTradeName()==null && immunization2.getTradeName()!=null)
		{
			vaccinetyperesult=Result.UNSURE;
		}
		else if (immunization1.getTradeName()!=null && immunization2.getTradeName()==null)
		{
			vaccinetyperesult=Result.UNSURE;
		}
		
		return Result.UNSURE;
	}
}
