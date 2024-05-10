package cn.hutool.jackie.algorithm.string;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * <p>
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * <p>
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * <p>
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300]
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/decode-string/">...</a>
 */
public class DecodeString {

    public static void main(String[] args) {
        System.out.println(new DecodeString().decodeString("3[a]2[bc]"));
        System.out.println(new DecodeString().decodeString("3[a2[c]]"));
        System.out.println(new DecodeString().decodeString("2[abc]3[cd]ef"));
    }

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                String str = brackets(s, i);
                sb.append(decodeString(str));
                i += str.length() + 1;
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int count = 0;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    count = count * 10 + (s.charAt(i) - '0');
                    i++;
                }
                String str = brackets(s, i);
                for (int j = 0; j < count; j++) {
                    String decode = decodeString(str);
                    sb.append(decode);
                }
                i += str.length() + 1;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private String brackets(String s, int index) {
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '[') {
                depth++;
            } else if (s.charAt(index) == ']') {
                depth--;
            }
            sb.append(s.charAt(index));
            if (depth <= 0) {
                break;
            }
            index++;
        }
        return sb.substring(1, sb.length() - 1);
    }
}
