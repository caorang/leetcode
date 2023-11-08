package cn.hutool.jackie.algorithm.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你四个整数 length ，width ，height 和 mass ，分别表示一个箱子的三个维度和质量，请你返回一个表示箱子 类别 的字符串。
 * <p>
 * 如果满足以下条件，那么箱子是 "Bulky" 的：
 * 箱子 至少有一个 维度大于等于 10^4 。
 * 或者箱子的 体积 大于等于 10^9 。
 * 如果箱子的质量大于等于 100 ，那么箱子是 "Heavy" 的。
 * 如果箱子同时是 "Bulky" 和 "Heavy" ，那么返回类别为 "Both" 。
 * 如果箱子既不是 "Bulky" ，也不是 "Heavy" ，那么返回类别为 "Neither" 。
 * 如果箱子是 "Bulky" 但不是 "Heavy" ，那么返回类别为 "Bulky" 。
 * 如果箱子是 "Heavy" 但不是 "Bulky" ，那么返回类别为 "Heavy" 。
 * 注意，箱子的体积等于箱子的长度、宽度和高度的乘积。
 */
public class CategorizeBox {

	public static void main(String[] args) {
		System.out.println(new CategorizeBox().categorizeBox(2909, 3968, 3272, 300));
	}

	public String categorizeBox(int length, int width, int height, int mass) {
		List<String> tags = new ArrayList<>();
		if (length >= 10000 || width >= 10000 || height >= 10000 || (long) length * width * height >= 1000000000L) {
			tags.add("Bulky");
		}
		if (mass >= 100) {
			tags.add("Heavy");
		}
		if (tags.contains("Bulky") && tags.contains("Heavy")) {
			return "Both";
		}
		if (tags.contains("Bulky") && !tags.contains("Heavy")) {
			return "Bulky";
		}
		if (!tags.contains("Bulky") && tags.contains("Heavy")) {
			return "Heavy";
		}
		return "Neither";
	}
}
