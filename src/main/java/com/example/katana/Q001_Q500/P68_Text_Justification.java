package com.example.katana.Q001_Q500;

import java.util.ArrayList;
import java.util.List;

public class P68_Text_Justification {
    private String generateLeftJustifiedString(int start, int end, String[] words, int totalWords, int maxWidth) {
        System.out.println(String.format("Current Line: %d words",
                totalWords));
        StringBuilder sb = new StringBuilder();
        int appendedLength = 0;
        for (int i = start; i < end; i++) {
            String joinWord = words[i];
            sb.append(joinWord);
            appendedLength += joinWord.length();

            if (i != end - 1) {
                sb.append(" ");
                appendedLength += 1;
            } else { // Last Line
                int spaceCount = 0;
                while (spaceCount < maxWidth - appendedLength) {
                    sb.append(" ");
                    spaceCount++;
                }
            }
        }

        return sb.toString();
    }

    private String generatePaddedString(int start, int end, String[] words, int totalWords, int wordSpace,
            int wordSpaceOverflow) {
        System.out.println(String.format("Current Line: %d words | %d space with %d overflow",
                totalWords, wordSpace, wordSpaceOverflow));
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            String joinWord = words[i];
            sb.append(joinWord);
            int spaceCount = 0;

            if (i != end - 1 || totalWords == 1) { // Not Last Word or Only Word
                while (spaceCount < wordSpace) {
                    sb.append(" ");
                    spaceCount++;
                }

                if (wordSpaceOverflow > 0) {
                    sb.append(" ");
                    wordSpaceOverflow -= 1;
                }
            }
        }
        return sb.toString();
    }

    private String generateJoinSentence(int maxWidth, int wordLength, int totalWords, int start, int end,
            String[] words, boolean leftJustified) {
        int currentAvailableSpaceLength = (maxWidth - wordLength);
        int currentAvailableSpace = (totalWords - 1) == 0 ? 1 : (totalWords - 1);
        int currentWordSpace = currentAvailableSpaceLength / currentAvailableSpace;
        int currentWordSpaceOverflow = currentAvailableSpaceLength % currentAvailableSpace;

        return leftJustified ? generateLeftJustifiedString(start, end, words, totalWords, maxWidth)
                : generatePaddedString(start, end, words, totalWords, currentWordSpace, currentWordSpaceOverflow);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> justifiedText = new ArrayList<>();

        int remainingWidth = maxWidth;

        int count = 0;
        int firstWord = 0;
        int currentWordLength = 0;
        int currentTotalWords = 0;

        while (count < words.length) {
            String currentWord = words[count];
            if (remainingWidth - currentWord.length() >= currentTotalWords) {
                System.out.println(String.format("Adding #%d: %s", count, currentWord));

                // add to the list of words
                currentWordLength += currentWord.length();
                currentTotalWords += 1;
                remainingWidth -= currentWord.length();
            } else {
                String joinSentence = generateJoinSentence(maxWidth, currentWordLength, currentTotalWords, firstWord,
                        count, words, false);
                justifiedText.add(joinSentence);
                System.out.println(String.format("Current Line: %d words | %s", currentTotalWords, joinSentence));

                // go to next work
                System.out.println(String.format("Adding #%d: %s", count, currentWord));
                firstWord = count;
                currentWordLength = words[count].length();
                currentTotalWords = 1;
                remainingWidth = maxWidth - words[count].length();
            }
            count++;
        }

        String joinSentence = generateJoinSentence(maxWidth, currentWordLength, currentTotalWords, firstWord,
                count, words, true);
        justifiedText.add(joinSentence);
        System.out.println(String.format("Current Line: %d words | %s", currentTotalWords, joinSentence));

        return justifiedText;
    }
}
