package org.immregistries.vaccination_deduplication;

/**
 * Describes the result of a comparison between to immunization by one of the comparers.
 */
public enum ComparisonResult {
    EQUAL, UNSURE, DIFFERENT, TO_BE_DETERMINED
}
