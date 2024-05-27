package cn.hutool.jackie.algorithm.simulation;

/**
 * @See <a href="https://leetcode.cn/problems/count-the-digits-that-divide-a-number/">...</a>
 */
public class CountTheDigitsThatDivideNumber {
    public static void main(String[] args) {
        System.out.println(new CountTheDigitsThatDivideNumber().countDigits(11));
        System.out.println(new CountTheDigitsThatDivideNumber().countDigits(7));
        System.out.println(new CountTheDigitsThatDivideNumber().countDigits(121));
        System.out.println(new CountTheDigitsThatDivideNumber().countDigits(1248));
    }

    private int countDigits(int number) {
        if (number < 10) {
            return 1;
        }
        int original = number;
        int count = 0;
        while (number != 0) {
            int pos = number % 10;
            if (pos == 0) {
                return 0;
            }
            if (original % pos == 0) {
                count++;
            }
            number = number / 10;
        }
        return count;
    }
}
