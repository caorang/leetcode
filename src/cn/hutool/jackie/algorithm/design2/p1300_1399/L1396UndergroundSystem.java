package cn.hutool.jackie.algorithm.design2.p1300_1399;

import java.util.HashMap;
import java.util.Map;

/**
 * 地铁系统跟踪不同车站之间的乘客出行时间，并使用这一数据来计算从一站到另一站的平均时间。
 * <p>
 * 实现 UndergroundSystem 类：
 * <p>
 * void checkIn(int id, string stationName, int t)
 * <ul>
 * 	<li>通行卡 ID 等于 <code>id</code> 的乘客，在时间 <code>t</code> ，从 <code>stationName</code> 站进入</li>
 * 	<li>乘客一次只能从一个站进入</li>
 * </ul>
 * </li>
 * <li><code>void checkOut(int id, string stationName, int t)</code>
 * <ul>
 * 	<li>通行卡 ID 等于 <code>id</code> 的乘客，在时间 <code>t</code> ，从 <code>stationName</code> 站离开</li>
 * </ul>
 * </li>
 * <li><code>double getAverageTime(string startStation, string endStation)</code>
 * <ul>
 * 	<li>返回从 <code>startStation</code> 站到 <code>endStation</code> 站的平均时间</li>
 * 	<li>平均时间会根据截至目前所有从 <code>startStation</code> 站 <strong>直接</strong> 到达 <code>endStation</code> 站的行程进行计算，也就是从 <code>startStation</code> 站进入并从 <code>endStation</code> 离开的行程</li>
 * 	<li>从 <code>startStation</code> 到 <code>endStation</code> 的行程时间与从 <code>endStation</code> 到 <code>startStation</code> 的行程时间可能不同</li>
 * 	<li>在调用 <code>getAverageTime</code> 之前，至少有一名乘客从 <code>startStation</code> 站到达 <code>endStation</code> 站</li>
 * </ul>
 * </li>
 * 你可以假设对 checkIn 和 checkOut 方法的所有调用都是符合逻辑的。如果一名乘客在时间 t1 进站、时间 t2 出站，那么 t1 < t2 。所有时间都按时间顺序发生。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入
 * ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
 * [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]
 * <p>
 * 输出
 * [null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]
 * <p>
 * 解释
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(45, "Leyton", 3);
 * undergroundSystem.checkIn(32, "Paradise", 8);
 * undergroundSystem.checkIn(27, "Leyton", 10);
 * undergroundSystem.checkOut(45, "Waterloo", 15);  // 乘客 45 "Leyton" -> "Waterloo" ，用时 15-3 = 12
 * undergroundSystem.checkOut(27, "Waterloo", 20);  // 乘客 27 "Leyton" -> "Waterloo" ，用时 20-10 = 10
 * undergroundSystem.checkOut(32, "Cambridge", 22); // 乘客 32 "Paradise" -> "Cambridge" ，用时 22-8 = 14
 * undergroundSystem.getAverageTime("Paradise", "Cambridge"); // 返回 14.00000 。只有一个 "Paradise" -> "Cambridge" 的行程，(14) / 1 = 14
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000 。有两个 "Leyton" -> "Waterloo" 的行程，(10 + 12) / 2 = 11
 * undergroundSystem.checkIn(10, "Leyton", 24);
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000
 * undergroundSystem.checkOut(10, "Waterloo", 38);  // 乘客 10 "Leyton" -> "Waterloo" ，用时 38-24 = 14
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 12.00000 。有三个 "Leyton" -> "Waterloo" 的行程，(10 + 12 + 14) / 3 = 12
 * 示例 2：
 * <p>
 * 输入
 * ["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime"]
 * [[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton","Paradise"]]
 * <p>
 * 输出
 * [null,null,null,5.00000,null,null,5.50000,null,null,6.66667]
 * <p>
 * 解释
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(10, "Leyton", 3);
 * undergroundSystem.checkOut(10, "Paradise", 8); // 乘客 10 "Leyton" -> "Paradise" ，用时 8-3 = 5
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 5.00000 ，(5) / 1 = 5
 * undergroundSystem.checkIn(5, "Leyton", 10);
 * undergroundSystem.checkOut(5, "Paradise", 16); // 乘客 5 "Leyton" -> "Paradise" ，用时 16-10 = 6
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 5.50000 ，(5 + 6) / 2 = 5.5
 * undergroundSystem.checkIn(2, "Leyton", 21);
 * undergroundSystem.checkOut(2, "Paradise", 30); // 乘客 2 "Leyton" -> "Paradise" ，用时 30-21 = 9
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 6.66667 ，(5 + 6 + 9) / 3 = 6.66667
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= id, t <= 10^6
 * 1 <= stationName.length, startStation.length, endStation.length <= 10 次
 * 所有字符串由大小写英文字母与数字组成
 * 总共最多调用 checkIn、checkOut 和 getAverageTime 方法 2 * 10^4
 * 与标准答案误差在 10^-5 以内的结果都被视为正确结果
 *
 * @author rcao1
 */
