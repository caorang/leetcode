package cn.hutool.jackie.algorithm.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/maximum-sum-queries">...</a>
 */
public class MaximumSumQueries {

    public static void main(String[] args) {
        int[] nums1 = {4, 3, 1, 2};
        int[] nums2 = {2, 4, 9, 5};
        int[][] queries = {{4, 1}, {1, 3}, {2, 5}};
        MaximumSumQueries maximumSumQueries = new MaximumSumQueries();
        System.out.println(Arrays.toString(maximumSumQueries.maximumSumQueries2(nums1, nums2, queries)));
    }

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            result[i] = maximumSum(nums1, nums2, query[0], query[1]);
        }
        return result;
    }

    private int maximumSum(int[] nums1, int[] nums2, int min1, int min2) {
        int result = -1;
        for (int j = 0; j < nums1.length; j++) {
            if (nums1[j] >= min1 && nums2[j] >= min2) {
                result = Math.max(result, nums1[j] + nums2[j]);
            }
        }
        return result;
    }

    public int[] maximumSumQueries2(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] sortedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNums[i][0] = nums1[i];
            sortedNums[i][1] = nums2[i];
        }
        Arrays.sort(sortedNums, (a, b) -> b[0] - a[0]);
        int q = queries.length;
        int[][] sortedQueries = new int[q][3];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = i;
            sortedQueries[i][1] = queries[i][0];
            sortedQueries[i][2] = queries[i][1];
        }
        Arrays.sort(sortedQueries, (a, b) -> b[1] - a[1]);
        List<int[]> stack = new ArrayList<>();
        int[] answer = new int[q];
        Arrays.fill(answer, -1);
        int j = 0;
        for (int[] query : sortedQueries) {
            int i = query[0], x = query[1], y = query[2];
            while (j < n && sortedNums[j][0] >= x) {
                int[] pair = sortedNums[j];
                int num1 = pair[0], num2 = pair[1];
                while (!stack.isEmpty() && stack.get(stack.size() - 1)[1] <= num1 + num2) {
                    stack.remove(stack.size() - 1);
                }
                if (stack.isEmpty() || stack.get(stack.size() - 1)[0] < num2) {
                    stack.add(new int[]{num2, num1 + num2});
                }
                j++;
            }
            int k = binarySearch(stack, y);
            if (k < stack.size()) {
                answer[i] = stack.get(k)[1];
            }
        }
        return answer;
    }

    public int binarySearch(List<int[]> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid)[0] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
