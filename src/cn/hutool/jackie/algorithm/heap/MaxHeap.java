package cn.hutool.jackie.algorithm.heap;

/**
 * @author rcao1
 */
public class MaxHeap {

    static class TreeNode<T> {
        T value;
        MinHeap.TreeNode left;
        MinHeap.TreeNode right;

        TreeNode(T value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

    }
}
