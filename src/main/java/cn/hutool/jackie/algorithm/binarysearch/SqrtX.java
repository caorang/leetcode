package cn.hutool.jackie.algorithm.binarysearch;

public class SqrtX {

    public static void main(String[] args) {
        System.out.println(new SqrtX().mySqrt(8));
        System.out.println(new SqrtX().mySqrt(9));
        System.out.println(new SqrtX().mySqrt(10));
        System.out.println(new SqrtX().mySqrt(16));
        System.out.println(new SqrtX().mySqrt(17));
        System.out.println(new SqrtX().mySqrt(2147483647));
    }

    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        int left = 1;
        int right = x;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
