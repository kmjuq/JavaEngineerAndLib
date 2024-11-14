package design.pattern.behave.command;

public class VolumnMinusCommand extends AbstractCommand {


    public VolumnMinusCommand(TVReceiver tvReceiver) {
        super(tvReceiver);
    }

    @Override
    public void excute() {
        getTvReceiver().volumnMinus();
    }
}
