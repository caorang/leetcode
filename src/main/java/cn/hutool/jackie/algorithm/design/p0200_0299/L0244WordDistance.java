package cn.hutool.jackie.algorithm.design.p0200_0299;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 请设计一个类，使该类的构造函数能够接收一个字符串数组。然后再实现一个方法，该方法能够分别接收两个单词，并返回列表中这两个单词之间的最短距离。
 * <p>
 * 实现 WordDistanc 类:
 * <p>
 * WordDistance(String[] wordsDict) 用字符串数组 wordsDict 初始化对象。
 * int shortest(String word1, String word2) 返回数组 worddict 中 word1 和 word2 之间的最短距离。
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
 * 注意:
 * 1 <= wordsDict.length <= 3 * 10^4
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 在数组 wordsDict 中
 * word1 != word2
 * shortest 操作次数不大于 5000
 *
 * @author rcao1
 */
public class L0244WordDistance {

    private Map<String, Set<Integer>> wordIndexes;

    public L0244WordDistance(String[] wordsDict) {
        this.wordIndexes = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            String word = wordsDict[i];
            this.wordIndexes.computeIfAbsent(word, key -> new HashSet<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        int distance = Integer.MAX_VALUE;
        if (word1 == null || word2 == null) {
            return distance;
        }
        Set<Integer> word1Indexes = this.wordIndexes.get(word1);
        Set<Integer> word2Indexes = this.wordIndexes.get(word2);
        if (word1Indexes == null || word2Indexes == null) {
            return distance;
        }
        for (int i : word1Indexes) {
            for (int j : word2Indexes) {
                distance = Math.min(distance, Math.abs(i - j));
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        /**
         * 输入:
         * ["WordDistance", "shortest", "shortest"]
         * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
         * 输出:
         * [null, 3, 1]
         */
        String method = "[\"WordDistance\", \"shortest\", \"shortest\"]";
        String arguments = "[[[\"practice\", \"makes\", \"perfect\", \"coding\", \"makes\"]], [\"coding\", \"practice\"], [\"makes\", \"coding\"]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(L0244WordDistance.class);
    }
}
