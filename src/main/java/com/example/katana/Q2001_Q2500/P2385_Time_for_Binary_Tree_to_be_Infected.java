package com.example.katana.Q2001_Q2500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.utility.TreeNode;

public class P2385_Time_for_Binary_Tree_to_be_Infected {

    class Solution {
        private void buildDependencies(TreeNode node, Map<Integer, Set<Integer>> childNodes,
                Map<Integer, Integer> parentNodes) {
            if (node == null) {
                return;
            }

            Integer currentVal = node.val;
            if (node.left != null) {
                Set<Integer> currentChildNodes = childNodes.getOrDefault(currentVal, new HashSet<>());
                currentChildNodes.add(node.left.val);
                childNodes.put(currentVal, currentChildNodes);
                parentNodes.put(node.left.val, currentVal);
            }

            if (node.right != null) {
                Set<Integer> currentChildNodes = childNodes.getOrDefault(currentVal, new HashSet<>());
                currentChildNodes.add(node.right.val);
                childNodes.put(currentVal, currentChildNodes);
                parentNodes.put(node.right.val, currentVal);
            }

            buildDependencies(node.left, childNodes, parentNodes);
            buildDependencies(node.right, childNodes, parentNodes);
        }

        public int amountOfTime(TreeNode root, int start) {
            Map<Integer, Set<Integer>> childNodes = new HashMap<>();
            Map<Integer, Integer> parentNodes = new HashMap<>();
            buildDependencies(root, childNodes, parentNodes);

            LinkedList<Integer> currentQueue = new LinkedList<>();
            LinkedList<Integer> nextQueue = new LinkedList<>();
            currentQueue.push(start);

            int minutes = 0;
            Set<Integer> affected = new HashSet<>();
            affected.add(currentQueue.peek());

            while (!currentQueue.isEmpty()) {
                int currentValue = currentQueue.pop();

                affected.add(currentValue);

                Set<Integer> dependencies = childNodes.getOrDefault(currentValue, new HashSet<>());
                Integer parent = parentNodes.getOrDefault(currentValue, -1);

                Set<Integer> filteredDependencies = new HashSet<>();
                filteredDependencies.addAll(dependencies);
                filteredDependencies.add(parent);
                filteredDependencies = filteredDependencies.stream()
                        .filter(dependency -> dependency != -1 && !affected.contains(dependency))
                        .collect(Collectors.toSet());

                if (!filteredDependencies.isEmpty()) {
                    nextQueue.addAll(filteredDependencies);
                    affected.addAll(filteredDependencies);
                }

                // System.out.println(String.format("Next Queue: [%d] | Affected: [%d]",
                // nextQueue.size(), affected.size()));

                if (currentQueue.isEmpty() && !nextQueue.isEmpty()) {
                    currentQueue.addAll(nextQueue);
                    nextQueue = new LinkedList<>();
                    minutes += 1;
                    // System.out.println(String.format("-- Minutes %d --", minutes));
                }
            }
            return minutes;
        }
    }
}
