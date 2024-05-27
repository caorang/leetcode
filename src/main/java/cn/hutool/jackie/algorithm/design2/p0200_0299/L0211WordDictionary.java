package cn.hutool.jackie.algorithm.design2.p0200_0299;

import java.util.Map;
import java.util.TreeMap;

/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * <p>
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * <p>
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // 返回 False
 * wordDictionary.search("bad"); // 返回 True
 * wordDictionary.search(".ad"); // 返回 True
 * wordDictionary.search("b.."); // 返回 True
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length <= 25
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 104 次 addWord 和 search
 *
 * @author rcao1
 */
public class L0211WordDictionary {

    public static class Trie {

        public Node root;

        private class Node {
            public String word;
            public boolean isWord;

            public Map<Character, Node> next;

            private Node(String word, boolean isWord) {
                this.word = word;
                this.isWord = isWord;
                this.next = new TreeMap<>();
            }
        }

        public Trie() {
            this.root = new Node("", false);
        }

        public void insert(String sentence) {
            if (sentence == null || sentence.length() == 0) {
                return;
            }
            Node node = this.root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < sentence.length(); i++) {
                Character c = sentence.charAt(i);
                prefix.append(c);
                Map<Character, Node> next = node.next;
                if (next.containsKey(c)) {
                    node = next.get(c);
                } else {
                    boolean isWord = i >= sentence.length() - 1 ? true : false;
                    Node newNode = new Node(prefix.toString(), isWord);
                    next.put(c, newNode);
                    node = newNode;
                }
            }
        }

        public boolean search(Node root, String sentence, StringBuilder prefix) {
            if (sentence == null || sentence.length() == 0) {
                return false;
            }
            Node node = root;
            for (int i = 0; i < sentence.length(); i++) {
                Character c = sentence.charAt(i);
                Map<Character, Node> next = node.next;
                if (next.containsKey(c)) {
                    prefix.append(c);
                    node = next.get(c);
                    if (node.isWord == true && i == sentence.length() - 1 && node.word.equals(prefix.toString())) {
                        return true;
                    }
                } else if (c == '.') {
                    if (next == null || next.size() == 0) {
                        return false;
                    }
                    for (Map.Entry<Character, Node> entry : next.entrySet()) {
                        Node searchNode = entry.getValue();
                        prefix.append(entry.getKey());
                        if (searchNode.isWord == true && i == sentence.length() - 1 && searchNode.word.equals(prefix.toString())) {
                            return true;
                        }
                        String searchSentence = sentence.substring(i + 1, sentence.length());
                        if (search(searchNode, searchSentence, prefix)) {
                            return true;
                        }
                        prefix.deleteCharAt(prefix.length() - 1);
                    }
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    private Trie dictionary;

    public L0211WordDictionary() {
        this.dictionary = new Trie();
    }

    public void addWord(String word) {
        this.dictionary.insert(word);
    }

    public boolean search(String word) {
        return this.dictionary.search(this.dictionary.root, word, new StringBuilder());
    }

    public static void main(String[] args) {
        L0211WordDictionary wordDictionary = new L0211WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        //System.out.println(wordDictionary.search("a"));
        //System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");
        System.out.println(wordDictionary.search(".at"));
    }
}
