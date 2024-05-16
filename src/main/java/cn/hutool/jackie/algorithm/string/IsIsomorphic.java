package cn.hutool.jackie.algorithm.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author rcao1
 */
public class IsIsomorphic {

    private Map<Character, Character> dictionary = new HashMap<>();

    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            Character c1 = s.charAt(i);
            Character c2 = t.charAt(i);
            Character mc1 = null;
            Character mc2 = null;

            if (dictionary.containsKey(c1)) {
                mc1 = dictionary.get(c1);
            } else {
                dictionary.put(c1, c2);
                mc1 = c2;
            }

            if (dictionary.containsKey(c2)) {
                mc2 = dictionary.get(c2);
            } else {
                dictionary.put(c2, c1);
                mc2 = c1;
            }

            System.out.println("c1: " + c1 + " c2: " + c2 + " mc1: " + mc1 + " mc2: " + mc2);

            if (!Objects.equals(mc1, c2) || !Objects.equals(mc2, c1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsIsomorphic().isIsomorphic("paper", "title"));
        System.out.println(new IsIsomorphic().isIsomorphic2("paper", "title"));
    }
}
