package cn.hutool.jackie.algorithm.stack;

import java.util.Stack;

/**
 * @See <a href="https://leetcode.cn/problems/valid-parentheses/">...</a>
 */
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid("()[]{}"));
        System.out.println(new ValidParentheses().isValid("()"));
        System.out.println(new ValidParentheses().isValid("([{}])"));
        System.out.println(new ValidParentheses().isValid("(]"));
        System.out.println(new ValidParentheses().isValid("(])"));
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (character == '{' || character == '[' || character == '(') {
                stack.push(character);
            } else if (character == '}' || character == ']' || character == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek().equals('{') && character == '}') {
                    stack.pop();
                } else if (stack.peek().equals('[') && character == ']') {
                    stack.pop();
                } else if (stack.peek().equals('(') && character == ')') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
