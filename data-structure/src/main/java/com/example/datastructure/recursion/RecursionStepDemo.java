package com.example.datastructure.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 有N个阶梯, 每次可以走一步, 或者走两步, 问有多少种走法<br/>
 * 问题可以分解为第一次走1步,剩下N-1的走法加上第一次走2步,剩下N-2的走法<br/>
 */
public class RecursionStepDemo {

    private static Map<Long, Long> map = new HashMap<>();

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(calc(100));
        long end = System.nanoTime();

        System.out.println("未优化的算法耗时:" + (end - start) + "纳秒");

        long start1 = System.nanoTime();
        System.out.println(calcWithCache(100));
        long end1 = System.nanoTime();
        System.out.println("缓存优化的算法耗时:" + (end1 - start1) + "纳秒");

        System.out.println("缓存优化比原始递归速度提升了" + ((end - start) / (end1 - start1)));

        long start2 = System.nanoTime();
        System.out.println(calcWithStore(100));
        long end2 = System.nanoTime();
        System.out.println("存值优化的算法耗时:" + (end2 - start2) + "纳秒");

        System.out.println("存值优化比缓存优化速度提升了" + ((end1 - start1) / (end2 - start2)));
        System.out.println("存值优化比原始递归速度提升了" + ((end - start) / (end2 - start2)));
    }

    /**
     * 通过保存前两次的值来优化
     *
     * @param n
     * @return
     */
    private static long calcWithStore(long n) {
        if (n == 1 || n == 2) {
            return n;
        }
        long k = n - 2;
        long prevPrev = 1;
        long prev = 2;
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum = prev + prevPrev;
            prevPrev = prev;
            prev = sum;
        }
        return sum;
    }

    /**
     * 利用缓存优化, 本例中使用Map
     *
     * @param n
     * @return
     */
    private static long calcWithCache(long n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n == 1 || n == 2) {
            return n;
        }
        long num1 = calcWithCache(n - 1);
        if (!map.containsKey(n - 1)) {
            map.put(n - 1, num1);
        }

        long num2 = calcWithCache(n - 2);
        if (!map.containsKey(n - 2)) {
            map.put(n - 2, num2);
        }
        return num1 + num2;
    }

    static private long calc(long n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return calc(n - 1) + calc(n - 2);
    }
}
