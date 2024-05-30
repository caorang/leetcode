package cn.hutool.jackie.algorithm.design.p2300_2399;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 设计一个支持下述操作的食物评分系统：
 * <p>
 * 修改 系统中列出的某种食物的评分。
 * 返回系统中某一类烹饪方式下评分最高的食物。
 * 实现 FoodRatings 类：
 * <p>
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings) 初始化系统。食物由 foods、cuisines 和 ratings 描述，长度均为 n 。
 * foods[i] 是第 i 种食物的名字。
 * cuisines[i] 是第 i 种食物的烹饪方式。
 * ratings[i] 是第 i 种食物的最初评分。
 * void changeRating(String food, int newRating) 修改名字为 food 的食物的评分。
 * String highestRated(String cuisine) 返回指定烹饪方式 cuisine 下评分最高的食物的名字。如果存在并列，返回 字典序较小 的名字。
 * 注意，字符串 x 的字典序比字符串 y 更小的前提是：x 在字典中出现的位置在 y 之前，也就是说，要么 x 是 y 的前缀，或者在满足 x[i] != y[i] 的第一个位置 i 处，x[i] 在字母表中出现的位置在 y[i] 之前。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
 * [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
 * 输出
 * [null, "kimchi", "ramen", null, "sushi", null, "ramen"]
 * <p>
 * 解释
 * FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
 * foodRatings.highestRated("korean"); // 返回 "kimchi"
 * // "kimchi" 是分数最高的韩式料理，评分为 9 。
 * foodRatings.highestRated("japanese"); // 返回 "ramen"
 * // "ramen" 是分数最高的日式料理，评分为 14 。
 * foodRatings.changeRating("sushi", 16); // "sushi" 现在评分变更为 16 。
 * foodRatings.highestRated("japanese"); // 返回 "sushi"
 * // "sushi" 是分数最高的日式料理，评分为 16 。
 * foodRatings.changeRating("ramen", 16); // "ramen" 现在评分变更为 16 。
 * foodRatings.highestRated("japanese"); // 返回 "ramen"
 * // "sushi" 和 "ramen" 的评分都是 16 。
 * // 但是，"ramen" 的字典序比 "sushi" 更小。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2 * 10^4
 * n == foods.length == cuisines.length == ratings.length
 * 1 <= foods[i].length, cuisines[i].length <= 10
 * foods[i]、cuisines[i] 由小写英文字母组成
 * 1 <= ratings[i] <= 10^8
 * foods 中的所有字符串 互不相同
 * 在对 changeRating 的所有调用中，food 是系统中食物的名字。
 * 在对 highestRated 的所有调用中，cuisine 是系统中 至少一种 食物的烹饪方式。
 * 最多调用 changeRating 和 highestRated 总计 2 * 10^4 次
 *
 * @author rcao1
 */
public class L2353FoodRatings {

    class Food implements Comparable<Food> {
        String name;
        String cuisine;
        Integer rating;

        public Food(String name, String cuisine, Integer rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }

        @Override
        public int compareTo(Food o) {
            int compare = o.rating - this.rating;
            if (compare == 0) {
                return this.name.compareTo(o.name);
            }
            return compare;
        }
    }

    private Map<String, Food> foods;
    private Map<String, TreeMap<Integer, TreeSet<Food>>> cuisineHighestRatings;

    public L2353FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        this.foods = new HashMap<>();
        this.cuisineHighestRatings = new HashMap<>();
        init(foods, cuisines, ratings);
    }

    private void init(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            String name = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            Food food = new Food(name, cuisine, rating);
            this.foods.put(name, food);
            this.cuisineHighestRatings.computeIfAbsent(cuisine, k -> new TreeMap<>())
                    .computeIfAbsent(rating, k -> new TreeSet<>()).add(food);
        }
    }

    public void changeRating(String food, int newRating) {
        if (this.foods.containsKey(food)) {
            Food curFood = this.foods.get(food);
            TreeMap<Integer, TreeSet<Food>> highestRatings = this.cuisineHighestRatings.get(curFood.cuisine);
            TreeSet<Food> curFoods = highestRatings.computeIfAbsent(curFood.rating, k -> new TreeSet<>());
            curFoods.remove(curFood);
            if (curFoods.isEmpty()) {
                highestRatings.remove(curFood.rating);
            }
            curFood.rating = newRating;
            highestRatings.computeIfAbsent(curFood.rating, k -> new TreeSet<>()).add(curFood);
        }
    }

    public String highestRated(String cuisine) {
        if (this.cuisineHighestRatings.containsKey(cuisine)) {
            TreeMap<Integer, TreeSet<Food>> highestRatings = this.cuisineHighestRatings.get(cuisine);
            if (highestRatings.size() == 0) {
                return null;
            }
            for (Map.Entry<Integer, TreeSet<Food>> entry : highestRatings.descendingMap().entrySet()) {
                TreeSet<Food> foods = entry.getValue();
                if (foods != null && foods.size() > 0) {
                    return foods.first().name;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["FoodRatings","changeRating","highestRated","changeRating","changeRating","changeRating","highestRated","highestRated"]
         * [[["emgqdbo","jmvfxjohq","qnvseohnoe","yhptazyko","ocqmvmwjq"],["snaxol","snaxol","snaxol","fajbervsj","fajbervsj"],[2,6,18,6,5]],["qnvseohnoe",11],["fajbervsj"],["emgqdbo",3],["jmvfxjohq",9],["emgqdbo",14],["fajbervsj"],["snaxol"]]
         * 输出
         * [null,null,"yhptazyko",null,null,null,"yhptazyko","emgqdbo"]
         */
        String method = "[\"FoodRatings\",\"changeRating\",\"highestRated\",\"changeRating\",\"changeRating\",\"changeRating\",\"highestRated\",\"highestRated\"]\n";
        String arguments =
                "[[[\"emgqdbo\",\"jmvfxjohq\",\"qnvseohnoe\",\"yhptazyko\",\"ocqmvmwjq\"],[\"snaxol\",\"snaxol\",\"snaxol\",\"fajbervsj\",\"fajbervsj\"],[2,6,18,6,5]],[\"qnvseohnoe\",11],[\"fajbervsj\"],[\"emgqdbo\",3],[\"jmvfxjohq\",9],[\"emgqdbo\",14],[\"fajbervsj\"],[\"snaxol\"]]\n";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(L2353FoodRatings.class);
    }
}
