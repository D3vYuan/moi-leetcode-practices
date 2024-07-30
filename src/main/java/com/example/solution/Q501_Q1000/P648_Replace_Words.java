package com.example.solution.Q501_Q1000;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P648_Replace_Words {
    /**
     * Reference:
     * https://leetcode.com/problems/replace-words/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create a data structure wordArray that contains each word from the
     * sentence.
     * 2. Create a hash set dictSet containing each word from the dictionary.
     * 3. Define a helper function shortestRoot that finds the shortest
     * corresponding root word in the given dictionary for a given word.
     * a. For each index of word, save the substring of word that starts at the
     * beginning of word and ends at the index as root. If the dictSet contains
     * root, return root.
     * b. Return word if there is not a corresponding root in the dictionary.
     * 4. For each index word in wordArray:
     * a. Search for the corresponding shortest root using the helper function and
     * set wordArray[word] to the shortest root.
     * 5. Convert wordArray to a string and return.
     */

    public String replaceWords(List<String> dictionary, String sentence) {
        String[] wordArray = sentence.split(" ");
        Set<String> dictSet = new HashSet<>(dictionary);

        // Replace each word in sentence with the corresponding shortest root
        for (int i = 0; i < wordArray.length; i++) {
            wordArray[i] = shortestRoot(wordArray[i], dictSet);
        }

        return String.join(" ", wordArray);
    }

    private String shortestRoot(String word, Set<String> dictSet) {
        // Find the shortest root of the word in the dictionary
        for (int i = 1; i <= word.length(); i++) {
            String root = word.substring(0, i);
            if (dictSet.contains(root)) {
                return root;
            }
        }
        // There is not a corresponding root in the dictionary
        return word;
    }
}
