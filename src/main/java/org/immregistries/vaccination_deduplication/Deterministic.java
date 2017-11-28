package org.immregistries.vaccination_deduplication;

public class Deterministic {

    public enum RESULT {EQUAL, UNSURE, DIFFERENT}

    public Deterministic() {
    }

    public RESULT score(Immunization immunization1, Immunization immunization2) {
        return RESULT.UNSURE;
    }
}
