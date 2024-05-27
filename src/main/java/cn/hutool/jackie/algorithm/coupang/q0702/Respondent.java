package cn.hutool.jackie.algorithm.coupang.q0702;

class Respondent extends Employee {
    public Respondent(CallHandler callHandler, int employeeId) {
        super(callHandler, employeeId);
        rank = Rank.Responder;
    }
}
