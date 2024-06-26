package cn.hutool.jackie.algorithm.system;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 你将获得多条日志，每条日志都有唯一的 id 和 timestamp ，timestamp 是形如 Year:Month:Day:Hour:Minute:Second 的字符串，2017:01:01:23:59:59 ，所有值域都是零填充的十进制数。
 * <p>
 * 实现 LogSystem 类：
 * <p>
 * LogSystem() 初始化 LogSystem 对象
 * void put(int id, string timestamp) 给定日志的 id 和 timestamp ，将这个日志存入你的存储系统中。
 * int[] retrieve(string start, string end, string granularity) 返回在给定时间区间 [start, end] （包含两端）内的所有日志的 id 。start 、end 和 timestamp 的格式相同，granularity 表示考虑的时间粒度（例如，精确到 Day、Minute 等）。
 * 例如 start = "2017:01:01:23:59:59"、end = "2017:01:02:23:59:59" 且 granularity = "Day" 意味着需要查找从 Jan. 1st 2017 到 Jan. 2nd 2017 范围内的日志，可以忽略日志的 Hour、Minute 和 Second 。
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
public class LogSystem {

    private Map<Integer, SystemLog> database = new HashMap<>();
    private RedBlackTree<Long, SystemLog> indexOfLog = new RedBlackTree();

    /**
     * 初始化 LogSystem 对象
     */
    public LogSystem() {
    }

    /**
     * 给定日志的 id 和 timestamp ，将这个日志存入你的存储系统中。
     *
     * @param id
     * @param timestamp
     */
    public void put(int id, String timestamp) {
        if (database.containsKey(id)) {
            throw new RuntimeException("duplicated id");
        }
        SystemLog log = new SystemLog(id, timestamp);
        database.put(id, log);
        long stamp = convertTimestamp(timestamp);
        indexOfLog.insert(stamp, log);
    }

    private long convertTimestamp(String timestamp) {
        return 0;
    }

    /**
     * 返回在给定时间区间 [start, end] （包含两端）内的所有日志的 id 。
     * start 、end 和 timestamp 的格式相同，granularity 表示考虑的时间粒度（例如，精确到 Day、Minute 等）。
     * 例如 start = "2017:01:01:23:59:59"、end = "2017:01:02:23:59:59" 且 granularity = "Day" 意味着需要查找从 Jan. 1st 2017 到 Jan. 2nd 2017 范围内的日志，可以忽略日志的 Hour、Minute 和 Second 。
     *
     * @param start
     * @param end
     * @param granularity
     * @return
     */
    public int[] retrieve(String start, String end, String granularity) {
        return null;
    }

    public static void main(String[] args) {
        LogSystem logSystem = new LogSystem();
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

class SystemLog {
    public int id;
    public String timestamp;

    public SystemLog(int id, String timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }
}
