package se.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public interface ILog {

    void log(String content);

    public static void main(String[] args) {
        ServiceLoader<ILog> load = ServiceLoader.load(ILog.class);
        Iterator<ILog> iterator = load.iterator();
        while (iterator.hasNext()) {
            ILog next = iterator.next();
            next.log("spi");
        }
    }

}
