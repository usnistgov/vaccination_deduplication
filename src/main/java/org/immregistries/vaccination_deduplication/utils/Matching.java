package org.immregistries.vaccination_deduplication.utils;

import java.util.List;

public class Matching {
    public static boolean isThereAMatch(List<String> list1, List<String> list2) {
        for (String s1 : list1) {
            for (String s2 : list2)
                if (s1.equals(s2))
                    return true;
        }
        return false;
    }

    public static double compareForWeighted(String s1, String s2, double scoreSame, double scoreAbsent, double scoreDifferent) {
        if (!(s1 == null || s2 == null) && !(s1.length() == 0 || s2.length() == 0)){
            if (s1.equals(s2)) {
                return scoreSame; // Present and same
            }
            else {
                return scoreDifferent; // Present and different
            }
        } else {
            return scoreAbsent; // Absent one or both
        }
    }
}
