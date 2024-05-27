package cn.hutool.jackie.algorithm.array;

/**
 * 给定一个字符串数组 wordDict 和两个已经存在于该数组中的不同的字符串 word1 和 word2 。返回列表中这两个单词之间的最短距离。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * 输出: 1
 * <p>
 * <p>
 * 提示:
 * 1 <= wordsDict.length <= 3 * 104
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 在 wordsDict 中
 * word1 != word2
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/shortest-word-distance/description/">...</a>
 */
public class ShortestWordDistance {

    public static void main(String[] args) {
        String[] wordsDict = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "practice";
        System.out.println(new ShortestWordDistance().shortestDistance(wordsDict, word1, word2));

        wordsDict = new String[]{"practice", "makes", "perfect", "coding", "makes"};
        word1 = "makes";
        word2 = "coding";
        System.out.println(new ShortestWordDistance().shortestDistance(wordsDict, word1, word2));
    }

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        if (wordsDict == null || wordsDict.length == 0) {
            return 0;
        }
        if (word1 == null || word2 == null || word1.equals(word2)) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0, left = -1, right = -1; i < wordsDict.length; i++) {
            String thisWord = wordsDict[i];
            if (thisWord.equals(word1)) {
                left = i;
            } else if (thisWord.equals(word2)) {
                right = i;
            }
            if (left != -1 && right != -1) {
                ans = Math.min(ans, Math.abs(left - right));
            }
        }
        return ans;
    }
}
