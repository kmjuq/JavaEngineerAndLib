package zzz;

import lombok.*;
import lombok.experimental.Accessors;





/**
 * <p>
 * 用来测试对象类
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/6/20 09:57
 */

@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    private int value;

    public static Node of(int value) {
        return new Node(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node node)) {
            return false;
        }

        return value == node.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

}
