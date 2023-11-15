package com.example.katana;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class P1930_Unique_Length_3_Palindromic_Subsequence {
    /**
     * 
     * Strategy
     * [1] Count each occurrences
     * [2] Find maximum that each character can possibly generate
     * - If occurrence divisible by 3 - +1
     * - If occurrence divisible by 2 - +(1 * number of characters available)
     * - If occurrence is 1 - +(1 * number of characters with occurrence > 2)
     * [3] Generate subsequence and see if the subsequence is available
     */

    private Map<Character, Integer> generateCharacterMapping(String s) {

        Map<Character, Integer> characterMapping = new HashMap<>();
        for (Character item : s.toCharArray()) {
            if (!characterMapping.containsKey(item)) {
                characterMapping.put(item, 0);
            }

            characterMapping.put(item, characterMapping.get(item) + 1);
        }

        return characterMapping;
    }

    private boolean isValid(String s, String subset) {
        boolean isValid = false;
        int search = 0;
        String find = subset.substring(search, search + 1);

        System.out.println(String.format("Checking: %s is subset of %s", subset, s));
        for (String item : s.split("")) {
            System.out.println(String.format("Checking: Comparing %s with %s", item, find));
            if (item.equals(find)) {
                search += 1;
                if (search >= subset.length()) {
                    isValid = true;
                    break;
                }
                find = subset.substring(search, search + 1);
            }
        }
        return isValid;
    }

    private int countTotalAvailableSubset(String s, List<Character> combinationCharacters,
            List<Character> singleCharacter) {
        int count = 0;

        for (int i = 0; i < singleCharacter.size(); i++) {
            for (int j = 0; j < combinationCharacters.size(); j++) {
                if (singleCharacter.get(i).equals(combinationCharacters.get(j))){
                    continue;
                }
                String subset = String.format("%s%s%s", combinationCharacters.get(j), singleCharacter.get(i),
                        combinationCharacters.get(j));
                if (isValid(s, subset)) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public int countPalindromicSubsequence(String s) {
        Map<Character, Integer> characterMapping = generateCharacterMapping(s);

        int totalCharacters = characterMapping.size();
        List<Character> moreThan3Keys = characterMapping.entrySet().stream().filter(entry -> entry.getValue() >= 3)
                .map(entry -> entry.getKey()).collect(Collectors.toList());
        int moreThan3KeysTotal = moreThan3Keys.size();

        List<Character> divisibleBy2Keys = characterMapping.entrySet().stream()
                .filter(entry -> entry.getValue() % 2 == 0)
                .filter(entry -> !moreThan3Keys.contains(entry.getKey()))
                .map(entry -> entry.getKey()).collect(Collectors.toList());
        int divisibleBy2Total = divisibleBy2Keys.size();

        // List<Character> equalTo1Key = characterMapping.entrySet().stream().filter(entry -> entry.getValue() == 1)
        //         .map(entry -> entry.getKey()).collect(Collectors.toList());
        // int equalTo1KeyTotal = equalTo1Key.size();

        List<Character> combinationKeys = new ArrayList<>();
        combinationKeys.addAll(moreThan3Keys);
        combinationKeys.addAll(divisibleBy2Keys);

        List<Character> singleKeys = characterMapping.keySet().stream().collect(Collectors.toList());
        int totalAvailableSubset = countTotalAvailableSubset(s, combinationKeys, singleKeys);

        return moreThan3KeysTotal + totalAvailableSubset;
    }
}
