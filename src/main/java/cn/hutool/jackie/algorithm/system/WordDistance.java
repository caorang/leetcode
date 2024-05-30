package cn.hutool.jackie.algorithm.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请设计一个类，使该类的构造函数能够接收一个字符串数组。然后再实现一个方法，该方法能够分别接收两个单词，并返回列表中这两个单词之间的最短距离。
 * <p>
 * 实现 WordDistance 类:
 * <p>
 * WordDistance(String[] wordsDict) 用字符串数组 wordsDict 初始化对象。
 * int shortest(String word1, String word2) 返回数组 wordDict 中 word1 和 word2 之间的最短距离。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * 输出:
 * [null, 3, 1]
 * <p>
 * 解释：
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // 返回 3
 * wordDistance.shortest("makes", "coding");    // 返回 1
 *
 * @author rcao1
 */
public class WordDistance {

    private Map<String, List<Integer>> words;

    public WordDistance(String[] words) {
        this.words = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (this.words.containsKey(words[i])) {
                this.words.get(words[i]).add(i);
            } else {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(i);
                this.words.put(words[i], indexes);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> indexes1 = this.words.get(word1);
        List<Integer> indexes2 = this.words.get(word2);
        if (indexes1 == null || indexes2 == null) {
            return -1;
        }
        int ans = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < indexes1.size() && j < indexes2.size()) {
            ans = Math.min(ans, Math.abs(indexes1.get(i) - indexes2.get(j)));
            if (indexes1.get(i) <= indexes2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        WordDistance wordDistance = new WordDistance(new String[] {"practice", "makes", "perfect", "coding", "makes"});
        System.out.println(wordDistance.shortest("coding", "practice"));
        System.out.println(wordDistance.shortest("makes", "coding"));
    }
}
