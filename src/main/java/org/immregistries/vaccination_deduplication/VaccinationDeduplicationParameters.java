package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;

public class VaccinationDeduplicationParameters {
    private double dateWindow;

    private double weightMaxThreshold;
    private double weightMinThreshold;

    private double weightSameSourceHistorical;
    private double weightSameSourceAdmin;
    private double weightAbsentSource;
    private double weightDifferentSource;

    private double weightSameCVX;
    private double weightAbsentCVX;
    private double weightDifferentCVX;

    private double weightSameMVX;
    private double weightAbsentMVX;
    private double weightDifferentMVX;

    private double weightSameProductCode;
    private double weightAbsentProductCode;
    private double weightDifferentProductCode;

    private double weightSameLotNumber;
    private double weightAbsentLotNumber;
    private double weightDifferentLotNumber;

    private double weightSameOrganisationID;
    private double weightAbsentOrganisationID;
    private double weightDifferentOrganisationID;

    private ArrayList<Double> weightDateDifference;

    public VaccinationDeduplicationParameters() {
        this.weightDateDifference = new ArrayList<Double>();
    }

    public VaccinationDeduplicationParameters(double dateWindow, double weightMaxThreshold, double weightMinThreshold, double weightSameSourceHistorical, double weightSameSourceAdmin, double weightAbsentSource, double weightDifferentSource, double weightSameCVX, double weightAbsentCVX, double weightDifferentCVX, double weightSameMVX, double weightAbsentMVX, double weightDifferentMVX, double weightSameProductCode, double weightAbsentProductCode, double weightDifferentProductCode, double weightSameLotNumber, double weightAbsentLotNumber, double weightDifferentLotNumber, double weightSameOrganisationID, double weightAbsentOrganisationID, double weightDifferentOrganisationID, ArrayList<Double> weightDateDifference) {
        this.dateWindow = dateWindow;
        this.weightMaxThreshold = weightMaxThreshold;
        this.weightMinThreshold = weightMinThreshold;
        this.weightSameSourceHistorical = weightSameSourceHistorical;
        this.weightSameSourceAdmin = weightSameSourceAdmin;
        this.weightAbsentSource = weightAbsentSource;
        this.weightDifferentSource = weightDifferentSource;
        this.weightSameCVX = weightSameCVX;
        this.weightAbsentCVX = weightAbsentCVX;
        this.weightDifferentCVX = weightDifferentCVX;
        this.weightSameMVX = weightSameMVX;
        this.weightAbsentMVX = weightAbsentMVX;
        this.weightDifferentMVX = weightDifferentMVX;
        this.weightSameProductCode = weightSameProductCode;
        this.weightAbsentProductCode = weightAbsentProductCode;
        this.weightDifferentProductCode = weightDifferentProductCode;
        this.weightSameLotNumber = weightSameLotNumber;
        this.weightAbsentLotNumber = weightAbsentLotNumber;
        this.weightDifferentLotNumber = weightDifferentLotNumber;
        this.weightSameOrganisationID = weightSameOrganisationID;
        this.weightAbsentOrganisationID = weightAbsentOrganisationID;
        this.weightDifferentOrganisationID = weightDifferentOrganisationID;
        this.weightDateDifference = weightDateDifference;
    }

    public double getDateWindow() {
        return dateWindow;
    }

    public void setDateWindow(double dateWindow) {
        this.dateWindow = dateWindow;
    }

    public double getWeightMaxThreshold() {
        return weightMaxThreshold;
    }

    public void setWeightMaxThreshold(double weightMaxThreshold) {
        this.weightMaxThreshold = weightMaxThreshold;
    }

    public double getWeightMinThreshold() {
        return weightMinThreshold;
    }

    public void setWeightMinThreshold(double weightMinThreshold) {
        this.weightMinThreshold = weightMinThreshold;
    }

    public double getWeightSameSourceHistorical() {
        return weightSameSourceHistorical;
    }

    public void setWeightSameSourceHistorical(double weightSameSourceHistorical) {
        this.weightSameSourceHistorical = weightSameSourceHistorical;
    }

    public double getWeightSameSourceAdmin() {
        return weightSameSourceAdmin;
    }

    public void setWeightSameSourceAdmin(double weightSameSourceAdmin) {
        this.weightSameSourceAdmin = weightSameSourceAdmin;
    }

