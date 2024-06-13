package cn.hutool.jackie.algorithm.design.p0500_0599;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 设计一个内存文件系统，模拟以下功能：
 * <p>
 * 实现文件系统类:
 * <p>
 * FileSystem() 初始化系统对象
 * List ls(String path)
 * 如果 path 是一个文件路径，则返回一个仅包含该文件名称的列表。
 * 如果 path 是一个目录路径，则返回该目录中文件和 目录名 的列表。
 * 答案应该 按字典顺序 排列。
 * <p>
 * void mkdir(String path) 根据给定的路径创建一个新目录。给定的目录路径不存在。如果路径中的中间目录不存在，您也应该创建它们。
 * void addContentToFile(String filePath, String content)
 * 如果 filePath 不存在，则创建包含给定内容 content的文件。
 * 如果 filePath 已经存在，将给定的内容 content附加到原始内容。
 * String readContentFromFile(String filePath) 返回 filePath下的文件内容。
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入:
 * ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 * [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 * 输出:
 * [null,[],null,null,["a"],"hello"]
 * <p>
 * 解释:
 * FileSystem fileSystem = new FileSystem();
 * fileSystem.ls("/");                         // 返回 []
 * fileSystem.mkdir("/a/b/c");
 * fileSystem.addContentToFile("/a/b/c/d", "hello");
 * fileSystem.ls("/");                         // 返回 ["a"]
 * fileSystem.readContentFromFile("/a/b/c/d"); // 返回 "hello"
 * 注意:
 * <p>
 * 1 <= path.length, filePath.length <= 100
 * path 和 filePath 都是绝对路径，除非是根目录 ‘/’ 自身，其他路径都是以 ‘/’ 开头且 不 以 ‘/’ 结束。
 * 你可以假定所有操作的参数都是有效的，即用户不会获取不存在文件的内容，或者获取不存在文件夹和文件的列表。
 * 你可以假定所有文件夹名字和文件名字都只包含小写字母，且同一文件夹下不会有相同名字的文件夹或文件。
 * 1 <= content.length <= 50
 * ls, mkdir, addContentToFile, and readContentFromFile 最多被调用 300 次
 *
 * @author rcao1
 */
public class L0588FileSystem {

    static class File {
        public String name;
        public String content;
        public boolean isDirectory;
        public Map<String, File> children;

        public File(String name, boolean isDirectory) {
            this.name = name;
            this.isDirectory = isDirectory;
            this.children = new HashMap<>();
        }

        public void append(String content) {
            if (this.content == null) {
                this.content = content;
            } else {
                this.content = this.content.concat(content);
            }
        }
    }

    static class FileSystem {

        private File root;

        public FileSystem() {
            this.root = new File("/", true);
        }

        public List<String> ls(String path) {
            if (path == null || path.length() == 0) {
                return new ArrayList<>();
            }
            if (path.equals("/")) {
                List<String> res = this.root.children.keySet().stream().collect(Collectors.toList());
                res.sort((o1, o2) -> o1.compareTo(o2));
                return res;
            }
            if (path.startsWith("/")) {
                path = path.substring(1, path.length());
            }
            String[] paths = path.split("/");
            File current = this.root;
            for (int i = 0; i < paths.length; i++) {
                String currentPath = paths[i];
                if (current.children.containsKey(currentPath)) {
                    current = current.children.get(currentPath);
                } else {
                    return new ArrayList<>();
                }
            }
            if (current.isDirectory) {
                List<String> res = current.children.keySet().stream().collect(Collectors.toList());
                res.sort((o1, o2) -> o1.compareTo(o2));
                return res;
            }
            List<String> res = new ArrayList<>();
            res.add(current.name);
            return res;
        }

        public void mkdir(String path) {
            if (path == null || path.length() == 0) {
                return;
            }
            if (path.startsWith("/")) {
                path = path.substring(1, path.length());
            }
            String[] paths = path.split("/");
            File current = this.root;
            for (int i = 0; i < paths.length; i++) {
                String currentPath = paths[i];
                if (current.children.containsKey(currentPath)) {
                    current = current.children.get(currentPath);
                } else {
                    File newDir = new File(currentPath, true);
                    current.children.put(currentPath, newDir);
                    current = newDir;
                }
            }
        }

        public void addContentToFile(String filePath, String content) {
            if (filePath == null || filePath.length() == 0) {
                return;
            }
            if (filePath.startsWith("/")) {
                filePath = filePath.substring(1, filePath.length());
            }
            String[] paths = filePath.split("/");
            File current = this.root;
            for (int i = 0; i < paths.length - 1; i++) {
                String currentPath = paths[i];
                if (current.children.containsKey(currentPath)) {
                    current = current.children.get(currentPath);
                } else {
                    return;
                }
            }
            String fileName = paths[paths.length - 1];
            File newFile;
            if (current.children.containsKey(fileName)) {
                newFile = current.children.get(fileName);
            } else {
                newFile = new File(fileName, false);
                current.children.put(fileName, newFile);
            }
            newFile.append(content);
        }

        public String readContentFromFile(String filePath) {
            if (filePath == null || filePath.length() == 0) {
                return "";
            }
            if (filePath.startsWith("/")) {
                filePath = filePath.substring(1, filePath.length());
            }
            String[] paths = filePath.split("/");
            File current = this.root;
            for (int i = 0; i < paths.length; i++) {
                String currentPath = paths[i];
                if (current.children.containsKey(currentPath)) {
                    current = current.children.get(currentPath);
                } else {
                    return "";
                }
            }
            return current.isDirectory ? null : current.content;
        }
    }

    public static void main(String[] args) {
        /**
         * 输入:
         * ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
         * [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
         * 输出:
         * [null,[],null,null,["a"],"hello"]
         */
        String method = "[\"FileSystem\",\"mkdir\",\"ls\",\"ls\",\"mkdir\",\"ls\",\"ls\",\"addContentToFile\",\"readContentFromFile\",\"ls\",\"readContentFromFile\"]";
        String arguments = "[[],[\"/zijzllb\"],[\"/\"],[\"/zijzllb\"],[\"/r\"],[\"/\"],[\"/r\"],[\"/zijzllb/hfktg\",\"d\"],[\"/zijzllb/hfktg\"],[\"/\"],[\"/zijzllb/hfktg\"]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(FileSystem.class);

        FileSystem fileSystem = new FileSystem();
        // 返回 []
        System.out.println(fileSystem.ls("/"));
        // 创建目录
        fileSystem.mkdir("/a/b/c");
        // 创建文件内容
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        fileSystem.addContentToFile("/a/b/c/d", ", world!");
        // 返回 ["a"]
        System.out.println(fileSystem.ls("/"));
        // 返回 "hello, world!"
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d"));
        // 创建目录
        fileSystem.mkdir("/a/d");
        // 创建目录
        fileSystem.mkdir("/a/e");
        // 返回 [b, d, e]
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
