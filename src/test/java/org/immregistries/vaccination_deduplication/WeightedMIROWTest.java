package org.immregistries.vaccination_deduplication;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.junit.Ignore;
import org.junit.Test;


public class WeightedMIROWTest {



    @Test
    public void testS007() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20050826");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP")));
        immunization1.setCVX("20");
        immunization1.setLotNumber("C2221AA");
        immunization1.setOrganisationID("Olmsted Med");

        immunization2.setDate("20050909");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP")));
        immunization2.setCVX("20");
        immunization2.setLotNumber("C2253AA");
        immunization2.setOrganisationID("Olmsted Med");

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 108;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.364;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    public void testS008() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20050826");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP")));
        immunization1.setCVX("20");
        immunization1.setLotNumber("C2221AA");
        immunization1.setOrganisationID("Olmsted Med");

        immunization2.setDate("20050909");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP")));
        immunization2.setCVX("20");
        immunization2.setLotNumber("C2253AA");
        immunization2.setOrganisationID("Brained Med");

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 93;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.313;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    public void testS009() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20060106");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP", "HepB", "Polio")));
        immunization1.setCVX("110");
        immunization1.setLotNumber("AC21A011CA");
        immunization1.setProductCode("Pediatrix");
        immunization1.setOrganisationID("Brainerd Med");

        immunization2.setDate("20060116");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP", "HepB", "Polio")));
        immunization2.setCVX("110");
        immunization2.setLotNumber("AC21A011CA");
        immunization2.setProductCode("Pediatrix");
        immunization2.setOrganisationID("Brainerd Med");

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 193;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.653;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    @Ignore
    public void testS010() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20062303");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP", "HepB", "Polio")));
        immunization1.setProductCode("Pediatrix");
        immunization1.setOrganisationID("Open Cities HC");
        immunization1.setSource(ImmunizationSource.SOURCE);

        immunization2.setDate("20062803");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP", "HepB", "Polio")));
        immunization2.setProductCode("Pediatrix");
        immunization2.setOrganisationID("Baby Tracks");
        immunization2.setSource(ImmunizationSource.SOURCE);

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 143;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.483;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    @Ignore
    public void testS011() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20060102");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP", "HepB", "Polio")));
        immunization1.setOrganisationID("SMDC");
        immunization1.setSource(ImmunizationSource.HISTORICAL);

        immunization2.setDate("20060106");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP", "HepB", "Polio")));
        immunization2.setLotNumber("Ac21B037CA");
        immunization2.setOrganisationID("Duluth Clinic");
        immunization2.setSource(ImmunizationSource.SOURCE);

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 203;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.687;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    @Ignore
    public void testS013() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20050110");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HepB")));
        immunization1.setCVX("51");
        immunization1.setOrganisationID("123");
        immunization1.setSource(ImmunizationSource.SOURCE);

        immunization2.setDate("20050112");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HepB")));
        immunization2.setOrganisationID("54 (Medicaid)");
        immunization2.setSource(ImmunizationSource.HISTORICAL);

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 215;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.728;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    public void testS017() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20060608");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP", "HepB", "Polio")));
        immunization1.setCVX("110");
        immunization1.setLotNumber("C2221AA");
        immunization1.setProductCode("Pediatrix");
        immunization1.setOrganisationID("Kidhealth VT");

        immunization2.setDate("20060608");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("Polio")));
        immunization2.setCVX("10");
        immunization2.setLotNumber("C2253AA");
        immunization2.setProductCode("IPOL");
        immunization2.setOrganisationID("Kidhealth VT");

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 95;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.320;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    public void testS006() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20040929");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HepB")));
        immunization1.setCVX("08");
        immunization1.setLotNumber("ENG123");
        immunization1.setProductCode("Engerix-B-Peds");

        immunization2.setDate("20040926");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HepB")));

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 133;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.449;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    public void testS006A() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20040929");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HepB")));
        immunization1.setCVX("08");
        immunization1.setLotNumber("ENG123");
        immunization1.setProductCode("Engerix-B-Peds");
        immunization1.setSource(ImmunizationSource.SOURCE);

        immunization2.setDate("20040926");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HepB")));
        immunization2.setSource(ImmunizationSource.HISTORICAL);

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 178;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.602;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    @Ignore
    public void testS012() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20060213");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP", "HepB", "Polio")));
        immunization1.setProductCode("Pediatrix");
        immunization1.setOrganisationID("Baby Tracks");

        immunization2.setDate("20060220");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("DTaP")));
        immunization2.setOrganisationID("Aspen Med Grp");

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 98;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.330;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    public void testS014() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20060202");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HIB")));
        immunization1.setCVX("49");
        immunization1.setLotNumber("2345mm");
        immunization1.setOrganisationID("332");
        immunization1.setSource(ImmunizationSource.SOURCE);

        immunization2.setDate("20060202");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HIB")));
        immunization2.setCVX("49");
        immunization2.setOrganisationID("354");
        immunization2.setSource(ImmunizationSource.SOURCE);

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 178;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.602;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }

    @Test
    public void testS016() throws Exception {

        Immunization immunization1 = new Immunization();
        Immunization immunization2 = new Immunization();

        immunization1.setDate("20040909");
        immunization1.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HIB")));
        immunization1.setCVX("17");
        immunization1.setOrganisationID("89");
        immunization1.setSource(ImmunizationSource.HISTORICAL);

        immunization2.setDate("20040910");
        immunization2.setVaccineGroupList(
                new ArrayList<String>(Arrays.asList("HIB")));
        immunization2.setCVX("48");
        immunization2.setOrganisationID("90");

        Weighted weighted = new Weighted();

        double score = weighted.getScore(immunization1, immunization2);
        double expectedScore = 185;
        assertEquals(expectedScore, score, 0.001);

        double balancedScore = weighted.getBalancedScore(score);
        double expectedBalancedScore = 0.626;

        assertEquals(expectedBalancedScore, balancedScore, 0.001);

    }
}
