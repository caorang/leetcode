package cn.hutool.jackie.algorithm.design.p0600_0699;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 你将获得多条日志，每条日志都有唯一的 id 和 timestamp ，timestamp 是形如 Year:Month:Day:Hour:Minute:Second 的字符串，2017:01:01:23:59:59 ，所有值域都是零填充的十进制数。
 * <p>
 * 实现 LogSystem 类：
 * <p>
 * LogSystem() 初始化 LogSystem 对象
 * void put(int id, string timestamp) 给定日志的 id 和 timestamp ，将这个日志存入你的存储系统中。
 * int[] retrieve(string start, string end, string granularity) 返回在给定时间区间 [start, end] （包含两端）内的所有日志的 id 。start 、end 和 timestamp 的格式相同，granularity 表示考虑的时间粒度（例如，精确到 Day、Minute 等）。例如 start = "2017:01:01:23:59:59"、end = "2017:01:02:23:59:59" 且 granularity = "Day" 意味着需要查找从 Jan. 1st 2017 到 Jan. 2nd 2017 范围内的日志，可以忽略日志的 Hour、Minute 和 Second 。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["LogSystem", "put", "put", "put", "retrieve", "retrieve"]
 * [[], [1, "2017:01:01:23:59:59"], [2, "2017:01:01:22:59:59"], [3, "2016:01:01:00:00:00"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"]]
 * 输出：
 * [null, null, null, null, [3, 2, 1], [2, 1]]
 * <p>
 * 解释：
 * LogSystem logSystem = new LogSystem();
 * logSystem.put(1, "2017:01:01:23:59:59");
 * logSystem.put(2, "2017:01:01:22:59:59");
 * logSystem.put(3, "2016:01:01:00:00:00");
 * <p>
 * // 返回 [3,2,1]，返回从 2016 年到 2017 年所有的日志。
 * logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year");
 * <p>
 * // 返回 [2,1]，返回从 Jan. 1, 2016 01:XX:XX 到 Jan. 1, 2017 23:XX:XX 之间的所有日志
 * // 不返回日志 3 因为记录时间 Jan. 1, 2016 00:00:00 超过范围的起始时间
 * logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= id <= 500
 * 2000 <= Year <= 2017
 * 1 <= Month <= 12
 * 1 <= Day <= 31
 * 0 <= Hour <= 23
 * 0 <= Minute, Second <= 59
 * granularity 是这些值 ["Year", "Month", "Day", "Hour", "Minute", "Second"] 之一
 * 最多调用 500 次 put 和 retrieve
 *
 * @author rcao1
 */
public class L0635LogSystem {

    static class Log {
        int id;
        String ts;

        public Log(int id, String ts) {
            this.id = id;
            this.ts = ts;
        }
    }

    private TreeMap<Long, Log> map;

    public L0635LogSystem() {
        this.map = new TreeMap();
    }

    /**
     * 给定日志的 id 和 timestamp ，将这个日志存入你的存储系统中
     *
     * @param id
     * @param timestamp
     */
    public void put(int id, String timestamp) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        Log log = new Log(id, timestamp);
        Date date = sdf.parse(timestamp);
        this.map.putIfAbsent(date.getTime(), log);
    }

    /**
     * 返回在给定时间区间 [start, end] （包含两端）内的所有日志的id
     * start 、end 和 timestamp 的格式相同，granularity 表示考虑的时间粒度（例如，精确到Day/Hour/Minute等）
     *
     * @param start
     * @param end
     * @param granularity
     * @return
     */
    public int[] retrieve(String start, String end, String granularity) throws Exception {
        Long startTime = 0L;
        Long endTime = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        if ("year".equalsIgnoreCase(granularity)) {
            // 获取当年的第一天
            Date startDate = sdf.parse(start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date from = calendar.getTime();
            startTime = from.getTime();

            // 获取当年的最后一天
            Date endDate = sdf.parse(end);
            calendar.setTime(endDate);
            calendar.add(Calendar.YEAR, 1);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date to = calendar.getTime();
            endTime = to.getTime();
        } else if ("month".equalsIgnoreCase(granularity)) {

        } else if ("day".equalsIgnoreCase(granularity)) {

        } else if ("hour".equalsIgnoreCase(granularity)) {
            Date startDate = sdf.parse(start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            // 获取小时的第一秒
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date from = calendar.getTime();
            startTime = from.getTime();

            // 获取小时的最后一秒
            Date endDate = sdf.parse(end);
            calendar.setTime(endDate);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date to = calendar.getTime();
            endTime = to.getTime();
        } else if ("minute".equalsIgnoreCase(granularity)) {

        } else {

        }
        Map<Long, Log> subMap = map.subMap(startTime, endTime);
        List<Integer> result = new ArrayList<>();
        subMap.forEach((key, value) -> result.add(value.id));
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) throws Exception {
        L0635LogSystem logSystem = new L0635LogSystem();
        logSystem.put(1, "2017:01:01:23:59:59");
        logSystem.put(2, "2017:01:01:22:59:59");
        logSystem.put(3, "2016:01:01:00:00:00");
        // 返回 [3,2,1]，返回从 2016 年到 2017 年所有的日志。
        System.out.println(Arrays.toString(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year")));
        // 返回 [2,1]，返回从 Jan. 1, 2016 01:XX:XX 到 Jan. 1, 2017 23:XX:XX 之间的所有日志
        // 不返回日志 3 因为记录时间 Jan. 1, 2016 00:00:00 超过范围的起始时间
        System.out.println(Arrays.toString(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour")));
    }
}
