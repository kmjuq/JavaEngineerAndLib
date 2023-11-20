package design.pattern.behave.chain.fun;

import design.pattern.behave.chain.AskLeaveRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AskLeaveHandlerFunTest {

    public static void main(String[] args) {
        AskLeaveRequest askLeaveRequest = new AskLeaveRequest(10);
        final AskLeaveHandlerFun handlerChain =
                new AskLeaveHandlerFun(ask -> {
                    if (ask.askLeaveDays() <= 2) {
                        log.info("班主任已批准请假申请");
                        return true;
                    } else {
                        log.info("班主任已拒绝请假申请");
                        return false;
                    }
                }).last(ask -> {
                    if (ask.askLeaveDays() <= 7) {
                        log.info("系主任已批准请假申请");
                        return true;
                    } else {
                        log.info("系主任已拒绝请假申请");
                        return false;
                    }
                }).last(ask -> {
                    if (ask.askLeaveDays() <= 10) {
                        log.info("院长已批准请假申请");
                        return true;
                    } else {
                        log.info("院长已拒绝请假申请");
                        return false;
                    }
                });
        handlerChain.first().handler(askLeaveRequest);
    }

}
