package extra.disruptor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@Accessors(fluent = true, chain = true)
public class BinaryOperatorEvent {

    private int first;
    private int second;
    private int add;
    private int sub;
    private int mul;


}
