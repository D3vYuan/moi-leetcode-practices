package com.example.solution.Q501_Q1000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P857_Minimum_Cost_To_Hire_K_Workers {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. Suppose we have 2 workers i and j
     * a1. wage[j] / wage[i] = quality[j] / quality[i]
     * a2. quality[i] / wage[i] = quality[j] / wage[j]
     * ​a3. Meaning if a worker’s quality is double that of another worker in the
     * group, then they must be paid twice as much as the other worker.
     * 2. Consider the first example(k=2k = 2k=2) from the problem description
     * a. To start, let's say we hire workers 0 and 2. Their combined quality is 15
     * units (10+5).
     * Now, we allocate payment based on their contribution to this total quality:
     * Input: quality = [10,20,5], wage = [70,50,30], k = 2
     * a1. Worker 0 will be paid as follows: 10 / 15 = 2 / 3 (individual quality /
     * total quality)
     * a2. Worker 2 will be paid as follows: 5 / 15 = 1 / 3 (individual quality /
     * total quality)
     * 3. We use this to meet condition 2: Workers are compensated in proportion to
     * their quality relative to other workers in the group
     * i.e., worker 0 receives 2/3 of the total payment, and Worker 2 receives 1/3
     * a. Worker 0 has the higher minimum wage, $70.
     * We can set up the following proportion to determine xxx, the amount of money
     * worker 2 will make:
     * a1. (Lower Salary Quality) / (Higher Salary Quality) = x / (Higher Salary)
     * a2. 1/2 = x/70 -> x = 35
     * a3. The cost of the paid group is 70 + 35 = $105
     * 4. The wage to quality ratio, for worker 0 is $7 per unit (wage / quality =
     * 70 / 10) and for worker 2 is $6 per unit (wage / quality = 30 / 5)
     * 5. Thus, to satisfy both conditions, we must pay each worker at least $7\$7$7
     * per unit to meet the minimum wage and quality requirements. This internal
     * selection process ensures that both quality and wage requirements are met.
     * 6.
     * 
     * Strategy:
     * 1. Initialize variables n to store the size of the input arrays (quality and
     * wage), totalCost to store the minimum total cost (initially set to the
     * maximum possible value) and currentTotalQuality to keep track of the sum of
     * qualities of the current set of workers.
     * 2. Create a array wageToQualityRatio to store the wage-to-quality ratio and
     * the quality of each worker as pairs.
     * 3. Calculate the wage-to-quality ratio for each worker and store it in
     * wageToQualityRatio.
     * 4. Sort wageToQualityRatio in ascending order based on the wage-to-quality
     * ratio.
     * 5. Create a priority queue highestQualityWorkers (max heap) to store the
     * workers with the highest qualities.
     * 6. Iterate through the sorted wageToQualityRatio:
     * a. Push the current worker's quality to highestQualityWorkers.
     * b. Update currentTotalQuality by adding the current worker's quality.
     * c. If the size of highestQualityWorkers exceeds k:
     * c1. Remove the worker with the highest quality from highestQualityWorkers.
     * c2. Update currentTotalQuality by subtracting the removed worker's quality.
     * d. If the size of highestQualityWorkers is equal to k:
     * d1. Calculate the total cost for the current set of workers by multiplying
     * currentTotalQuality by the wage-to-quality ratio of the current worker.
     * d2. Update totalCost if the calculated cost is smaller than the current
     * minimum cost.
     * 7. After iterating through all workers, return totalCost, which holds the
     * minimum total cost for hiring k workers.
     * 8. Return totalCost.
     */
    public double minCostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double totalCost = Double.MAX_VALUE;
        double currentTotalQuality = 0;
        List<Pair<Double, Integer>> wageToQualityRatio = new ArrayList<>();

        // Calculate wage-to-quality ratio for each worker
        for (int i = 0; i < n; i++) {
            wageToQualityRatio.add(
                    new ImmutablePair<>((double) wage[i] / quality[i], quality[i]));
        }

        // Sort workers based on their wage-to-quality ratio
        Collections.sort(wageToQualityRatio,
                Comparator.comparingDouble(Pair::getKey));

        // Use a priority queue to keep track of the highest quality workers
        PriorityQueue<Integer> highestQualityWorkers = new PriorityQueue<>(
                Collections.reverseOrder());

        // Iterate through workers
        for (int i = 0; i < n; i++) {
            highestQualityWorkers.add(wageToQualityRatio.get(i).getValue());
            currentTotalQuality += wageToQualityRatio.get(i).getValue();

            // If we have more than k workers,
            // remove the one with the highest quality
            if (highestQualityWorkers.size() > k) {
                currentTotalQuality -= highestQualityWorkers.poll();
            }

            // If we have exactly k workers,
            // calculate the total cost and update if it's the minimum
            if (highestQualityWorkers.size() == k) {
                totalCost = Math.min(totalCost, currentTotalQuality *
                        wageToQualityRatio.get(i).getKey());
            }
        }
        return totalCost;

    }
}
