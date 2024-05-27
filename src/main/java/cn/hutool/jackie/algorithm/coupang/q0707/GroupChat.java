package cn.hutool.jackie.algorithm.coupang.q0707;

public class GroupChat extends Conversation {
    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public void addParticipant(User user) {
        participants.add(user);
    }
}
