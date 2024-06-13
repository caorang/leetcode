package cn.hutool.jackie.algorithm.design.p2200_2299;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 你有一个视频分享平台，用户可以上传和删除视频。每个 video 都是 字符串 类型的数字，其中字符串的第 i 位表示视频中第 i 分钟的内容。例如，第一个数字表示视频中第 0 分钟的内容，第二个数字表示视频中第 1 分钟的内容，以此类推。视频的观众也可以喜欢和不喜欢视频。该平台会跟踪每个视频的 观看次数、点赞次数 和 不喜欢次数。
 * <p>
 * 当视频上传时，它与最小可用整数 videoId 相关联，videoId 从 0 开始的。一旦一个视频被删除，与该视频关联的 videoId 就可以用于另一个视频。
 * <p>
 * 实现 VideoSharingPlatform 类:
 * <p>
 * VideoSharingPlatform() 初始化对象。
 * int upload(String video) 用户上传一个 video. 返回与视频相关联的videoId 。
 * void remove(int videoId) 如果存在与 videoId 相关联的视频，则删除该视频。
 * String watch(int videoId, int startMinute, int endMinute) 如果有一个视频与 videoId 相关联，则将该视频的观看次数增加 1，并返回视频字符串的子字符串，从 startMinute 开始，以 min(endMinute, video.length - 1)(含边界) 结束。否则，返回 "-1"。
 * void like(int videoId) 如果存在与 videoId 相关联的视频，则将与 videoId 相关联的视频的点赞数增加 1。
 * void dislike(int videoId) 如果存在与 videoId 相关联的视频，则将与 videoId 相关联的视频上的不喜欢次数增加 1。
 * int[] getLikesAndDislikes(int videoId) 返回一个长度为 2 ，下标从 0 开始 的整型数组，其中 values[0] 是与 videoId 相关联的视频上的点赞数，values[1] 是不喜欢数。如果没有与 videoId 相关联的视频，则返回 [-1]。
 * int getViews(int videoId) 返回与 videoId 相关联的视频的观看次数，如果没有与 videoId 相关联的视频，返回 -1。
 * 示例 1:
 * <p>
 * 输入
 * ["VideoSharingPlatform", "upload", "upload", "remove", "remove", "upload", "watch", "watch", "like", "dislike", "dislike", "getLikesAndDislikes", "getViews"]
 * [[], ["123"], ["456"], [4], [0], ["789"], [1, 0, 5], [1, 0, 1], [1], [1], [1], [1], [1]]
 * 输出
 * [null, 0, 1, null, null, 0, "456", "45", null, null, null, [1, 2], 2]
 * <p>
 * 解释
 * VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
 * videoSharingPlatform.upload("123");          // 最小的可用 videoId 是 0，所以返回 0。
 * videoSharingPlatform.upload("456");          // 最小的可用 videoId 是 1，所以返回 1。
 * videoSharingPlatform.remove(4);              // 没有与 videoId 4 相关联的视频，所以什么都不做。
 * videoSharingPlatform.remove(0);              // 删除与 videoId 0 关联的视频。
 * videoSharingPlatform.upload("789");          // 由于与 videoId 0 相关联的视频被删除，
 * // 0 是最小的可用 videoId，所以返回 0。
 * videoSharingPlatform.watch(1, 0, 5);         // 与 videoId 1 关联的视频为 "456"。
 * // 从分钟 0 到分钟 min(5,3 - 1)= 2 的视频为 "456"，因此返回 "456"。
 * videoSharingPlatform.watch(1, 0, 1);         // 与 videoId 1 关联的视频为 "456"。
 * // 从分钟 0 到分钟 min(1,3 - 1)= 1 的视频为 "45"，因此返回 "45"。
 * videoSharingPlatform.like(1);                // 增加与 videoId 1 相关的视频的点赞数。
 * videoSharingPlatform.dislike(1);             // 增加与 videoId 1 相关联的视频的不喜欢的数量。
 * videoSharingPlatform.dislike(1);             // 增加与 videoId 1 相关联的视频的不喜欢的数量。
 * videoSharingPlatform.getLikesAndDislikes(1); // 在与 videoId 1 相关的视频中有 1 个喜欢和 2 个不喜欢，因此返回[1,2]。
 * videoSharingPlatform.getViews(1);            // 与 videoId 1 相关联的视频有 2 个观看数，因此返回2。
 * 示例 2:
 * <p>
 * 输入
 * ["VideoSharingPlatform", "remove", "watch", "like", "dislike", "getLikesAndDislikes", "getViews"]
 * [[], [0], [0, 0, 1], [0], [0], [0], [0]]
 * 输出
 * [null, null, "-1", null, null, [-1], -1]
 * <p>
 * 解释
 * VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
 * videoSharingPlatform.remove(0);              // 没有与 videoId 0 相关联的视频，所以什么都不做。
 * videoSharingPlatform.watch(0, 0, 1);         // 没有与 videoId 0 相关联的视频，因此返回 "-1"。
 * videoSharingPlatform.like(0);                // 没有与 videoId 0 相关联的视频，所以什么都不做。
 * videoSharingPlatform.dislike(0);             // 没有与 videoId 0 相关联的视频，所以什么都不做。
 * videoSharingPlatform.getLikesAndDislikes(0); // 没有与 videoId 0 相关联的视频，因此返回 [-1]。
 * videoSharingPlatform.getViews(0);            // 没有视频与 videoId 0 相关联，因此返回 -1。
 * 提示:
 * <p>
 * 1 <= video.length <= 10^5
 * 调用 upload 时所有 video.length 的总和不会超过 10^5
 * video 由数字组成
 * 0 <= videoId <= 10^5
 * 0 <= startMinute < endMinute < 10^5
 * startMinute < video.length
 * 调用 watch 时所有 endMinute - startMinute 的总和不会超过 10^5。
 * 所有函数 总共 最多调用 10^5 次。
 *
 * @author rcao1
 */
