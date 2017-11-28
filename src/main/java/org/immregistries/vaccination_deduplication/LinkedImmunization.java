package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;

// https://www.hl7.org/fhir/linkage.html

public class LinkedImmunization {
    // TODO declare constants
    // TODO enum types

    private int type;
    // indicates if we are sure or not
    // may also indicate if singleton

    private ArrayList<Immunization> immunizations;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Immunization> getImmunizations() {
        return immunizations;
    }

    public void setImmunizations(ArrayList<Immunization> immunizations) {
        this.immunizations = immunizations;
    }

    public void addImmunization(Immunization immunization) {
        this.immunizations.add(immunization);
    }

    public void addImmunizations(ArrayList<Immunization> immunizations) {
        this.immunizations.addAll(immunizations);
    }
}
