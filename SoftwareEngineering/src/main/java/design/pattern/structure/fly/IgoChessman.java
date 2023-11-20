package design.pattern.structure.fly;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class IgoChessman {

    private Color color;

    private Location location;

}
