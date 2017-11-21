package org.immregistries.vaccination_deduplication;

public class Deterministic {

    public static final int EQUAL = 2;
    public static final int UNSURE = 1;
    public static final int DIFFERENT = 0;

    public Deterministic() {
    }

    public int score(Immunization immunization1, Immunization immunization2) {
        return UNSURE;
    }
}
