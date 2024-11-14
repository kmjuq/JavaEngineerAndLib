package design.pattern.structure.fly;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IgoChessmanFactory {

    private static final IgoChessmanFactory igoChessmanFactory = new IgoChessmanFactory();
    private static final ConcurrentMap<String, Color> maps = new ConcurrentHashMap<>() {{
        put("white", new Color("white"));
        put("black", new Color("black"));
    }};

    private IgoChessmanFactory() {

    }

    public static IgoChessmanFactory getInstance() {
        return igoChessmanFactory;
    }

    public IgoChessman createIgoChessman(String color, Location location) {
        return new IgoChessman(maps.get(color), location);
    }


}
