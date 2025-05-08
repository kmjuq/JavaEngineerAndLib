package spock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class AbstractService {

    void show() {
        log.info("abstract");
    }

}