package cn.hutool.jackie.algorithm.string;

/**
 * @author rcao1
 */
public class AddString {
    public String addStrings(String num1, String num2) {
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder result = new StringBuilder();
        int temp = 0;
        for (; i >= 0 && j >= 0; i--, j--) {
            int k = num1.charAt(i) - '0' + num2.charAt(j) - '0' + temp;
            temp = 0;
            if (k < 10) {
                result.append(k);
            } else {
                result.append(k - 10);
                temp = 1;
            }
        }
        while (i >= 0) {
            int k = num1.charAt(i--) - '0' + temp;
            temp = 0;
            if (k < 10) {
                result.append(k);
            } else {
                result.append(k - 10);
                temp = 1;
            }
        }
        while (j >= 0) {
            int k = num2.charAt(j--) - '0' + temp;
            temp = 0;
            if (k < 10) {
                result.append(k);
            } else {
                result.append(k - 10);
                temp = 1;
            }
        }
        if (temp > 0) {
            result.append(temp);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        AddString add = new AddString();
        System.out.println(add.addStrings("6", "501"));
        System.out.println(add.addStrings("1", "9"));
        System.out.println(add.addStrings("999991", "9"));
    }
}
