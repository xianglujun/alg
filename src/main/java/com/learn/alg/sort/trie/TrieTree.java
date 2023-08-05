package com.learn.alg.sort.trie;

public class TrieTree {

    private Node root;

    public TrieTree() {
        this.root = new Node(0, 0);
    }

    /**
     * 插入单词
     *
     * @param word 单词信息
     */
    public void insertWord(String word) {
        if (word == null || "".equals(word)) {
            return;
        }

        root.pass++;
        int path;
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (node.next[path] == null) {
                node.next[path] = new Node(0, 0);
            }
            node.next[path].pass++;
            node = node.next[path];
            if (i == word.length() - 1) {
                node.end++;
            }
        }
    }

    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.insertWord("hello");
        tree.insertWord("world");
        System.out.println(tree);
    }

    /**
     * 前缀树节点定义
     */
    public static class Node {
        private int pass;
        private int end;
        /**
         * 这里只是在明确的情况下，可以使用数组的方式
         * 如果路比较多的时候，可以使用hashmap的方式存储数据
         */
        private Node[] next;

        public Node(int pass, int end) {
            this.pass = pass;
            this.end = end;
            // 判断对应的i位置是否为null, 如果为null, 则表示没有路；否则为有路
            this.next = new Node[26];
        }
    }
}
