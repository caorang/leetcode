package cn.hutool.jackie.algorithm.design.p0400_0499;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计一个算法，可以将 N 叉树编码为二叉树，并能将该二叉树解码为原 N 叉树。
 * 一个 N 叉树是指每个节点都有不超过 N 个孩子节点的有根树。类似地，一个二叉树是指每个节点都有不超过 2 个孩子节点的有根树。
 * 你的编码 / 解码的算法的实现没有限制，你只需要保证一个 N 叉树可以编码为二叉树且该二叉树可以解码回原始 N 叉树即可。
 * <p>
 * 例如，你可以将下面的 3-叉 树以该种方式编码：
 * <p>
 * 注意，上面的方法仅仅是一个例子，可能可行也可能不可行。你没有必要遵循这种形式转化，你可以自己创造和实现不同的方法。
 * <p>
 * 注意：
 * <p>
 * N 的范围在 [1, 1000]
 * 不要使用类成员 / 全局变量 / 静态变量来存储状态。你的编码和解码算法应是无状态的。
 *
 * @author rcao1
 */
public class L0431Codec {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Node {
        int val;
        List<Node> children;

        Node(int x, List<Node> children) {
            this.val = x;
            this.children = children;
        }
    }

    static class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode newRoot = new TreeNode(root.val);
            List<Node> children = root.children;
            //第一个子节点存在
            TreeNode cur = null;
            if (!children.isEmpty()) {
                newRoot.left = encode(children.get(0));
                cur = newRoot.left;
            }
            //如果还存在第二个子节点，把子节点挂在第一个子节点的右子树
            for (int i = 1; i < children.size(); i++) {
                cur.right = encode(children.get(i));
                cur = cur.right;
            }
            return newRoot;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            Node newNode = new Node(root.val, new ArrayList<>());
            TreeNode cur = root.left;
            while (cur != null) {
                newNode.children.add(decode(cur));
                cur = cur.right;
            }
            return newNode;
        }
    }

    public static void main(String[] args) {

    }
}
