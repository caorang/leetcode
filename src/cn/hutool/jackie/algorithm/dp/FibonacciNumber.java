package cn.hutool.jackie.algorithm.dp;

public class FibonacciNumber {

    public static void main(String[] args) {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();
        System.out.println(fibonacciNumber.fib2(45));
        System.out.println(fibonacciNumber.fib2(95));
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

    public int fib2(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n - 1];
    }
}
