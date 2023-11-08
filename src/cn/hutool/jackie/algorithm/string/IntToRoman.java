package cn.hutool.jackie.algorithm.string;

/**
 * @See <a href="https://leetcode.cn/problems/integer-to-roman/">...</a>
 */
public class IntToRoman {

	public static void main(String[] args) {
		System.out.println(new IntToRoman().intToRoman(3));
		System.out.println(new IntToRoman().intToRoman(4));
		System.out.println(new IntToRoman().intToRoman(9));
		System.out.println(new IntToRoman().intToRoman(1004));
	}

	int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

	public String intToRoman(int num) {
		StringBuffer roman = new StringBuffer();
		for (int i = 0; i < values.length; ++i) {
			int value = values[i];
			String symbol = symbols[i];
			while (num >= value) {
				num -= value;
				roman.append(symbol);
			}
			if (num == 0) {
				break;
			}
		}
		return roman.toString();
	}
}
