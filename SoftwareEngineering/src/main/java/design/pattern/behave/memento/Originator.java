package design.pattern.behave.memento;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

@Slf4j
@Setter
@Getter
@Accessors(chain = true)
public class Originator {

    private final Stack<Memento> historyMemento = new Stack<>();

    private String x;
    private String y;

    public Originator(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public void saveMemento() {
        historyMemento.push(new Memento(x, y));
    }

    public void restoreMemento() {
        if (historyMemento.size() == 0) return;
        Memento pop = historyMemento.pop();
        this.x = pop.getX();
        this.y = pop.getY();
    }

    public void render() {
        log.info("{} {}", this.x, this.y);
    }

}
