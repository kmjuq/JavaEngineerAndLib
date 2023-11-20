package se.lambda;

import cn.hutool.core.lang.Console;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kmj
 */
public class Lambda {

    @Test
    public void demoa() {
        final ArrayList<Integer> integers = Lists.newArrayList(
                1,
                2,
                3
        );
        integers.sort(Comparator
                .comparing(Integer::intValue)
                .reversed());
        Console.log(integers);
    }

    @Test
    public void demo1() {
        List<Integer> map = map(
                Arrays.asList(
                        "lambdas",
                        "in",
                        "action"
                ),
                String::length
        );
        System.out.println(map);
    }

    @Test
    public void demo2() {
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple(
                "green",
                150D
        ));
        apples.add(new Apple(
                "red",
                250D
        ));
        apples.add(new Apple(
                "blue",
                100D
        ));
        List<Apple> heavyAppleStream = apples
                .stream()
                .filter((Apple a) -> a.getWeight() >= 150)
                .collect(Collectors.toList());
        List<Apple> heavyAppleParallelStream = apples
                .parallelStream()
                .filter((Apple a) -> a.getWeight() >= 100)
                .collect(Collectors.toList());
        System.out.println(heavyAppleStream);
        System.out.println(heavyAppleParallelStream);
        System.out.println(filterGreenApple(apples));
        System.out.println(filterApple(
                apples,
                (Apple a) -> "blue".equals(a.getColor())
        ));
    }

    @Test
    public void demo3() {
        Runnable runnable = () -> {
            for (int j = 0; j < 100; j++) {
                System.out.println(Thread
                        .currentThread()
                        .getName() + " " + j);
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread
                    .currentThread()
                    .interrupt();
        }
    }

    @Test
    public void demo4() throws
            IOException {
        System.out.println(processFile(br -> br.readLine() + "\n" + br.readLine()));
        System.out.println();
        System.out.println(processFile(BufferedReader::readLine));

    }

    @Test
    public void demo5() {
        Arrays
                .asList(
                        1,
                        2,
                        3,
                        4,
                        5
                )
                .forEach(System.out::println);
        List<Integer> integers = Arrays.asList(
                1,
                2,
                3,
                4,
                5
        );
        integers.sort(Comparator
                .comparing(Integer::intValue)
                .reversed());
        System.out.println(integers);
    }

    @Test
    public void demo6() {
        IntPredicate intPredicate = (int i) -> i % 2 == 0;
        Predicate<Integer> integerPredicate = (Integer i) -> i % 2 == 1;
        System.out.println(intPredicate.test(1000));
        System.out.println(integerPredicate.test(1000));
    }

    @Test
    public void demo7() {
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple(
                "red",
                200D
        ));
        apples.add(new Apple(
                "blue",
                200D
        ));
        apples.add(new Apple(
                "yellow",
                150D
        ));
        apples.add(new Apple(
                "red",
                150D
        ));
        apples.add(new Apple(
                "yellow",
                250D
        ));
        apples.add(new Apple(
                "red",
                150D
        ));
        apples.add(new Apple(
                "blue",
                250D
        ));
        apples.add(new Apple(
                "red",
                150D
        ));
        apples.add(new Apple(
                "blue",
                100D
        ));
        apples.add(new Apple(
                "red",
                300D
        ));
        apples.add(new Apple(
                "yellow",
                200D
        ));
        apples.add(new Apple(
                "blue",
                300D
        ));
        apples.add(new Apple(
                "blue",
                100D
        ));
        apples.add(new Apple(
                "red",
                500D
        ));
        apples.sort(Comparator
                .comparing(Apple::getColor)
                .thenComparing(Apple::getWeight));
        System.out.println(apples);
        Predicate<Apple> apple150Filter = (Apple a) -> a.getWeight() > 150D;
        Predicate<Apple> appleRedFilter = (Apple a) -> "red".equals(a.getColor());
        System.out.println(filterApple(
                apples,
                apple150Filter.and(appleRedFilter)
        ));
        System.out.println(filterApple(
                apples,
                apple150Filter.or(appleRedFilter)
        ));
        System.out.println(filterApple(
                apples,
                apple150Filter
                        .and(appleRedFilter)
                        .negate()
        ));
    }

    @Test
    public void demo8() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> t = f.compose(g);
        System.out.println(h.apply(1));
        System.out.println(t.apply(1));
    }

    @Test
    public void demo9() {
        Stream<String> ss = Stream.of(
                "Java",
                "In",
                "Action"
        );
        ss.forEach(System.out::println);
        ss.forEach(System.out::println);
    }

    private static <T, R> List<R> map(List<T> list,
                                      Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;

    }

    private List<Apple> filterGreenApple(List<Apple> list) {
        ArrayList<Apple> apples = new ArrayList<>();
        for (Apple temp : list) {
            if ("green".equals(temp.getColor())) {
                apples.add(temp);
            }
        }
        return apples;
    }

    private List<Apple> filterApple(List<Apple> inventory,
                                    Predicate<Apple> p) {
        ArrayList<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Apple {
        private String color;
        private Double weight;
    }

    private static String processFile(BufferedReaderProcessor brp) throws
            IOException {
        InputStream resourceAsStream = Lambda.class
                .getClassLoader()
                .getResourceAsStream("mongo.properties");
        InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(resourceAsStream));
        try (BufferedReader br = new BufferedReader(inputStreamReader)) {
            return brp.process(br);
        }
    }

    @FunctionalInterface
    interface BufferedReaderProcessor {
        String process(BufferedReader br) throws
                IOException;
    }

}
