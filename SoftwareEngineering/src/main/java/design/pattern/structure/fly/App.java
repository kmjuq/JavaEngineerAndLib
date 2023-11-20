package design.pattern.structure.fly;

public class App {

    public static void main(String[] args) {
        IgoChessmanFactory factory = IgoChessmanFactory.getInstance();
        IgoChessman white1 = factory.createIgoChessman("white", new Location(1, 2));
        IgoChessman white2 = factory.createIgoChessman("white", new Location(2, 2));
        IgoChessman black1 = factory.createIgoChessman("black", new Location(3, 2));
        IgoChessman black2 = factory.createIgoChessman("black", new Location(4, 2));

        System.out.println(white1);
        System.out.println(white2);
        System.out.println(black1);
        System.out.println(black2);
    }

}
