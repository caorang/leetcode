package cn.hutool.jackie.algorithm.coupang.q0702;

public class Caller {
    private String name;
    private int userId;

    public Caller(int id, String nm) {
        name = nm;
        userId = id;
    }

    @Override
    public String toString() {
        return "[call by: " + name + "]";
    }
}
