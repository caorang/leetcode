package cn.hutool.jackie.algorithm.coupang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/design-in-memory-file-system/">...</a>
 */
public class FileSystem {

    private Trie root = new Trie();

    public FileSystem() {
    }

    public List<String> ls(String path) {
        List<String> ans = new ArrayList<>();
        Trie node = root.search(path);
        if (node == null) {
            return ans;
        }
        if (node.isFile) {
            ans.add(node.name);
            return ans;
        }
        for (String v : node.children.keySet()) {
            ans.add(v);
        }
        Collections.sort(ans);
        return ans;
    }

    public void mkdir(String path) {
        root.insert(path, false);
    }

    public void addContentToFile(String filePath, String content) {
        Trie node = root.insert(filePath, true);
        node.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        Trie node = root.search(filePath);
        return node.content.toString();
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d"));
        fileSystem.addContentToFile("/a/b/c/d", " world");
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d"));
        System.out.println(fileSystem.ls("/a/b/c/d"));
        System.out.println(fileSystem.ls("/a/b/c"));
        System.out.println(fileSystem.ls("/a/b"));
        System.out.println(fileSystem.ls("/a"));
        System.out.println(fileSystem.ls("/a/b/c/d"));
    }
}

class Trie {
    String name;
    boolean isFile;
    StringBuilder content = new StringBuilder();
    Map<String, Trie> children = new HashMap<>();

    Trie insert(String path, boolean isFile) {
        Trie node = this;
        String[] ps = path.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                node.children.put(p, new Trie());
            }
            node = node.children.get(p);
        }
        node.isFile = isFile;
        if (isFile) {
            node.name = ps[ps.length - 1];
        }
        return node;
    }

    Trie search(String path) {
        Trie node = this;
        String[] ps = path.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                return null;
            }
            node = node.children.get(p);
        }
        return node;
    }
}
