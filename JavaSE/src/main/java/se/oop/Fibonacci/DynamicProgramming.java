package se.oop.Fibonacci;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 斐波拉契数列的动态规划解决方法
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/5/29 11:23
 */
public class DynamicProgramming {

    Map<Integer, Integer> _cache = new HashMap<>();

    public static void main(String[] args) {
        new DynamicProgramming().logDynamic(20);
        new DynamicProgramming().logRecursion(20);
    }

    public Integer recursionDynamic(int i) {
        if (i == 0 || i == 1) {
            return 1;
        } else {
            final Integer sum = _cache.containsKey(i) ? _cache.get(i) : recursionDynamic(i - 1) + recursionDynamic(i - 2);
            _cache.put(
                    i,
                    sum
            );
            return sum;
        }
    }

    public Integer recursion(int i) {
        if (i == 0 || i == 1) {
            return 1;
        } else {
            return recursion(i - 1) + recursion(i - 2);
        }
    }

    public void logDynamic(int i) {
        final long startTime = System.nanoTime();
        System.out.println(recursionDynamic(i));
        final long endTime = System.nanoTime();
        System.out.println("dynamic cost time:\t\t" + (endTime - startTime));
    }

    public void logRecursion(int i) {
        final long startTime = System.nanoTime();
        System.out.println(recursion(i));
        final long endTime = System.nanoTime();
        System.out.println("recustion cost time:\t" + (endTime - startTime));
    }

}
