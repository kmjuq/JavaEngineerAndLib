package design.pattern.behave.chain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public class AskLeaveRequest {

    private int askLeaveDays;

    public AskLeaveRequest(int askLeaveDays) {
        this.askLeaveDays = askLeaveDays;
    }
}
