package cn.hutool.jackie.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/two-sum-iii-data-structure-design/">...</a>
 * <p>
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
public class TwoSum3 {
    Map<Integer, Integer> map = new HashMap<>();

    public TwoSum3() {

    }

    public void add(int number) {
        if (!map.containsKey(number)) {
            map.put(number, 1);
        } else {
            map.put(number, map.get(number) + 1);
        }
    }

    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int current = entry.getKey();
            int target = value - current;
            if (target == current) {
                if (map.get(target) > 1) {
                    return true;
                }
            } else if (map.containsKey(target)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSum3 twoSum = new TwoSum3();
        // [] --> [1]
        twoSum.add(1);
        // [1] --> [1,3]
        twoSum.add(3);
        // [1,3] --> [1,3,5]
        twoSum.add(5);
        // 1 + 3 = 4，返回 true
        System.out.println(twoSum.find(4));
        // 没有两个整数加起来等于 7 ，返回 false
        System.out.println(twoSum.find(7));
    }
}
