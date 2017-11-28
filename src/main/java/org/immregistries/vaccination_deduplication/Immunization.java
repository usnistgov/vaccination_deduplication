package org.immregistries.vaccination_deduplication;

import java.util.Date;

// https://www.hl7.org/fhir/immunization.html

public class Immunization {

    public enum SOURCE {SOURCE, ALTERNATE, HISTORICAL}

    // business identifier ~= data base key
    private String vaccineType ; // Vaccine Type(CVX) 
    private String vaccineCode; // Vaccine product administered

    // TODO change to date object
    private Date date; // Vaccination administration/payment/... date

    private String lotNumber; // Vaccine lot number

    private String practitioner;

    // get organisation through encounter.serviceProvider

    private SOURCE source;

    public String getVaccineCode() {
        return vaccineCode;
    }

    public void setVaccineCode(String vaccineCode) {
        this.vaccineCode = vaccineCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(String practitioner) {
        this.practitioner = practitioner;
    }

}
