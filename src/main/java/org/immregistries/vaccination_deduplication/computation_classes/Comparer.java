package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.ComparisonResult;
import org.immregistries.vaccination_deduplication.Immunization;

public interface Comparer {
    ComparisonResult score(Immunization immunization1, Immunization immunization2);
}
