package com.example.solution.Q1501_Q2000;

import java.util.Stack;

public class P1598_Crawler_Log_Folder {
    /**
     * Reference:
     * https://leetcode.com/problems/crawler-log-folder/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an empty stack folderStack. This stack will track the sequence
     * of directories as we navigate through them.
     * 2. For each currentOperation in logs, perform the following steps:
     * a. If currentOperation equals "../", check if folderStack is not empty. If
     * true, pop the top directory from folderStack to move up to the parent
     * directory.
     * b. If currentOperation equals "./", ignore it as it signifies staying in the
     * current directory and does not change the stack.
     * c. For any other currentOperation, push currentOperation onto folderStack,
     * indicating we are entering a new directory.
     * 3. Operations such as "./" are ignored because they do not change the current
     * directory structure represented by folderStack.
     * 4. Return the size of folderStack as it represents the minimum number of
     * operations required to navigate the file system effectively. The size of
     * folderStack corresponds to the depth of the directory structure we have
     * navigated.
     */

    public int minOperations(String[] logs) {
        Stack<String> folderStack = new Stack<>();

        for (String currentOperation : logs) {
            if (currentOperation.equals("../")) {
                // Move up to parent directory if not already at main folder
                if (!folderStack.empty()) {
                    folderStack.pop();
                }
            } else if (!currentOperation.equals("./")) {
                // Enter a new folder
                folderStack.push(currentOperation);
            }
            // Ignore "./" operations as they don't change the current folder
        }

        // The stack size represents the number of folders deep we are
        return folderStack.size();
    }
}
