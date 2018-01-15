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

public class LinkedImmunization implements Iterable<Immunization> {

    public LinkedImmunization() {
		super();
	}

	public enum TYPE {SURE, UNSURE, SINGLETON}

    private TYPE type;

    private ArrayList<Immunization> immunizations;
    
    public int getSize() {
    	return immunizations.size();
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
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

	public Iterator<Immunization> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
