package cn.hutool.jackie.algorithm.system;

/**
 * 给出两个一维的向量，请你实现一个迭代器，交替返回它们中间的元素。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 * <p>
 * 输出: [1,3,2,4,5,6]
 * <p>
 * 解析: 通过连续调用 next 函数直到 hasNext 函数返回 false，
 * next 函数返回值的次序应依次为: [1,3,2,4,5,6]。
 */
public class ZigzagIterator {
    private int[] v1;
    private int i = 0;
    private int[] v2;
    private int j = 0;
    private int flag = 0;

    public ZigzagIterator(int[] v1, int[] v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        if (hasNext()) {
            int ans = -1;
            if (flag == 0 && i < v1.length) {
                ans = v1[i++];
                flag = 1;
            } else if (j < v2.length) {
                ans = v2[j++];
                flag = 0;
            }
            return ans;
        }
        return -1;
    }

    public boolean hasNext() {
        return i < v1.length || j < v2.length;
    }

    public static void main(String[] args) {
        ZigzagIterator iterator = new ZigzagIterator(new int[] {1, 2, 3, 4}, new int[] {5, 6, 7, 8});
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print("→");
            }
        }
    }
}
