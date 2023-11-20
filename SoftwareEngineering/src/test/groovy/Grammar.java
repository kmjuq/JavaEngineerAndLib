import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Grammar {

    public void branch(int type) {
        switch (type) {
            case 0:
                run();
                break;
            case 1:
                supply();
                break;
            case 2:
                consumer();
                break;
            case 4:
                fun(depend());
                break;
        }
    }

    public int depend() {
        return 100;
    }

    public void run() {
        log.info("run");
    }

    public String supply() {
        return "supply";
    }

    public String consumer() {
        return "consumer";
    }

    public String fun(int param) {
        return "fun " + param;
    }
}