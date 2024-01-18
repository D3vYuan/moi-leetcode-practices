package com.example.katana.Q2001_Q2500;

import java.util.HashMap;
import java.util.Map;

public class P2125_Number_Laser_Beams {
    Map<Integer, Integer> rowDevices = new HashMap<>();

    private void generateBankSecurityDevice(String[] bank) {
        for (int i = 0; i < bank.length; i++) {
            rowDevices.put(i, 0);
            String currentRow = bank[i];
            for (int j = 0; j < currentRow.length(); j++) {
                if (currentRow.charAt(j) == '1') {
                    rowDevices.put(i, rowDevices.getOrDefault(i, 0) + 1);
                    // System.out.println(String.format("Adding Device: Row #%d (%d)", i,
                    // rowDevices.get(i)));
                }
            }
        }
    }

    public int numberOfBeams(String[] bank) {
        generateBankSecurityDevice(bank);
        int currentRowDevices = 0;
        int previousRowDevices = 0;
        int totalBeams = 0;

        for (int i = rowDevices.size() - 1; i > 0; i--) {
            // System.out.println(String.format("Processing: Row #%s", i));
            if (rowDevices.getOrDefault(i, 0) == 0) {
                continue;
            }
            currentRowDevices = rowDevices.get(i);
            int previousRow = i - 1;
            while (previousRow >= 0) {
                if (rowDevices.getOrDefault(previousRow, 0) == 0) {
                    previousRow -= 1;
                    continue;
                } else {
                    previousRowDevices = rowDevices.get(previousRow);
                    totalBeams += currentRowDevices * previousRowDevices;
                    break;
                }
            }
            i = previousRow + 1;
            // System.out.println(String.format("Processing: Row #%s", i));
        }

        return totalBeams;
    }
}
