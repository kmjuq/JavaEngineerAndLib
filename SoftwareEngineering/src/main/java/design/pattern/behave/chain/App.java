package design.pattern.behave.chain;

public class App {

    public static void main(String[] args) {
        AskLeaveHandler handler = new ClassAdviserHandler();
        handler.next(new DepartmentHeadHandler()).next(new DeanHandler());
        AskLeaveRequest askLeaveRequest = new AskLeaveRequest(10);
        handler.handlerChain(askLeaveRequest);
    }

}
