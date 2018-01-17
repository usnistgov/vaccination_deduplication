package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.computation_classes.Deterministic;

public class DeterministicTest extends TestCase {
/*
    public void testScoreEquals() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20161217");
        //immunization1.setLotNumber("test lot number");
        immunization1.setCVX("03");
        immunization1.setTradeName("MMR");
        immunization1.setSource(Immunization.SOURCE.SOURCE);
        immunization1.setOrganisationID("Dr Murphey");
        
        immunization2.setDate("20161218");
        //immunization2.setLotNumber("test lot number");
        immunization2.setTradeName("MMR");
        immunization2.setCVX("03");
        immunization2.setSource(Immunization.SOURCE.HISTORICAL);
        immunization2.setOrganisationID("Medicare");

        Deterministic deterministic = new Deterministic();
        
        Result score = deterministic.score(immunization1, immunization2);
        assertEquals(Result.EQUAL, score);
    }

    public void testScoreEquals1() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20161217");
        //immunization1.setLotNumber("test lot number");
        immunization1.setCVX("10");
        immunization1.setTradeName("IPV");
        immunization1.setSource(Immunization.SOURCE.SOURCE);
        immunization1.setOrganisationID("Dr Murphey");
        
        immunization2.setDate("20161218");
        //immunization2.setLotNumber("test lot number");
        immunization2.setTradeName("Polio");
        immunization2.setCVX("89");
        immunization2.setSource(Immunization.SOURCE.HISTORICAL);
        immunization2.setOrganisationID("Mercy Hospital");

        Deterministic deterministic = new Deterministic();
        
        Result score = deterministic.score(immunization1, immunization2);
        assertEquals(Result.EQUAL, score);
    }*/
    public void testScoreDifferent() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20161217");
        //immunization1.setLotNumber("test lot number");
        immunization1.setCVX("03");
        immunization1.setProductCode("MMR");
        immunization1.setSource(Immunization.SOURCE.SOURCE);
        immunization1.setOrganisationID("Mercy Hospital");
        
        immunization2.setDate("20160506");
        //immunization2.setLotNumber("test lot number 2");
        immunization2.setProductCode("HEP B");
        immunization2.setCVX("08");
        immunization2.setOrganisationID("Dr Murphey");
        immunization2.setSource(Immunization.SOURCE.HISTORICAL);

        Deterministic deterministic = new Deterministic();

        Result score = deterministic.score(immunization1, immunization2);
        assertEquals(Result.DIFFERENT, score);
    }/*

    public void testScoreUnsure() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        // TODO find better values

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number");
        immunization1.setCVX("VCX");
        immunization1.setSource(Immunization.SOURCE.SOURCE);

        immunization2.setDate("20171225");
        immunization2.setLotNumber("test lot number");
        immunization2.setCVX("VCX");
        immunization2.setSource(Immunization.SOURCE.ALTERNATE);

        Deterministic deterministic = new Deterministic();

        Result score = deterministic.score(immunization1, immunization2);
        assertEquals(Result.UNSURE, score);
    }*/
}