public class L2254VideoPlatform {

    static class Video {

        public String content;
        public int likes;
        public int dislikes;
        public int views;

        public Video(String content) {
            this.content = content;
            this.likes = 0;
            this.dislikes = 0;
            this.views = 0;
        }
    }

    static class VideoSharingPlatform {

        private int id;
        private Map<Integer, Video> videos;
        private TreeSet<Integer> reservedIds;

        public VideoSharingPlatform() {
            this.id = 0;
            this.videos = new HashMap<>();
            this.reservedIds = new TreeSet<>();
        }

        public int getId() {
            if (reservedIds.isEmpty()) {
                return this.id++;
            }
            return this.reservedIds.pollFirst();
        }

        public void deleteId(int id) {
            this.reservedIds.add(id);
        }

        public int upload(String video) {
            int id = getId();
            Video v = new Video(video);
            this.videos.put(id, v);
            return id;
        }

        public void remove(int videoId) {
            if (this.videos.containsKey(videoId)) {
                this.videos.remove(videoId);
                this.reservedIds.add(videoId);
            }
        }

        public String watch(int videoId, int startMinute, int endMinute) {
            if (this.videos.containsKey(videoId)) {
                Video v = this.videos.get(videoId);
                v.views++;
                if (endMinute < v.content.length()) {
                    return v.content.substring(startMinute, endMinute + 1);
                }
                return v.content.substring(startMinute, v.content.length());
            }
            return "-1";
        }

        public void like(int videoId) {
            if (this.videos.containsKey(videoId)) {
                Video v = this.videos.get(videoId);
                v.likes++;
            }
        }

        public void dislike(int videoId) {
            if (this.videos.containsKey(videoId)) {
                Video v = this.videos.get(videoId);
                v.dislikes++;
            }
        }

        public int[] getLikesAndDislikes(int videoId) {
            if (this.videos.containsKey(videoId)) {
                Video v = this.videos.get(videoId);
                return new int[] {v.likes, v.dislikes};
            }
            return new int[] {-1};
        }

        public int getViews(int videoId) {
            if (this.videos.containsKey(videoId)) {
                Video v = this.videos.get(videoId);
                return v.views;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        VideoSharingPlatform platform = new VideoSharingPlatform();
        platform.upload("hello");
        platform.upload("world");
        System.out.println(platform.watch(0, 0, 2));
        System.out.println(platform.watch(1, 0, 10));
        platform.remove(0);
        platform.upload("beautiful");
        platform.like(0);
        platform.dislike(1);
        System.out.println(platform.watch(0, 0, 2));
        System.out.println(Arrays.toString(platform.getLikesAndDislikes(0)));
        System.out.println(Arrays.toString(platform.getLikesAndDislikes(1)));
        System.out.println(platform.getViews(0));
        /**
         * 输入
         * ["VideoSharingPlatform", "remove", "watch", "like", "dislike", "getLikesAndDislikes", "getViews"]
         * [[], [0], [0, 0, 1], [0], [0], [0], [0]]
         * 输出
         * [null, null, "-1", null, null, [-1], -1]
         */
        String method = "[\"VideoSharingPlatform\", \"remove\", \"watch\", \"like\", \"dislike\", \"getLikesAndDislikes\", \"getViews\"]";
        String arguments = "[[], [0], [0, 0, 1], [0], [0], [0], [0]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(VideoSharingPlatform.class);
    }
}
