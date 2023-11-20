package design.pattern.structure.proxy;

public class App {

    public static void main(String[] args) {
        FrontDeskProxy frontDeskProxy = new FrontDeskProxy(new FrontDesk());
        frontDeskProxy.checkTicket();
        frontDeskProxy.checkTicket();
        frontDeskProxy.checkTicket();
        frontDeskProxy.checkTicket();
        frontDeskProxy.checkTicket();
        frontDeskProxy.ticketNumShow();
    }

}
