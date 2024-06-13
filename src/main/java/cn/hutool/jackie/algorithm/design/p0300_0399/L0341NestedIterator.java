package cn.hutool.jackie.algorithm.design.p0300_0399;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 给你一个嵌套的整数列表 nestedList 。
 * 每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。
 * <p>
 * 实现扁平迭代器类 NestedIterator ：
 * <p>
 * NestedIterator(List nestedList) 用嵌套列表 nestedList 初始化迭代器。
 * int next() 返回嵌套列表的下一个整数。
 * boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
 * 你的代码将会用下述伪代码检测：
 * <p>
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 * append iterator.next() to the end of res
 * return res
 * 如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nestedList = [[1,1],2,[1,1]]
 * 输出：[1,1,2,1,1]
 * 解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2：
 * <p>
 * 输入：nestedList = [1,[4,[6]]]
 * 输出：[1,4,6]
 * 解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 * 提示：
 * <p>
 * 1 <= nestedList.length <= 500
 * 嵌套列表中的整数值在范围 [-10^6, 10^6] 内
 *
 * @author rcao1
 */
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}

public class L0341NestedIterator {

    static class NestedIntegerImpl implements NestedInteger {

        private int value;

        public NestedIntegerImpl(int value) {
            this.value = value;
        }

        @Override
        public boolean isInteger() {
            return true;
        }

        @Override
        public Integer getInteger() {
            return this.value;
        }

        @Override
        public List<NestedInteger> getList() {
            return null;
        }
    }

    static class NestedListImpl implements NestedInteger {

        private List<NestedInteger> value;

        public NestedListImpl(List<NestedInteger> value) {
            this.value = value;
        }

        @Override
        public boolean isInteger() {
            return false;
        }

        @Override
        public Integer getInteger() {
            return null;
        }

        @Override
        public List<NestedInteger> getList() {
            return this.value;
        }
    }

    static class NestedIterator implements Iterator<Integer> {

        private List<Integer> list;
        private Iterator<Integer> current;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.list = new ArrayList<>();
            traverse(nestedList);
            this.current = list.iterator();
        }

        private void traverse(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    this.list.add(nestedInteger.getInteger());
                } else {
                    traverse(nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                return -1;
            }
            return this.current.next();
        }

        @Override
        public boolean hasNext() {
            return this.current.hasNext();
        }
    }

    public static void main(String[] args) {
        /**
         * 示例 1：
         * <p>
         * 输入：nestedList = [[1,1],2,[1,1]]
         * 输出：[1,1,2,1,1]
         * 解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
         * 示例 2：
         * <p>
         * 输入：nestedList = [1,[4,[6]]]
         * 输出：[1,4,6]
         * 解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
         */
        List<NestedInteger> integers1 = new ArrayList<>();
        integers1.add(new NestedIntegerImpl(1));
        integers1.add(new NestedIntegerImpl(1));
        List<NestedInteger> integers2 = new ArrayList<>();
        integers2.add(new NestedIntegerImpl(1));
        integers2.add(new NestedIntegerImpl(1));
        List<NestedInteger> integers = new ArrayList<>();
        integers.add(new NestedListImpl(integers1));
        integers.add(new NestedIntegerImpl(2));
        integers.add(new NestedListImpl(integers2));
        NestedIterator iterator = new NestedIterator(integers);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "->");
        }
    }
}
