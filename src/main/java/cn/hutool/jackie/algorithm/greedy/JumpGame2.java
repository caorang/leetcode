package cn.hutool.jackie.algorithm.greedy;

/**
 * @author rcao1
 * @see <a href="https://leetcode.com/problems/jump-game-ii/">...</a>
 */
public class JumpGame2 {

    public static void main(String[] args) {
        System.out.println(new JumpGame2().jump(new int[] {2, 3, 1, 1, 4}));
        System.out.println(new JumpGame2().jump(new int[] {2, 2, 0, 1, 4}));
        System.out.println(new JumpGame2().jump(new int[] {3, 2, 1, 0, 4}));
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
