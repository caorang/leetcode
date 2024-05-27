package cn.hutool.jackie.algorithm.design2.p1700_p1799;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 你需要设计一个包含验证码的验证系统。每一次验证中，用户会收到一个新的验证码，这个验证码在 currentTime 时刻之后 timeToLive 秒过期。如果验证码被更新了，那么它会在 currentTime （可能与之前的 currentTime 不同）时刻延长 timeToLive 秒。
 * <p>
 * 请你实现 AuthenticationManager 类：
 * <p>
 * AuthenticationManager(int timeToLive) 构造 AuthenticationManager 并设置 timeToLive 参数。
 * generate(string tokenId, int currentTime) 给定 tokenId ，在当前时间 currentTime 生成一个新的验证码。
 * renew(string tokenId, int currentTime) 将给定 tokenId 且 未过期 的验证码在 currentTime 时刻更新。如果给定 tokenId 对应的验证码不存在或已过期，请你忽略该操作，不会有任何更新操作发生。
 * countUnexpiredTokens(int currentTime) 请返回在给定 currentTime 时刻，未过期 的验证码数目。
 * 如果一个验证码在时刻 t 过期，且另一个操作恰好在时刻 t 发生（renew 或者 countUnexpiredTokens 操作），过期事件 优先于 其他操作。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：
 * ["AuthenticationManager", "renew", "generate", "countUnexpiredTokens", "generate", "renew", "renew", "countUnexpiredTokens"]
 * [[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
 * 输出：
 * [null, null, null, 1, null, null, null, 0]
 * <p>
 * 解释：
 * AuthenticationManager authenticationManager = new AuthenticationManager(5); // 构造 AuthenticationManager ，设置 timeToLive = 5 秒。
 * authenticationManager.renew("aaa", 1); // 时刻 1 时，没有验证码的 tokenId 为 "aaa" ，没有验证码被更新。
 * authenticationManager.generate("aaa", 2); // 时刻 2 时，生成一个 tokenId 为 "aaa" 的新验证码。
 * authenticationManager.countUnexpiredTokens(6); // 时刻 6 时，只有 tokenId 为 "aaa" 的验证码未过期，所以返回 1 。
 * authenticationManager.generate("bbb", 7); // 时刻 7 时，生成一个 tokenId 为 "bbb" 的新验证码。
 * authenticationManager.renew("aaa", 8); // tokenId 为 "aaa" 的验证码在时刻 7 过期，且 8 >= 7 ，所以时刻 8 的renew 操作被忽略，没有验证码被更新。
 * authenticationManager.renew("bbb", 10); // tokenId 为 "bbb" 的验证码在时刻 10 没有过期，所以 renew 操作会执行，该 token 将在时刻 15 过期。
 * authenticationManager.countUnexpiredTokens(15); // tokenId 为 "bbb" 的验证码在时刻 15 过期，tokenId 为 "aaa" 的验证码在时刻 7 过期，所有验证码均已过期，所以返回 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= timeToLive <= 10^8
 * 1 <= currentTime <= 10^8
 * 1 <= tokenId.length <= 5
 * tokenId 只包含小写英文字母。
 * 所有 generate 函数的调用都会包含独一无二的 tokenId 值。
 * 所有函数调用中，currentTime 的值 严格递增 。
 * 所有函数的调用次数总共不超过 2000 次。
 *
 * @author rcao1
 */
public class L1797AuthenticationManager {

    private int timeToLive;
    private Map<String, Integer> tokenExpiration;
    private TreeMap<Integer, Set<String>> ttlTokens;

    public L1797AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        this.ttlTokens = new TreeMap<>();
        this.tokenExpiration = new HashMap<>();
    }

    /**
     * 给定 tokenId ，在当前时间 currentTime 生成一个新的验证码。
     *
     * @param tokenId
     * @param currentTime
     */
    public void generate(String tokenId, int currentTime) {
        int expireTime = currentTime + timeToLive;
        this.tokenExpiration.put(tokenId, expireTime);
        Set<String> tokens = new HashSet<>();
        tokens.add(tokenId);
        this.ttlTokens.put(expireTime, tokens);
    }

    /**
     * 将给定 tokenId 且 未过期 的验证码在 currentTime 时刻更新。如果给定 tokenId 对应的验证码不存在或已过期，请你忽略该操作，不会有任何更新操作发生。
     *
     * @param tokenId
     * @param currentTime
     */
    public void renew(String tokenId, int currentTime) {
        if (tokenExpiration.containsKey(tokenId)) {
            int expire = tokenExpiration.get(tokenId);
            if (currentTime < expire) {
                int expireTime = currentTime + timeToLive;
                this.tokenExpiration.put(tokenId, expireTime);
                this.ttlTokens.computeIfAbsent(expire, k -> new HashSet<>()).remove(tokenId);
                this.ttlTokens.computeIfAbsent(expireTime, k -> new HashSet<>()).add(tokenId);
            }
        }
    }

    /**
     * 请返回在给定 currentTime 时刻，未过期 的验证码数目。
     *
     * @param currentTime
     * @return
     */
    public int countUnexpiredTokens(int currentTime) {
        AtomicInteger total = new AtomicInteger();
        Map<Integer, Set<String>> tail = ttlTokens.tailMap(currentTime);
        tail.forEach((k, v) -> {
            if (k > currentTime) {
                total.addAndGet(v.size());
            }
        });
        return total.get();
    }

    public static void main(String[] args) {
        String method = "[\"AuthenticationManager\",\"renew\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"generate\",\"generate\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"renew\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"renew\",\"countUnexpiredTokens\",\"generate\",\"renew\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"renew\",\"renew\",\"renew\",\"generate\",\"renew\",\"generate\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"generate\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"renew\",\"generate\",\"generate\",\"generate\",\"countUnexpiredTokens\",\"renew\",\"generate\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"generate\",\"generate\",\"generate\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"countUnexpiredTokens\",\"renew\",\"renew\",\"renew\",\"countUnexpiredTokens\",\"countUnexpiredTokens\"]";
        String arguments = "[[104],[\"ox\",50],[73],[87],[93],[\"yyeu\",104],[\"r\",131],[167],[172],[191],[206],[232],[\"r\",235],[239],[257],[\"vi\",268],[292],[\"vi\",296],[\"yu\",303],[326],[339],[\"aimzm\",343],[\"umdzy\",346],[\"qyf\",347],[\"mfne\",353],[\"nn\",357],[\"hz\",359],[422],[434],[\"pel\",473],[494],[498],[508],[524],[\"pt\",552],[\"vbaa\",568],[\"gt\",592],[\"zxdv\",611],[618],[\"fbp\",619],[\"giih\",622],[623],[629],[\"chmi\",659],[\"doohl\",671],[\"svxbv\",715],[722],[749],[754],[771],[794],[\"pel\",865],[\"i\",919],[\"aqa\",962],[976],[978]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(L1797AuthenticationManager.class);
    }
}
