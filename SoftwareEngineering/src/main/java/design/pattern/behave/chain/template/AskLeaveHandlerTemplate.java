package design.pattern.behave.chain.template;

import design.pattern.behave.chain.AskLeaveRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/3/12 17:23
 */
public abstract class AskLeaveHandlerTemplate {

    private AskLeaveHandlerTemplate first;
    private AskLeaveHandlerTemplate next;

    public AskLeaveHandlerTemplate() {
        this.first = this;
    }

    public AskLeaveHandlerTemplate first() {
        return this.first;
    }

    public AskLeaveHandlerTemplate next() {
        return this.next;
    }

    public AskLeaveHandlerTemplate next(AskLeaveHandlerTemplate handler) {
        this.next = handler;
        this.next.first = this.first;
        return this.next;
    }

    abstract boolean business(AskLeaveRequest request);

    public final boolean handler(AskLeaveRequest request) {
        if (business(request)) {
            return true;
        }
        if (this.next() != null) {
            return this.next().handler(request);
        } else {
            return false;
        }
    }

}
