package com.example.katana.Q1001_Q1500;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class P1282_Group_People_Given_Size {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        PriorityQueue<int[]> groupingHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < groupSizes.length; i++) {
            int currentGroupSize = groupSizes[i];
            System.out.println(String.format("Adding: Person %d | Size [%d]", i, currentGroupSize));
            groupingHeap.offer(new int[] { i, currentGroupSize });
        }

        List<List<Integer>> groupings = new ArrayList<>();
        groupings.add(new ArrayList<>());

        while (!groupingHeap.isEmpty()) {
            List<Integer> latestGroup = groupings.get(groupings.size() - 1);
            int[] currentPersonGroup = groupingHeap.poll();
            int currentPerson = currentPersonGroup[0];
            int currentGroupSize = currentPersonGroup[1];
            System.out.println(String.format("Processing: Person %d | Size [%d] | Previous Group Size [%d]",
                    currentPerson, currentGroupSize, latestGroup.size()));

            latestGroup.add(currentPerson);
            if (latestGroup.size() == currentGroupSize && !groupingHeap.isEmpty()) {
                List<Integer> nextGroup = new ArrayList<>();
                groupings.add(nextGroup);
            }
        }

        return groupings;
    }
}
