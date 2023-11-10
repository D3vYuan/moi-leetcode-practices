package com.example.template;

import java.util.HashMap;
import java.util.Map;

public class Tries {
    class TrieNode {
        // you can store data at nodes if you wish
        int data;
        Map<Character, TrieNode> children;

        TrieNode() {
            this.children = new HashMap<>();
        }
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }

                curr = curr.children.get(c);
            }

            // at this point, you have a full word at curr
            // you can perform more logic here to give curr an attribute if you want
        }

        return root;
    }
}
