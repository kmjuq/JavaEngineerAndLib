package se.stream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {

    private List<Dish> menu = null;

    @BeforeAll
    public void before() {
        menu = Arrays.asList(
                new Dish(
                        "pork",
                        false,
                        800,
                        Dish.Type.MEAT
                ),
                new Dish(
                        "beef",
                        false,
                        700,
                        Dish.Type.MEAT
                ),
                new Dish(
                        "chicken",
                        false,
                        400,
                        Dish.Type.MEAT
                ),
                new Dish(
                        "french fries",
                        true,
                        530,
                        Dish.Type.OTHER
                ),
                new Dish(
                        "rice",
                        true,
                        350,
                        Dish.Type.OTHER
                ),
                new Dish(
                        "season fruit",
                        true,
                        120,
                        Dish.Type.OTHER
                ),
                new Dish(
                        "pizza",
                        true,
                        550,
                        Dish.Type.OTHER
                ),
                new Dish(
                        "prawns",
                        false,
                        300,
                        Dish.Type.FISH
                ),
                new Dish(
                        "salmon",
                        false,
                        450,
                        Dish.Type.FISH
                )
        );
    }

    @Test
    public void demo1() {
        List<String> collect = menu
                .stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void demo2() {
        List<String> collect = menu
                .stream()
                .filter(dish -> {
                    System.out.println("filtering " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void demo3() {
        List<Integer> integers = Arrays.asList(
                1,
                2,
                1,
                3,
                3,
                2,
                4
        );
        List<Integer> collect = integers
                .stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
        integers
                .stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        menu
                .stream()
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void demo4() {
        long count = menu
                .stream()
                .filter(dish -> dish.getCalories() > 300)
                .count();
        System.out.println(count);
        List<Dish> collect = menu
                .stream()
                .filter(dish -> dish.getCalories() > 300)
                .sorted(Comparator.comparing(Dish::getCalories))
                .skip(1)
                .limit(1)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void demo5() {
        List<Dish> dishes = Arrays.asList(
                new Dish(
                        "pork",
                        false,
                        800,
                        Dish.Type.MEAT
                ),
                new Dish(
                        "pork",
                        false,
                        800,
                        Dish.Type.MEAT
                ),
                new Dish(
                        "pork",
                        false,
                        800,
                        Dish.Type.MEAT
                )
        );
        List<Dish> collect = dishes
                .stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void demo6() {
        List<String> strings = Arrays.asList(
                "Java 8",
                "Lambdas",
                "In",
                "Action"
        );
        List<Integer> collect = strings
                .stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void demo7() {
        List<String> words = Arrays.asList(
                "hello",
                "world"
        );
        List<String> collect = words
                .stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
        Stream
                .of(
                        "hello",
                        "world"
                )
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    @Test
    public void demo8() {
        List<Integer> integers = Arrays.asList(
                1,
                2,
                3,
                4,
                5
        );
        List<Integer> collect = integers
                .stream()
                .map(i -> i * i)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void demo9() {
        List<Integer> math1 = Arrays.asList(
                1,
                2,
                3
        );
        List<Integer> math2 = Arrays.asList(
                3,
                4
        );
        List<int[]> collect = math1
                .stream()
                .flatMap(
                        i -> math2
                                .stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                        //i -> math2.stream().map(j -> new int[]{i, j}).filter(array -> (array[0]+array[1]) % 3 == 0)
                )
                .collect(Collectors.toList());
        collect.forEach(i -> System.out.println(Arrays.toString(i)));
    }

    @Test
    public void demo10() {
        if (menu
                .stream()
                .anyMatch(Dish::isVegetarian)) {
            System.out.println("This menu is (somewhat) vegetarian friendly!");
        }

        boolean allMatch = menu
                .stream()
                .allMatch(d -> d.getCalories() < 1000);
        boolean noneMatch = menu
                .stream()
                .noneMatch(dish -> dish.getCalories() >= 1000);
    }

    @Test
    public void demo11() {
        menu
                .stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.getName()));
    }

    @Test
    public void demo12() {
        List<Integer> integers = Arrays.asList(
                1,
                2,
                3,
                4,
                5
        );
        integers
                .stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);
    }

    @Test
    public void demo13() {
        List<Integer> integers = Arrays.asList(
                1,
                2,
                3,
                4,
                5
        );
        Integer reduce = integers
                .stream()
                .reduce(
                        0,
                        Integer::sum
                );
        System.out.println(reduce);
        Optional<Integer> optionalReduce = integers
                .stream()
                .reduce(Integer::sum);
        System.out.println(optionalReduce.orElse(1));
    }

    @Test
    public void demo14() {
        Optional<Integer> reduce = menu
                .stream()
                .map(dish -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.orElse(0));
    }

    @Test
    public void demo15() {
        List<Integer> integers = Arrays.asList(
                1,
                2,
                3,
                4,
                5
        );
        Optional<Integer> max = integers
                .stream()
                .reduce(Integer::max);
        Optional<Integer> min = integers
                .stream()
                .reduce(Integer::min);
        System.out.println(max);
        System.out.println(min);
    }

    @Test
    public void demo16() {
        Trader raoul = new Trader(
                "Raoul",
                "Cambridge"
        );
        Trader mario = new Trader(
                "Mario",
                "Milan"
        );
        Trader alan = new Trader(
                "Alan",
                "Cambridge"
        );
        Trader brian = new Trader(
                "Brian",
                "Cambridge"
        );
        List<Trader> traders = Arrays.asList(
                raoul,
                mario,
                alan,
                brian
        );
        List<Transaction> transactions = Arrays.asList(
                new Transaction(
                        brian,
                        2011,
                        300
                ),
                new Transaction(
                        raoul,
                        2012,
                        1000
                ),
                new Transaction(
                        raoul,
                        2011,
                        400
                ),
                new Transaction(
                        mario,
                        2012,
                        710
                ),
                new Transaction(
                        mario,
                        2012,
                        700
                ),
                new Transaction(
                        alan,
                        2012,
                        950
                )
        );

        //        1.找出2011年发生的所有交易,并按交易额排序(从低到高)
        List<Transaction> collect1 = transactions
                .stream()
                .filter(trans -> trans.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(collect1);
        //        2.交易员都在哪些不同的城市工作过
        List<String> collect2 = traders
                .stream()
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect2);
        //        3.查找所有来自于剑桥的交易员,并按姓名排序.
        List<Trader> collect3 = traders
                .stream()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(collect3);
        //        4.返回所有交易员的姓名字符串,按字母顺序排序.
        List<String> collect4 = traders
                .stream()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collect4);
        //        5.有没有交易员是在米兰工作的
        boolean present = traders
                .stream()
                .anyMatch(trader -> "Milan".equals(trader.getCity()));
        System.out.println(present);
        //        6.打印生活在剑桥的交易员的所有交易额
        List<Integer> collect5 = transactions
                .stream()
                .filter(trans -> "Cambridge".equals(trans
                        .getTrader()
                        .getCity()))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        System.out.println(collect5);
        //        7.所有交易中,最高的交易额是多少
        Integer transactionMax = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .orElse(0);
        System.out.println(transactionMax);
        //        8.找到交易额最小的交易
        Integer transactionMin = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .orElse(0);
        System.out.println(transactionMin);
    }

    @Test
    public void demo17() {
        IntStream intStreamClosed = IntStream
                .rangeClosed(
                        1,
                        100
                )
                .filter(i -> i % 2 == 0);
        IntStream intStream = IntStream
                .range(
                        1,
                        100
                )
                .filter(i -> i % 2 == 0);
        System.out.println(intStreamClosed.count());
        System.out.println(intStream.count());
    }

    @Test
    public void demo18() {
        Stream<double[]> stream = IntStream
                .rangeClosed(
                        1,
                        100
                )
                .boxed()
                .flatMap(a -> IntStream
                        .rangeClosed(
                                a,
                                100
                        )
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0);
    }

    @Test
    public void demo19() {
        Stream<String> stringStream = Stream.of(
                "Java 8",
                "lambda",
                "in",
                "action"
        );
        List<String> collect = stringStream
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void demo20() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays
                .stream(numbers)
                .sum());
        System.out.println(Arrays
                .stream(numbers)
                .count());
    }

    @Test
    public void demo21() {
        try (
                Stream<String> lines = Files.lines(
                        Paths.get("/Users/kmj/Git/javase/src/main/resources/mongo.properties"),
                        Charset.defaultCharset()
                )
        ) {
            final List<String> collect = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println(collect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo22() {
        final List<Integer> collect = Stream
                .iterate(
                        0,
                        n -> n + 2
                )
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void demo23() {
        final List<int[]> collect = Stream
                .iterate(
                        new int[]{0, 1},
                        n -> new int[]{n[1], n[0] + n[1]}
                )
                .limit(10)
                .collect(Collectors.toList());
        collect.forEach(n -> System.out.println(Arrays.toString(n)));
    }

    @Test
    public void demo24() {
        Stream
                .generate(() -> (char) (97 + Math.random() * (122 - 97)))
                .limit(10)
                .collect(Collectors.toList())
                .forEach(ch -> System.out.print(ch + " "));
    }

    @Test
    public void demo25() {
        System.out.println(menu.size());
        System.out.println(menu
                .stream()
                .count());
        System.out.println(menu
                .stream()
                .collect(Collectors.counting()));
    }

    @Test
    public void demo26() {
        //        final Optional<Dish> collect = menu.stream().max(Comparator.comparing(Dish::getCalories));
        final Comparator<Dish> comparing = Comparator.comparing(Dish::getCalories);
        final Optional<Dish> collect = menu
                .stream()
                .collect(Collectors.maxBy(comparing));
        collect.ifPresent(System.out::println);
        Optional<Dish> collect1 = menu
                .stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(collect1.orElseGet(() -> new Dish(
                null,
                false,
                0,
                null
        )));
    }

    @Test
    public void demo27() {
        System.out.println((Integer) menu
                .stream()
                .mapToInt(Dish::getCalories)
                .sum());
        System.out.println(menu
                .stream()
                .collect(Collectors.summingInt(Dish::getCalories)));
        System.out.println(menu
                .stream()
                .collect(Collectors.reducing(
                        0,
                        Dish::getCalories,
                        (i, j) -> i + j
                )));
    }

    @Test
    public void demo28() {
        System.out.println(menu
                .stream()
                .collect(Collectors.averagingInt(Dish::getCalories)));
    }

    @Test
    public void demo29() {
        final IntSummaryStatistics collect = menu
                .stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(collect); //IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
    }

    @Test
    public void demo30() {
        System.out.println(menu
                .stream()
                .map(Dish::getName)
                .collect(Collectors.joining()));
        System.out.println(menu
                .stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", ")));
    }

    @Test
    public void demo31() {
        menu
                .stream()
                .collect(Collectors.reducing(
                        0,
                        Dish::getCalories,
                        (i, j) -> i + j
                ));
    }

    @Test
    public void demo32() {
        menu
                .stream()
                .collect(Collectors.groupingBy(Dish::getType))
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo33() {
        menu
                .stream()
                .collect(
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return Dish.CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return Dish.CaloricLevel.NORMAL;
                            } else {
                                return Dish.CaloricLevel.FAT;
                            }
                        })
                )
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo34() {
        menu
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                Collectors.groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return Dish.CaloricLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return Dish.CaloricLevel.NORMAL;
                                    } else {
                                        return Dish.CaloricLevel.FAT;
                                    }
                                })
                        )
                )
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo35() {
        menu
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                Collectors.counting()
                        )
                )
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo36() {
        menu
                .stream()
                .collect(
                        Collectors.toMap(
                                Dish::getType,
                                Function.identity(),
                                BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))
                        )
                )
                .forEach((a, b) -> System.out.println(a + ": " + b));
        menu
                .stream()
                .collect(
                        Collectors.toMap(
                                Dish::getType,
                                Function.identity(),
                                BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))
                        )
                )
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo37() {
        menu
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                Collectors.mapping(
                                        dish -> {
                                            if (dish.getCalories() <= 400) {
                                                return Dish.CaloricLevel.DIET;
                                            } else if (dish.getCalories() <= 700) {
                                                return Dish.CaloricLevel.NORMAL;
                                            } else {
                                                return Dish.CaloricLevel.FAT;
                                            }
                                        },
                                        Collectors.toSet()
                                )
                        )
                )
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo38() {
        menu
                .stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return Dish.CaloricLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return Dish.CaloricLevel.NORMAL;
                                    } else {
                                        return Dish.CaloricLevel.FAT;
                                    }
                                },
                                Collectors.toCollection(ArrayList::new)
                        )
                ))
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo39() {
        menu
                .stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian))
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo40() {
        menu
                .stream()
                .collect(
                        Collectors.partitioningBy(
                                Dish::isVegetarian,
                                Collectors.groupingBy(Dish::getType)
                        )
                )
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    private boolean isPrime(int candidate) {
        return IntStream
                .range(
                        2,
                        candidate
                )
                .noneMatch(i -> candidate % i == 0);
    }

    private Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream
                .rangeClosed(
                        2,
                        n
                )
                .boxed()
                .collect(Collectors.partitioningBy(this::isPrime));
    }

    @Test
    public void demo41() {
        partitionPrimes(10).forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo42() {
        IntStream
                .rangeClosed(
                        2,
                        10
                )
                .boxed()
                .collect(
                        Collectors.partitioningBy(d -> IntStream
                                .range(
                                        2,
                                        d
                                )
                                .noneMatch(i -> d % i == 0)
                        )
                )
                .forEach((a, b) -> System.out.println(a + ": " + b));
    }

    @Test
    public void demo43() {
        Arrays
                .asList(
                        1,
                        2,
                        1,
                        3,
                        3,
                        2,
                        4
                )
                .stream()
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void demo44() {
        System.out.println("Sequential sum done in:" + measureSumPerf(
                StreamTest::sequentialSum,
                10_000_000
        ) + " msecs");
        System.out.println("Iterative sum done in:" + measureSumPerf(
                StreamTest::iterativeSum,
                10_000_000
        ) + " msecs");
        System.out.println("Parallel sum done in: " + measureSumPerf(
                StreamTest::parallelSum,
                10_000_000
        ) + " msecs");
        System.out.println("Ranged sum done in: " + measureSumPerf(
                StreamTest::rangedSum,
                10_000_000
        ) + " msecs");
        System.out.println("ParallelRanged sum done in: " + measureSumPerf(
                StreamTest::parallelRangedSum,
                10_000_000
        ) + " msecs");
        System.out.println("ForkJoin sum done in: " + measureSumPerf(
                ForkJoinSumCalculator::forkJoinSum,
                10_000_000
        ) + " msecs");
    }

    @Test
    public void demo45() {
        Stream
                .of(
                        "kmj",
                        "KMJ",
                        "Kmj"
                )
                .filter("KMJ"::equals)
                .forEach(System.out::println);
    }

    @Test
    public void demo46() {
        Stream
                .of(
                        1,
                        2,
                        3,
                        4,
                        5,
                        6
                )
                .limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void demo47() {
        Stream
                .of(
                        1,
                        2,
                        3,
                        4,
                        5,
                        6
                )
                .skip(3)
                .forEach(System.out::println);
    }

    @Test
    public void demo48() {
        Stream
                .of(
                        "Java",
                        "In",
                        "Action"
                )
                .map(s -> s.charAt(1))
                .forEach(System.out::println);
    }

    @Test
    public void demo49() {
        String[][] words = new String[][]{{"Java", "In", "Action"}, {"Java 8", "实战"}};
        Arrays
                .stream(words)
                .map(Arrays::stream)
                .forEach(System.out::print);
        System.out.println();
        Arrays
                .stream(words)
                .flatMap(Arrays::stream)
                .forEach(System.out::print);
    }

    @Test
    public void demo50() {
        System.out.println(Stream
                .of(
                        "Java",
                        "In",
                        "Action"
                )
                .allMatch("In"::equals));
    }

    @Test
    public void demo51() {
        System.out.println(Stream
                .of(
                        "Java",
                        "In",
                        "Action"
                )
                .noneMatch("In"::equals));
    }

    @Test
    public void demo52() {
        System.out.println(Stream
                .of(
                        "Java",
                        "In",
                        "Action"
                )
                .anyMatch("In"::equals));
    }

    @Test
    public void demo53() {
        List<String> source = Arrays.asList(
                "Jhonny",
                "David",
                "Jack",
                "Duke",
                "Jill",
                "Dany",
                "Julia",
                "Jenish",
                "Divya"
        );

        Optional<String> findFirst = source
                .parallelStream()
                .filter(s -> s.startsWith("D"))
                .findFirst();
        Optional<String> fidnAny = source
                .parallelStream()
                .filter(s -> s.startsWith("J"))
                .findAny();

        System.out.println(findFirst.orElse("")); //总是打印出David
        System.out.println(fidnAny.orElse("")); //会随机地打印出Jack/Jill/Julia/Jenish
    }

    @Test
    public void demo54() {
        Stream
                .of(
                        1,
                        2,
                        3,
                        4
                )
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
    }

    @Test
    public void demo55() {
        menu
                .stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
        System.out.println(
                menu
                        .stream()
                        .mapToInt(Dish::getCalories)
                        .sum()
        );
    }

    @Test
    public void demo56() {
        final ArrayList<Dish> dishes = new ArrayList<>();
        System.out.println(dishes.stream().sorted(Comparator.comparing(Dish::getCalories)).collect(Collectors.toList()));
    }

    private static long sequentialSum(long n) {
        return Stream
                .iterate(
                        1L,
                        k -> k + 1
                )
                .limit(n)
                .reduce(
                        0L,
                        Long::sum
                );
    }

    private static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    private static long parallelSum(long n) {
        return Stream
                .iterate(
                        1L,
                        i -> i + 1
                )
                .limit(n)
                .parallel()
                .reduce(
                        0L,
                        Long::sum
                );
    }

    private static long rangedSum(long n) {
        return LongStream
                .rangeClosed(
                        1,
                        n
                )
                .reduce(
                        0L,
                        Long::sum
                );
    }

    private static long parallelRangedSum(long n) {
        return LongStream
                .rangeClosed(
                        1,
                        n
                )
                .parallel()
                .reduce(
                        0L,
                        Long::sum
                );
    }

    private long measureSumPerf(Function<Long, Long> adder,
                                long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    @Setter
    @Getter
    @ToString
    class Transaction {

        private final Trader trader;
        private final int year;
        private final int value;

        Transaction(Trader trader,
                    int year,
                    int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

    }

    @Setter
    @Getter
    @ToString
    class Trader {

        private final String name;
        private final String city;

        Trader(String name,
               String city) {
            this.name = name;
            this.city = city;
        }
    }

    @Setter
    @Getter
    public static class Dish {

        private final String name;

        private final boolean vegetarian;

        private final int calories;

        private final Type type;


        Dish(String name,
             boolean vegetarian,
             int calories,
             Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }


        @Override
        public boolean equals(Object o) {
            return this
                    .toString()
                    .equals(Optional
                            .ofNullable(o)
                            .orElse("")
                            .toString());
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (vegetarian ? 1 : 0);
            result = 31 * result + calories;
            result = 31 * result + (type != null ? type.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return name;
        }

        enum Type {
            /**
             * 肉
             */
            MEAT,
            /**
             * 鱼
             */
            FISH,
            /**
             * 其他
             */
            OTHER

        }

        enum CaloricLevel {
            /**
             * 低卡路里
             */
            DIET,
            /**
             * 中卡路里
             */
            NORMAL,
            /**
             * 高卡路里
             */
            FAT
        }
    }

}


