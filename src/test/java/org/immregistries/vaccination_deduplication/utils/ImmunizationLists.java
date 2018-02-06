package org.immregistries.vaccination_deduplication.utils;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.ImmunizationSource;
import org.immregistries.vaccination_deduplication.LinkedImmunization;
import org.immregistries.vaccination_deduplication.LinkedImmunizationType;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * Lists immunizations, those will be used for the tests in CompleteTest
 */
public class ImmunizationLists {
    static ImmunizationLists instance;

    // setting them all to public to not have to create getters
    public LinkedImmunization patient1 = new LinkedImmunization();
    public LinkedImmunization patient2 = new LinkedImmunization();
    public LinkedImmunization patient3 = new LinkedImmunization();
    public Immunization immunization1 = new Immunization();
    public Immunization immunization2 = new Immunization();
    public Immunization immunization3 = new Immunization();
    public Immunization immunization4 = new Immunization();
    public Immunization immunization5 = new Immunization();
    public Immunization immunization6 = new Immunization();
    public Immunization immunization7 = new Immunization();
    public Immunization immunization8 = new Immunization();
    public Immunization immunization9 = new Immunization();
    public Immunization immunization10 = new Immunization();
    public Immunization immunization11 = new Immunization();
    public Immunization immunization12 = new Immunization();
    public Immunization immunization13 = new Immunization();
    public Immunization immunization14 = new Immunization();
    public Immunization immunization15 = new Immunization();
    public Immunization immunization16 = new Immunization();
    public Immunization immunization17 = new Immunization();
    public Immunization immunization18 = new Immunization();
    public Immunization immunization19 = new Immunization();
    public Immunization immunization20 = new Immunization();
    public Immunization immunization21 = new Immunization();

