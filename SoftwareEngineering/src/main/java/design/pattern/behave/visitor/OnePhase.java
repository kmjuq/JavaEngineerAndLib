package design.pattern.behave.visitor;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class OnePhase implements IData {

    private String content;

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
