package design.pattern.behave.state;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class Context {

    private IState state;

    private boolean flag = false;

    public void handle() {
        state.handle();
    }

}
