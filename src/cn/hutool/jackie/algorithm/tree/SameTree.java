package cn.hutool.jackie.algorithm.tree;

/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * 示例 3：
 * <p>
 * <p>
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -104 <= Node.val <= 104
 *
 * @See <a href="https://leetcode.cn/problems/same-tree/">...</a>
 */
public class SameTree {

	public static class TreeNode {
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
	}

	public static void main(String[] args) {
		TreeNode p = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(5));
		TreeNode q = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(5));
		System.out.println(new SameTree().isSameTree(p, q));
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p != null && q == null) {
			return false;
		}
		if (p == null && q != null) {
			return false;
		}
		return p.val == q.val && isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
	}
}
