package cn.hutool.jackie.algorithm.simulation;

/**
 * 社团共有 num 为成员参与破冰游戏，编号为 0 ~ num-1。成员们按照编号顺序围绕圆桌而坐。
 * 社长抽取一个数字 target，从 0 号成员起开始计数，排在第 target 位的成员离开圆桌，且成员离开后从下一个成员开始计数。
 * 请返回游戏结束时最后一位成员的编号。
 * 示例 1：
 * <p>
 * 输入：num = 7, target = 4
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：num = 12, target = 5
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= num <= 10^5
 * 1 <= target <= 10^6
 *
 * @See <a href="https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/">...</a>
 */
public class GameWhoLeft {

    public static void main(String[] args) {
        System.out.println(new GameWhoLeft().iceBreakingGame(7, 4));
        System.out.println(new GameWhoLeft().iceBreakingGame(12, 5));
        System.out.println(new GameWhoLeft().iceBreakingGame(5, 1));
    }

    public int iceBreakingGame(int num, int target) {
        if (target == 1) {
            return num - 1;
        }
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for (int i = 1; i < num; i++) {
            head.next = new ListNode(i);
            head = head.next;
        }
        head.next = temp;
        head = temp;
        int index = 1;
        while (head != null) {
            index++;
            if (index == target) {
                index = 1;
                head.next = head.next.next;
            }
            if (head == head.next) {
                break;
            }
            head = head.next;
        }
        return head.val;
    }

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
    }
}
