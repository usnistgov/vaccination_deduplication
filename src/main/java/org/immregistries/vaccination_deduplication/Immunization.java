package org.immregistries.vaccination_deduplication;

// https://www.hl7.org/fhir/immunization.html

public class Immunization {

    public enum SOURCE {SOURCE, ALTERNATE, HISTORICAL}

    private String vaccineCode; // Vaccine product administered

    // TODO change to date object
    private String date; // Vaccination administration/payment/... date

    private String lotNumber; // Vaccine lot number

    private String organisationID; // get organisation through encounter.serviceProvider

    private SOURCE source;

    public Immunization() {

    }

    public String getVaccineCode() {
        return vaccineCode;
    }

    public void setVaccineCode(String vaccineCode) {
        this.vaccineCode = vaccineCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getOrganisationID() {
        return organisationID;
    }

    public void setOrganisationID(String organisationID) {
        this.organisationID = organisationID;
    }

    public SOURCE getSource() {
        return source;
    }

    public void setSource(SOURCE source) {
        this.source = source;
    }
}
