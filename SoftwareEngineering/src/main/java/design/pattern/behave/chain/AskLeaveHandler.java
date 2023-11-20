package design.pattern.behave.chain;

import java.util.Objects;

public abstract class AskLeaveHandler {

    private AskLeaveHandler handler;

    public AskLeaveHandler next() {
        return this.handler;
    }


    public AskLeaveHandler next(AskLeaveHandler handler) {
        this.handler = handler;
        return this.handler;
    }

    public abstract void handler(AskLeaveRequest request);

    public void handlerChain(AskLeaveRequest request) {
        AskLeaveHandler current = this;
        current.handler(request);
        while(Objects.nonNull(current.next())){
            current = current.next();
            current.handler(request);
        }
    }
}
