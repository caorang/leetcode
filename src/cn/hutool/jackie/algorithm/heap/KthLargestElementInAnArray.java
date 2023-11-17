package cn.hutool.jackie.algorithm.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author rcao1
 * @See <a href="https://leetcode.cn/problems/kth-largest-element-in-an-array">...</a>
 */
public class KthLargestElementInAnArray {

    public static void main(String[] args) {
        System.out.println(new KthLargestElementInAnArray().findKthLargest3(new int[] {3, 2, 1, 5, 6, 4}, 2));
        System.out.println(new KthLargestElementInAnArray().findKthLargest3(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    public int findKthLargest(int[] nums, int k) {
        Integer[] nums1 = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(nums1, (a, b) -> b - a);
        return nums1[k - 1];
    }

    public int findKthLargest2(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int root, int heapSize) {
        int left = root * 2 + 1, right = root * 2 + 2, largest = root;
        if (left < heapSize && a[left] > a[largest]) {
            largest = left;
        }
        if (right < heapSize && a[right] > a[largest]) {
            largest = right;
        }
        if (largest != root) {
            swap(a, root, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public int findKthLargest3(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; ++i) {
            pq.add(-nums[i]);
        }
        for (int i = 0; i < k - 1; ++i) {
            pq.poll();
        }
        return -pq.poll();
    }
}
