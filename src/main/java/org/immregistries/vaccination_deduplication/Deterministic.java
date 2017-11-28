package org.immregistries.vaccination_deduplication;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Deterministic {

	public enum RESULT {EQUAL, UNSURE, DIFFERENT}

	public Deterministic() {

	}

	public RESULT score(Immunization immunization1, Immunization immunization2) {
		String lotresult ; 
		long duration ; 
		String vaccineresult; 
		if (immunization1.getLotNumber().equals(immunization2.getLotNumber()))
		{
			lotresult="Same";
		}
		else if (immunization1.getLotNumber()==null && immunization2.getLotNumber()==null)
		{
			lotresult="in neither";
		}
		else if (immunization1.getLotNumber()==null && immunization2.getLotNumber()!=null)
		{
			lotresult="only in one";
		}
		else if (immunization1.getLotNumber()!=null && immunization2.getLotNumber()==null)
		{
			lotresult="only in one";
		}
		LocalDate date1 = immunization1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date2 = immunization2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		duration= ChronoUnit.DAYS.between(date1,date2);
		if (immunization1.getVaccineCode().equals(immunization2.getVaccineCode()))
		{
			vaccineresult="Same";
		}
		else if (immunization1.getVaccineCode()==null && immunization2.getVaccineCode()==null)
		{
			vaccineresult="in neither";
		}
		else if (immunization1.getVaccineCode()==null && immunization2.getVaccineCode()!=null)
		{
			vaccineresult="only in one";
		}
		else if (immunization1.getVaccineCode()!=null && immunization2.getVaccineCode()==null)
		{
			vaccineresult="only in one";
		}
		
		if (immunization1.getVaccineCode().equals(immunization2.getVaccineCode()))
		{
			vaccineresult="Same";
		}
		else if (immunization1.getVaccineCode()==null && immunization2.getVaccineCode()==null)
		{
			vaccineresult="in neither";
		}
		else if (immunization1.getVaccineCode()==null && immunization2.getVaccineCode()!=null)
		{
			vaccineresult="only in one";
		}
		else if (immunization1.getVaccineCode()!=null && immunization2.getVaccineCode()==null)
		{
			vaccineresult="only in one";
		}
		
		return RESULT.UNSURE;
	}
}
