package cn.hutool.jackie.algorithm.coupang;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author rcao1
 */
public class TimeStamp {

    /**
     * from -12 to 12
     */
    private int zone;

    public TimeStamp(int zone) {
        this.zone = zone;
    }

    static class Pair {
        int value;
        long left;

        public Pair(int value, long left) {
            this.value = value;
            this.left = left;
        }

        @Override
        public String toString() {
            return "value=" + value + ", left=" + left;
        }
    }

    int[] monthDays = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * timestamp → [yyyy-MM-dd HH:mm:ss]
     *
     * @param timestamp
     * @return
     */
    public String format(long timestamp) {
        Pair year = getYear(timestamp);
        Pair month = getMonth(year.value, year.left);
        Pair day = getDay(month.left);
        Pair hour = getHour(day.left);
        Pair min = getMin(hour.left);
        long second = min.left;
        return String.format("%04d", year.value)
                + "-" + String.format("%02d", month.value)
                + "-" + String.format("%02d", day.value)
                + " "
                + String.format("%02d", hour.value)
                + ":" + String.format("%02d", min.value)
                + ":" + String.format("%02d", second);
    }

    private Pair getMin(long timestamp) {
        long t = 0;
        for (int m = 0; m <= 59; m++) {
            long temp = t;
            t += 60;
            if (t > timestamp) {
                return new Pair(m, timestamp - temp);
            }
        }
        return new Pair(60, 0);
    }

    private Pair getHour(long timestamp) {
        long t = 0;
        for (int h = 0; h <= 23; h++) {
            long temp = t;
            t += 3600;
            if (t > timestamp) {
                return new Pair(h, timestamp - temp);
            }
        }
        return new Pair(24, 0);
    }

    private Pair getDay(long timestamp) {
        long t = 0;
        for (int d = 1; d <= 31; d++) {
            long temp = t;
            t += 86400;
            if (t > timestamp) {
                return new Pair(d, timestamp - temp);
            }
        }
        return new Pair(32, 0);
    }

    private Pair getMonth(int year, long timestamp) {
        long t = 0;
        for (int m = 1; m <= 12; m++) {
            long temp = t;
            if (isLeapYear(year) && m == 2) {
                t += 86400 * 29;
            } else {
                t += 86400 * monthDays[m - 1];
            }
            if (t > timestamp) {
                return new Pair(m, timestamp - temp);
            }
        }
        return new Pair(13, 0);
    }

    private Pair getYear(long timestamp) {
        // 东八区调整
        timestamp += this.zone * 3600;
        long t = 0;
        for (int year = 1970; year <= 9999; year++) {
            long temp = t;
            if (isLeapYear(year)) {
                t += 86400 * 366;
            } else {
                t += 86400 * 365;
            }
            if (t > timestamp) {
                return new Pair(year, timestamp - temp);
            }
        }
        return new Pair(9999, 0);
    }

    private boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0)) {
            return true;
        }
        return false;
    }

    public String timeStamp2Date(long seconds) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Instant instant = Instant.ofEpochSecond(seconds);
        return dtf.format(instant.atZone(ZoneId.of("GMT+8")));
    }

    public static void main(String[] args) {
        case1();
    }

    private static void case1() {
        TimeStamp ts = new TimeStamp(8);

        long t = 936842400L;
        System.out.print(ts.timeStamp2Date(t) + " = ");
        System.out.println(ts.format(t));
        System.out.print(ts.timeStamp2Date(t + 1) + " = ");
        System.out.println(ts.format(t + 1));
        System.out.print(ts.timeStamp2Date(t - 1) + " = ");
        System.out.println(ts.format(t - 1));

        t = 976550400L;
        System.out.print(ts.timeStamp2Date(t) + " = ");
        System.out.println(ts.format(t));
        System.out.print(ts.timeStamp2Date(t + 1) + " = ");
        System.out.println(ts.format(t + 1));
        System.out.print(ts.timeStamp2Date(t - 1) + " = ");
        System.out.println(ts.format(t - 1));

        t = 1325347200L;
        System.out.print(ts.timeStamp2Date(t) + " = ");
        System.out.println(ts.format(t));
        System.out.print(ts.timeStamp2Date(t + 1) + " = ");
        System.out.println(ts.format(t + 1));
        System.out.print(ts.timeStamp2Date(t - 1) + " = ");
        System.out.println(ts.format(t - 1));

        t = 1701314348L;
        System.out.print(ts.timeStamp2Date(t) + " = ");
        System.out.println(ts.format(t));
        System.out.print(ts.timeStamp2Date(t + 1) + " = ");
        System.out.println(ts.format(t + 1));
        System.out.print(ts.timeStamp2Date(t - 1) + " = ");
        System.out.println(ts.format(t - 1));

        Random random = new Random();
        for (long time = 0; time <= System.currentTimeMillis() / 1000; ) {
            String s1 = ts.timeStamp2Date(time);
            String s2 = ts.format(time);
            System.out.println(s1.equals(s2) + ": " + time + ", " + s1 + ", " + s2);
            time = time + random.nextInt(10000000);
            if (!s1.equals(s2)) {
                break;
            }
        }
    }

    private static void case2() {
        TimeStamp ts = new TimeStamp(8);
        long t = 515524332L;
        System.out.print(ts.timeStamp2Date(t) + " = ");
        System.out.println(ts.format(t));

        t = 515527332L;
        System.out.print(ts.timeStamp2Date(t) + " = ");
        System.out.println(ts.format(t));
    }
}
