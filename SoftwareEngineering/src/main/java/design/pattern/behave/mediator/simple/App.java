package design.pattern.behave.mediator.simple;

public class App {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        ColleagueA colleagueA = new ColleagueA(mediator);
        ColleagueB colleagueB = new ColleagueB(mediator);
        ColleagueC colleagueC = new ColleagueC(mediator);
        mediator.register(colleagueA);
        mediator.register(colleagueB);
        mediator.register(colleagueC);
        colleagueA.sendT(new Request());
        colleagueB.sendT(new Request());
    }

}
