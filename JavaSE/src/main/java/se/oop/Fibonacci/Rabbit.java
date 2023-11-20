package se.oop.Fibonacci;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 假设有一对兔子，长两个月它们就算长大成年了。
 * 然后以后每个月都会生出1对兔子，生下来的兔子也都是长两个月就算成年，然后每个月也都会生出1对兔子了。
 * 这里假设兔子不会死，每次都是只生1对兔子，这样过了一年之后，会有多少对兔子了呢？
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/5/29 09:32
 */
public class Rabbit {

    public int age = 1;

    public Rabbit bear() {
        return age > 2 ? new Rabbit() : null;
    }

    public void grow() {
        ++age;
    }

    public static void main(String[] args) {
        List<Rabbit> sum = new ArrayList<>();
        sum.add(new Rabbit());
        for (int i = 1; i < 12; i++) {
            final int size = sum.size();
            for (int j = 0; j < size; j++) {
                final Rabbit rabbit = sum.get(j);
                rabbit.grow();
                Rabbit baby = rabbit.bear();
                if (baby != null) {
                    sum.add(baby);
                }
            }
        }
        System.out.println(sum.size());
    }

}
