package spock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class Service extends AbstractService {

    void testShow() {
        show();
    }

    void testSuperShow() {
        super.show();
    }

    void testline(){
        int i = Component.line(4);
        log.info("line num {}",i);
    }

}