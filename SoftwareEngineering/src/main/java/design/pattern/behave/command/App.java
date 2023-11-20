package design.pattern.behave.command;

public class App {

    public static void main(String[] args) {
        TVReceiver tvReceiver = new TVReceiver(new TV());
        ChannelNextCommand channelNextCommand = new ChannelNextCommand(tvReceiver);
        channelNextCommand.excute();
        ChannelPrevCommand channelPrevCommand = new ChannelPrevCommand(tvReceiver);
        channelPrevCommand.excute();
    }

}
