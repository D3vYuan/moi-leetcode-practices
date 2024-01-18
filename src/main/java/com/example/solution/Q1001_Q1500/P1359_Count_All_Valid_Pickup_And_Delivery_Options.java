package com.example.solution.Q1001_Q1500;

public class P1359_Count_All_Valid_Pickup_And_Delivery_Options {
    /**
     * Reference:
     * https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/editorial/?envType=daily-question&envId=2024-01-17
     *
     */

    /**
     * Explanation:
     * 1. If there are n unpicked orders, then we have n different options for
     * orders that we can pick up at the current step. So instead of picking up each
     * order one by one and making a recursive call for each one, we could count the
     * number of ways to pick (n−1) unpicked orders and multiply it by the number of
     * choices at the current step, n.
     * 2. we have unpicked number of orders that have not been picked up and
     * undelivered number orders to be delivered. If we want to pick one order then
     * there are unpicked different choices to pick at the current step. Or if we
     * want to deliver one order then there are undelivered - unpicked (orders which
     * are picked but not delivered) different choices
     * a. waysToPick = unpicked * totalWays(unpicked - 1, undelivered)
     * b. waysToDeliver = (undelivered - unpicked) * totalWays(unpicked, undelivered
     * - 1)
     * 3. Base Case: When we deliver all the orders that means we have generated
     * some combination of pickup and delivery, thus return 1
     * 4. Recurring: Some of the subproblems occur again and again, thus these
     * subproblems are also overlapping - for example, we would need to calculate
     * totalWays(4, 5) in both totalWays(4, 6) and totalWays(5, 5)
     * 
     * Strategy:
     * 1. Initialize some variables:
     * a. MOD as 10^{9} + 7 to prevent integer overflow
     * b. memo, a data structure that we will use to cache results to prevent
     * duplicate computation.
     * 2. Create a function totalWays(unpicked, undelivered) that will be used for
     * recursion.
     * a. First, check if we have delivered all the orders (unpicked == 0 and
     * undelivered == 0). If they are all delivered, return 1.
     * b. We can't deliver/pick up more than N orders or deliver more orders than we
     * picked up. If unpicked < 0 or undelivered < 0 or undelivered < unpicked, then
     * return 0.
     * c. Check if this subproblem has already been visited once. If it has, then
     * return the cached result.
     * d. Otherwise, add the ways to pick unpicked and deliver undelivered orders
     * while preventing integer overflow.
     * e. At the end of each recursive call, store the number of valid pickup and
     * delivery options for the current subproblem in the cache and return it.
     * 3. Call the function we created in step 2 (totalWays) with the initial input
     * values of N unpicked and N undelivered orders.
     */
    private int MOD = 1_000_000_007;
    private long[][] memo;

    private long totalWays(int unpicked, int undelivered) {
        if (unpicked == 0 && undelivered == 0) {
            // We have completed all orders.
            return 1;
        }

        if (unpicked < 0 || undelivered < 0 || undelivered < unpicked) {
            // We can't pick or deliver more than N items
            // Number of deliveries can't exceed number of pickups
            // as we can only deliver after a pickup.
            return 0;
        }

        if (memo[unpicked][undelivered] != 0) {
            // Return cached value, if already present.
            return memo[unpicked][undelivered];
        }

        long ans = 0;

        // Count all choices of picking up an order.
        ans += unpicked * totalWays(unpicked - 1, undelivered);

        // Handle integer overflow.
        ans %= MOD;

        // Count all choices of delivering a picked order.
        ans += (undelivered - unpicked) * totalWays(unpicked, undelivered - 1);

        // Handle integer overflow.
        ans %= MOD;

        return memo[unpicked][undelivered] = ans;
    }

    public int topDown(int n) {
        memo = new long[n + 1][n + 1];
        return (int) totalWays(n, n);
    }

    /**
     * Explanation:
     * 1. If there are n unpicked orders then we have n choices of picking orders,
     * so the number of ways to pick these nnn orders will be n multiplied by the
     * number of ways to pick (n−1) orders, which will already be stored in the DP
     * table.
     * 2. We have unpicked number of orders to be picked and undelivered orders to
     * be delivered. If we want to pick one order then there are unpicked different
     * choices to pick at the current step. Or if we want to deliver one order then
     * there are undelivered - unpicked (orders which are picked but not delivered)
     * different choices.
     * a. // There are some unpicked elements left. if unpicked > 0: waysToPick +=
     * unpicked * dp[unpicked - 1][undelivered]
     * b. // Delivery done are less than picked orders. if undelivered > unpicked:
     * waysToDeliver += (undelivered - unpicked) * dp[unpicked][undelivered - 1]
     * 
     * Strategy:
     * 1. Initialize some variables:
     * a. MOD as ^{9} + 7 to prevent integer overflow
     * b. dp, a data structure that will be used to store all subproblems results.
     * 2. We iterate over each subproblem of unpicked and undelivered orders.
     * a. We start from the base case (unpicked = 0 and undelivered = 0) and
     * increment undelivered count whenever we reach undelivered equal to N, we
     * increment unpicked count and reset undelivered to unpicked count because the
     * count of undelivered orders can't be less than unpicked orders (i.e.
     * delivered orders can't be more than picked orders).
     * b. The outer loop will iterate over unpicked orders and the inner loop will
     * iterate over undelivered orders.
     * b1. If both the unpicked and undelivered orders are 0, the number of ways to
     * arrange them is 1.
     * b2. Otherwise, add the ways to pick unpicked and deliver undelivered orders
     * with handling the integer overflow.
     * 3. Return the number of ways to pick and deliver N unpicked and undelivered
     * orders, stored in dp[N][N].
     */

