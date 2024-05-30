package cn.hutool.jackie.algorithm.design.p0700_0799;

import java.util.Map;
import java.util.TreeMap;

/**
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * <p>
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * <p>
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，start <= x < end 。
 * <p>
 * 实现 MyCalendar 类：
 * <p>
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * 输出：
 * [null, true, false, true]
 * <p>
 * 解释：
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= start < end <= 10^9
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次
 *
 * @author rcao1
 */
public class L0729MyCalendar {

    class Booking {
        int start;
        int end;

        public Booking(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private TreeMap<Integer, Integer> bookings;

    public L0729MyCalendar() {
        this.bookings = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> ent = bookings.floorEntry(start);
        if (ent != null && ent.getValue() > start) {
            return false;
        }
        ent = bookings.ceilingEntry(start);
        if (ent != null && ent.getKey() < end) {
            return false;
        }
        bookings.put(start, end);
        return true;
    }

    public static void main(String[] args) {
        L0729MyCalendar myCalendar = new L0729MyCalendar();
        System.out.println(myCalendar.book(47, 50));
        System.out.println(myCalendar.book(33, 41));
        System.out.println(myCalendar.book(39, 45));
        System.out.println(myCalendar.book(33, 42));
        System.out.println(myCalendar.book(25, 32));
        System.out.println(myCalendar.book(26, 35));
        System.out.println(myCalendar.book(19, 25));
        System.out.println(myCalendar.book(3, 8));
        System.out.println(myCalendar.book(8, 13));
        System.out.println(myCalendar.book(18, 27));
    }
}
