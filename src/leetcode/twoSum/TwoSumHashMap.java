package leetcode.twoSum;

/**
 * @author taoruizhe
 * @description 两数之和，用hashmap来处理
 * @date 20190916
 */

import java.util.HashMap;
import java.util.Map;

public class TwoSumHashMap {
    public int[] twoSum(int[] nums, int target) {
        int[] factors = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                factors[0] = map.get(nums[i]);
                factors[1] = i;
            }
            map.put(nums[i],i);
        }

        return factors;
    }

    public static void main(String[] args) {
        int[] nums = {3, 3};
        int target = 6;
        int[] factors = new int[2];
        TwoSumHashMap tshm = new TwoSumHashMap();
        factors = tshm.twoSum(nums, target);
        for(int num:factors) {
            System.out.println(num);
        }
    }

}
