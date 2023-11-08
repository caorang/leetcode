package cn.hutool.jackie.algorithm.binarysearch;

/**
 * @See <a href="https://leetcode.cn/problems/search-a-2d-matrix/">...</a>
 */
public class SearchA2dMatrix {

	public static void main(String[] args) {
		int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}, {70, 80, 100, 150}};
		System.out.println(new SearchA2dMatrix().searchMatrix(matrix, 30));
		System.out.println(new SearchA2dMatrix().searchMatrix(matrix, 71));
		System.out.println(new SearchA2dMatrix().searchMatrix(matrix, 80));

		matrix = new int[][] {{1}};
		System.out.println(new SearchA2dMatrix().searchMatrix(matrix, 0));
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int left1 = 0, right1 = matrix.length - 1;
		while (left1 <= right1) {
			int mid1 = left1 + (right1 - left1) / 2;
			if (matrix[mid1][0] < target) {
				left1 = mid1 + 1;
			} else if (matrix[mid1][0] == target) {
				left1 = mid1;
				break;
			} else {
				right1 = mid1 - 1;
			}
		}
		int row = Math.max(0, left1 < right1 ? left1 : right1);
		boolean flag = false;
		int left2 = 0, right2 = matrix[0].length - 1;
		while (left2 <= right2) {
			int mid2 = left2 + (right2 - left2) / 2;
			if (matrix[row][mid2] < target) {
				left2 = mid2 + 1;
			} else if (matrix[row][mid2] == target) {
				left2 = mid2;
				flag = true;
				break;
			} else {
				right2 = mid2 - 1;
			}
		}
		return flag;
	}
}
