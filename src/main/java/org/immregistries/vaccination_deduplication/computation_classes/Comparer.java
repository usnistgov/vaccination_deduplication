package org.immregistries.vaccination_deduplication.computation_classes;

import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.Result;

public interface Comparer {
    public Result score(Immunization immunization1, Immunization immunization2);
}
