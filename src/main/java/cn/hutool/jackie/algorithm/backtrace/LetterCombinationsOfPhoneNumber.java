package cn.hutool.jackie.algorithm.backtrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @See <a href="https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number">...</a>
 */
public class LetterCombinationsOfPhoneNumber {

    static Map<Integer, List<String>> dictionary = new HashMap<>();

    static {
        dictionary.put(2, new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }});
        dictionary.put(3, new ArrayList<String>() {{
            add("d");
            add("e");
            add("f");
        }});
        dictionary.put(4, new ArrayList<String>() {{
            add("g");
            add("h");
            add("i");
        }});
        dictionary.put(5, new ArrayList<String>() {{
            add("j");
            add("k");
            add("l");
        }});
        dictionary.put(6, new ArrayList<String>() {{
            add("m");
            add("n");
            add("o");
        }});
        dictionary.put(7, new ArrayList<String>() {{
            add("p");
            add("q");
            add("r");
            add("s");
        }});
        dictionary.put(8, new ArrayList<String>() {{
            add("t");
            add("u");
            add("v");
        }});
        dictionary.put(9, new ArrayList<String>() {{
            add("w");
            add("x");
            add("y");
            add("z");
        }});
    }

    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfPhoneNumber().letterCombinations("23"));
        System.out.println(new LetterCombinationsOfPhoneNumber().letterCombinations("22"));
        System.out.println(new LetterCombinationsOfPhoneNumber().letterCombinations("234"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        backTrack(combinations, digits, 0, new StringBuffer());
        return combinations;
    }

    private void backTrack(List<String> combinations, String digits, int i, StringBuffer stringBuffer) {
        if (i >= digits.length()) {
            combinations.add(stringBuffer.toString());
            return;
        }
        int digit = digits.charAt(i) - '0';
        List<String> letters = dictionary.get(digit);
        for (String letter : letters) {
            stringBuffer.append(letter);
            backTrack(combinations, digits, i + 1, stringBuffer);
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
    }
}
