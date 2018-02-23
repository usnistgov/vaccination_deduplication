package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;
import org.immregistries.vaccination_deduplication.computation_classes.Deterministic;
import org.immregistries.vaccination_deduplication.utils.ImmunizationLists;

import java.text.ParseException;

public class DeterministicTest extends TestCase {
    public void testDeterministicEqual() throws ParseException {
        Deterministic deterministic = new Deterministic();
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        assertEquals(ComparisonResult.EQUAL, deterministic.compare(immunizationLists.immunization1, immunizationLists.immunization2));
        assertEquals(ComparisonResult.EQUAL, deterministic.compare(immunizationLists.immunization1, immunizationLists.immunization3));
    }

    public void testDeterministicUnsure() throws ParseException {
        Deterministic deterministic = new Deterministic();
        ImmunizationLists immunizationLists = ImmunizationLists.getInstance();

        assertEquals(ComparisonResult.UNSURE, deterministic.compare(immunizationLists.immunization1, immunizationLists.immunization8));
    }

    public void testDeterministicDifferent() throws ParseException {

        // TODO find a pair that return different
    }
}