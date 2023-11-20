package se.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/4/3 15:04
 */
public class OptionalTest {

    @Test
    public void demo1() {
        Optional<Fruit> empty = Optional.empty();
        empty.map(Fruit::getName).ifPresent(System.out::println);
    }


}
