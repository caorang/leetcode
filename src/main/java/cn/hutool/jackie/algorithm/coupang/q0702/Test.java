package cn.hutool.jackie.algorithm.coupang.q0702;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        CallHandler ch = new CallHandler();
        for (int i = 0; i <= 100; i++) {
            Call call = new Call(new Caller(i, "Bob" + i));
            ch.dispatchCall(call);
            if (i > 20 && i % 2 == 0) {
                call.disconnect();
            }
        }
    }

}
