package cn.hutool.jackie.algorithm.array;

import java.util.Stack;

/**
 * @author rcao1
 * @see <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/">...</a>
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        System.out.println(new EvaluateReversePolishNotation().evalRPN(new String[] {"2", "1", "+", "3", "*"}));
        System.out.println(new EvaluateReversePolishNotation().evalRPN(new String[] {"4", "13", "5", "/", "+"}));
        System.out.println(new EvaluateReversePolishNotation().evalRPN(
                new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String c = tokens[i];
            if (c.equals("+")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(num2 + num1));
            } else if (c.equals("-")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(num2 - num1));
            } else if (c.equals("*")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(num2 * num1));
            } else if (c.equals("/")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(num2 / num1));
            } else {
                stack.push(c);
            }
        }
        try {
            return Integer.parseInt(stack.pop());
        } catch (Exception ex) {
            return 0;
        }
    }
}
