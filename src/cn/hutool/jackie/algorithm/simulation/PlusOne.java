package cn.hutool.jackie.algorithm.simulation;

import java.util.Arrays;

/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 * <p>
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 * <p>
 * 输入：digits = [0]
 * 输出：[1]
 * 提示：
 * <p>
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 */
public class PlusOne {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new PlusOne().plusOne(new int[] {1, 2, 3})));
		System.out.println(Arrays.toString(new PlusOne().plusOne(new int[] {4, 3, 2, 1})));
		System.out.println(Arrays.toString(new PlusOne().plusOne(new int[] {0})));
		System.out.println(Arrays.toString(new PlusOne().plusOne(new int[] {9})));
		System.out.println(Arrays.toString(new PlusOne().plusOne(new int[] {9, 9, 9})));
		System.out.println(Arrays.toString(new PlusOne().plusOne(new int[] {1, 2, 9})));
		System.out.println(Arrays.toString(new PlusOne().plusOne(new int[] {1, 9, 9})));
	}

	public int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0) {
			return digits;
		}
		boolean flag = false;
		for (int i = digits.length - 1; i >= 0; i--) {
			int num = digits[i];
			if (num != 9) {
				digits[i]++;
				flag = true;
				break;
			} else {
				digits[i] = 0;
			}
		}
		if (!flag) {
			digits = new int[digits.length + 1];
			Arrays.fill(digits, 0);
			digits[0] = 1;
		}
		return digits;
	}
}
