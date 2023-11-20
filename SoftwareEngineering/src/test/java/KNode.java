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
public class KNode {

    private KNode prev;

    private KNode next;

    private int value;

    public static KNode of(int value) {
        return new KNode(null, null, value);
    }

    public static KNode[] of(int... args){
        KNode[] kNodes = new KNode[args.length];
        for (int i = 0; i < args.length; i++) {
            kNodes[i] = KNode.of(args[i]);
        }
        return kNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KNode kNode = (KNode) o;
        return value == kNode.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
