package cn.hutool.jackie.algorithm.plus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 给定两个人的空闲时间表：slots1 和 slots2，以及会议的预计持续时间 duration，请你为他们安排 时间段最早 且合适的会议时间。
 * <p>
 * 如果没有满足要求的会议时间，就请返回一个 空数组。
 * <p>
 * 「空闲时间」的格式是 [start, end]，由开始时间 start 和结束时间 end 组成，表示从 start 开始，到 end 结束。
 * <p>
 * 题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，对于同一个人的两个空闲时间 [start1, end1] 和 [start2, end2]，要么 start1 > end2，要么 start2 > end1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * 输出：[60,68]
 * 示例 2：
 * <p>
 * 输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= slots1.length, slots2.length <= 104
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 109
 * 1 <= duration <= 106
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/meeting-scheduler/description/">...</a>
 */
public class MeetingScheduler {

    public static void main(String[] args) {
        MeetingScheduler meetingScheduler = new MeetingScheduler();
        int[][] slots1 = {{10, 50}, {60, 120}, {140, 210}};
        int[][] slots2 = {{0, 15}, {60, 70}};
        int duration = 8;
        System.out.println(meetingScheduler.minAvailableDuration(slots1, slots2, duration));
    }

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        if (slots1 == null || slots2 == null) {
            return new ArrayList<>();
        }
        Arrays.sort(slots1, Comparator.comparingInt(k -> k[0]));
        Arrays.sort(slots2, Comparator.comparingInt(k -> k[0]));
        for (int i = 0, j = 0; i < slots1.length && j < slots2.length; ) {
            int[] range1 = slots1[i];
            int[] range2 = slots2[j];
            int left = range1[0] >= range2[0] ? range1[0] : range2[0];
            int right = range1[1] >= range2[1] ? range2[1] : range1[1];
            if (right - left >= duration) {
                return Arrays.asList(left, left + duration);
            }
            if (slots1[i][1] < slots2[j][1]) {
                ++i;
            } else {
                ++j;
            }
        }
        return Collections.emptyList();
    }
}
