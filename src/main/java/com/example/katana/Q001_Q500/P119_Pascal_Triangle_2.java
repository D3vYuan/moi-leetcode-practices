package com.example.katana.Q001_Q500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P119_Pascal_Triangle_2 {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> numbers = new ArrayList<>();
        numbers.add(new ArrayList<>());
        numbers.add(new ArrayList<>());
        numbers.get(0).add(1);
        numbers.get(1).addAll(Arrays.asList(1, 1));

        for (int i = 2; i <= rowIndex; i++) {
            List<Integer> previousList = numbers.get(i - 1);
            List<Integer> nextList = new ArrayList<>();
            nextList.add(1);
            for (int j = 0; j < i / 2; j++) {
                if (i % 2 == 0 && j == i / 2 - 1) {
                    nextList.add(previousList.get(j) + previousList.get(j));
                } else {
                    nextList.add(previousList.get(j) + previousList.get(j + 1));
                }
            }

            String nextListValues = nextList.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.println(String.format("#%d: %s", i, nextListValues));
            numbers.add(nextList);
        }

        List<Integer> returnList = numbers.get(rowIndex);
        if (rowIndex > 1) {
            int appendStartIndex = rowIndex % 2 == 1 ? returnList.size() - 1 : returnList.size() - 2;
            for (int i = appendStartIndex; i >= 0; i--) {
                returnList.add(returnList.get(i));
            }
        }

        return returnList;
    }
}
