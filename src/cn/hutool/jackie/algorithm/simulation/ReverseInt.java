package cn.hutool.jackie.algorithm.simulation;

/**
 * @See <a href="https://leetcode.cn/problems/reverse-integer/">...</a>
 */
public class ReverseInt {

	public static void main(String[] args) {
		System.out.println(new ReverseInt().reverse(123));
		System.out.println(new ReverseInt().reverse(-123));
	}

	public int reverse(int x) {
		long result = 0;
		while (x != 0) {
			result = result * 10 + x % 10;
			x = x / 10;
			if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
				return 0;
			}
		}
		return (int) result;
	}
}
