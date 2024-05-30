package cn.hutool.jackie.algorithm.design2.p2900_2999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个仅由小写英文字母组成的字符串 s 。
 * <p>
 * 如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 * <p>
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 * <p>
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aaaa"
 * 输出：2
 * 解释：出现三次的最长特殊子字符串是 "aa" ：子字符串 "aaaa"、"aaaa" 和 "aaaa"。
 * 可以证明最大长度是 2 。
 * 示例 2：
 * <p>
 * 输入：s = "abcdef"
 * 输出：-1
 * 解释：不存在出现至少三次的特殊子字符串。因此返回 -1 。
 * 示例 3：
 * <p>
 * 输入：s = "abcaba"
 * 输出：1
 * 解释：出现三次的最长特殊子字符串是 "a" ：子字符串 "abcaba"、"abcaba" 和 "abcaba"。
 * 可以证明最大长度是 1 。
 * 提示：
 * <p>
 * 3 <= s.length <= 5 * 10^5
 * s 仅由小写英文字母组成。
 *
 * @author rcao1
 */
public class L2982MaximumLength {

    public int maximumLength(String s) {
        char[] charArray = s.toCharArray();
        List<Integer>[] groups = new ArrayList[26];
        Arrays.setAll(groups, i -> new ArrayList<>());
        int cnt = 0;
        for (int i = 0; i < charArray.length; i++) {
            cnt++;
            if (i + 1 == charArray.length || charArray[i] != charArray[i + 1]) {
                groups[charArray[i] - 'a'].add(cnt); // 统计连续字符长度
                cnt = 0;
            }
        }

        int ans = 0;
        for (List<Integer> group : groups) {
            if (group.isEmpty()) {
                continue;
            }
            /**
             * - 从最长的特殊子串（$a[0]$）中取三个长度均为 $a[0]-2$ 的特殊子串。例如示例 1 的 aaaa 可以取三个 aa。
             * - 或者，从最长和次长的特殊子串（$a[0],a[1]$）中取三个长度一样的特殊子串：
             *   - 如果 $a[0]=a[1]$，那么可以取三个长度均为 $a[0]-1$ 的特殊子串。
             *   - 如果 $a[0]>a[1]$，那么可以取三个长度均为 $a[1]$ 的特殊子串：从最长中取两个，从次长中取一个。
             *   - 这两种情况合并成 $\min(a[0]-1, a[1])$。
             * - 又或者，从最长、次长、第三长的的特殊子串（$a[0],a[1],a[2]$）中各取一个长为 $a[2]$ 的特殊子串。
             *
             * 这三种情况取最大值
             */
            group.sort(Collections.reverseOrder());
            group.add(0);
            group.add(0);
            ans = Math.max(ans, Math.max(group.get(0) - 2, Math.max(Math.min(group.get(0) - 1, group.get(1)), group.get(2))));
        }

        return ans > 0 ? ans : -1;
    }

    public static void main(String[] args) {
        System.out.println(new L2982MaximumLength().maximumLength("aaaa"));
        System.out.println(new L2982MaximumLength().maximumLength("abcdef"));
        System.out.println(new L2982MaximumLength().maximumLength("abcaba"));
    }
}