    private ImmunizationLists() throws ParseException {
        patient1.setType(LinkedImmunizationType.INPUT);
        patient2.setType(LinkedImmunizationType.INPUT);
        patient3.setType(LinkedImmunizationType.INPUT);

        immunization1.setVaccineGroupList(new ArrayList<String>(Arrays.asList("MMR")));
        immunization1.setOrganisationID("Dr Murphey");
        immunization1.setDate("20161217");
        immunization1.setCVX("03");
        immunization1.setMVX("MSD");
        immunization1.setLotNumber("1");
        immunization1.setSource(ImmunizationSource.SOURCE);

        immunization2.setVaccineGroupList(new ArrayList<String>(Arrays.asList("MMR")));
        immunization2.setOrganisationID("Mercy Hospital");
        immunization2.setDate("20161217");
        immunization2.setCVX("03");
        immunization2.setMVX("MSD");
        immunization2.setLotNumber("1");
        immunization2.setSource(ImmunizationSource.HISTORICAL);

        immunization3.setVaccineGroupList(new ArrayList<String>(Arrays.asList("MMR")));
        immunization3.setOrganisationID("Medicare");
        immunization3.setDate("20161218");
        immunization3.setCVX("03");
        immunization3.setMVX("MSD");
        immunization3.setLotNumber("1");
        immunization3.setSource(ImmunizationSource.HISTORICAL);

        immunization4.setVaccineGroupList(new ArrayList<String>(Arrays.asList("DTaP", "IPV")));
        immunization4.setOrganisationID("Dr Murphey");
        immunization4.setDate("20161217");
        immunization4.setCVX("130");
        immunization4.setSource(ImmunizationSource.SOURCE);

        immunization5.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep A")));
        immunization5.setOrganisationID("Dr Murphey");
        immunization5.setDate("20161217");
        immunization5.setCVX("83");
        immunization5.setSource(ImmunizationSource.SOURCE);

        immunization6.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
        immunization6.setOrganisationID("Dr Murphey");
        immunization6.setDate("20160605");
        immunization6.setCVX("08");
        immunization6.setSource(ImmunizationSource.SOURCE);

        immunization7.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
        immunization7.setOrganisationID("Dr Murphey");
        immunization7.setDate("20160401");
        immunization7.setCVX("08");
        immunization7.setSource(ImmunizationSource.SOURCE);

        immunization8.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
        immunization8.setOrganisationID("Dr Murphey");
        immunization8.setDate("20151111");
        immunization8.setCVX("08");
        immunization8.setSource(ImmunizationSource.HISTORICAL);
        
        immunization9.setVaccineGroupList(new ArrayList<String>(Arrays.asList("DTaP", "Hep B", "IPV")));
        immunization9.setOrganisationID("Dr Murphey");
        immunization9.setDate("20161217");
        immunization9.setCVX("110");
        immunization9.setSource(ImmunizationSource.SOURCE);

        immunization10.setVaccineGroupList(new ArrayList<String>(Arrays.asList("DTaP")));
        immunization10.setOrganisationID("Mercy Hospital");
        immunization10.setDate("20161217");
        immunization10.setCVX("20");
        immunization10.setSource(ImmunizationSource.HISTORICAL);

        immunization11.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
        immunization11.setOrganisationID("Mercy Hospital");
        immunization11.setDate("20161217");
        immunization11.setCVX("08");
        immunization11.setSource(ImmunizationSource.HISTORICAL);

        immunization12.setVaccineGroupList(new ArrayList<String>(Arrays.asList("IPV")));
        immunization12.setOrganisationID("Mercy Hospital");
        immunization12.setDate("20161217");
        immunization12.setCVX("10");
        immunization12.setSource(ImmunizationSource.HISTORICAL);

        immunization13.setVaccineGroupList(new ArrayList<String>(Arrays.asList("MMR")));
        immunization13.setOrganisationID("Mercy Hospital");
        immunization13.setDate("20161217");
        immunization13.setCVX("03");
        immunization13.setSource(ImmunizationSource.HISTORICAL);

        immunization14.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
        immunization14.setOrganisationID("Dr Murphey");
        immunization14.setDate("20160605");
        immunization14.setCVX("08");
        immunization14.setSource(ImmunizationSource.SOURCE);

        immunization15.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
        immunization15.setOrganisationID("Mercy Hospital");
        immunization15.setDate("20160605");
        immunization15.setCVX("08");
        immunization15.setSource(ImmunizationSource.SOURCE);

        immunization16.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
        immunization16.setOrganisationID("Mercy Hospital");
        immunization16.setDate("20160401");
        immunization16.setCVX("08");
        immunization16.setSource(ImmunizationSource.HISTORICAL);
        
        immunization17.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
        immunization17.setOrganisationID("Dr Murphey");
        immunization17.setDate("20160201");
        immunization17.setCVX("08");
        immunization17.setLotNumber("AAAAA");
        immunization17.setSource(ImmunizationSource.SOURCE);
        
        immunization18.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Hep B")));
        immunization18.setOrganisationID("Mercy Hospital");
        immunization18.setDate("20160201");
        immunization18.setCVX("08");
        immunization18.setLotNumber("AAAAA");
        immunization18.setSource(ImmunizationSource.SOURCE);
        
        immunization19.setVaccineGroupList(new ArrayList<String>(Arrays.asList("IPV")));
        immunization19.setOrganisationID("Dr Murphey");
        immunization19.setDate("20161217");
        immunization19.setCVX("10");
        immunization19.setSource(ImmunizationSource.SOURCE);
        
        immunization20.setVaccineGroupList(new ArrayList<String>(Arrays.asList("Polio")));
        immunization20.setOrganisationID("Mercy Hospital");
        immunization20.setDate("20161217");
        immunization20.setCVX("89");
        immunization20.setSource(ImmunizationSource.HISTORICAL);
        
        immunization21.setVaccineGroupList(new ArrayList<String>(Arrays.asList("IPV")));
        immunization21.setOrganisationID("Dr Murphey");
        immunization21.setDate("20161217");
        immunization21.setCVX("10");
        immunization21.setSource(ImmunizationSource.SOURCE);

        patient1.add(immunization1);
        patient1.add(immunization2);
        patient1.add(immunization3);
        patient1.add(immunization4);
        patient1.add(immunization5);
        patient1.add(immunization6);
        patient1.add(immunization7);
        patient1.add(immunization8);
        
        patient2.add(immunization9);
        patient2.add(immunization10);
        patient2.add(immunization11);
        patient2.add(immunization12);
        patient2.add(immunization13);
        patient2.add(immunization14);
        patient2.add(immunization15);
        patient2.add(immunization16);
        patient2.add(immunization17);
        patient2.add(immunization18);
        
        patient3.add(immunization19);
        patient3.add(immunization20);
        patient3.add(immunization21);
 
    }

    public static ImmunizationLists getInstance() throws ParseException {
        if (ImmunizationLists.instance == null) {
            ImmunizationLists.instance = new ImmunizationLists();
        }
        return ImmunizationLists.instance;
    }
}
