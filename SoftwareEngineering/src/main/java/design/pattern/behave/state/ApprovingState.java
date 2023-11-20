package design.pattern.behave.state;

import design.pattern.behave.state.IState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApprovingState extends AbstractState {

    public ApprovingState(Context context) {
        super(context);
    }

    @Override
    protected void doHandle() {
        log.info("approving state handle");
    }

    @Override
    public void nextState() {
        Context context = this.getContext();
        if (context.isFlag()) {
            context.setState(new ApprovedState(context));
        }else{
            context.setState(new RejectedState(context));
        }
    }
}
