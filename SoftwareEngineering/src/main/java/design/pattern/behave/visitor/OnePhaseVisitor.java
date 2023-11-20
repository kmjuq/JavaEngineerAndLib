package design.pattern.behave.visitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OnePhaseVisitor extends VisitorAdaptor {

    @Override
    public void visit(OnePhase onePhase) {
        log.info("log one phase visitor {}",onePhase.getContent());
    }
}
