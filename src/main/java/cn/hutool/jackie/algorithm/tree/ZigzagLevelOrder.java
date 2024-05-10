package cn.hutool.jackie.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * 示例 2：
 * <p>
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 * <p>
 * 输入：root = []
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/">...</a>
 */
public class ZigzagLevelOrder {
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

    public ZigzagLevelOrder() {

    }

    public List<List<Integer>> bfs(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for (int n = queue.size(); n > 0; n--) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (!flag) {
                Collections.reverse(list);
            }
            flag = !flag;
            ans.add(list);
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean left = true;
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int n = q.size(); n > 0; --n) {
                TreeNode node = q.poll();
                t.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (!left) {
                Collections.reverse(t);
            }
            ans.add(t);
            left = !left;
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode treeNode = buildTree(new Integer[] {3, 9, 20, null, null, 15, 7});
        System.out.println(new ZigzagLevelOrder().bfs(treeNode));
    }

    private static TreeNode buildTree(Integer[] integers) {
        Map<Integer, TreeNode> map = new HashMap<>(8);
        TreeNode root = null;
        for (int i = 0; i < integers.length / 2; i++) {
            Integer cur = integers[i];
            TreeNode curNode = map.get(i);
            int l = i * 2 + 1;
            int r = i * 2 + 2;
            Integer left = integers[l];
            TreeNode leftNode = null;
            if (left != null) {
                leftNode = new TreeNode(left);
                map.put(l, leftNode);
            }
            Integer right = integers[r];
            TreeNode rightNode = null;
            if (right != null) {
                rightNode = new TreeNode(right);
                map.put(r, rightNode);
            }
            if (curNode == null) {
                curNode = new TreeNode(cur, leftNode, rightNode);
            } else {
                curNode.left = leftNode;
                curNode.right = rightNode;
            }
            if (i == 0) {
                root = curNode;
            }
        }
        return root;
    }
}
