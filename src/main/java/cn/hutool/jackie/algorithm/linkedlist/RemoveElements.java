package cn.hutool.jackie.algorithm.linkedlist;

import java.util.StringJoiner;

/**
 * @author rcao1
 */
public class RemoveElements {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringJoiner sb = new StringJoiner("→");
            ListNode node = this;
            while (node != null) {
                sb.add(node.val + "");
                node = node.next;
            }
            return sb.toString();
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode superNode = new ListNode(-1, head);
        ListNode node = superNode;
        while (node != null) {
            if (node.next != null && node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return superNode.next;
    }

    public static void main(String[] args) throws Exception {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3,
                new ListNode(4, new ListNode(5, new ListNode(6, null)))))));
        System.out.println(head);
        System.out.println(new RemoveElements().removeElements(head, 6));

        head = new ListNode(7, new ListNode(7, new ListNode(7, new ListNode(7,
                new ListNode(7, new ListNode(7, new ListNode(7, null)))))));
        System.out.println(head);
        System.out.println(new RemoveElements().removeElements(head, 7));
    }
}
