package cn.hutool.jackie.algorithm.linkedlist;

/**
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * 提示：
 * <p>
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 *
 * @author rcao1
 * @see
 */
public class RemoveDuplicatesFromSortedList2 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(1));
        System.out.println(new RemoveDuplicatesFromSortedList2().deleteDuplicates(listNode));

        listNode = new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
        System.out.println(new RemoveDuplicatesFromSortedList2().deleteDuplicates(listNode));

        listNode = new ListNode(1, new ListNode(1, new ListNode(1,
                new ListNode(2, new ListNode(3)))));
        System.out.println(new RemoveDuplicatesFromSortedList2().deleteDuplicates(listNode));
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        while (cur != null) {
            ListNode next = cur.next;
            if (next != null && next.val == cur.val) {
                while (next != null) {
                    if (next.val != cur.val) {
                        break;
                    }
                    next = next.next;
                }
                pre.next = next;
                cur = pre;
            }
            pre = cur;
            cur = next;
        }
        return dummy.next;
    }
}

class ListNode {
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
