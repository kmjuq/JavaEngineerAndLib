package design.pattern.behave.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApprovedState extends AbstractState {
    public ApprovedState(Context context) {
        super(context);
    }

    @Override
    protected void doHandle() {
        log.info("approved state handle");
    }

    @Override
    public void nextState() {
        log.info("state end");
    }
}
