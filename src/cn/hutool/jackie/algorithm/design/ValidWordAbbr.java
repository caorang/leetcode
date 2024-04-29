package cn.hutool.jackie.algorithm.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 单词的 缩写 需要遵循 <起始字母><中间字母数><结尾字母> 这样的格式。如果单词只有两个字符，那么它就是它自身的 缩写 。
 * <p>
 * 以下是一些单词缩写的范例：
 * <p>
 * dog --> d1g 因为第一个字母 'd' 和最后一个字母 'g' 之间有 1 个字母
 * internationalization --> i18n 因为第一个字母 'i' 和最后一个字母 'n' 之间有 18 个字母
 * it --> it 单词只有两个字符，它就是它自身的 缩写
 * <p>
 * <p>
 * 实现 ValidWordAbbr 类：
 * <p>
 * ValidWordAbbr(String[] dictionary) 使用单词字典 dictionary 初始化对象
 * boolean isUnique(string word) 如果满足下述任意一个条件，返回 true ；否则，返回 false ：
 * 字典 dictionary 中没有任何其他单词的 缩写 与该单词 word 的 缩写 相同。
 * 字典 dictionary 中的所有 缩写 与该单词 word 的 缩写 相同的单词都与 word 相同 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["ValidWordAbbr", "isUnique", "isUnique", "isUnique", "isUnique", "isUnique"]
 * [[["deer", "door", "cake", "card"]], ["dear"], ["cart"], ["cane"], ["make"], ["cake"]]
 * 输出
 * [null, false, true, false, true, true]
 * <p>
 * 解释
 * ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
 * validWordAbbr.isUnique("dear"); // 返回 false，字典中的 "deer" 与输入 "dear" 的缩写都是 "d2r"，但这两个单词不相同
 * validWordAbbr.isUnique("cart"); // 返回 true，字典中不存在缩写为 "c2t" 的单词
 * validWordAbbr.isUnique("cane"); // 返回 false，字典中的 "cake" 与输入 "cane" 的缩写都是 "c2e"，但这两个单词不相同
 * validWordAbbr.isUnique("make"); // 返回 true，字典中不存在缩写为 "m2e" 的单词
 * validWordAbbr.isUnique("cake"); // 返回 true，因为 "cake" 已经存在于字典中，并且字典中没有其他缩写为 "c2e" 的单词
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= dictionary.length <= 3 * 104
 * 1 <= dictionary[i].length <= 20
 * dictionary[i] 由小写英文字母组成
 * 1 <= word <= 20
 * word 由小写英文字母组成
 * 最多调用 5000 次 isUnique
 *
 * @author rcao1
 */
public class ValidWordAbbr {

    private Map<String, Set<String>> dictionary;

    public ValidWordAbbr(String[] words) {
        init(words);
    }

    private void init(String[] words) {
        this.dictionary = new HashMap<>(8);
        for (String word : words) {
            String key = getKey(word);
            if (this.dictionary.containsKey(key)) {
                this.dictionary.get(key).add(word);
            } else {
                Set<String> set = new HashSet<>();
                set.add(word);
                this.dictionary.put(key, set);
            }
        }
    }

    private String getKey(String word) {
        if (word == null) {
            return null;
        }
        if (word.length() <= 2) {
            return word;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(word.charAt(0));
        stringBuilder.append(word.length() - 2);
        stringBuilder.append(word.charAt(word.length() - 1));
        return stringBuilder.toString();
    }

    public boolean isUnique(String word) {
        String key = getKey(word);
        if (this.dictionary.containsKey(key)) {
            Set<String> exist = this.dictionary.get(key);
            if (exist.contains(word) && exist.size() == 1) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ValidWordAbbr validWordAbbr = new ValidWordAbbr(new String[] {"deer", "door", "cake", "card"});
        // 返回 false，字典中的 "deer" 与输入 "dear" 的缩写都是 "d2r"，但这两个单词不相同
        System.out.println(validWordAbbr.isUnique("dear"));
        // 返回 true，字典中不存在缩写为 "c2t" 的单词
        System.out.println(validWordAbbr.isUnique("cart"));
        // 返回 false，字典中的 "cake" 与输入 "cane" 的缩写都是 "c2e"，但这两个单词不相同
        System.out.println(validWordAbbr.isUnique("cane"));
        // 返回 true，字典中不存在缩写为 "m2e" 的单词
        System.out.println(validWordAbbr.isUnique("make"));
        // 返回 true，因为 "cake" 已经存在于字典中，并且字典中没有其他缩写为 "c2e" 的单词
        System.out.println(validWordAbbr.isUnique("cake"));
    }
}
