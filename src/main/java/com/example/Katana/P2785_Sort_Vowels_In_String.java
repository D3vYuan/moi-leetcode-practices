package com.example.katana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class P2785_Sort_Vowels_In_String {

    String[] vowelArrays = new String[] { "a", "e", "i", "o", "u", "A", "E", "I", "O", "U" };
    List<String> vowels = new ArrayList<>(Arrays.asList(vowelArrays));

    private boolean isVowels(char i) {
        if (vowels.contains(String.valueOf(i))) {
            return true;
        }
        return false;
    }

    private void showList(List<?> listItems) {
        String listItemsDescription = listItems.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(String.format("List: %s", listItemsDescription));
    }

    public String sortVowels(String s) {

        // Store Finding In HashMap
        // Sort HashMap Values
        // Rebuild String, Check if Characters in HashMap, Otherwise Get From String
        char[] stringCharacters = s.toCharArray();
        Map<Integer, Character> vowelsMap = new HashMap<>();

        for (int i = 0; i < stringCharacters.length; i++) {
            char currentCharacter = stringCharacters[i];
            if (isVowels(currentCharacter)) {
                vowelsMap.put(i, currentCharacter);
            }
        }

        // No vowels exists
        if (vowelsMap.isEmpty()) {
            return s;
        }

        List<Character> existingVowels = vowelsMap.values().stream().collect(Collectors.toList());
        showList(existingVowels);
        Collections.sort(existingVowels);
        showList(existingVowels);

        List<Integer> existingIndex = vowelsMap.keySet().stream().collect(Collectors.toList());
        Collections.sort(existingIndex);

        Map<Integer, Character> sortedVowelsMap = new HashMap<>();
        for (int i = 0; i < existingVowels.size(); i++) {
            Character currentCharacter = existingVowels.get(i);
            int currentIndex = existingIndex.get(i);
            System.out.println(String.format("Assigning: %s to index @%d", currentCharacter, currentIndex));
            sortedVowelsMap.put(currentIndex, currentCharacter);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (sortedVowelsMap.containsKey(i)) {
                sb.append(sortedVowelsMap.get(i));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
