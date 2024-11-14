package design.pattern.behave.memento;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Memento {

    @NonNull
    private String x;

    @NonNull
    private String y;

    public Memento copy() {
        return new Memento(this.x, this.y);
    }
}
