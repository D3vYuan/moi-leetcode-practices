package com.example.katana.Q1001_Q1500;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P1051_Heigh_Checker {
    public int heightChecker(int[] heights) {
        List<Integer> sortedHeights = Arrays.stream(heights).sorted().boxed().collect(Collectors.toList());
        int notInOrder = 0;
        for (int i = 0; i < heights.length; i++) {
            int sortedHeight = sortedHeights.get(i);
            int height = heights[i];
            if (sortedHeight != height) {
                notInOrder += 1;
            }
        }
        return notInOrder;
    }
}
