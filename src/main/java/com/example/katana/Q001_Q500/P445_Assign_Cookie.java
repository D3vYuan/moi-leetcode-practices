package com.example.katana.Q001_Q500;

import java.util.Arrays;

public class P445_Assign_Cookie {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int gStart = 0;
        int sStart = 0;

        int contentChildren = 0;
        while (gStart < g.length && sStart < s.length) {
            int gCurrent = g[gStart];
            while (sStart < s.length && gCurrent > s[sStart]) {
                sStart++;
            }

            // System.out.println(String.format("Comparing: g (%d - %d) | s (%d - %d)",
            // gStart,g[gStart], sStart, s[sStart]));
            if (sStart < s.length && gCurrent <= s[sStart]) {
                System.out.println(String.format("Found: Content Children #%d | Greedy %d | Cookie #%d | Satisfy %d",
                        gStart, gCurrent, sStart, s[sStart]));
                contentChildren += 1;
            }
            System.out.println(String.format("-- End Children #%d | Greedy %d --", gStart, gCurrent));
            gStart++;
            sStart++;
        }

        return contentChildren;
    }
}
