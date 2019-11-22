package leetcode.twoSum;

/**
 * @author taoruizhe
 * @description 两数之和@leetcode
 * @date 20190916
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] factors = new int[2];
        for (int i = 0; i < nums.length; i++)
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    factors[0] = j;
                    factors[1] = i;
                }
            }
        return factors;
    }

}




