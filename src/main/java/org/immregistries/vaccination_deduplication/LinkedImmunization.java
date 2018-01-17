package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * Identifies two or more records that are possibly referring to the same real-world "occurrence"
 * LinkedImmunization object based on a structure similar to FHIR : https://www.hl7.org/fhir/linkage.html
 * 
 *
 */

public class LinkedImmunization extends ArrayList<Immunization> {
	public enum TYPE {SURE, UNSURE, SINGLETON}

    private TYPE type;

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
