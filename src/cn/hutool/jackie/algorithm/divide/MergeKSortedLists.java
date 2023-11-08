package cn.hutool.jackie.algorithm.divide;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * @See <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">...</a>
 */
public class MergeKSortedLists {

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

		@Override
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
		ListNode[] lists = new ListNode[3];
		lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
		lists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
		lists[2] = new ListNode(2, new ListNode(6));
		MergeKSortedLists solution = new MergeKSortedLists();
		System.out.println(solution.mergeKLists(lists));
	}

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		int minIndex = 0;
		ListNode min = lists[minIndex];
		for (int i = 1; i < lists.length; i++) {
			if (lists[i] == null) {
				continue;
			}
			if (min == null) {
				min = lists[i];
				minIndex = i;
			}
			if (lists[i].val < min.val) {
				min = lists[i];
				minIndex = i;
			}
		}
		if (min == null) {
			return null;
		}
		lists[minIndex] = min.next;
		min.next = mergeKLists(lists);
		return min;
	}
}
