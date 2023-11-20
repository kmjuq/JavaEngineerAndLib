package design.pattern.behave.visitor;

public interface IVisitor {

    void visit(OnePhase onePhase);

    void visit(TwoPhase twoPhase);

}
