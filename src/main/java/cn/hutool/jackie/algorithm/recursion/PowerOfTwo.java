package cn.hutool.jackie.algorithm.recursion;

/**
 * @author rcao1
 */
public class PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 2 != 0) {
            return false;
        }
        return isPowerOfTwo(n / 2);
    }

    public static void main(String[] args) {
        System.out.println(new PowerOfTwo().isPowerOfTwo(1));
        System.out.println(new PowerOfTwo().isPowerOfTwo(2));
        System.out.println(new PowerOfTwo().isPowerOfTwo(3));
        System.out.println(new PowerOfTwo().isPowerOfTwo(4));
        System.out.println(new PowerOfTwo().isPowerOfTwo(5));
        System.out.println(new PowerOfTwo().isPowerOfTwo(6));
        System.out.println(new PowerOfTwo().isPowerOfTwo(8));
        System.out.println(new PowerOfTwo().isPowerOfTwo(16));
    }
}
