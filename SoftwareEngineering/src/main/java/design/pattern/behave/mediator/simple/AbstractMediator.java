package design.pattern.behave.mediator.simple;

import java.util.ArrayList;
import java.util.List;


public class AbstractMediator {

    List<AbstractColleague> colleagues = new ArrayList<>();

    public void register(AbstractColleague ac) {
        colleagues.add(ac);
    }

    public void unregister(AbstractColleague ac) {
        colleagues.remove(ac);
    }

    public void spreadRequest(AbstractColleague ac, Request req) {
        colleagues.stream().filter(c -> !c.equals(ac)).forEach(c -> c.receive(req));
    }

}
