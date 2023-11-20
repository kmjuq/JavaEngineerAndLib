package design.pattern.behave.command.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@Accessors(chain = true)
public class Receiver {

    public void excute() {
        log.info("receiver ");
    }

}
