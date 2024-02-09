package com.example.katana.Q1001_Q1500;

import java.util.ArrayList;
import java.util.List;

public class P1291_Sequential_Digits {
    private boolean isSequential(int number) {
        int previousNumber = -1;
        while (number > 0) {
            if (previousNumber == -1) {
                previousNumber = number % 10;
            } else {
                int currentNumber = number % 10;
                if (previousNumber - currentNumber != 1) {
                    return false;
                }
                previousNumber = currentNumber;
            }

            number /= 10;
        }

        return true;
    }

    private int[] generateStartSequence(int length, int firstNumber) {

        int increment = 0;
        int startNumber = 0;
        int nextNumber = firstNumber;

        int count = 0;

        while (count < length) {
            increment = increment * 10 + 1;
            startNumber = startNumber * 10 + nextNumber;
            nextNumber++;
            count++;
        }

        System.out.println(String.format("Start Number: %d | Increment: %d", startNumber, increment));
        return new int[] { startNumber, increment };
    }

    public List<Integer> sequentialDigits(int low, int high) {
        String lowString = String.valueOf(low);
        int firstNumber = Integer.valueOf(String.valueOf(lowString.charAt(0)));

        int[] startSequence = generateStartSequence(lowString.length(), firstNumber);
        int startNumber = startSequence[0];
        int increment = startSequence[1];

        List<Integer> sequeuntialNumbers = new ArrayList<>();
        int startLength = lowString.length();

        while (startNumber <= high) {
            if (startNumber < low) {
                startNumber += increment;
                continue;
            }

            boolean sequential = isSequential(startNumber);
            System.out.println(String.format("Sequential: %d [%s]", startNumber, sequential));
            if (sequential) {
                sequeuntialNumbers.add(startNumber);
                startNumber += increment;
            } else {
                int[] nextSequence = generateStartSequence(startLength + 1, 1);
                startNumber = nextSequence[0];
                increment = nextSequence[1];
                startLength++;
            }
        }

        return sequeuntialNumbers;
    }
}
