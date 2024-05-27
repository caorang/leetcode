package cn.hutool.jackie.algorithm.plus;

/**
 * 给你一个二叉树的根节点 root ，请你将此二叉树上下翻转，并返回新的根节点。
 * <p>
 * 你可以按下面的步骤翻转一棵二叉树：
 * <p>
 * 原来的左子节点变成新的根节点
 * 原来的根节点变成新的右子节点
 * 原来的右子节点变成新的左子节点
 * <p>
 * 上面的步骤逐层进行。题目数据保证每个右节点都有一个同级节点（即共享同一父节点的左节点）且不存在子节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5]
 * 输出：[4,5,2,null,null,3,1]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [0, 10] 内
 * 1 <= Node.val <= 10
 * 树中的每个右节点都有一个同级节点（即共享同一父节点的左节点）
 * 树中的每个右节点都没有子节点
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/binary-tree-upside-down/description/">...</a>
 */
public class BinaryTreeUpsideDown {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(new BinaryTreeUpsideDown().upsideDownBinaryTree(root));
    }

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        return null;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "val=" + val + ", left=" + left + ", right=" + right + '}';
    }
}
