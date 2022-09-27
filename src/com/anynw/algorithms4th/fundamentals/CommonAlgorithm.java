package com.anynw.algorithms4th.fundamentals;

import edu.princeton.cs.algs4.StdRandom;

/**
 * @author wuhp
 * @date 2022/9/27
 */
public class CommonAlgorithm {

    /**
     * 找出数组中最大的元素
     *
     * @param nums
     * @return
     */
    public static int findMaxValue(int[] nums) {
        int maxValue = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (maxValue < nums[i]) {
                maxValue = nums[i];
            }
        }
        double random = StdRandom.uniformDouble();

        return maxValue;
    }
}
