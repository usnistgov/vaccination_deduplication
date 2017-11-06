package org.immregistries.vaccination_deduplication;

/**
 * Created by Ansel on 06/11/2017.
 */
public class Immunization {

    //private boolean notGiven; // flag wether immunization was given

    private String vaccineCode; // Vaccine product administered

    private String date; // Vaccination administration date
    // should this be more of an expretion of when event was recorded?
    // day of for doctor, later for other players

    private String lotNumber; // Vaccine lot number

    //private String expirationDate; // Vaccine expiration date

    //private String Site; // Body site where vaccine was administered

    private String Route;

    //private int doseQuantity;

    private String practitioner;

}
