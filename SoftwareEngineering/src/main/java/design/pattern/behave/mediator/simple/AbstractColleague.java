package design.pattern.behave.mediator.simple;


public abstract class AbstractColleague {

    private final AbstractMediator mediator;

    public AbstractColleague(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    public void sendT(Request req) {
        send(req);
        mediator.spreadRequest(this, req);
    }

    public void receiveT(Request req) {
        receive(req);
        mediator.spreadRequest(this, req);
    }

    abstract void send(Request req);

    abstract void receive(Request req);

}
