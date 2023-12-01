package cn.hutool.jackie.algorithm.dp;

public class FibonacciNumber {

    public static void main(String[] args) {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();
        System.out.println(fibonacciNumber.fib(0));
        System.out.println(fibonacciNumber.fib(1));
        System.out.println(fibonacciNumber.fib(2));
        System.out.println(fibonacciNumber.fib(3));
        System.out.println(fibonacciNumber.fib(4));
        System.out.println(fibonacciNumber.fib(5));
        System.out.println(fibonacciNumber.fib(6));
        System.out.println(fibonacciNumber.fib(7));
        System.out.println(fibonacciNumber.fib(8));
        System.out.println(fibonacciNumber.fib(9));
        System.out.println(fibonacciNumber.fib(10));
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
