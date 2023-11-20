package design.pattern.behave.chain.template;

import design.pattern.behave.chain.AskLeaveRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/3/12 17:37
 */
@Slf4j
public class AskLeaveHandlerTemplateTest {

    public static void main(String[] args) {
        AskLeaveRequest askLeaveRequest = new AskLeaveRequest(10);
        final AskLeaveHandlerTemplate handlerChain = new AskLeaveHandlerTemplate() {
            @Override
            boolean business(AskLeaveRequest request) {
                if (request.askLeaveDays() <= 2) {
                    log.info("班主任已批准请假申请");
                    return true;
                } else {
                    log.info("班主任已拒绝请假申请");
                    return false;
                }
            }
        }.next(
                new AskLeaveHandlerTemplate() {
                    @Override
                    boolean business(AskLeaveRequest request) {
                        if (request.askLeaveDays() <= 7) {
                            log.info("系主任已批准请假申请");
                            return true;
                        } else {
                            log.info("系主任已拒绝请假申请");
                            return false;
                        }
                    }
                }
        ).next(
                new AskLeaveHandlerTemplate() {
                    @Override
                    boolean business(AskLeaveRequest request) {
                        if (request.askLeaveDays() <= 10) {
                            log.info("院长已批准请假申请");
                            return true;
                        } else {
                            log.info("院长已拒绝请假申请");
                            return false;
                        }
                    }
                }
        );
        handlerChain.first().handler(askLeaveRequest);
    }

}
