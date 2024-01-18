package com.example.solution.Q001_Q500;

public class P458_Poor_Pigs {

    /**
     * Reference:
     * https://leetcode.com/problems/poor-pigs/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. How many states does a pig have
     * a. If there is no time to test, i.e. minutesToTest / minutesToDie = 0, the
     * pig has only one state - alive.
     * b. If minutesToTest / minutesToDie = 1 then the pig has time to die from the
     * poison, that means that now there are two states available for the pig: alive
     * or dead.
     * c. If minutesToTest / minutesToDie = 2 then there are three
     * available states for the pig: alive / dead after the first test / dead after
     * the second test
     * 2. Hence if one pig has two available states, x pigs could test 2^x buckets
     * 3. the problem is to find x such that statesx ≥ buckets, where x is a number
     * of pigs, states = minutesToTest / minutesToDie + 1 is a number of states
     * available for each pig, and buckets is the number of buckets.
     * 4. In Java, and in many programming languages, comparing floating-point
     * numbers like double values for exact equality can be problematic due to the
     * way these numbers are represented in binary format. For example, the
     * expression log125 / log5 involves the division of two floating-point numbers,
     * and the result might not be exactly equal to the expected result of 3 due to
     * rounding errors in the representation of the logarithmic values. Therefore,
     * it's common to use a small tolerance value when comparing floating-point
     * numbers and consider the values equal when they are very close to each other
     * within this specified tolerance. In Java code, we can represent the value of
     * this tolerance as 1e-10
     * 
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = minutesToTest / minutesToDie + 1;

        // We use a small tolerance value 1e-10 in the floating-point calculation
        return (int) Math.ceil(Math.log(buckets) / Math.log(states) - 1e-10);
    }
}
