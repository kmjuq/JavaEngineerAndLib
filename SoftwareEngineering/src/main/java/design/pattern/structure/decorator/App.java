package design.pattern.structure.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {

        // simple troll
        log.info("A simple looking troll approaches.");
        var troll = new SimpleTroll();
        troll.attack();
        troll.fleeBattle();
        log.info("Simple troll power: {}.\n", troll.getAttackPower());

        // change the behavior of the simple troll by adding a decorator
        log.info("A troll with huge club surprises you.");
        var clubbedTroll = new ClubbedTroll(troll);
        clubbedTroll.attack();
        clubbedTroll.fleeBattle();
        log.info("Clubbed troll power: {}.\n", clubbedTroll.getAttackPower());
    }
}