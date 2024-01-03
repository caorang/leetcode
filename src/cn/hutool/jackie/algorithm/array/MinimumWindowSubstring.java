package cn.hutool.jackie.algorithm.array;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * <p>
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/minimum-window-substring/description/">...</a>
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(new MinimumWindowSubstring().minWindow("a", "a"));
        System.out.println(new MinimumWindowSubstring().minWindow("a", "aa"));
        System.out.println(new MinimumWindowSubstring().minWindow("abc", "c"));
    }

    public String minWindow(String s, String t) {
        int[] need = new int[128];
        int[] window = new int[128];
        int m = s.length(), n = t.length();
        for (int i = 0; i < n; ++i) {
            ++need[t.charAt(i)];
        }
        int cnt = 0, j = 0, k = -1, mi = 1 << 30;
        for (int i = 0; i < m; ++i) {
            ++window[s.charAt(i)];
            if (need[s.charAt(i)] >= window[s.charAt(i)]) {
                ++cnt;
            }
            while (cnt == n) {
                if (i - j + 1 < mi) {
                    mi = i - j + 1;
                    k = j;
                }
                if (need[s.charAt(j)] >= window[s.charAt(j)]) {
                    --cnt;
                }
                --window[s.charAt(j++)];
            }
        }
        return k < 0 ? "" : s.substring(k, k + mi);
    }

    public String minWindow2(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        if (!contains(s, t)) {
            return "";
        }
        String ans = s;
        for (int left = 0, right = 0; left < s.length() && right < s.length(); ) {
            char c = s.charAt(left);
            if (t.indexOf(c) != -1) {
                String subStr = s.substring(left, right + 1);
                if (contains(subStr, t)) {
                    ans = subStr.length() < ans.length() ? subStr : ans;
                    left++;
                    continue;
                }
                right++;
            } else {
                left++;
                right = left;
            }
        }
        return ans;
    }

    private boolean contains(String s, String t) {
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (s.indexOf(c) == -1) {
                return false;
            }
            s = s.replaceFirst(c + "", "");
        }
        return s.length() >= 0;
    }
}
