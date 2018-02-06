package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;

public class WeightedTest extends TestCase {
    public void testScoreEquals() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number");
        immunization1.setCVX("CVX");
        immunization1.setMVX("MVX");
        immunization1.setProductCode("pc");
        immunization1.setSource(ImmunizationSource.SOURCE);
        immunization1.setOrganisationID("20171225");

        immunization2.setDate("20171225");
        immunization2.setLotNumber("test lot number");
        immunization2.setCVX("CVX");
        immunization2.setMVX("MVX");
        immunization2.setProductCode("pc");
        immunization2.setSource(ImmunizationSource.ALTERNATE);
        immunization2.setOrganisationID("20171225");

        Weighted weighted = new Weighted();

        Result score = weighted.score(immunization1, immunization2);
        assertEquals(Result.EQUAL, score);
    }

    public void testScoreDifferent() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number 1");
        immunization1.setCVX("VCX 1");
        immunization1.setMVX("MVX 1");
        immunization1.setProductCode("pc 2");
        immunization1.setSource(ImmunizationSource.SOURCE);
        immunization1.setOrganisationID("20171225");

        immunization2.setDate("20101102");
        immunization2.setLotNumber("test lot number 2");
        immunization2.setCVX("VCX 2");
        immunization2.setMVX("MVX 2");
        immunization2.setProductCode("pc 2");
        immunization2.setSource(ImmunizationSource.ALTERNATE);
        immunization2.setOrganisationID("20171225");

        Weighted weighted = new Weighted();

        Result score = weighted.score(immunization1, immunization2);
        assertEquals(Result.DIFFERENT, score);
    }

    public void testScoreUnsure() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        // TODO find better values

        immunization1.setDate("20171225");
        immunization1.setLotNumber("test lot number");
        immunization1.setCVX("VCX");
        immunization1.setMVX("MVX");
        immunization1.setProductCode("pc");
        immunization1.setSource(ImmunizationSource.SOURCE);
        immunization1.setOrganisationID("20171225");

        immunization2.setDate("20171225");
        immunization2.setLotNumber("test lot number");
        immunization2.setCVX("VCX");
        immunization2.setMVX("MVX");
        immunization2.setProductCode("pc");
        immunization2.setSource(ImmunizationSource.HISTORICAL);
        immunization2.setOrganisationID("20171225");

        Weighted weighted = new Weighted();

        Result score = weighted.score(immunization1, immunization2);
        assertEquals(Result.UNSURE, score);
    }

}
