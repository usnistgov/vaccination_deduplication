package org.immregistries.vaccination_deduplication;
import junit.framework.TestCase;

import org.immregistries.vaccination_deduplication.computation_classes.Deterministic;
import org.immregistries.vaccination_deduplication.computation_classes.Hybrid;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

public class HybridTest extends TestCase{
    public void testScoreEquals() throws Exception {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        Hybrid hybrid = new Hybrid(new PropertyLoader().getParameters());

        ComparisonResult score = hybrid.compare(immunizationLists.immunization1, immunizationLists.immunization2);
        assertEquals(ComparisonResult.EQUAL, score);

    }

    public void testScoreDifferent() throws Exception {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        Hybrid hybrid = new Hybrid(new PropertyLoader().getParameters());

        ComparisonResult score = hybrid.compare(immunizationLists.immunization1, immunizationLists.immunization6);
        assertEquals(ComparisonResult.DIFFERENT, score);
    }

    public void testScoreUnsure() throws Exception {
        ImmunizationLists immunizationLists = new ImmunizationLists();

        Hybrid hybrid = new Hybrid(new PropertyLoader().getParameters());

        ComparisonResult score = hybrid.compare(immunizationLists.immunization1, immunizationLists.immunization4);
        assertEquals(ComparisonResult.UNSURE, score);
    }
}

