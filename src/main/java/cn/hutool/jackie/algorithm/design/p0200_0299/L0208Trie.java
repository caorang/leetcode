package cn.hutool.jackie.algorithm.design.p0200_0299;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * 示例：
 * <p>
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 *
 * @author rcao1
 */
public class L0208Trie {

    public static class Dictionary {
        Character character;
        boolean isWord;
        Map<Character, Dictionary> children;

        public Dictionary() {
            this.children = new HashMap<>();
            this.isWord = false;
        }
    }

    private Dictionary root;

    public L0208Trie() {
        this.root = new Dictionary();
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        Dictionary current = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Map<Character, Dictionary> children = current.children;
            if (children.containsKey(c)) {
                current = children.get(c);
            } else {
                Dictionary child = new Dictionary();
                child.character = c;
                current = child;
                children.put(c, child);
            }
            if (i == word.length() - 1) {
                current.isWord = true;
            }
        }
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        Dictionary current = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Map<Character, Dictionary> children = current.children;
            if (children.containsKey(c)) {
                current = children.get(c);
            } else {
                return false;
            }
        }
        return current.isWord;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        Dictionary current = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            Map<Character, Dictionary> children = current.children;
            if (children.containsKey(c)) {
                current = children.get(c);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
         * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
         * 输出
         * [null, null, true, false, true, null, true]
         */
        String method = "[\"Trie\", \"insert\", \"search\", \"search\", \"startsWith\", \"insert\", \"search\"]";
        String arguments = "[[], [\"apple\"], [\"apple\"], [\"app\"], [\"app\"], [\"app\"], [\"app\"]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(L0208Trie.class);
    }
}
