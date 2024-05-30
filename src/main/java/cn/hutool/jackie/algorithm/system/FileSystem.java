package cn.hutool.jackie.algorithm.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目描述
 * 设计一个内存文件系统，模拟以下功能：
 * <p>
 * 实现文件系统类:
 * <p>
 * FileSystem() 初始化系统对象
 * List<String> ls(String path)
 * 如果 path 是一个文件路径，则返回一个仅包含该文件名称的列表。
 * 如果 path 是一个目录路径，则返回该目录中文件和 目录名 的列表。
 * 答案应该 按字典顺序 排列。
 * <p>
 * void mkdir(String path) 根据给定的路径创建一个新目录。给定的目录路径不存在。如果路径中的中间目录不存在，您也应该创建它们。
 * void addContentToFile(String filePath, String content)
 * 如果 filePath 不存在，则创建包含给定内容 content的文件。
 * 如果 filePath 已经存在，将给定的内容 content附加到原始内容。
 * String readContentFromFile(String filePath) 返回 filePath下的文件内容。
 *
 * @author rcao1
 */
public class FileSystem {

    private DirectoryFile root;

    public FileSystem() {
        this.root = new DirectoryFile("");
    }

    public List<String> ls(String path) {
        String[] paths = path.split("/");
        DirectoryFile current = root;
        for (int i = 0; i < paths.length; i++) {
            String currentPath = paths[i];
            if (currentPath == null || currentPath.equals("")) {
                if (i == 0) {
                    continue;
                } else {
                    return new ArrayList<>();
                }
            }
            Map<String, DirectoryFile> children = current.children;
            if (children.containsKey(currentPath)) {
                current = children.get(currentPath);
            } else {
                return new ArrayList<>();
            }
        }
        List<String> ans = new ArrayList<>();
        if (current != null) {
            current.children.forEach((k, v) -> {
                ans.add(v.name);
            });
        }
        return ans;
    }

    public void mkdir(String path) {
        String[] paths = path.split("/");
        DirectoryFile current = root;
        for (int i = 0; i < paths.length; i++) {
            String currentPath = paths[i];
            if (currentPath == null || currentPath.equals("")) {
                if (i == 0) {
                    continue;
                } else {
                    return;
                }
            }
            Map<String, DirectoryFile> children = current.children;
            if (children.containsKey(currentPath)) {
                current = children.get(currentPath);
            } else {
                DirectoryFile child = new DirectoryFile(currentPath);
                current.children.put(child.name, child);
                current = child;
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] paths = filePath.split("/");
        DirectoryFile current = root;
        for (int i = 0; i < paths.length - 1; i++) {
            String currentPath = paths[i];
            if (currentPath == null || currentPath.equals("")) {
                if (i == 0) {
                    continue;
                } else {
                    return;
                }
            }
            Map<String, DirectoryFile> children = current.children;
            if (children.containsKey(currentPath)) {
                current = children.get(currentPath);
            } else {
                return;
            }
        }
        String file = paths[paths.length - 1];
        if (current.children.containsKey(file)) {
            DirectoryFile child = current.children.get(file);
            child.content = content;
        } else {
            DirectoryFile child = new DirectoryFile(file);
            child.isFile = true;
            child.content = content;
            current.children.put(file, child);
        }
    }

    public String readContentFromFile(String filePath) {
        String[] paths = filePath.split("/");
        DirectoryFile current = root;
        for (int i = 0; i < paths.length - 1; i++) {
            String currentPath = paths[i];
            if (currentPath == null || currentPath.equals("")) {
                if (i == 0) {
                    continue;
                } else {
                    return "NaN";
                }
            }
            Map<String, DirectoryFile> children = current.children;
            if (children.containsKey(currentPath)) {
                current = children.get(currentPath);
            } else {
                return "NaN";
            }
        }
        String file = paths[paths.length - 1];
        if (current.children.containsKey(file)) {
            DirectoryFile child = current.children.get(file);
            if (child.isFile) {
                return child.content;
            }
        }
        return "NaN";
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        // 返回 []
        System.out.println(fileSystem.ls("/"));
        // 创建目录
        fileSystem.mkdir("/a/b/c");
        // 创建文件内容
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        // 返回 ["a"]
        System.out.println(fileSystem.ls("/"));
        // 返回 "hello"
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d"));
        // 创建目录
        fileSystem.mkdir("/a/d");
        // 创建目录
        fileSystem.mkdir("/a/e");
        // 返回 [d, e]
        System.out.println(fileSystem.ls("/a"));
        // 返回 []
        System.out.println(fileSystem.ls("/a/k"));
        // 返回 ""
        System.out.println(fileSystem.readContentFromFile("/a/k"));
        // 创建文件内容
        fileSystem.addContentToFile("/a/s/k", "Can't Put Content");
        // 返回 ""
        System.out.println(fileSystem.readContentFromFile("/a/s/k"));
        // 创建目录
        fileSystem.mkdir("/a/s");
        // 创建文件内容
        fileSystem.addContentToFile("/a/s/k", "Can Put Content");
        // 返回 "Can Put Content"
        System.out.println(fileSystem.readContentFromFile("/a/s/k"));
    }
}

class DirectoryFile {
    public String name;
    public Map<String, DirectoryFile> children = new HashMap<>();
    public boolean isFile = false;
    public String content;

    public DirectoryFile(String name) {
        this.name = name;
    }
}
