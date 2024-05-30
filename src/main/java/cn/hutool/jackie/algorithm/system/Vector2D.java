package cn.hutool.jackie.algorithm.system;

/**
 * 题目描述
 * 请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 * <p>
 * iterator.next(); // 返回 1
 * iterator.next(); // 返回 2
 * iterator.next(); // 返回 3
 * iterator.hasNext(); // 返回 true
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 4
 * iterator.hasNext(); // 返回 false
 * <p>
 * <p>
 * 注意：
 * <p>
 * 请记得 重置 在 Vector2D 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
 * 你可以假定 next() 的调用总是合法的，即当 next() 被调用时，二维向量总是存在至少一个后续元素。
 *
 * @author rcao1
 */
public class Vector2D {

    private int row;
    private int column;
    private int[][] vec;

    public Vector2D(int[][] vec) {
        this.vec = vec;
    }

    public int next() {
        if (hasNext()) {
            int temp = vec[row][column++];
            if (column >= vec[row].length) {
                column = 0;
                row++;
            }
            return temp;
        }
        return -1;
    }

    public boolean hasNext() {
        return row < vec.length && column < vec[row].length;
    }

    public static void main(String[] args) {
        Vector2D iterator = new Vector2D(new int[][] {{1, 2}, {3}, {4}});
        System.out.println(iterator.next()); // 返回 1
        System.out.println(iterator.next()); // 返回 2
        System.out.println(iterator.next()); // 返回 3
        System.out.println(iterator.hasNext()); // 返回 true
        System.out.println(iterator.hasNext()); // 返回 true
        System.out.println(iterator.next()); // 返回 4
        System.out.println(iterator.hasNext()); // 返回 false
    }
}
