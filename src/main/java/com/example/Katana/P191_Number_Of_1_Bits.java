package com.example.katana;

import java.util.Arrays;
import java.util.stream.Collectors;

public class P191_Number_Of_1_Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        System.out.println(String.format("n: %d", n));
        String binaryString = Integer.toBinaryString(n);
        return Arrays.stream(binaryString.split("")).filter(num -> num.equals("1")).collect(Collectors.toList()).size();
    }
}
