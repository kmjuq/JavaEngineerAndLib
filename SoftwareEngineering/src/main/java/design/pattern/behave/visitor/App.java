package design.pattern.behave.visitor;

public class App {

    public static void main(String[] args) {
        AllData allData = new AllData();
        allData.setOnePhase("one").setTwoPhase("two");
        allData.accept(new OnePhaseVisitor(),new TwoPhaseVisitor());
    }

}
