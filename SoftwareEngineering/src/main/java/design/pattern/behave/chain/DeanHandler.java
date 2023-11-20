package design.pattern.behave.chain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeanHandler extends AskLeaveHandler {

    @Override
    public void handler(AskLeaveRequest request) {
        if (request.askLeaveDays() <= 10) {
            log.info("院长已批准请假申请");
        } else {
            log.info("院长已拒绝请假申请");
        }
    }

}
