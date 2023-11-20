package design.pattern.behave.command.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@Accessors(chain = true)
public class ConcreteCommand implements Command {

    private Receiver receiver;

    @Override
    public void excute() {
        receiver.excute();
    }
}
