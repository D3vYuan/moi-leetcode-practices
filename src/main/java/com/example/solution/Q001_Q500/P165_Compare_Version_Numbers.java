package com.example.solution.Q001_Q500;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P165_Compare_Version_Numbers {
    /**
     * Reference:
     * https://leetcode.com/problems/compare-version-numbers/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Set a pointer p1 pointed to the beginning of string version1 and a pointer
     * p2 to the beginning of string version2: p1 = p2 = 0.
     * 2. Iterate over both strings in parallel. While p1 < n1 or p2 < n2:
     * a. Retrieve the next chunk i1 from string version1 and next chunk i2 from
     * string version2 using the above-defined get_next_chunk function.
     * b. Compare i1 and i2. If they are not equal, return 1 or -1.
     * 3. If we're here, the versions are equal. Return 0.
     */
    public int compareVersion(String version1, String version2) {
        int p1 = 0, p2 = 0;
        int n1 = version1.length(), n2 = version2.length();

        // Compare versions
        int i1, i2;
        Pair<Integer, Integer> pair;
        while (p1 < n1 || p2 < n2) {
            pair = getNextChunk(version1, n1, p1);
            i1 = pair.getKey();
            p1 = pair.getValue();

            pair = getNextChunk(version2, n2, p2);
            i2 = pair.getKey();
            p2 = pair.getValue();
            if (i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }

        // The versions are equal
        return 0;
    }

    public Pair<Integer, Integer> getNextChunk(String version, int n, int p) {
        // If the pointer is set to the end of the string, return 0
        if (p > n - 1) {
            return new ImmutablePair<>(0, p);
        }

        // Find the end of chunk
        int i, pEnd = p;
        while (pEnd < n && version.charAt(pEnd) != '.') {
            ++pEnd;
        }

        // Retrieve the chunk
        if (pEnd != n - 1) {
            i = Integer.parseInt(version.substring(p, pEnd));
        } else {
            i = Integer.parseInt(version.substring(p, n));
        }

        // Find the beginning of the next chunk
        p = pEnd + 1;

        return new ImmutablePair<>(i, p);
    }
}
