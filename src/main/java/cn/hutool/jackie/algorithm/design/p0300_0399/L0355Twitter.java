package cn.hutool.jackie.algorithm.design.p0300_0399;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。
 * <p>
 * 实现 Twitter 类：
 * <p>
 * Twitter() 初始化简易版推特对象
 * void postTweet(int userId, int tweetId) 根据给定的 tweetId 和 userId 创建一条新推文。每次调用此函数都会使用一个不同的 tweetId 。
 * List getNewsFeed(int userId) 检索当前用户新闻推送中最近 10 条推文的 ID 。新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。推文必须 按照时间顺序由最近到最远排序 。
 * void follow(int followerId, int followeeId) ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
 * void unfollow(int followerId, int followeeId) ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
 * 示例：
 * <p>
 * 输入
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * 输出
 * [null, null, [5], null, null, [6, 5], null, [5]]
 * <p>
 * 解释
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // 用户 1 发送了一条新推文 (用户 id = 1, 推文 id = 5)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含一个 id 为 5 的推文
 * twitter.follow(1, 2);    // 用户 1 关注了用户 2
 * twitter.postTweet(2, 6); // 用户 2 发送了一个新推文 (推文 id = 6)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含两个推文，id 分别为 -> [6, 5] 。推文 id 6 应当在推文 id 5 之前，因为它是在 5 之后发送的
 * twitter.unfollow(1, 2);  // 用户 1 取消关注了用户 2
 * twitter.getNewsFeed(1);  // 用户 1 获取推文应当返回一个列表，其中包含一个 id 为 5 的推文。因为用户 1 已经不再关注用户 2
 * 提示：
 * <p>
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 10^4
 * 所有推特的 ID 都互不相同
 * postTweet、getNewsFeed、follow 和 unfollow 方法最多调用 3 * 10^4 次
 *
 * @author rcao1
 */
public class L0355Twitter {


    static class Tweet implements Comparable<Tweet> {
        public int userId;
        public int tweetId;
        public int timestamp;

        public Tweet(int userId, int tweetId) {
            this.userId = userId;
            this.tweetId = tweetId;
            this.timestamp = Twitter.timestamp++;
        }

        @Override
        public int compareTo(Tweet o) {
            return o.timestamp - this.timestamp;
        }
    }

    static class Twitter {

        private static int timestamp = 1;

        private Map<Integer, Set<Tweet>> userPosts;
        private Map<Integer, Set<Integer>> userFollowee;

        public Twitter() {
            this.userPosts = new HashMap<>();
            this.userFollowee = new HashMap<>();
        }

        /**
         * 根据给定的 tweetId 和 userId 创建一条新推文。每次调用此函数都会使用一个不同的 tweetId
         *
         * @param userId
         * @param tweetId
         */
        public void postTweet(int userId, int tweetId) {
            if (this.userPosts.containsKey(userId)) {
                Set<Tweet> posts = this.userPosts.get(userId);
                posts.add(new Tweet(userId, tweetId));
            } else {
                Set<Tweet> posts = new TreeSet<>();
                posts.add(new Tweet(userId, tweetId));
                this.userPosts.put(userId, posts);
            }
        }

        /**
         * 检索当前用户新闻推送中最近 10 条推文的 ID
         * 新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文
         * 推文必须 按照时间顺序由最近到最远排序
         *
         * @param userId
         * @return
         */
        public List<Integer> getNewsFeed(int userId) {
            Set<Tweet> result = new TreeSet<>();
            if (this.userPosts.containsKey(userId)) {
                result.addAll(this.userPosts.get(userId));
            }
            if (this.userFollowee.containsKey(userId)) {
                for (int followeeId : this.userFollowee.get(userId)) {
                    if (this.userPosts.containsKey(followeeId)) {
                        result.addAll(this.userPosts.get(followeeId));
                    }
                }
            }
            List<Integer> res = result.stream().map(i -> i.tweetId).collect(Collectors.toList());
            return res.size() > 10 ? res.subList(0, 10) : res;
        }

        /**
         * ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户
         *
         * @param followerId
         * @param followeeId
         */
        public void follow(int followerId, int followeeId) {
            this.userFollowee.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }

        /**
         * ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户
         *
         * @param followerId
         * @param followeeId
         */
        public void unfollow(int followerId, int followeeId) {
            this.userFollowee.computeIfAbsent(followerId, k -> new HashSet<>()).remove(followeeId);
        }
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
         * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
         * 输出
         * [null, null, [5], null, null, [6, 5], null, [5]]
         */
        String method =
                "[\"Twitter\",\"postTweet\",\"postTweet\",\"postTweet\",\"postTweet\",\"postTweet\",\"postTweet\",\"postTweet\",\"postTweet\",\"postTweet\",\"postTweet\",\"postTweet\",\"getNewsFeed\"]";
        String arguments = "[[],[1,5],[1,3],[1,101],[1,13],[1,10],[1,2],[1,94],[1,505],[1,333],[1,22],[1,11],[1]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(Twitter.class);
    }
}
