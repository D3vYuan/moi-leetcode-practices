package com.example.solution.Q1501_Q2000;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P1531_String_Compression_2 {
    /**
     * Reference:
     * https://leetcode.com/problems/string-compression-ii/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. We traverse over the symbols from left to right and for each symbol, we
     * decide whether to take it or not. This leaves us with the state: (string,
     * left to take). We can represent our possible states as binary trees, where
     * for the left children are where we take symbols and for the right, we do not
     * take them
     * 2. For everyone to understand how the [key] is calculated :
     * a. k's range is from 0 to length of the string , indicating 101 possible
     * values.
     * b. To avoid overlap of the next set of values we add to the value of k ,
     * lastCharCount 101 (which is the max value possible for k)
     * c. Next we want to store last character which has to exceed range of k which
     * is 101 + range of lastCharCount which is 101 , so the character value
     * relative to 'a' and multiplied by 101 * 101 , will give us the unique value
     * corresponding to lastChar.
     * d. Next we want to store index which should exceed the range of k (101) +
     * range of lastCharCount (101) + range of characters (27) , so the index is
     * multiplied by 101 101* 27.
     * e. Reason of character range to be of 27 is to include an invalid of lastChar
     * for the first position , that is for index 0 in string, we start with
     * lastChar which is out of range of 26 characters 'a' to 'z' . So that is an
     * additional value to be considered that makes char range of 27.
     * 
     * Strategy:
     * 1. First is the number of symbols we have already traversed. We need it to
     * know the next symbol to be processed.
     * 2. The last letter that was added to the compressed string that we are
     * building. This is so that we can decide how the compression will change if a
     * new symbol is added.
     * 3. The count of the last letter. Imagine that we have compression a3b5. If we
     * add one more b, our compression will become a3b6. We need to be careful with
     * several cases. When we have, say, a3 compression, and we add a b, then we
     * will now have a3b. Also if we have a3b9 and we add one more b, then we will
     * have a3b10. Similarly, a3b99 will change to a3b100 after addition of a b.
     * Notice that when the count of the last letter is 0, 1, 9, or 99, the length
     * of the compression will change. However, when the count of the last letter is
     * anything else, the length of the compression will not change.
     * 4. Finally, we need to keep track of how many symbols we are still allowed to
     * delete.
     * 
     */

    private Map<Integer, Integer> dp = new HashMap<>();
    Set<Integer> numberSet = new HashSet<>();

    private int dp(String subString, int idx, char lastCharacter, int lastCharacterCount, int leftToDelete) {
        if (leftToDelete < 0) {
            return Integer.MAX_VALUE / 2;
        }

        if (idx == subString.length()) {
            return 0;
        }

        int key = idx * 101 * 27 * 101 + (lastCharacter - 'a') * 101 * 101 + lastCharacterCount * 101 + leftToDelete;

        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int keepCharacter;
        int deleteCharacter = dp(subString, idx + 1, lastCharacter, lastCharacterCount, leftToDelete - 1);
        if (subString.charAt(idx) == lastCharacter) {
            // Repeating Character
            keepCharacter = dp(subString, idx + 1, lastCharacter, lastCharacterCount + 1, leftToDelete)
                    + (numberSet.contains(lastCharacterCount) ? 1 : 0);
        } else {
            // New Character
            keepCharacter = dp(subString, idx + 1, subString.charAt(idx), 1, leftToDelete) + 1;
        }
        int res = Math.min(keepCharacter, deleteCharacter);
        dp.put(key, res);

        return dp.get(key);
    }

    public int getLengthOfOptimalCompression(String s, int k) {
        numberSet.add(1);
        numberSet.add(9);
        numberSet.add(99);

        return dp(s, 0, (char) ('a' + 26), 0, k);
    }
}
