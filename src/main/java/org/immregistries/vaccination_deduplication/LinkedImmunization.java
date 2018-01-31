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
	public enum TYPE {SURE, UNSURE, DIFFERENT}

    private TYPE type;

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String s = "";

        if (type != null)
            switch (this.type) {
                case SURE:
                    s += "TYPE = sure\n";
                case UNSURE:
                    s += "TYPE = unsure\n";
                case DIFFERENT:
                    s += "TYPE = different\n";
            }
        else
            s += "TYPE = not set\n";

        s += "{\n";

        for (Immunization immunization : this) {
            s += "\t" + immunization.toString() + "\n";
        }
        s += "}\n";
        return s;
    }
}
