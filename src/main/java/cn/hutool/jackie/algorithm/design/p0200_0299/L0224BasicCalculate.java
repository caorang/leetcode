package cn.hutool.jackie.algorithm.design.p0200_0299;

import java.util.Stack;

/**
 * @author rcao1
 */
public class L0224BasicCalculate {

    public int calculate(String s) {
        return calculate(s, 0)[0];
    }

    public int[] calculate(String s, int start) {
        if (s == null || s.length() == 0) {
            return new int[] {0, 0};
        }
        s = s.replaceAll(" ", "");
        Stack<Integer> stack = new Stack<>();
        char preSign = '+';
        int num = 0;
        int[] res = new int[2];
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                int[] nextNum = calculate(s, i + 1);
                num = nextNum[0];
                i = nextNum[1];
            }
            if (i == s.length() - 1 || !Character.isDigit(c) && c != '(') {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                preSign = c;
                num = 0;
            }
            if (c == ')') {
                res[1] = i;
                break;
            }
        }
        while (!stack.isEmpty()) {
            res[0] += stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        L0224BasicCalculate calculator = new L0224BasicCalculate();
        System.out.println(calculator.calculate("3+(2*2)"));
        System.out.println(calculator.calculate("(4-3)*(2+2)"));
        System.out.println(calculator.calculate("(3+2)*2"));
        System.out.println(calculator.calculate(" (3+5) / 2 "));
        System.out.println(calculator.calculate(" 3+5 / 2 "));
    }
}
