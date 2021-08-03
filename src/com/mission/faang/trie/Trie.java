package com.mission.faang.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.endOfWord;
    }

    public void delete(String word) {
        deleteUtil(root, word, 0);
    }

    /**
     * Returns true if parent should delete the mapping
     */
    private boolean deleteUtil(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //when end of word is reached only delete if current.endOfWord is true.
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            //if current has no other mapping then return true
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = deleteUtil(node, word, index + 1);

        //if true is returned then delete the mapping of character and trie node reference from map.
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            //return true if no mappings are left in the map.
            return current.children.size() == 0;
        }
        return false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Atul");
        trie.insert("Atula");
        System.out.println(trie.search("Atula"));
        trie.delete("Atula");
        System.out.println(trie.search("Atula"));
    }

    private class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean endOfWord;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }
}
