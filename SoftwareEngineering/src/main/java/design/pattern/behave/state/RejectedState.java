package design.pattern.behave.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RejectedState extends AbstractState {
    public RejectedState(Context context) {
        super(context);
    }

    @Override
    protected void doHandle() {
        log.info("rejected state handle");
    }

    @Override
    public void nextState() {
        log.info("state end");
    }
}