public class L1396UndergroundSystem {

    class Travel {
        String checkIn;
        String checkOut;
        int start = -1;
        int end = -1;

        public Travel(String checkIn, int start) {
            this.checkIn = checkIn;
            this.start = start;
        }

        public void checkOut(String stationName, int end) {
            this.checkOut = stationName;
            this.end = end;
        }

        double getDuration() {
            if (this.start != -1 && this.end != -1) {
                return this.end - this.start;
            }
            return -1;
        }
    }

    class Summary {
        double total;
        int count;

        public Summary(double total, int count) {
            this.total = total;
            this.count = count;
        }
    }

    private Map<String, Summary> average;
    private Map<Integer, Travel> travels;

    public L1396UndergroundSystem() {
        this.average = new HashMap<>();
        this.travels = new HashMap<>();
    }

    /**
     * 通行卡 ID 等于 id 的乘客，在时间 t ，从 stationName 站进入
     * 乘客一次只能从一个站进入
     *
     * @param id
     * @param stationName
     * @param t
     */
    public void checkIn(int id, String stationName, int t) {
        Travel travel = new Travel(stationName, t);
        this.travels.put(id, travel);
    }

    /**
     * 通行卡 ID 等于 id 的乘客，在时间 t ，从 stationName 站离开
     *
     * @param id
     * @param stationName
     * @param t
     */
    public void checkOut(int id, String stationName, int t) {
        Travel travel = this.travels.get(id);
        travel.checkOut(stationName, t);
        String stationKey = travel.checkIn + "_" + travel.checkOut;
        if (average.containsKey(stationKey)) {
            Summary summary = average.get(stationKey);
            summary.total += travel.getDuration();
            summary.count++;
        } else {
            average.put(stationKey, new Summary(travel.getDuration(), 1));
        }
        this.travels.remove(id);
    }

    /**
     * 返回从 startStation 站到 endStation 站的平均时间
     * 平均时间会根据截至目前所有从 startStation 站 直接 到达 endStation 站的行程进行计算
     * 也就是从 startStation 站进入并从 endStation 离开的行程
     * 从 startStation 到 endStation 的行程时间与从 endStation 到 startStation 的行程时间可能不同
     * 在调用 getAverageTime 之前，至少有一名乘客从 startStation 站到达 endStation 站
     *
     * @param startStation
     * @param endStation
     * @return
     */
    public double getAverageTime(String startStation, String endStation) {
        String stationKey = startStation + "_" + endStation;
        if (average.containsKey(stationKey)) {
            return average.get(stationKey).total / average.get(stationKey).count;
        }
        return -1;
    }

    public static void main(String[] args) {
        L1396UndergroundSystem undergroundSystem = new L1396UndergroundSystem();
        undergroundSystem.checkIn(10, "Leyton", 3);
        // 乘客 10 "Leyton" -> "Paradise" ，用时 8-3 = 5
        undergroundSystem.checkOut(10, "Paradise", 8);
        // 返回 5.00000 ，(5) / 1 = 5
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Paradise"));
        // 乘客 5 "Leyton" -> "Paradise" ，用时 16-10 = 6
        undergroundSystem.checkIn(5, "Leyton", 10);
        undergroundSystem.checkOut(5, "Paradise", 16);
        // 返回 5.50000 ，(5 + 6) / 2 = 5.5
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Paradise"));
        // 乘客 2 "Leyton" -> "Paradise" ，用时 30-21 = 9
        undergroundSystem.checkIn(2, "Leyton", 21);
        undergroundSystem.checkOut(2, "Paradise", 30);
        // 返回 6.66667 ，(5 + 6 + 9) / 3 = 6.66667
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Paradise"));
    }
}
