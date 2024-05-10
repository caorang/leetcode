package cn.hutool.jackie.algorithm.design2.p0700_0799;

import java.util.Map;
import java.util.TreeMap;

/**
 * Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
 * <p>
 * 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。
 * <p>
 * 实现 RangeModule 类:
 * <p>
 * RangeModule() 初始化数据结构的对象。
 * void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
 * boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true ，否则返回 false 。
 * void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * 输出
 * [null, null, null, true, false, true]
 * <p>
 * 解释
 * RangeModule rangeModule = new RangeModule();
 * rangeModule.addRange(10, 20);
 * rangeModule.removeRange(14, 16);
 * rangeModule.queryRange(10, 14); 返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
 * rangeModule.queryRange(13, 15); 返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
 * rangeModule.queryRange(16, 17); 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= left < right <= 10^9
 * 在单个测试用例中，对addRange、queryRange和removeRange的调用总数不超过 10^4 次
 *
 * @author rcao1
 */
public class L0715RangeModule {

    private TreeMap<Integer, Integer> tm;

    public L0715RangeModule() {
        this.tm = new TreeMap<>();
    }

    /**
     * 添加 半开区间 [left, right)，跟踪该区间中的每个实数
     * 添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中
     *
     * @param left
     * @param right
     */
    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> leftEntry = tm.floorEntry(left);
        boolean success;
        if (leftEntry != null) {
            if (leftEntry.getValue() < left) {
                tm.put(left, right);
            } else {
                tm.remove(leftEntry.getKey());
                tm.put(leftEntry.getKey(), right);
            }
            success = true;
        } else {
            tm.put(left, right);
            success = true;
        }
        Map.Entry<Integer, Integer> rightEntry = tm.ceilingEntry(left);
        if (rightEntry != null) {
            if (rightEntry.getKey() > right) {
                tm.put(left, right);
            } else {
                tm.remove(rightEntry.getKey());
                tm.put(left, rightEntry.getValue());
            }
        } else if (success == false) {
            tm.put(left, right);
        }
    }

    /**
     * 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数
     *
     * @param left
     * @param right
     */
    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> leftEntry = tm.floorEntry(left);
        if (leftEntry != null) {
            if (leftEntry.getValue() > left) {
                if (right < leftEntry.getValue()) {
                    tm.put(right, leftEntry.getValue());
                }
                tm.remove(leftEntry.getKey());
                tm.put(leftEntry.getKey(), left);
            }
        }
        Map.Entry<Integer, Integer> rightEntry = tm.ceilingEntry(left);
        if (rightEntry != null) {
            if (rightEntry.getKey() < right) {
                if (right < rightEntry.getValue()) {
                    tm.put(right, rightEntry.getValue());
                }
                tm.remove(rightEntry.getKey());
            }
        }
    }

    /**
     * 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true ，否则返回 false
     *
     * @param left
     * @param right
     * @return
     */
    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> ent = tm.floorEntry(left);
        if (ent != null && ent.getValue() >= right) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        //case1();
        case2();
    }

    private static void case2() {
        //["RangeModule","addRange","removeRange","removeRange","addRange","removeRange","addRange","queryRange","queryRange","queryRange"]
        //[[],[6,8],[7,8],[8,9],[8,9],[1,3],[1,8],[2,4],[2,9],[4,6]]
        L0715RangeModule rangeModule = new L0715RangeModule();
        rangeModule.addRange(6, 8);
        rangeModule.removeRange(7, 8);
        rangeModule.removeRange(8, 9);
        rangeModule.addRange(8, 9);
        rangeModule.removeRange(1, 3);
        rangeModule.addRange(1, 8);
        System.out.println(rangeModule.queryRange(2, 4));
        System.out.println(rangeModule.queryRange(2, 9));
        System.out.println(rangeModule.queryRange(4, 6));
    }

    private static void case1() {
        L0715RangeModule rangeModule = new L0715RangeModule();
        rangeModule.addRange(10, 20);
        rangeModule.removeRange(14, 16);
        //返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
        System.out.println(rangeModule.queryRange(10, 14));
        //返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
        System.out.println(rangeModule.queryRange(13, 15));
        // 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
        System.out.println(rangeModule.queryRange(16, 17));
    }
}
