package org.immregistries.vaccination_deduplication;

import com.sun.org.apache.regexp.internal.RE;

public class Weighted {

    public enum RESULT {EQUAL, UNSURE, DIFFERENT}

    public static final int DEFAULT_MIN_THRESHOLD = 50;
    public static final int DEFAULT_MAX_THRESHOLD = 75;

    public Weighted() {
    }

    public RESULT score(Immunization immunization1, Immunization immunization2, double minThreshold, double maxThreshold) {
        return RESULT.UNSURE;
    }

    public RESULT score(Immunization immunization1, Immunization immunization2) {
        return score(immunization1, immunization2, DEFAULT_MIN_THRESHOLD, DEFAULT_MAX_THRESHOLD);
    }
}

