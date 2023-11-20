package se.mt;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/9/26 00:22
 */
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {
        final ConcurrentLinkedQueue<String> strings = new ConcurrentLinkedQueue<>();
        final String peek = strings.peek();
        System.out.println(peek);
    }

}
