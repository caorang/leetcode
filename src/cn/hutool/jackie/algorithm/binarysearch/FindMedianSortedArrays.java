package cn.hutool.jackie.algorithm.binarysearch;

/**
 * <a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/">...</a>
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = merge(nums1, nums2);
        int length = nums.length;
        if (length % 2 == 0) {
            return (double) (nums[length / 2 - 1] + nums[length / 2]) / 2;
        } else {
            return (double) nums[length / 2];
        }
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] mergedArray = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                mergedArray[k++] = nums1[i++];
            } else {
                mergedArray[k++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            mergedArray[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            mergedArray[k++] = nums2[j++];
        }
        return mergedArray;
    }
}
