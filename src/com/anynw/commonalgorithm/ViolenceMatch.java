package com.anynw.commonalgorithm;

/**
 * 暴力算法
 *
 * @author wuhp
 * @date 2021/12/29
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "123456789";
        String str2 = "67";
        System.out.println("暴力匹配算法");
        int index = violenceMatch(str1, str2);
        System.out.println("index = " + index);
        System.out.println("count = " + count);
    }

    private static volatile int count = 0;

    /**
     * 暴力匹配算法
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1len = s1.length;
        int s2len = s2.length;

        int i = 0; // 指向s1的索引
        int j = 0; // 指向s2的索引

        while (i < s1len && j < s2len) {
            count++;

            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == s2len) {
            return i - j;
        }
        return -1;
    }
}
