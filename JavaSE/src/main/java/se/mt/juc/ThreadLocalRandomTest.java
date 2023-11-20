package se.mt.juc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/14 23:11
 */
public class ThreadLocalRandomTest {

    public static void main(String[] args) {
        final ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int i = 0; i < 16; i++) {
            System.out.println(Double.valueOf((current.nextDouble(0.1, 0.8) + 1) * 3600).longValue());
            System.out.println((long) ((current.nextDouble(0.1, 0.8) + 1) * 3600));
        }
    }

}
