package com.example.solution;

public class P2125_Number_Laser_Beams {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-laser-beams-in-a-bank/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Strategy:
     * 1. Initialize prev and ans to 0.
     * 2. Iterate over each string in bank and initialize the count to 0. Iterate
     * over each character in the string and increment the counter count if the
     * character is a 1.
     * 3. After iterating over all characters of a string, if the count is not zero
     * then add prev * count to ans. Also update the value of prev to count if count
     * != 0.
     * 4. Return ans.
     */
    public int numberOfBeams(String[] bank) {
        int prev = 0, ans = 0;

        for (String s : bank) {
            int count = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) == '1') {
                    count++;
                }

            if (count > 0) {
                ans += prev * count;
                prev = count;
            }
        }

        return ans;
    }
}
