package com.example.katana;

import java.util.Arrays;
import java.util.stream.Collectors;

public class P1422_Maximum_Score_After_String {
    public int maxScore(String s) {
        int leftZeroes = 0;
        int rightOnes = 0;
        int startElement = 0;
        int nextElement = startElement + 1;

        if (s.length() == 1) {
            return 1;
        }

        String initialLeft = s.substring(0, 1);
        String initialRight = s.substring(1, s.length());

        leftZeroes = initialLeft.equals("0") ? 1 : 0;
        rightOnes = Arrays.stream(initialRight.split("")).filter(number -> number.equals("1"))
                .collect(Collectors.toList()).size();

        int maximumScore = leftZeroes + rightOnes;
        int currentScore = maximumScore;

        System.out.println(String.format("Initial: Left (%s) | Right (%s) | Score: %d | Maximum: %d", initialLeft,
                initialRight, maximumScore, maximumScore));

        while (nextElement < s.length() - 1) {
            char nextCharacter = s.charAt(nextElement);
            int leftAddition = 0;
            int rightAddition = 0;

            if (nextCharacter == '0') {
                leftAddition = 1;
            } else { // move 1 to the left
                rightAddition = -1;
            }

            currentScore = currentScore + leftAddition + rightAddition;
            System.out.println(
                    String.format("Next #%d: Moving (%s) to left | Left: %d | Right: %d | Score: %d | Maximum: %d",
                            nextElement, nextCharacter, leftAddition, rightAddition, currentScore, maximumScore));

            maximumScore = Math.max(maximumScore, currentScore);

            nextElement++;
        }

        return maximumScore;
    }
}