    public double getWeightAbsentSource() {
        return weightAbsentSource;
    }

    public void setWeightAbsentSource(double weightAbsentSource) {
        this.weightAbsentSource = weightAbsentSource;
    }

    public double getWeightDifferentSource() {
        return weightDifferentSource;
    }

    public void setWeightDifferentSource(double weightDifferentSource) {
        this.weightDifferentSource = weightDifferentSource;
    }

    public double getWeightSameCVX() {
        return weightSameCVX;
    }

    public void setWeightSameCVX(double weightSameCVX) {
        this.weightSameCVX = weightSameCVX;
    }

    public double getWeightAbsentCVX() {
        return weightAbsentCVX;
    }

    public void setWeightAbsentCVX(double weightAbsentCVX) {
        this.weightAbsentCVX = weightAbsentCVX;
    }

    public double getWeightDifferentCVX() {
        return weightDifferentCVX;
    }

    public void setWeightDifferentCVX(double weightDifferentCVX) {
        this.weightDifferentCVX = weightDifferentCVX;
    }

    public double getWeightSameMVX() {
        return weightSameMVX;
    }

    public void setWeightSameMVX(double weightSameMVX) {
        this.weightSameMVX = weightSameMVX;
    }

    public double getWeightAbsentMVX() {
        return weightAbsentMVX;
    }

    public void setWeightAbsentMVX(double weightAbsentMVX) {
        this.weightAbsentMVX = weightAbsentMVX;
    }

    public double getWeightDifferentMVX() {
        return weightDifferentMVX;
    }

    public void setWeightDifferentMVX(double weightDifferentMVX) {
        this.weightDifferentMVX = weightDifferentMVX;
    }

    public double getWeightSameProductCode() {
        return weightSameProductCode;
    }

    public void setWeightSameProductCode(double weightSameProductCode) {
        this.weightSameProductCode = weightSameProductCode;
    }

    public double getWeightAbsentProductCode() {
        return weightAbsentProductCode;
    }

    public void setWeightAbsentProductCode(double weightAbsentProductCode) {
        this.weightAbsentProductCode = weightAbsentProductCode;
    }

    public double getWeightDifferentProductCode() {
        return weightDifferentProductCode;
    }

    public void setWeightDifferentProductCode(double weightDifferentProductCode) {
        this.weightDifferentProductCode = weightDifferentProductCode;
    }

    public double getWeightSameLotNumber() {
        return weightSameLotNumber;
    }

    public void setWeightSameLotNumber(double weightSameLotNumber) {
        this.weightSameLotNumber = weightSameLotNumber;
    }

    public double getWeightAbsentLotNumber() {
        return weightAbsentLotNumber;
    }

    public void setWeightAbsentLotNumber(double weightAbsentLotNumber) {
        this.weightAbsentLotNumber = weightAbsentLotNumber;
    }

    public double getWeightDifferentLotNumber() {
        return weightDifferentLotNumber;
    }

    public void setWeightDifferentLotNumber(double weightDifferentLotNumber) {
        this.weightDifferentLotNumber = weightDifferentLotNumber;
    }

    public double getWeightSameOrganisationID() {
        return weightSameOrganisationID;
    }

    public void setWeightSameOrganisationID(double weightSameOrganisationID) {
        this.weightSameOrganisationID = weightSameOrganisationID;
    }

    public double getWeightAbsentOrganisationID() {
        return weightAbsentOrganisationID;
    }

    public void setWeightAbsentOrganisationID(double weightAbsentOrganisationID) {
        this.weightAbsentOrganisationID = weightAbsentOrganisationID;
    }

    public double getWeightDifferentOrganisationID() {
        return weightDifferentOrganisationID;
    }

    public void setWeightDifferentOrganisationID(double weightDifferentOrganisationID) {
        this.weightDifferentOrganisationID = weightDifferentOrganisationID;
    }

    public ArrayList<Double> getWeightDateDifference() {
        return weightDateDifference;
    }

    public void setWeightDateDifference(ArrayList<Double> weightDateDifference) {
        this.weightDateDifference = weightDateDifference;
    }

    public void addWeightDateDifference(double weight) {
        this.weightDateDifference.add(weight);
    }
}
