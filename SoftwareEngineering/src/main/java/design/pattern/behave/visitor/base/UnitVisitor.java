package design.pattern.behave.visitor.base;

/**
 * Visitor interface.
 */
public interface UnitVisitor {

  void visit(Soldier soldier);

  void visit(Sergeant sergeant);

  void visit(Commander commander);

}
