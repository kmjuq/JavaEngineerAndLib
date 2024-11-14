/**
 * 用来测试 java8 之后的 jep 特性
 * <pre>{@code
 *     int sum = widgets.stream()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mapToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 * See <a href="https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/stream/package-summary.html#package.description">stream</a>
 * {@snippet :
 * class HelloWorld {
 *     public static void main(String... args) {
 *         System.out.println("Hello World!");      // @highlight substring="println"
 *     }
 * }
 *}
 * See <a href="https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/lang/InterruptedException.html">InterruptedException</a>
 */
package jep;