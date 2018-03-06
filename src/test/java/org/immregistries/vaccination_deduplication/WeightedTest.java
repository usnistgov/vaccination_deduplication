package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.computation_classes.Weighted;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import java.util.ArrayList;
import java.util.Arrays;

public class WeightedTest extends TestCase {
    public void testScoreEquals() throws Exception {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        Weighted weighted = new Weighted(new PropertyLoader().getParameters());

        ComparisonResult score = weighted.compare(immunizationLists.immunization1, immunizationLists.immunization2);
        assertEquals(ComparisonResult.EQUAL, score);

    }

    public void testScoreDifferent() throws Exception {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        Weighted weighted = new Weighted(new PropertyLoader().getParameters());

        ComparisonResult score = weighted.compare(immunizationLists.immunization1, immunizationLists.immunization2);
        assertEquals(ComparisonResult.DIFFERENT, score);
    }

    public void testScoreUnsure() throws Exception {
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();
        Weighted weighted = new Weighted(new PropertyLoader().getParameters());

        ComparisonResult score = weighted.compare(immunizationLists.immunization1, immunizationLists.immunization3);
        assertEquals(ComparisonResult.UNSURE, score);
    }

}
