package cn.hutool.jackie.algorithm.coupang.q0702;

class Director extends Employee {
    public Director(CallHandler callHandler, int employeeId) {
        super(callHandler, employeeId);
        rank = Rank.Director;
    }
}
