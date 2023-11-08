package cn.hutool.jackie.algorithm.linkedlist;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1]
 * 输出：[1]
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 */
public class SwapNodesInPairs {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			ListNode cur = this;
			while (cur != null) {
				sb.append(cur.val);
				if (cur.next != null) {
					sb.append(" → ");
				}
				cur = cur.next;
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		SwapNodesInPairs solution = new SwapNodesInPairs();
		System.out.println(solution.swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))))));
		System.out.println(solution.swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))))));
	}

	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		head.next = swapPairs(next.next);
		next.next = head;
		return next;
	}
}
