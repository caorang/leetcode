package cn.hutool.jackie.algorithm.design2.p0600_0699;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 题目描述
 * 为搜索引擎设计一个搜索自动补全系统。用户会输入一条语句（最少包含一个字母，以特殊字符 '#' 结尾）。
 * <p>
 * 给定一个字符串数组 sentences 和一个整数数组 times ，长度都为 n ，其中 sentences[i] 是之前输入的句子， times[i] 是该句子输入的相应次数。对于除 ‘#’ 以外的每个输入字符，返回前 3 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。
 * <p>
 * 下面是详细规则：
 * <p>
 * 一条句子的热度定义为历史上用户输入这个句子的总次数。
 * 返回前 3 的句子需要按照热度从高到低排序（第一个是最热门的）。如果有多条热度相同的句子，请按照 ASCII 码的顺序输出（ASCII 码越小排名越前）。
 * 如果满足条件的句子个数少于 3 ，将它们全部输出。
 * 如果输入了特殊字符，意味着句子结束了，请返回一个空集合。
 * 实现 AutocompleteSystem 类：
 * <p>
 * AutocompleteSystem(String[] sentences, int[] times): 使用数组sentences 和 times 对对象进行初始化。
 * List<String> input(char c) 表示用户输入了字符 c 。
 * 如果 c == '#' ，则返回空数组 [] ，并将输入的语句存储在系统中。
 * 返回前 3 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。如果少于 3 个匹配项，则全部返回。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入
 * ["AutocompleteSystem", "input", "input", "input", "input"]
 * [[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
 * 输出
 * [null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]
 * <p>
 * 解释
 * AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
 * obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
 * obj.input("a"); // return []. There are no sentences that have prefix "i a".
 * obj.input("#"); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 * <p>
 * <p>
 * 提示:
 * <p>
 * n == sentences.length
 * n == times.length
 * 1 <= n <= 100
 * 1 <= sentences[i].length <= 100
 * 1 <= times[i] <= 50
 * c 是小写英文字母， '#', 或空格 ' '
 * 每个被测试的句子将是一个以字符 '#' 结尾的字符 c 序列。
 * 每个被测试的句子的长度范围为 [1,200]
 * 每个输入句子中的单词用单个空格隔开。
 * input 最多被调用 5000 次
 *
 * @author rcao1
 */
public class L0642AutocompleteSystem {
    public class Trie {

        private class Node {
            public String word; // 当前词组
            public boolean isWord; // 是否是某个单词的结束
            public int times; // 次数
            public TreeMap<Character, Node> next; //到下一个节点的映射

            public Node(boolean isWord, int times) {
                this.isWord = isWord;
                this.times = times;
                next = new TreeMap<>();
            }

            public Node() {
                this(false, 0);
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String sentence, int time) {
            if (sentence == null || sentence.length() == 0) {
                return;
            }
            Node node = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < sentence.length(); i++) {
                Character c = sentence.charAt(i);
                prefix.append(c);
                TreeMap<Character, Node> next = node.next;
                if (next.containsKey(c)) {
                    node = next.get(c);
                } else {
                    int count = 0;
                    boolean isWord = false;
                    if (i == sentence.length() - 1) {
                        isWord = true;
                        count = time;
                    }
                    Node newNode = new Node(isWord, count);
                    newNode.word = prefix.toString();
                    next.put(c, newNode);
                    node = newNode;
                }
            }
        }

        public Map<String, Integer> search(String prefix) {
            Map<String, Integer> result = new HashMap<>(8);
            if (prefix == null || prefix.length() == 0) {
                return result;
            }
            Node node = this.root;
            for (int i = 0; i < prefix.length(); i++) {
                Character c = prefix.charAt(i);
                TreeMap<Character, Node> next = node.next;
                if (next.containsKey(c)) {
                    node = next.get(c);
                    if (node.isWord == true) {
                        result.put(node.word, node.times);
                    }
                } else {
                    return new HashMap<>(8);
                }
            }
            if (node != null && node.isWord != true) {
                return getNodeWords(node);
            }
            return result;
        }

        private Map<String, Integer> getNodeWords(Node node) {
            Map<String, Integer> result = new HashMap<>(8);
            for (Map.Entry<Character, Node> entry : node.next.entrySet()) {
                Node child = entry.getValue();
                if (child.isWord == true) {
                    result.put(child.word, child.times);
                } else {
                    result.putAll(getNodeWords(child));
                }
            }
            return result;
        }
    }

    Trie dictionary;

    private StringBuilder buffer;

    public static final char CHAR_END = '#';

    public L0642AutocompleteSystem(String[] sentences, int[] times) {
        this.dictionary = initTrie(sentences, times);
        this.buffer = new StringBuilder();
    }

    private Trie initTrie(String[] sentences, int[] times) {
        Trie trie = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            trie.insert(sentences[i], times[i]);
        }
        return trie;
    }

    /**
     * 表示用户输入了字符c
     * 如果 c == '#' ，则返回空数组 [] ，并将输入的语句存储在系统中。
     * 返回前 3 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。如果少于 3 个匹配项，则全部返回
     */
    public List<String> input(char c) {
        if (c == CHAR_END) {
            this.buffer = new StringBuilder();
            return new ArrayList<>();
        }
        this.buffer.append(c);
        return searchWords();
    }

    private List<String> searchWords() {
        Map<String, Integer> words = this.dictionary.search(this.buffer.toString());
        // 按照次数从大到小取3个，如果数量一致，则按照ASCII码排序
        List<Map.Entry<String, Integer>> list = new LinkedList<>(words.entrySet());
        // 使用Comparator对list进行排序
        list.sort((o1, o2) -> {
            int valueCompare = o2.getValue().compareTo(o1.getValue());
            if (valueCompare == 0) {
                // 如果value一致，按照key的ASCII值大小排序
                return o1.getKey().compareTo(o2.getKey());
            }
            return valueCompare;
        });

        List<String> result = list.stream().map(i -> i.getKey()).collect(Collectors.toList());
        return result.size() > 3 ? result.subList(0, 3) : result;
    }

    public static void main(String[] args) {
        String[] input = new String[] {"i love you", "island", "iroman", "i love leetcode"};
        int[] times = new int[] {5, 3, 2, 2};
        L0642AutocompleteSystem obj = new L0642AutocompleteSystem(input, times);
        // return ["i love you", "island", "i love leetcode"]
        System.out.println(obj.input('i'));
        // return ["i love you", "i love leetcode"]
        System.out.println(obj.input(' '));
        // return []
        System.out.println(obj.input('a'));
        // return []
        System.out.println(obj.input('#'));

        // return ["i love you", "island", "i love leetcode"]
        System.out.println(obj.input('i'));
        // return ["island"]
        System.out.println(obj.input('s'));
        // return []
        System.out.println(obj.input('#'));
    }
}
