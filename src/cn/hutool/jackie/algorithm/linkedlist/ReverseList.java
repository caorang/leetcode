package cn.hutool.jackie.algorithm.linkedlist;

/**
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/reverse-linked-list/">...</a>
 */
public class ReverseList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode node = this;
            while (node != null) {
                sb.append(node.val).append("->");
                node = node.next;
            }
            sb.append("null");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(head);
        System.out.println(new ReverseList().reverseList(head));
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
