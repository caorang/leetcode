package cn.hutool.jackie.algorithm.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @See <a href="https://leetcode.cn/problems/permutations-ii/">...</a>
 */
public class Permutations2 {

    boolean[] vis;

    public static void main(String[] args) {
        System.out.println(new Permutations2().permuteUnique(new int[] {3, 3, 0, 3}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrace(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> path, int[] nums) {
        if (path.size() >= nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            path.add(nums[i]);
            vis[i] = true;
            backtrace(result, path, nums);
            vis[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
