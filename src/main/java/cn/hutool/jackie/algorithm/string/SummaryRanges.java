package cn.hutool.jackie.algorithm.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rcao1
 */
public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<String>();
        for (int left = 0; left < nums.length; ) {
            int num1 = nums[left];
            String sb = new String();
            int right = left + 1;
            for (; right < nums.length;) {
                int temp = nums[right - 1];
                int num2 = nums[right];
                if (num2 <= num1 + right - left) {
                    right++;
                    continue;
                } else {
                    if (num1 != temp) {
                        sb = num1 + "->" + temp;
                    } else {
                        sb = num1 + "";
                    }
                    left = right - 1;
                    break;
                }
            }
            if (sb.length() == 0) {
                if (left < right -1) {
                    sb = num1 + "->" + nums[right - 1];
                    left = right - 1;
                } else {
                    sb = num1 + "";
                }
            }
            ans.add(sb);
            left++;
        }
        return ans;
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        System.out.println(summaryRanges.summaryRanges(new int[] {0, 2, 3, 4, 6, 8, 9}));
    }
}
