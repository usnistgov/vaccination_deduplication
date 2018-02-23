package org.immregistries.vaccination_deduplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This object represents an immunization record containing only the relevant fields for the deduplication process.
 * It is based on the immunization structure from FHIR : <a href="https://www.hl7.org/fhir/immunization.html">https://www.hl7.org/fhir/immunization.html</a>
 */

public class Immunization {

    private String immunizationID;

    private String CVX; // Vaccine code

    private ArrayList<String> vaccineGroupList ; // Vaccine Group

    private String MVX; // Manufacturer vaccine code

    private String productCode ; // Directly linkable to the tradename

    private Date date; // Vaccination administration/payment/... date

    private String lotNumber; // Vaccine lot number

    private String organisationID; // get organisation through encounter.serviceProvider

    private ImmunizationSource source;

    public Immunization(String immunizationID, String cvx, ArrayList<String> vaccineGroupList, String mvx, String productCode, Date date, String lotNumber, String organisationID, ImmunizationSource source) {
        this.immunizationID = immunizationID;
        this.CVX = cvx;
        this.vaccineGroupList = vaccineGroupList;
        this.MVX = mvx;
        this.productCode = productCode;
        this.date = date;
        this.lotNumber = lotNumber;
        this.organisationID = organisationID;
        this.source = source;
    }

    public Immunization() {
        this("", "", new ArrayList<String>(), "", "", new Date(), "", "", ImmunizationSource.HISTORICAL);
    }

    public String getCVX() {
        return CVX;
    }

    public void setCVX(String CVX) {
        this.CVX = CVX;
    }

    public ArrayList<String> getVaccineGroupList() {
        return vaccineGroupList;
    }

    public void setVaccineGroupList(ArrayList<String> vaccineGroupList) {
        this.vaccineGroupList = vaccineGroupList;
    }

    public void addVaccineGroupList(ArrayList<String> vaccineGroupList) {
        this.vaccineGroupList.addAll(vaccineGroupList);
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

    public ImmunizationSource getSource() {
        return source;
    }

    public void setSource(ImmunizationSource source) {
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
