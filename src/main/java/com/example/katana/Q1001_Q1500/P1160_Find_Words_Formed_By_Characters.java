package com.example.katana.Q1001_Q1500;

import java.util.HashMap;
import java.util.Map;

public class P1160_Find_Words_Formed_By_Characters {
    public boolean match(String word, Map<String, Integer> characterCount) {
        Map<String, Integer> currentWordCount = new HashMap<>();
        for (String wordSplit : word.split("")) {
            if (!characterCount.containsKey(wordSplit)) {
                return false;
            }

            if (!currentWordCount.containsKey(wordSplit)) {
                currentWordCount.put(wordSplit, 0);
            }
            currentWordCount.put(wordSplit, currentWordCount.get(wordSplit) + 1);
            if (currentWordCount.get(wordSplit) > characterCount.get(wordSplit)) {
                return false;
            }
        }
        return true;
    }

    public int countCharacters(String[] words, String chars) {
        Map<String, Integer> characterCount = new HashMap<>();
        for (String word : chars.split("")) {
            if (!characterCount.containsKey(word)) {
                characterCount.put(word, 0);
            }
            characterCount.put(word, characterCount.get(word) + 1);
        }

        int totalCharacters = 0;
        for (String word : words) {
            if (match(word, characterCount)) {
                totalCharacters += word.length();
            }
        }
        return totalCharacters;
    }
}
