package design.pattern.behave.state;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class AbstractState implements IState {

    private Context context;

    public AbstractState(Context context) {
        this.context = context;
    }

    @Override
    public void handle() {
        doHandle();
        nextState();
    }

    protected void doHandle() {

    }

    @Override
    public void nextState() {

    }
}
