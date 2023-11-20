package design.pattern.behave.command;

public abstract class AbstractCommand implements Command{

    private final TVReceiver tvReceiver;

    public AbstractCommand(TVReceiver tvReceiver){
        this.tvReceiver = tvReceiver;
    }

    public TVReceiver getTvReceiver(){
        return tvReceiver;
    }

    @Override
    public abstract void excute();
}
