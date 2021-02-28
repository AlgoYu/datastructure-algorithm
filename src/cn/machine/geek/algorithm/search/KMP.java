package cn.machine.geek.algorithm.search;

/**
 * @Author: MachineGeek
 * @Description: KMP搜索
 * @Email: 794763733@qq.com
 * @Date: 2021/2/28
 */
public class KMP {
    public static int indexOf(String text, String pattern){
        // 边界检查
        if(text == null || pattern == null){
            return -1;
        }
        int tlen = text.length();
        int plen = pattern.length();
        if(tlen == 0 || plen == 0 || plen > tlen){
            return -1;
        }
        // 获取字符数组
        char[] tChars = text.toCharArray();
        char[] pChars = pattern.toCharArray();
        // 获取公共长度匹配数组
        int[] next = getNext(pChars);
        // 初始下标
        int ti = 0,pi = 0,delta = tlen - plen;
        while (pi < plen && ti - pi <= delta){
            if(pi > 0 && tChars[ti] != pChars[pi]){
                pi = next[pi];
                continue;
            }
            ti++;
            pi++;
        }
        return pi == plen ? ti - plen : -1;
    }

    private static int[] getNext(char[] pattern){
        int plen = pattern.length;
        int[] next = new int[plen];
        int i = 0;
        int n = next[i] = -1;
        int imax = plen - 1;
        while (i < imax){
            if(n < 0 || pattern[i] == pattern[n]){
                next[++i] = ++n;
            }else{
                n = next[n];
            }
        }
        return next;
    }
}
