package com.ceair.commonalgorithm;

import java.util.Arrays;

/**
 * KMP算法
 *
 * @author wuhp
 * @date 2021/12/30
 */
public class Kmp {
    public static void main(String[] args) {
        String str1 = "QWSD F ERASDFTUGH";
        String str2 = "SDF";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        int i = kmpSearch(str1, str2, next);
        System.out.println("index = " + i);
    }

    /**
     * 查询匹配到的下标
     *
     * @param str1 被匹配的长串
     * @param str2 子串
     * @param next
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while(j>0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if(j == str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    /**
     * 获取一个字符串（子串）的部分匹配值表
     *
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];// 从不匹配的地方，更新j的值
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
