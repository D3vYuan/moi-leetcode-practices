package com.example.solution.Q501_Q1000;

import java.util.PriorityQueue;

public class P767_Reorganize_String {
    /**
     * Reference:
     * https://leetcode.com/problems/reorganize-string/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Initialize an empty list ans to store the rearranged characters.
     * 2. Create a priority queue pq using a heap data structure. Each element in pq
     * is a tuple containing the count of a character and the character itself. The
     * priority queue is ordered in a way such that elements with higher counts have
     * higher priority.
     * a. Pop the element with the highest priority from pq. Assign its count and
     * character to count_first and char_first respectively.
     * b. If ans is empty or the current character char_first is different from the
     * last character in ans, append char_first to ans. If the count of char_first
     * is not zero, update its count by decreasing it by one. If the updated count
     * is larger than zero, push it back to pq. Continue to the next iteration.
     * c. Otherwise, if char_first is the same as the last character in ans, it
     * means we need to choose a different character. If pq is empty, return an
     * empty string as it is impossible to rearrange the characters.
     * d. Pop the next element from pq, assigning its count and character to
     * count_second and char_second respectively. Append char_second to ans.
     * e. If the count of char_second is not zero, update its count by decreasing it
     * by one. If the updated count is larger than zero, push it back to pq.
     * f. Finally, push the original char_first back to pq.
     * 3. Return the rearranged characters as a string by joining the elements in
     * ans.
     * 
     */
    public String reorganizeString(String s) {
        int[] charCounts = new int[26];
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }

        // Max heap ordered by character counts
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(b[1], a[1]));
        for (int i = 0; i < 26; i++) {
            if (charCounts[i] > 0) {
                pq.offer(new int[] { i + 'a', charCounts[i] });
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) first[0]);
                if (--first[1] > 0) {
                    pq.offer(first);
                }
            } else {
                if (pq.isEmpty()) {
                    return "";
                }

                int[] second = pq.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    pq.offer(second);
                }

                pq.offer(first);
            }
        }

        return sb.toString();
    }
}
