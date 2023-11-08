package cn.hutool.jackie.algorithm.greedy;

/**
 * @See <a href="https://leetcode.com/problems/container-with-most-water/">...</a>
 */
public class ContainerWithMostWater {

	public static void main(String[] args) {
		System.out.println(new ContainerWithMostWater().maxAreaWithTwoPointers(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
		System.out.println(new ContainerWithMostWater().maxAreaWithTwoPointers(new int[] {1, 1}));
	}

	private int maxArea(int[] ints) {
		int max = 0;
		for (int i = 0; i < ints.length; i++) {
			for (int j = i + 1; j < ints.length; j++) {
				max = Math.max(max, Math.min(ints[i], ints[j]) * (j - i));
			}
		}
		return max;
	}

	private int maxAreaWithTwoPointers(int[] ints) {
		int left = 0, right = ints.length - 1;
		int max = 0;
		while (left < right) {
			max = Math.max(max, Math.min(ints[left], ints[right]) * (right - left));
			if (ints[left] < ints[right]) {
				left++;
			} else {
				right--;
			}
		}
		return max;
	}
}
