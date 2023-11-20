package design.pattern.behave.command;

public class ChannelNextCommand extends AbstractCommand{

    public ChannelNextCommand(TVReceiver tvReceiver) {
        super(tvReceiver);
    }

    @Override
    public void excute() {
        getTvReceiver().nextIndex();
    }
}