    public int bottomUp(int n) {
        long[][] dp = new long[n + 1][n + 1];

        for (int unpicked = 0; unpicked <= n; unpicked++) {
            for (int undelivered = unpicked; undelivered <= n; undelivered++) {
                // If all orders are picked and delivered then,
                // for remaining '0' orders we have only one way.
                if (unpicked == 0 && undelivered == 0) {
                    dp[unpicked][undelivered] = 1;
                    continue;
                }

                // There are some unpicked elements left.
                // We have choice to pick any one of those orders.
                if (unpicked > 0) {
                    dp[unpicked][undelivered] += unpicked * dp[unpicked - 1][undelivered];
                }
                dp[unpicked][undelivered] %= MOD;

                // Number of deliveries done is less than picked orders.
                // We have choice to deliver any one of (undelivered - unpicked) orders.
                if (undelivered > unpicked) {
                    dp[unpicked][undelivered] += (undelivered - unpicked) * dp[unpicked][undelivered - 1];
                }
                dp[unpicked][undelivered] %= MOD;
            }
        }

        return (int) dp[n][n];
    }

    /**
     * Explanation:
     * 1. Let's assume we want to place 4 different objects (A, B, C, D
     * respectively) in some order in a row, what are all the possible ways to do
     * it?
     * 2. We have 4 empty positions and at each position, we have to place one
     * object.
     * a. At the 1st position, we can place any one of the 4 objects so we have 4
     * choices here.
     * b. At the 2nd position, we can only place any one of 3 objects because one
     * object is already placed at the 1st position.
     * c. At the 3rd position, we can place any one of the remaining 2 objects (2
     * objects are already placed).
     * d. At the 4th position, we can place the remaining 1 object (3 objects are
     * already placed).
     * 3.Hence, total number of ways to place 4 different objects = 4⋅3⋅2⋅1=4!
     * 4. We have N orders each with a pickup and delivery, let's denote Pi=Pickup
     * of ith order and Di=Delivery of ith order
     * 5. We have 2N empty positions and we need to count all ways to place all Pi
     * and Di such that all Di is placed after Pi
     * 6. So, we first place all the N pickups in some random order as we don't
     * have any constraints on placing pickups. So N pickups can be placed in N!
     * ways.
     * 7. Assuming P2, P4, P1, P3
     * a. The last pickup was P3, hence for D3 we have only one place i.e. after P3
     * - (ie P2 P4 P1 P3 _)
     * b. The second last pickup was P1, so we have three places to place the
     * delivery D1. (ie P2 P4 P1 _ P3 _ D3 _)
     * c. Similarly, for D4 we have five places and for D2 we have seven places.
     * d. So, the number of ways to place all deliveries is 1⋅3⋅5⋅7.
     * e. we can come to the formula that, to place N pickups we have N! ways, and
     * to place the N deliveries we have 1⋅3⋅5 ⋅....⋅ (2N−1) ways
     * 
     * Strategy:
     * 1. Initialize some variables:
     * a. MOD as 10^{9} + 7 to prevent integer overflow (as stated in
     * the problem description).
     * b. ans, to store the final result.
     * 2. Calculate the number of ways to arrange pickups and deliveries, i.e.
     * $$N!$$ and $$\prod_{i=1}^{N} (2 * i - 1) $$ and multiply them to calculate
     * total ways to arrange pickups and deliveries for N` orders with handling the
     * overflow.
     * 3. Return the final result ans.
     */
    public int byPermutations(int n) {
        long ans = 1;
        int MOD = 1_000_000_007;

        for (int i = 1; i <= n; ++i) {
            // Ways to arrange all pickups, 1*2*3*4*5*...*n
            ans = ans * i;
            // Ways to arrange all deliveries, 1*3*5*...*(2n-1)
            ans = ans * (2 * i - 1);
            ans %= MOD;
        }

        return (int) ans;
    }

    public int countOrders(int n) {
        return topDown(n);
        // return bottomUp(n);
        // return byProbability(n);
    }
}
