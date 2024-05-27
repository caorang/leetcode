package cn.hutool.jackie.algorithm.plus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 前缀树（trie ，发音为 "try"）是一个树状的数据结构，用于高效地存储和检索一系列字符串的前缀。前缀树有许多应用，如自动补全和拼写检查。
 * <p>
 * 实现前缀树 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 将字符串 word 插入前缀树中。
 * int countWordsEqualTo(String word) 返回前缀树中字符串 word 的实例个数。
 * int countWordsStartingWith(String prefix) 返回前缀树中以 prefix 为前缀的字符串个数。
 * void erase(String word) 从前缀树中移除字符串 word 。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入
 * ["Trie", "insert", "insert", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsStartingWith"]
 * [[], ["apple"], ["apple"], ["apple"], ["app"], ["apple"], ["apple"], ["app"], ["apple"], ["app"]]
 * 输出
 * [null, null, null, 2, 2, null, 1, 1, null, 0]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");               // 插入 "apple"。
 * trie.insert("apple");               // 插入另一个 "apple"。
 * trie.countWordsEqualTo("apple");    // 有两个 "apple" 实例，所以返回 2。
 * trie.countWordsStartingWith("app"); // "app" 是 "apple" 的前缀，所以返回 2。
 * trie.erase("apple");                // 移除一个 "apple"。
 * trie.countWordsEqualTo("apple");    // 现在只有一个 "apple" 实例，所以返回 1。
 * trie.countWordsStartingWith("app"); // 返回 1
 * trie.erase("apple");                // 移除 "apple"。现在前缀树是空的。
 * trie.countWordsStartingWith("app"); // 返回 0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 只包含小写英文字母。
 * insert、 countWordsEqualTo、 countWordsStartingWith 和 erase 总共调用最多 3 * 104 次。
 * 保证每次调用 erase 时，字符串 word 总是存在于前缀树中。
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/implement-trie-ii-prefix-tree/description/">...</a>
 */
public class ImplementTrie2PrefixTree {
    public static void main(String[] args) throws Exception {
        //case1();
        case2();
    }

    private static void case1() throws Exception {
        String[] operations = {"insert", "insert", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsEqualTo",
                "countWordsStartingWith", "erase", "countWordsStartingWith" };
        String[] parameters = {"apple", "apple", "apple", "app", "apple", "apple", "app", "apple", "app" };
        Trie trie = new Trie();
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            String parameter = parameters[i];
            Method method = Trie.class.getMethod(operation, String.class);
            Object rsp = method.invoke(trie, parameter);
            System.out.println("method=" + operation + ", param=" + parameter + ", return=" + rsp);
        }
    }

    private static void case2() throws Exception {
        String[] operations = {
                "countWordsEqualTo", "countWordsEqualTo", "countWordsStartingWith", "countWordsStartingWith", "countWordsEqualTo", "insert",
                "insert", "countWordsStartingWith", "countWordsStartingWith", "countWordsStartingWith", "insert", "countWordsStartingWith",
                "erase", "insert", "insert", "countWordsStartingWith", "countWordsStartingWith", "countWordsEqualTo",
                "countWordsStartingWith", "insert", "countWordsStartingWith", "countWordsEqualTo", "countWordsEqualTo", "erase", "insert",
                "erase", "insert", "countWordsEqualTo", "countWordsStartingWith", "countWordsStartingWith", "countWordsEqualTo",
                "countWordsEqualTo", "insert", "erase", "insert", "countWordsEqualTo", "countWordsStartingWith", "countWordsEqualTo",
                "countWordsEqualTo", "countWordsStartingWith", "insert", "insert", "insert", "insert", "countWordsEqualTo", "erase",
                "countWordsStartingWith", "insert", "countWordsEqualTo", "insert", "countWordsEqualTo", "erase", "erase", "insert", "erase",
                "countWordsStartingWith", "countWordsEqualTo", "erase", "erase", "erase", "countWordsEqualTo", "insert", "erase",
                "countWordsStartingWith", "insert", "countWordsStartingWith", "countWordsEqualTo", "countWordsEqualTo", "insert", "erase",
                "insert", "countWordsEqualTo", "countWordsEqualTo", "erase", "countWordsEqualTo", "countWordsStartingWith", "insert",
                "countWordsEqualTo", "countWordsStartingWith", "erase", "erase", "countWordsStartingWith", "erase", "insert",
                "countWordsEqualTo", "insert", "countWordsEqualTo", "countWordsEqualTo", "countWordsStartingWith", "erase", "insert",
                "insert", "insert", "insert", "erase", "countWordsStartingWith", "insert", "countWordsStartingWith",
                "countWordsStartingWith", "countWordsStartingWith", "erase", "countWordsEqualTo", "insert", "insert",
                "countWordsStartingWith" };
        String[] parameters =
                {"bp", "ypg", "uh", "t", "bp", "hylma", "q", "i", "le", "biywd", "t", "u", "hylma", "agoa", "aq", "s", "nslu", "dbi", "m",
                        "f", "e", "si", "i", "aq", "oonmr", "f", "nxpwu", "jtx", "ooqb", "aq", "bj", "u", "su", "oonmr", "xgls", "dc",
                        "nww", "skksv", "ivomo", "tw", "ttzgj", "nsluj", "ma", "qridy", "dbi", "xgls", "a", "bj", "iupom", "agoa", "am",
                        "ttzgj", "agoa", "htkog", "bj", "jlcei", "ndxzb", "ma", "nsluj", "htkog", "gjihm", "chsg", "qridy", "b", "tth", "p",
                        "i", "z", "am", "su", "s", "vuaz", "ipph", "tth", "oonmr", "y", "l", "am", "fjr", "agoa", "q", "n", "nxpwu", "txbp",
                        "kbb", "saido", "kbb", "lzfcd", "bcw", "saido", "xgls", "xy", "d", "lrr", "lrr", "ryls", "sqj", "d", "el", "p", "s",
                        "xtpeg", "htkog", "vzwp", "s"
                };
        Trie trie = new Trie();
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            String parameter = parameters[i];
            if (parameter.startsWith("s")) {
                System.out.println();
            }
            Method method = Trie.class.getMethod(operation, String.class);
            Object rsp = method.invoke(trie, parameter);
            System.out.println("method=" + operation + ", param=" + parameter + ", return=" + rsp);
        }
        System.out.println("end");
    }
}

