package cn.hutool.jackie.algorithm.design.p1500_1599;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 我们需要使用一套文件分享系统来分享一个非常大的文件，该文件由 m 个从 1 到 m 编号的文件块组成。
 * <p>
 * 当用户加入系统时，系统应为其注册一个独有的 ID。这个独有的 ID 应当被相应的用户使用一次，但是当用户离开系统时，其 ID 应可以被（后续新注册的用户）再次使用。
 * <p>
 * 用户可以请求文件中的某个指定的文件块，系统应当返回拥有这个文件块的所有用户的 ID。如果用户收到 ID 的非空列表，就表示成功接收到请求的文件块。
 * <p>
 * <p>
 * 实现 FileSharing 类：
 * <p>
 * FileSharing(int m) 初始化该对象，文件有 m 个文件块。
 * int join(int[] ownedChunks)：一个新用户加入系统，并拥有文件的一些文件块。系统应当为该用户注册一个 ID，该 ID 应是未被其他用户占用的最小正整数。返回注册的 ID。
 * void leave(int userID)：ID 为 userID 的用户将离开系统，你不能再从该用户提取文件块了。
 * int[] request(int userID, int chunkID)：ID 为 userID 的用户请求编号为 chunkID 的文件块。返回拥有这个文件块的所有用户的 ID 所构成的列表或数组，按升序排列。
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入:
 * ["FileSharing","join","join","join","request","request","leave","request","leave","join"]
 * [[4],[[1,2]],[[2,3]],[[4]],[1,3],[2,2],[1],[2,1],[2],[[]]]
 * 输出:
 * [null,1,2,3,[2],[1,2],null,[],null,1]
 * 解释:
 * FileSharing fileSharing = new FileSharing(4); // 我们用该系统分享由 4 个文件块组成的文件。
 * <p>
 * fileSharing.join([1, 2]);    // 一个拥有文件块 [1,2] 的用户加入系统，为其注册 id = 1 并返回 1。
 * <p>
 * fileSharing.join([2, 3]);    // 一个拥有文件块 [2,3] 的用户加入系统，为其注册 id = 2 并返回 2。
 * <p>
 * fileSharing.join([4]);       // 一个拥有文件块 [4] 的用户加入系统，为其注册 id = 3 并返回 3。
 * <p>
 * fileSharing.request(1, 3);   // id = 1 的用户请求第 3 个文件块，只有 id = 2 的用户拥有文件块，返回 [2] 。注意，现在用户 1 现拥有文件块 [1,2,3]。
 * <p>
 * fileSharing.request(2, 2);   // id = 2 的用户请求第 2 个文件块，id 为 [1,2] 的用户拥有该文件块，所以我们返回 [1,2] 。
 * <p>
 * fileSharing.leave(1);        // id = 1 的用户离开系统，其所拥有的所有文件块不再对其他用户可用。
 * <p>
 * fileSharing.request(2, 1);   // id = 2 的用户请求第 1 个文件块，系统中没有用户拥有该文件块，所以我们返回空列表 [] 。
 * <p>
 * fileSharing.leave(2);        // id = 2 的用户离开系统。
 * <p>
 * fileSharing.join([]);        // 一个不拥有任何文件块的用户加入系统，为其注册 id = 1 并返回 1 。注意，id 1 和 2 空闲，可以重新使用。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= m <= 10^5
 * 0 <= ownedChunks.length <= min(100, m)
 * 1 <= ownedChunks[i] <= m
 * ownedChunks 的值是互不相同的。
 * 1 <= chunkID <= m
 * 当你正确地注册用户 ID 时，题目保证 userID 是系统中的一个已注册用户。
 * join、 leave 和 request 最多被调用 104 次。
 * 每次对 leave 的调用都有对应的对 join 的调用。
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 当系统以用户的 IP 地址而不是独有 ID 来识别用户，且用户断开连接后以相同 IP 重新连接系统时，会发生什么？
 * 当用户频繁加入并退出系统，且该用户不请求任何文件块时，你的解决方案仍然保持高效吗？
 * 当所有用户同时加入系统，请求所有文件并离开时，你的解决方案仍然保持高效吗？
 * 如果系统用于分享n个文件，其中第i个文件由 m[i] 组成，你需要如何修改？
 *
 * @author rcao1
 */
public class L1500FileSharing {

    private int chunks;
    private AtomicInteger cur;
    private ConcurrentSkipListSet<Integer> reused;
    private ConcurrentSkipListMap<Integer, Set<Integer>> userChunks;

    public L1500FileSharing(int m) {
        cur = new AtomicInteger(0);
        chunks = m;
        reused = new ConcurrentSkipListSet<>();
        userChunks = new ConcurrentSkipListMap<>();
    }

    public int join(List<Integer> ownedChunks) {
        int userID;
        if (reused.isEmpty()) {
            userID = cur.addAndGet(1);
        } else {
            userID = reused.pollFirst();
        }
        userChunks.put(userID, new HashSet<>(ownedChunks));
        return userID;
    }

    public void leave(int userID) {
        reused.add(userID);
        userChunks.remove(userID);
    }

    public List<Integer> request(int userID, int chunkID) {
        if (chunkID < 1 || chunkID > chunks) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : userChunks.entrySet()) {
            if (entry.getValue().contains(chunkID)) {
                res.add(entry.getKey());
            }
        }
        if (!res.isEmpty()) {
            userChunks.computeIfAbsent(userID, k -> new HashSet<>()).add(chunkID);
        }
        return res;
    }

    public static void main(String[] args) {
        L1500FileSharing fileSharing = new L1500FileSharing(4); // 我们用该系统分享由 4 个文件块组成的文件。
        System.out.println(fileSharing.join(Arrays.stream(new int[] {1, 2}).boxed().collect(Collectors.toList())));    // 一个拥有文件块 [1,2] 的用户加入系统，为其注册 id = 1 并返回 1。
        System.out.println(fileSharing.join(Arrays.stream(new int[] {2, 3}).boxed().collect(Collectors.toList())));    // 一个拥有文件块 [2,3] 的用户加入系统，为其注册 id = 2 并返回 2。
        System.out.println(fileSharing.join(Arrays.stream(new int[] {4}).boxed().collect(Collectors.toList())));       // 一个拥有文件块 [4] 的用户加入系统，为其注册 id = 3 并返回 3。
        System.out.println(fileSharing.request(1, 3));   // id = 1 的用户请求第 3 个文件块，只有 id = 2 的用户拥有文件块，返回 [2] 。注意，现在用户 1 现拥有文件块 [1,2,3]。
        System.out.println(fileSharing.request(2, 2));   // id = 2 的用户请求第 2 个文件块，id 为 [1,2] 的用户拥有该文件块，所以我们返回 [1,2] 。
        fileSharing.leave(1);        // id = 1 的用户离开系统，其所拥有的所有文件块不再对其他用户可用。
        System.out.println(fileSharing.request(2, 1));   // id = 2 的用户请求第 1 个文件块，系统中没有用户拥有该文件块，所以我们返回空列表 [] 。
        fileSharing.leave(2);        // id = 2 的用户离开系统。
        System.out.println(fileSharing.join(Arrays.stream(new int[] {}).boxed().collect(Collectors.toList())));        // 一个不拥有任何文件块的用户加入系统，为其注册 id = 1 并返回 1 。注意，id 1 和 2 空闲，可以重新使用。
    }
}
