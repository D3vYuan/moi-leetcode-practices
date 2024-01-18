package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P68_Text_Justification {
    /**
     * Reference:
     * https://leetcode.com/problems/text-justification/?envType=daily-question&envId=2024-01-17
     * 
     * Strategy:
     * 1. Create two helper methods getWords and createLine that we described above.
     * 2. Initialize an answer list ans and an integer i to iterate over the input.
     * 3. Use a while loop to iterate over the input. Each iteration in the while
     * loop will handle one line in the answer.
     * a. While i < words.length, do the following steps:
     * b. Get the words that should be in the current line as currentLine =
     * getWords(i).
     * c. Increment i by currentLine.length.
     * d. Create the line by calling createLine(line, i) and add it to ans.
     * 4. Return ans.
     * 
     */
    private List<String> getWords(int i, String[] words, int maxWidth) {
        List<String> currentLine = new ArrayList<>();
        int currLength = 0;

        while (i < words.length && currLength + words[i].length() <= maxWidth) {
            currentLine.add(words[i]);
            currLength += words[i].length() + 1;
            i++;
        }

        return currentLine;
    }

    private String createLine(List<String> line, int i, String[] words, int maxWidth) {
        int baseLength = -1;
        for (String word : line) {
            baseLength += word.length() + 1;
        }

        int extraSpaces = maxWidth - baseLength;

        if (line.size() == 1 || i == words.length) {
            return String.join(" ", line) + String.join("", Collections.nCopies(extraSpaces, " "));
        }

        int wordCount = line.size() - 1;
        int spacesPerWord = extraSpaces / wordCount;
        int needsExtraSpace = extraSpaces % wordCount;

        for (int j = 0; j < needsExtraSpace; j++) {
            line.set(j, line.get(j) + " ");
        }

        for (int j = 0; j < wordCount; j++) {
            line.set(j, line.get(j) + String.join("", Collections.nCopies(spacesPerWord, " ")));
        }

        return String.join(" ", line);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            List<String> currentLine = getWords(i, words, maxWidth);
            i += currentLine.size();
            ans.add(createLine(currentLine, i, words, maxWidth));
        }

        return ans;
    }
}