class Trie {

    char c;
    int count;
    Map<Character, Trie> children = new HashMap<>();

    public Trie() {
        this.c = '#';
    }

    public Trie(char c) {
        this.c = c;
    }

    private boolean isLeaf() {
        return children == null || children.size() == 0;
    }

    private int getDescendantCount() {
        AtomicInteger count = new AtomicInteger(this.count);
        this.children.forEach((k, v) -> {
            count.addAndGet(v.getDescendantCount());
        });
        return count.get();
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        Trie root = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Trie child;
            if (root.children.containsKey(c)) {
                child = root.children.get(c);
                if (i == word.length() - 1) {
                    child.count++;
                }
            } else {
                child = new Trie(c);
                if (i == word.length() - 1) {
                    child.count = 1;
                }
                root.children.put(c, child);
            }
            root = child;
        }
    }

    public int countWordsEqualTo(String word) {
        if (word == null || word.length() == 0) {
            return 0;
        }
        Trie root = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Trie child;
            if (root.children.containsKey(c)) {
                child = root.children.get(c);
                if (i == word.length() - 1) {
                    return child.count;
                }
            } else {
                return 0;
            }
            root = child;
        }
        return 0;
    }

    public int countWordsStartingWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return 0;
        }
        Trie root = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            Trie child;
            if (root.children.containsKey(c)) {
                child = root.children.get(c);
                if (i == prefix.length() - 1) {
                    return child.getDescendantCount();
                }
            } else {
                return 0;
            }
            root = child;
        }
        return 0;
    }

    public void erase(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        Trie root = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (root.children.containsKey(c)) {
                Trie child = root.children.get(c);
                if (i == word.length() - 1 && child.count > 0) {
                    if (child.count == 1 && (child.children == null || child.children.size() == 0)) {
                        root.children.remove(c);
                    } else {
                        child.count--;
                    }
                    return;
                }
                root = child;
            } else {
                break;
            }
        }
    }
}