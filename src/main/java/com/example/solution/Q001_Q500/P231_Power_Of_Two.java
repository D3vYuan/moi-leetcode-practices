package com.example.solution.Q001_Q500;

public class P231_Power_Of_Two {
    /**
     * Reference: https://leetcode.com/problems/power-of-two/editorial/
     * 
     * Explanation:
     * 1. a power of two in binary representation is one 1-bit, followed by some
     * zeros:
     * a. 1=(00000001)
     * b. 2=(00000010)
     * c. 4=(00000100)
     * d. 8=(00001000)
     * e. The only exception is 0, which should be treated separately.
     * 2. x & (-x) is a way to keep the rightmost 1-bit and to set all the other
     * bits to 0.
     * 3. In two's complement notation −x is the same as ¬x+1 (invert x binary + 1).
     * In other words, to compute −x one has to revert all bits in x and then add 1
     * to the result.
     * 4. Adding 1 to ¬x in binary representation means to carry that 1-bit till the
     * rightmost 0-bit in ¬x and to set all the lower bits to zero. Note, that the
     * rightmost 0-bit in ¬x corresponds to the rightmost 1-bit in x
     * 5. In summary, −x is the same as ¬x+1. This operation reverts all bits of x
     * except the rightmost 1-bit.
     * ​6. x and -x have just one bit in common - the rightmost 1-bit. That means
     * that x & (-x) would keep that rightmost 1-bit and set all the other bits to
     * 0.
     * 7. for the power of two, it would result in x itself, since a power of two
     * contains just one 1-bit. (see #1)
     * 8. Other numbers have more than 1-bit in their binary representation and
     * hence for them x & (-x) would not be equal to x itself
     * 9. a number is a power of two if x & (-x) == x
     * 
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0)
            return false;
        long x = (long) n;
        return (x & (-x)) == x;
    }
}
