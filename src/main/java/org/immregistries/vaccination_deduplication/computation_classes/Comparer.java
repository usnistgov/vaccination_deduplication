package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.ComparisonResult;
import org.immregistries.vaccination_deduplication.Immunization;

/**
 * 
 * Interface that we'll implement in Deterministic, Deighted and Hybrid 
 * in order to factorize some code
 */
public interface Comparer {
    ComparisonResult compare(Immunization immunization1, Immunization immunization2);
}
