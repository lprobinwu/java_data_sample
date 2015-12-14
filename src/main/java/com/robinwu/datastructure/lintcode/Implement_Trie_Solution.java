package com.robinwu.datastructure.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 12/12/15.
 *
 * http://www.lintcode.com/en/problem/implement-trie/
 * Implement a trie with insert, search, and startsWith methods.

Have you met this question in a real interview? Yes
Example
Note
You may assume that all inputs are consist of lowercase letters a-z.

 *
 */
public class Implement_Trie_Solution {

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie trie = new Trie();
     * trie.insert("lintcode");
     * trie.search("lint"); will return false
     * trie.startsWith("lint"); will return true
     */
    class TrieNode {
        // Initialize your data structure here.
        String val;
        boolean isWord = false;
        List<TrieNode> children = new ArrayList<>();

        public TrieNode() {

        }
    }

    private TrieNode root;

    public Implement_Trie_Solution() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.isEmpty()) return;

        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            String search = word.substring(0, i+1);

            boolean found = false;
            for (TrieNode child: cur.children) {
                if (search.equals(child.val)) {
                    cur = child;
                    found = true;
                    break;
                }
            }

            // insert a new node.
            if (!found) {
                TrieNode next = new TrieNode();
                next.val = search;
                cur.children.add(next);
                cur = next;
            }
        }
        cur.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null || word.isEmpty()) return false;

        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            String search1 = word.substring(0, i+1);

            boolean found = false;
            for (TrieNode child: cur.children) {
                if (search1.equals(child.val)) {
                    cur = child;
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return cur.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) return true;

        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            String search1 = prefix.substring(0, i+1);

            boolean found = false;
            for (TrieNode child: cur.children) {
                if (search1.equals(child.val)) {
                    cur = child;
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Implement_Trie_Solution trie = new Implement_Trie_Solution();
        trie.insert("lintcode");
        System.out.println(trie.search("lint"));
        System.out.println(trie.startsWith("lint"));
    }

}
