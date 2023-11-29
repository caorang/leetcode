package cn.hutool.jackie.algorithm.backtrace;

/**
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 * <p>
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 示例 3：
 * <p>
 * 输入：n = 3, k = 1
 * 输出："123"
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/permutation-sequence/description/">...</a>
 */
public class PermutationSequence {

    public static void main(String[] args) {
        System.out.println(new PermutationSequence().getPermutation(3, 3));
        System.out.println(new PermutationSequence().getPermutation(4, 9));
    }

    public String getPermutation(int n, int k) {
        StringBuilder ans = new StringBuilder();
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i < n; ++i) {
            int fact = 1;
            for (int j = 1; j < n - i; ++j) {
                fact *= j;
            }
            for (int j = 1; j <= n; ++j) {
                if (!vis[j]) {
                    if (k > fact) {
                        k -= fact;
                    } else {
                        ans.append(j);
                        vis[j] = true;
                        break;
                    }
                }
            }
        }
        return ans.toString();
    }
}
