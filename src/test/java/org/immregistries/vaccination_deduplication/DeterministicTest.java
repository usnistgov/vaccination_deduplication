package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;

public class DeterministicTest extends TestCase {
    public void testScoreEquals() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number");
        immunization1.setVaccineCode("VCX");
        immunization1.setSource(Immunization.SOURCE.SOURCE);

        immunization2.setDate("20171225");
        immunization2.setLotNumber("test lot number");
        immunization2.setVaccineCode("VCX");
        immunization2.setSource(Immunization.SOURCE.ALTERNATE);

        Deterministic deterministic = new Deterministic();

        Result score = deterministic.score(immunization1, immunization2);
        assertEquals(Result.EQUAL, score);
    }

    public void testScoreDifferent() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number 1");
        immunization1.setVaccineCode("VCX 1");
        immunization1.setSource(Immunization.SOURCE.SOURCE);

        immunization2.setDate("20101102");
        immunization2.setLotNumber("test lot number 2");
        immunization2.setVaccineCode("VCX 2");
        immunization2.setSource(Immunization.SOURCE.ALTERNATE);

        Deterministic deterministic = new Deterministic();

        Result score = deterministic.score(immunization1, immunization2);
        assertEquals(Result.DIFFERENT, score);
    }

    public void testScoreUnsure() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        // TODO find better values

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number");
        immunization1.setVaccineCode("VCX");
        immunization1.setSource(Immunization.SOURCE.SOURCE);

        immunization2.setDate("20171225");
        immunization2.setLotNumber("test lot number");
        immunization2.setVaccineCode("VCX");
        immunization2.setSource(Immunization.SOURCE.ALTERNATE);

        Deterministic deterministic = new Deterministic();

        Result score = deterministic.score(immunization1, immunization2);
        assertEquals(Result.UNSURE, score);
    }
}