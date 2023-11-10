package com.example.Katana;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.katana.P2484_Count_Palindrome_Subsequences;

import static org.assertj.core.api.Assertions.*;

public class P2484_Count_Palindrome_Subsequences_Test {
    private P2484_Count_Palindrome_Subsequences application;

    @BeforeEach
    void setup(){
        application = new P2484_Count_Palindrome_Subsequences();
    }

    @Test
    @DisplayName("Test Case 1 - Test Input 103301")
    void test_case_1(){
        String input = "103301";
        int substringLength = 5;
        int expectedResult = 2;
        verify_result(input, substringLength, expectedResult);
    }

    void verify_result(String input, int substringLength, int expectedResult){
        int actualResult = application.countPalindromes(input);
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
