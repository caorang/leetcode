package cn.hutool.jackie.algorithm.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * <p>
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * <p>
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
 * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
 * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
 * 输出顺序无关紧要。返回 [9,0] 也是可以的。
 * 示例 2：
 * <p>
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
 * s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
 * 所以我们返回一个空数组。
 * 示例 3：
 * <p>
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
 * 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
 * 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
 * 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 和 s 由小写英文字母组成
 *
 * @See https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
 */
public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords substringWithConcatenationOfAllWords = new SubstringWithConcatenationOfAllWords();
        System.out.println(substringWithConcatenationOfAllWords.findSubstring("barfoothefoobarman", new String[] {"foo", "bar"}));
        System.out.println(substringWithConcatenationOfAllWords.findSubstring("wordgoodgoodgoodbestword",
                new String[] {"word", "good", "best", "word"}));

        String s =
                "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel";
        String[] words =
                new String[] {"dhvf", "sind", "ffsl", "yekr", "zwzq", "kpeo", "cila", "tfty", "modg", "ztjg", "ybty", "heqg", "cpwo",
                        "gdcj", "lnle", "sefg", "vimw", "bxcb"};
        System.out.println(substringWithConcatenationOfAllWords.findSubstring(s, words));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null || words.length == 0) {
            return new ArrayList<>();
        }
        int length = words[0].length();
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (dictionary.containsKey(words[i])) {
                dictionary.put(words[i], dictionary.get(words[i]) + 1);
            } else {
                dictionary.put(words[i], 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= s.length() - length * words.length; i++) {
            String subString = s.substring(i, i + words[0].length() * words.length);
            Map<String, Integer> dictionary2 = new HashMap<>();
            dictionary2.putAll(dictionary);
            if (isSubStringMatched(dictionary2, subString, length)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isSubStringMatched(Map<String, Integer> dictionary, String string, int length) {
        for (int i = 0; i <= string.length() - length; ) {
            String cut = string.substring(i, i + length);
            if (dictionary.containsKey(cut)) {
                dictionary.put(cut, dictionary.get(cut) - 1);
            } else {
                return false;
            }
            i += length;
        }
        boolean flag = true;
        for (String key : dictionary.keySet()) {
            if (dictionary.get(key) != 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public List<Integer> findSubstringWithDictionary(String s, String[] words) {
        if (s == null || words == null || words.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Set<String> dictionary = init(words);
        for (int i = 0; i <= s.length() - words[0].length() * words.length; i++) {
            String subString = s.substring(i, i + words[0].length() * words.length);
            if (dictionary.contains(subString)) {
                result.add(i);
            }
        }
        return result;
    }

    private Set<String> init(String[] words) {
        Set<String> dictionary = new HashSet<>();
        backtrace(dictionary, new HashSet<>(), new StringBuilder(), words, words[0].length());
        return dictionary;
    }

    private void backtrace(Set<String> dictionary, Set<String> set, StringBuilder sb, String[] words, int wordLength) {
        if (sb.length() >= words.length * wordLength) {
            dictionary.add(sb.toString());
            return;
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (set.contains(i + "-" + word)) {
                continue;
            }
            sb.append(word);
            set.add(i + "-" + word);
            backtrace(dictionary, set, sb, words, wordLength);
            sb.delete(sb.length() - wordLength, sb.length());
            set.remove(i + "-" + word);
        }
    }
}
