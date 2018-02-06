package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This object links ImmunizationRecords together.
 * It is based on the linkage structure from FHIR : <a href="https://www.hl7.org/fhir/linkage.html">https://www.hl7.org/fhir/linkage.html</a>.
 * It is used as input for the API and as Output.
 * When used as output the type attribute will indicate if the contained Immunizations are the SAME, DIFFERENT or UNSURE.
 */

public class LinkedImmunization extends ArrayList<Immunization> {

    private LinkedImmunizationType type;

    public LinkedImmunizationType getType() {
        return type;
    }

    public void setType(LinkedImmunizationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String s = "";

        if (type != null)
            switch (this.type) {
                case SURE:
                    s += "TYPE = sure\n";
                    break;
                case UNSURE:
                    s += "TYPE = unsure\n";
                    break;
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
