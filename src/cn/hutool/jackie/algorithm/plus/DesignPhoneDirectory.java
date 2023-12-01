package cn.hutool.jackie.algorithm.plus;

/**
 * 设计一个电话目录管理系统，让它支持以下功能：
 * <p>
 * get: 分配给用户一个未被使用的电话号码，获取失败请返回 -1
 * check: 检查指定的电话号码是否被使用
 * release: 释放掉一个电话号码，使其能够重新被分配
 * <p>
 * <p>
 * 示例：
 * <p>
 * // 初始化电话目录，包括 3 个电话号码：0，1 和 2。
 * PhoneDirectory directory = new PhoneDirectory(3);
 * <p>
 * // 可以返回任意未分配的号码，这里我们假设它返回 0。
 * directory.get();
 * <p>
 * // 假设，函数返回 1。
 * directory.get();
 * <p>
 * // 号码 2 未分配，所以返回为 true。
 * directory.check(2);
 * <p>
 * // 返回 2，分配后，只剩一个号码未被分配。
 * directory.get();
 * <p>
 * // 此时，号码 2 已经被分配，所以返回 false。
 * directory.check(2);
 * <p>
 * // 释放号码 2，将该号码变回未分配状态。
 * directory.release(2);
 * <p>
 * // 号码 2 现在是未分配状态，所以返回 true。
 * directory.check(2);
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= maxNumbers <= 10^4
 * 0 <= number < maxNumbers
 * 调用方法的总数处于区间 [0 - 20000] 之内
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/design-phone-directory/description/">...</a>
 */
public class DesignPhoneDirectory {

    public static void main(String[] args) {

    }
}

class PhoneDirectory {

    public PhoneDirectory(int maxNumbers) {

    }

    public int get() {
        return 0;
    }

    public boolean check(int number) {
        return false;
    }

    public void release(int number) {

    }
}
