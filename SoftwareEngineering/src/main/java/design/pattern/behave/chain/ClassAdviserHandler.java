package design.pattern.behave.chain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassAdviserHandler extends AskLeaveHandler {

    @Override
    public void handler(AskLeaveRequest request) {
        if (request.askLeaveDays() <= 2) {
            log.info("班主任已批准请假申请");
        } else {
            log.info("班主任已拒绝请假申请");
        }
    }

}
