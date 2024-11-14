package design.pattern.behave.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewState extends AbstractState {

    public NewState(Context context) {
        super(context);
    }

    @Override
    protected void doHandle() {
        log.info("new state handle");
    }

    @Override
    public void nextState() {
        Context context = getContext();
        context.setState(new ApprovingState(context));
    }
}
