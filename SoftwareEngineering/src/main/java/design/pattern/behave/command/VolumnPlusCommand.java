package design.pattern.behave.command;

public class VolumnPlusCommand extends AbstractCommand{

    public VolumnPlusCommand(TVReceiver tvReceiver) {
        super(tvReceiver);
    }

    @Override
    public void excute() {
        getTvReceiver().volumnPlus();
    }
}
