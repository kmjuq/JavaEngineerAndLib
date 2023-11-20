package design.pattern.behave.command;

public class ChannelPrevCommand extends AbstractCommand{


    public ChannelPrevCommand(TVReceiver tvReceiver) {
        super(tvReceiver);
    }

    @Override
    public void excute() {
        getTvReceiver().prevIndex();
    }
}
