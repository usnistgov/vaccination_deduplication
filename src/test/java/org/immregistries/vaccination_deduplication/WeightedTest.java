package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;

public class WeightedTest extends TestCase {
    public void testScoreEquals() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number");
        immunization1.setPractitioner("test practitioner");
        immunization1.setVaccineCode("VCX");
        immunization1.setSource(Immunization.SOURCE.SOURCE);

        immunization2.setDate("20171225");
        immunization2.setLotNumber("test lot number");
        immunization2.setPractitioner("test practitioner");
        immunization2.setVaccineCode("VCX");
        immunization2.setSource(Immunization.SOURCE.ALTERNATE);

        Weighted weighted = new Weighted();

        Weighted.RESULT score = weighted.score(immunization1, immunization2);
        assertEquals(Weighted.RESULT.EQUAL, score);
    }

    public void testScoreDifferent() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number 1");
        immunization1.setPractitioner("test practitioner 1");
        immunization1.setVaccineCode("VCX 1");
        immunization1.setSource(Immunization.SOURCE.SOURCE);

        immunization2.setDate("20101102");
        immunization2.setLotNumber("test lot number 2");
        immunization2.setPractitioner("test practitioner 2");
        immunization2.setVaccineCode("VCX 2");
        immunization2.setSource(Immunization.SOURCE.ALTERNATE);

        Weighted weighted = new Weighted();

        Weighted.RESULT score = weighted.score(immunization1, immunization2);
        assertEquals(Weighted.RESULT.DIFFERENT, score);
    }

    public void testScoreUnsure() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        // TODO find better values

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number");
        immunization1.setPractitioner("test practitioner");
        immunization1.setVaccineCode("VCX");
        immunization1.setSource(Immunization.SOURCE.SOURCE);

        immunization2.setDate("20171225");
        immunization2.setLotNumber("test lot number");
        immunization2.setPractitioner("test practitioner");
        immunization2.setVaccineCode("VCX");
        immunization2.setSource(Immunization.SOURCE.SOURCE);

        Weighted weighted = new Weighted();

        Weighted.RESULT score = weighted.score(immunization1, immunization2);
        assertEquals(Weighted.RESULT.UNSURE, score);
    }

    public void testBothScores() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number");
        immunization1.setPractitioner("test practitioner");
        immunization1.setVaccineCode("VCX");
        immunization1.setSource(Immunization.SOURCE.SOURCE);

        immunization2.setDate("20171225");
        immunization2.setLotNumber("test lot number");
        immunization2.setPractitioner("test practitioner");
        immunization2.setVaccineCode("VCX");
        immunization2.setSource(Immunization.SOURCE.ALTERNATE);

        Weighted weighted = new Weighted();

        Weighted.RESULT score1 = weighted.score(immunization1, immunization2);
        Weighted.RESULT score2 = weighted.score(immunization1, immunization2, Weighted.DEFAULT_MIN_THRESHOLD, Weighted.DEFAULT_MAX_THRESHOLD);
        assertEquals(score1, score2);
    }

}
