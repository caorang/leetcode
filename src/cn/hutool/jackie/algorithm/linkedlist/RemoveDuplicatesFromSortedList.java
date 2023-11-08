package cn.hutool.jackie.algorithm.linkedlist;

/**
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 *
 * @See <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/">...</a>
 */
public class RemoveDuplicatesFromSortedList {

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
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(3, new ListNode(3)))));
		ListNode result = new RemoveDuplicatesFromSortedList().deleteDuplicates(head);
		System.out.println(result.toString());
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode temp = head;
		while (head != null && head.next != null) {
			ListNode cur = head;
			while (cur != null && cur.next != null && cur.val == cur.next.val) {
				cur = cur.next;
			}
			head.next = cur.next;
			if (cur.next != null) {
				head = head.next;
			}
		}
		return temp;
	}
}
