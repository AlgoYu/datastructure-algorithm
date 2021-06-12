package cn.machine.geek.algorithm.search;

/**
 * @Author: MachineGeek
 * @Description: KMP搜索
 * @Email: 794763733@qq.com
 * @Date: 2021/2/28
 */
public class KMP {
    public static int indexOf(String text, String pattern) {
        // 边界检查
        if (text == null || pattern == null) {
            return -1;
        }
        int tlen = text.length();
        int plen = pattern.length();
        if (tlen == 0 || plen == 0 || plen > tlen) {
            return -1;
        }
        // 获取字符数组
        char[] tChars = text.toCharArray();
        char[] pChars = pattern.toCharArray();
        // 获取公共长度匹配数组
        int[] next = getNext(pChars);
        // 初始下标
        int ti = 0, pi = 0, tmax = tlen - plen;
        while (pi < plen && ti - pi <= tmax) {
            if (pi < 0 || tChars[ti] == pChars[pi]) {
                ti++;
                pi++;
            } else {
                pi = next[pi];
            }
        }
        return pi == plen ? ti - pi : -1;
    }

    private static int[] getNext(char[] pattern) {
        // 创建next数组
        int[] next = new int[pattern.length];
        // 初始化
        int i = 0;
        int n = next[i] = -1;
        while (i < pattern.length - 1) {
            if (n < 0 || pattern[i] == pattern[n]) {
                i++;
                n++;
                if (pattern[i] == pattern[n]) {
                    next[i] = next[n];
                } else {
                    next[i] = n;
                }
            } else {
                n = next[n];
            }
        }
        return next;
    }
}
