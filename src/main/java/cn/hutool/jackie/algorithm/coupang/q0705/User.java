package cn.hutool.jackie.algorithm.coupang.q0705;

public class User {
    private int userId;
    private String details;
    private int accountType;

    public User(int id, String details, int accountType) {
        userId = id;
        this.details = details;
        this.accountType = accountType;
    }

    public void renewMembership() {
    }

    /* getters and setters */
    public int getID() {
        return userId;
    }

    public void setID(int id) {
        userId = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}

