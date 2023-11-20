package design.pattern.behave.visitor;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class AllData {

    private String onePhase;

    private String twoPhase;

    public List<IData> divide(){
        ArrayList<IData> allData = new ArrayList<>();
        allData.add(new OnePhase().setContent(onePhase));
        allData.add(new TwoPhase().setContent(twoPhase));
        return allData;
    }

    public void accept(IVisitor... visitors){
        divide().forEach(data -> Arrays.stream(visitors).forEach(data::accept));
    }

}
