package org.immregistries.vaccination_deduplication;

public class Weighted {

    public enum RESULT {EQUAL, UNSURE, DIFFERENT}

    private double minThreshold = 0.4;
    private double maxThreshold = 0.6;

    private int[] lotNumberWeight = {45, -25, 25};
    private int[] dateDifferenceWeight = {80, 65, 50, 43, 38, 30, 23, 0};
    private int[] vaccineTypeWeight = {50, 5, 15};
    private int[] providerWeight = {25, 10, 15};
    private int[] sourceWeight = {-7, 15, 60, 15};

    private int Smax;
    private int Smin;

    public Weighted() {
        updateSminAndSmax();
    }

    public RESULT score(Immunization immunization1, Immunization immunization2, double minThreshold, double maxThreshold) {
        return RESULT.UNSURE;
    }

    public RESULT score(Immunization immunization1, Immunization immunization2) {
        return score(immunization1, immunization2, this.minThreshold, this.maxThreshold);
    }

    public double getMinThreshold() {
        return minThreshold;
    }

    public void setMinThreshold(double minThreshold) {
        this.minThreshold = minThreshold;
    }

    public double getMaxThreshold() {
        return maxThreshold;
    }

    public void setMaxThreshold(double maxThreshold) {
        this.maxThreshold = maxThreshold;
    }

    public int[] getLotNumberWeight() {
        return lotNumberWeight;
    }

    public void setLotNumberWeight(int[] lotNumberWeight) {
        this.lotNumberWeight = lotNumberWeight;
        updateSminAndSmax();
    }

    public int[] getDateDifferenceWeight() {
        return dateDifferenceWeight;
    }

    public void setDateDifferenceWeight(int[] dateDifferenceWeight) {
        this.dateDifferenceWeight = dateDifferenceWeight;
        updateSminAndSmax();
    }

    public int[] getVaccineTypeWeight() {
        return vaccineTypeWeight;
    }

    public void setVaccineTypeWeight(int[] vaccineTypeWeight) {
        this.vaccineTypeWeight = vaccineTypeWeight;
        updateSminAndSmax();
    }

    public int[] getProviderWeight() {
        return providerWeight;
    }

    public void setProviderWeight(int[] providerWeight) {
        this.providerWeight = providerWeight;
        updateSminAndSmax();
    }

    public int[] getSourceWeight() {
        return sourceWeight;
    }

    public void setSourceWeight(int[] sourceWeight) {
        this.sourceWeight = sourceWeight;
        updateSminAndSmax();
    }

    private int getMinimum(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++)
            if (array[i] < min)
                min = array[i];
        return min;
    }

    private int getMaximum(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++)
            if (array[i] > max)
                max = array[i];
        return max;
    }

    public void updateSminAndSmax() {
        this.Smin =
                getMinimum(lotNumberWeight) +
                getMinimum(dateDifferenceWeight) +
                getMinimum(vaccineTypeWeight) +
                getMinimum(providerWeight) +
                getMinimum(sourceWeight);
        this.Smax =
                getMaximum(lotNumberWeight) +
                getMaximum(dateDifferenceWeight) +
                getMaximum(vaccineTypeWeight) +
                getMaximum(providerWeight) +
                getMaximum(sourceWeight);
    }
}

