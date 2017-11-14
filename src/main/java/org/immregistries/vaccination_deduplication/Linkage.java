package org.immregistries.vaccination_deduplication;

import java.util.ArrayList;

// TODO declare constants

// https://www.hl7.org/fhir/linkage.html

public class Linkage {

    private int type;
    // indicates if we are sure or not
    // may also indicate if singleton

    private ArrayList<Item> items;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addItems(ArrayList<Item> items) {
        this.items.addAll(items);
    }

    public class Item {
        // TODO change to private

        private int type; // source | alternate | historical

        private Immunization resource; // not sure this is a reference

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Immunization getResource() {
            return resource;
        }

        public void setResource(Immunization resource) {
            this.resource = resource;
        }
    }
}
