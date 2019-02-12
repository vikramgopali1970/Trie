package Trie;

public class TrieNode {
    TrieNode[] children;
    char c;
    int count;
    boolean isEnd;
    int same;

    public TrieNode(){
        this.children = new TrieNode[36];
        this.count = 0;
        this.same = -1;
    }
}
