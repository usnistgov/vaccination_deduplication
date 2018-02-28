package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import java.util.ArrayList;
import java.util.Arrays;

public class WeightedTest extends TestCase {
    public void testScoreEquals() throws Exception {
        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20040909");
        immunization1.setLotNumber("");
        immunization1.setCVX("17");
        immunization1.setVaccineGroupList(new ArrayList<String>(Arrays.asList("HIB")));
        immunization1.setMVX("");
        immunization1.setProductCode("");
        immunization1.setSource(ImmunizationSource.HISTORICAL);
        immunization1.setOrganisationID("89");

        immunization2.setDate("20040910");
        immunization2.setLotNumber("");
        immunization2.setCVX("48");
        immunization1.setVaccineGroupList(new ArrayList<String>(Arrays.asList("HIB", "PRP-T")));
        immunization2.setMVX("");
        immunization2.setProductCode("");
        //immunization2.setSource(ImmunizationSource.ALTERNATE);
        immunization2.setOrganisationID("90");

        Weighted weighted = new Weighted();

        ComparisonResult score = weighted.compare(immunization1, immunization2);
        assertEquals(ComparisonResult.EQUAL, score);

        score = weighted.compare(immunization2, immunization1);
        assertEquals(ComparisonResult.EQUAL, score);
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

        ComparisonResult score = weighted.compare(immunization1, immunization2);
        assertEquals(ComparisonResult.DIFFERENT, score);
    }

    public void testScoreUnsure() throws Exception {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();
        Weighted weighted = new Weighted();

        ComparisonResult score = weighted.compare(immunizationLists.immunization1, immunizationLists.immunization2);
        assertEquals(ComparisonResult.UNSURE, score);
    }

}
