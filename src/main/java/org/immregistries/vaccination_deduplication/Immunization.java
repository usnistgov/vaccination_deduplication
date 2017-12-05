package org.immregistries.vaccination_deduplication;

import java.util.Date;

/**
 *
 * Describes all the elements we can find in a record of a vaccination which are useful for the duplication process
 * Immunization object based on a structure similar to FHIR : https://www.hl7.org/fhir/immunization.html
 *
 */

public class Immunization {

    public enum SOURCE {SOURCE, ALTERNATE, HISTORICAL}

    private String vaccineCode; // Vaccine product administered

    private Date date; // Vaccination administration/payment/... date

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
