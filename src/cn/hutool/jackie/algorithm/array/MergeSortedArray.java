package cn.hutool.jackie.algorithm.array;

/**
 * @See <a href="https://leetcode.cn/problems/merge-sorted-array/">...</a>
 */
public class MergeSortedArray {

	public static void main(String[] args) {
		int[] nums1 = {1, 2, 3, 0, 0, 0};
		int m = 3;
		int[] nums2 = {2, 5, 6};
		int n = 3;
		MergeSortedArray merger = new MergeSortedArray();
		merger.merge(nums1, m, nums2, n);
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int[] mergedArray = new int[m + n];

		if (nums1 == null) {
			mergedArray = nums2;
		}
		if (nums2 == null) {
			mergedArray = nums1;
		}

		int i = 0, j = 0, k = 0;

		while (i < m && j < n) {
			if (nums1[i] <= nums2[j]) {
				mergedArray[k++] = nums1[i++];
			} else {
				mergedArray[k++] = nums2[j++];
			}
		}

		while (i < m) {
			mergedArray[k++] = nums1[i++];
		}

		while (j < n) {
			mergedArray[k++] = nums2[j++];
		}

		nums1 = mergedArray;

		System.out.print("[");
		for (int index = 0; index < mergedArray.length; index++) {
			System.out.print(mergedArray[index]);
			if (index < mergedArray.length - 1) {
				System.out.print(",");
			}
		}
		System.out.print("]");
	}
}
