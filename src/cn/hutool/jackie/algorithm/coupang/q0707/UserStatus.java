package cn.hutool.jackie.algorithm.coupang.q0707;

public class UserStatus {
    private String message;
    private UserStatusType type;

    public UserStatus(UserStatusType type, String message) {
        this.type = type;
        this.message = message;
    }

    public UserStatusType getStatusType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
