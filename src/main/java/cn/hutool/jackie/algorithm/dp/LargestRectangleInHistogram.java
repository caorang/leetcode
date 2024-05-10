package cn.hutool.jackie.algorithm.dp;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * <p>
 * <p>
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * <p>
 * 提示：
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/description/">...</a>
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(new LargestRectangleInHistogram().largestRectangleArea(heights));
    }

    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        if (heights == null || heights.length == 0) {
            return ans;
        }
        int[] dp = new int[heights.length];
        dp[0] = heights[0];
        for (int i = 1; i < heights.length; i++) {
            dp[i] = Math.min(heights[i], dp[i - 1]);
        }
        return ans;
    }
}
