package com.example.solution.Q001_Q500;

public class P287_Find_Duplicate_Number {
    /**
     * Reference:
     * https://leetcode.com/problems/find-the-duplicate-number/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. In phase 1, hare = nums[nums[hare]] is twice as fast as
     * tortoise = nums[tortoise]. Since the hare goes fast,
     * it would be the first to enter the cycle and run around the cycle.
     * At some point, the tortoise enters the cycle as well, and since
     * it's moving slower the hare catches up to the tortoise at some intersection
     * point.
     * Now phase 1 is over, and the tortoise has lost.
     * 
     * 2. In phase 2, we give the tortoise a second chance by slowing down the hare,
     * so that it now moves at the speed of tortoise: tortoise = nums[tortoise],
     * hare = nums[hare]. The tortoise is back at the starting
     * position, and the hare starts from the intersection point.
     * 
     * 3. Let's show that this time they meet at the cycle entrance after F steps.
     * a. The tortoise started at zero, so its position after F steps is F.
     * b. The hare started at the intersection point F+a=nC, so its position after F
     * steps is nC+F, that is the same point as F.
     * So the tortoise and the (slowed down) hare will meet at the entrance of the
     * cycle.
     */
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];

        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        tortoise = nums[0];

        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }
}
