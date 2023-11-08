package cn.hutool.jackie.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
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
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class BinaryTreeInorderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

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
			if (this == null) {
				return null;
			}
			StringBuilder sb = new StringBuilder();
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(this);

			while (!queue.isEmpty()) {
				int levelSize = queue.size();
				for (int i = 0; i < levelSize; i++) {
					TreeNode node = queue.poll();
					if (node == null) {
						sb.append("null ");
					} else {
						sb.append(node.val + " ");
						queue.offer(node.left);
						queue.offer(node.right);
					}
				}
				sb.append("\n");
			}

			return sb.toString();
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4) ), new TreeNode(5) );
		System.out.println(root);
		System.out.println(new BinaryTreeInorderTraversal().inorderTraversal(root));
	}

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		if (root.left != null) {
			result.addAll(inorderTraversal(root.left));
		}
		result.add(root.val);
		if (root.right != null) {
			result.addAll(inorderTraversal(root.right));
		}
		return result;
	}
}
