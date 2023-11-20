package jep;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.random.RandomGenerator;

@Slf4j
public class SwitchJep {

    /**
     * yield 跳出 switch , 用于返回 switch 表达式。
     */
    @Test
    public void demo1() {
        final Goods[] values = Goods.values();
        final Goods[] goods = RandomGenerator.getDefault()
                .ints(20, 0, values.length)
                .mapToObj(i -> values[i])
                .toArray(Goods[]::new);
        for (Goods good : goods) {
            int num = switch (good) {
                case APPLE, BANANA -> 1;
                case LEEK, GREENS -> 2;
                default -> {
                    if (good.equals(Goods.CRESS)) {
                        yield 3;
                    } else {
                        yield 4;
                    }
                }
            };
            log.info(num + "");
        }
    }

    enum Goods {
        APPLE(50), BANANA(20), CHERRY(10), PEACH(40),
        LEEK(20), GREENS(15), CRESS(5);

        final int weight;
        Goods(int weight){
            this.weight = weight;
        }
    }
}
