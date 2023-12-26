package com.example.katana;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P1637_Widest_Vertical_Area {
    public int maxWidthOfVerticalArea(int[][] points) {
        List<Integer> widths = Arrays.stream(points).map(pair -> pair[0]).distinct().sorted()
                .collect(Collectors.toList());

        if (widths.size() == 1) {
            return 0;
        }

        int maxWidth = Integer.MIN_VALUE;
        for (int i = 1; i < widths.size(); i++) {
            int currentWidth = widths.get(i) - widths.get(i - 1);
            maxWidth = Math.max(maxWidth, currentWidth);
        }

        return maxWidth;
    }
}
