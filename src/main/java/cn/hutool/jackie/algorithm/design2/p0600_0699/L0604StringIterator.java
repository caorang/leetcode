package cn.hutool.jackie.algorithm.design2.p0600_0699;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 设计并实现一个迭代压缩字符串的数据结构。给定的压缩字符串的形式是，每个字母后面紧跟一个正整数，表示该字母在原始未压缩字符串中出现的次数。
 * <p>
 * 设计一个数据结构，它支持如下两种操作： next 和 hasNext。
 * <p>
 * next() - 如果原始字符串中仍有未压缩字符，则返回下一个字符，否则返回空格。
 * hasNext() - 如果原始字符串中存在未压缩的的字母，则返回true，否则返回false。
 * 示例 1：
 * <p>
 * 输入：
 * ["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext"]
 * [["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
 * 输出：
 * [null, "L", "e", "e", "t", "C", "o", true, "d", true]
 * <p>
 * 解释：
 * StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
 * stringIterator.next(); // 返回 "L"
 * stringIterator.next(); // 返回 "e"
 * stringIterator.next(); // 返回 "e"
 * stringIterator.next(); // 返回 "t"
 * stringIterator.next(); // 返回 "C"
 * stringIterator.next(); // 返回 "o"
 * stringIterator.hasNext(); // 返回 True
 * stringIterator.next(); // 返回 "d"
 * stringIterator.hasNext(); // 返回 True
 * 提示：
 * <p>
 * 1 <= compressedString.length <= 1000
 * compressedString 由小写字母、大写字母和数字组成。
 * 在 compressedString 中，单个字符的重复次数在 [1,10 ^9] 范围内。
 * next 和 hasNext 的操作数最多为 100 。
 *
 * @author rcao1
 */
public class L0604StringIterator {

    static class StringIterator {

        private String str;
        private int pos = 0;

        public StringIterator(String compressedString) {
            this.str = intString(compressedString);
        }

        private String intString(String compressedString) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < compressedString.length()) {
                char ch = compressedString.charAt(i++);
                int num = 0;
                while (i < compressedString.length() && Character.isDigit(compressedString.charAt(i))) {
                    num = num * 10 + compressedString.charAt(i) - '0';
                    i++;
                }
                for (int j = 0; j < num; j++) {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }

        public char next() {
            if (hasNext()) {
                return this.str.charAt(pos++);
            }
            return ' ';
        }

        public boolean hasNext() {
            if (pos >= str.length()) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        /**
         * 输入：
         * ["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext"]
         * [["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
         * 输出：
         * [null, "L", "e", "e", "t", "C", "o", true, "d", true]
         */
        String method = "[\"StringIterator\",\"next\",\"next\",\"next\",\"hasNext\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"hasNext\",\"next\",\"next\",\"next\",\"next\",\"next\",\"hasNext\",\"next\",\"next\",\"next\",\"next\",\"next\",\"hasNext\",\"next\",\"next\",\"next\",\"next\",\"hasNext\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"hasNext\",\"next\",\"hasNext\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"hasNext\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"next\",\"hasNext\",\"next\",\"next\",\"next\",\"hasNext\",\"next\",\"next\",\"hasNext\",\"next\",\"next\",\"next\",\"next\",\"next\"]";
        String arguments = "[[\"z82d333333333\"],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(StringIterator.class);
    }
}
