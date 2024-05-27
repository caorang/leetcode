package cn.hutool.jackie.algorithm.coupang.q0702;

class Manager extends Employee {
    public Manager(CallHandler callHandler, int employeeId) {
        super(callHandler, employeeId);
        rank = Rank.Manager;
    }
}
