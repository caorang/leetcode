package cn.hutool.jackie.algorithm.dp;

/**
 * 给定两个字符串 s 和 t ，如果它们的编辑距离为 1 ，则返回 true ，否则返回 false 。
 * <p>
 * 字符串 s 和字符串 t 之间满足编辑距离等于 1 有三种可能的情形：
 * <p>
 * 往 s 中插入 恰好一个 字符得到 t
 * 从 s 中删除 恰好一个 字符得到 t
 * 在 s 中用 一个不同的字符 替换 恰好一个 字符得到 t
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 可以将 'c' 插入字符串 s 来得到 t。
 * 示例 2:
 * <p>
 * 输入: s = "cab", t = "ad"
 * 输出: false
 * 解释: 无法通过 1 步操作使 s 变为 t。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 0 <= s.length, t.length <= 104
 * s 和 t 由小写字母，大写字母和数字组成
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/one-edit-distance/">...</a>
 */
public class OneEditDistance {

    public static void main(String[] args) {
        String s = "ab";
        String t = "acb";
        System.out.println(new OneEditDistance().isOneEditDistance(s, t));
        s = "cab";
        t = "ad";
        System.out.println(new OneEditDistance().isOneEditDistance(s, t));
    }

    public boolean isOneEditDistance(String s, String t) {
        return minDistance(s, t) == 1;
    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
