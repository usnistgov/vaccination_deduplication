package org.immregistries.vaccination_deduplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * Describes all the elements we can find in a record of a vaccination which are useful for the duplication process
 * Immunization object based on a structure similar to FHIR : https://www.hl7.org/fhir/immunization.html
 *
 */

public class Immunization {

    public enum SOURCE {SOURCE, ALTERNATE, HISTORICAL}

    private String CVX; // Vaccine code

    private List<String> vaccineGroupList ; // Vaccine Group

    private String MVX; // Manufacturer vaccine code

    private String productCode ; // Directly linkable to the tradename

    private Date date; // Vaccination administration/payment/... date

    private String lotNumber; // Vaccine lot number

    private String organisationID; // get organisation through encounter.serviceProvider

    private SOURCE source;

    public Immunization() {

    }

    public String getCVX() {
        return CVX;
    }

    public void setCVX(String CVX) {
        this.CVX = CVX;
    }

    public List<String> getVaccineGroupList() {
        return vaccineGroupList;
    }

    public void setVaccineGroupList(List<String> vaccineGroupList) {
        this.vaccineGroupList = vaccineGroupList;
    }

    public String getMVX() {
        return MVX;
    }

    public void setMVX(String MVX) {
        this.MVX = MVX;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String dateString) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        this.date = sdf.parse(dateString);
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

    @Override
    public String toString() {
        return "Immunization{" +
                "CVX='" + CVX + '\'' +
                ", vaccineGroupList=" + vaccineGroupList +
                ", MVX='" + MVX + '\'' +
                ", productCode='" + productCode + '\'' +
                ", date=" + date +
                ", lotNumber='" + lotNumber + '\'' +
                ", organisationID='" + organisationID + '\'' +
                ", source=" + source +
                '}';
    }
}
