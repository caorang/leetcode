package cn.hutool.jackie.algorithm.design.p1600_1699;

import java.util.Stack;

/**
 * 给定一个算术表达式的后缀表示法的标记（token） postfix ，构造并返回该表达式对应的二叉表达式树。
 * <p>
 * 后缀表示法是一种将操作数写在运算符之前的表示法。例如，表达式 4*(5-(2+7)) 的后缀表示法表示为数组 postfix = ["4","5","7","2","+","-","*"] 。
 * <p>
 * 抽象类 Node 需要用于实现二叉表达式树。我们将通过 evaluate 函数来测试返回的树是否能够解析树中的值。你不可以移除 Node 类，但你可以按需修改此类，也可以定义其他类来实现它。
 * <p>
 * 二叉表达式树是一种表达算术表达式的二叉树。二叉表达式树中的每一个节点都有零个或两个子节点。 叶节点（有 0 个子节点的节点）表示操作数，非叶节点（有 2 个子节点的节点）表示运算符： '+' （加）、 '-' （减）、 '*' （乘）和 '/' （除）。
 * <p>
 * 我们保证任何子树对应值的绝对值不超过 109 ，且所有操作都是有效的（即没有除以零的操作）
 * <p>
 * 进阶： 你可以将表达式树设计得更模块化吗？例如，你的设计能够不修改现有的 evaluate 的实现就能支持更多的操作符吗？
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入： s = ["3","4","+","2","*","7","/"]
 * 输出： 2
 * 解释： 此表达式可解析为上述二叉树，其对应表达式为 ((3+4)*2)/7) = 14/7 = 2.
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: s = ["4","5","7","2","+","-","*"]
 * 输出: -16
 * 解释: 此表达式可解析为上述二叉树，其对应表达式为 4*(5-(2+7)) = 4*(-4) = -16.
 * 提示:
 * <p>
 * 1 <= s.length < 100
 * s.length 是奇数。
 * s 包含数字和字符 '+' 、 '-' 、 '*' 以及 '/' 。
 * 如果 s[i] 是数，则对应的整数不超过 105 。
 * s 保证是一个有效的表达式。
 * 结果值和所有过程值的绝对值均不超过 109 。
 * 保证表达式不包含除以零的操作。
 *
 * @author rcao1
 */
public class L1628Evaluate {
    static class Node {
        String val;
        Node left;
        Node right;

        public Node(String val) {
            this.val = val;
        }

        public int evaluate() {
            if (this.val.equals("*")) {
                return this.left.evaluate() * this.right.evaluate();
            } else if (this.val.equals("/")) {
                return this.left.evaluate() / this.right.evaluate();
            } else if (this.val.equals("+")) {
                return this.left.evaluate() + this.right.evaluate();
            } else if (this.val.equals("-")) {
                return this.left.evaluate() - this.right.evaluate();
            }
            return Integer.valueOf(this.val);
        }
    }

    static class TreeBuilder {
        public Node buildTree(String[] postfix) {
            if (postfix == null || postfix.length == 0) {
                return null;
            }
            Stack<Node> stack = new Stack<>();
            for (int i = 0; i < postfix.length; i++) {
                String str = postfix[i];
                if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                    Node newNode = new Node(str);
                    newNode.right = stack.pop();
                    newNode.left = stack.pop();
                    stack.push(newNode);
                } else {
                    stack.push(new Node(str));
                }
            }
            return stack.peek();
        }
    }

    public static void main(String[] args) {
        TreeBuilder obj = new TreeBuilder();
        Node expTree = obj.buildTree(new String[] {"3", "4", "+", "2", "*", "7", "/"});
        int ans = expTree.evaluate();
        System.out.println(ans);
    }
}
