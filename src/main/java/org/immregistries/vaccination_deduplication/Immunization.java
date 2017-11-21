package org.immregistries.vaccination_deduplication;

/**
 * Created by Ansel on 06/11/2017.
 */
public class Immunization {

    // business identifier ~= data base key

    private String vaccineCode; // Vaccine product administered

    private String date; // Vaccination administration/payment/... date

    private String lotNumber; // Vaccine lot number

    private String practitioner;

    // get organisation through encounter.serviceProvider

    private boolean primarySource;

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

    public String getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(String practitioner) {
        this.practitioner = practitioner;
    }

    public boolean isPrimarySource() {
        return primarySource;
    }

    public void setPrimarySource(boolean primarySource) {
        this.primarySource = primarySource;
    }
}
