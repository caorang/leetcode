package cn.hutool.jackie.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/majority-element/">...</a>
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(new MajorityElement().majorityElement(nums));
    }

    public int majorityElement(int[] nums) {
        int f = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > f) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
