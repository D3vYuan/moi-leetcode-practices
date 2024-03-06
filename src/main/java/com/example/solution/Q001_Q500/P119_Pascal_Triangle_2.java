package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.List;

public class P119_Pascal_Triangle_2 {
    /**
     * Reference:
     * https://leetcode.com/problems/pascals-triangle-ii/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. The recursion ends in some known base cases:
     * a. The first row is just a single 1, i.e. getNum(0, ...) = 1
     * b. The first and last number of each row is 111, i.e. getNum(k, 0) =
     * getNum(k, k) = 1
     */

    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        row.add(1);

        for (int i = 0; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
        }

        return row;
    }
}
