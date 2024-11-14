package design.pattern.behave.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@ToString
public class TV {

    /**
     * 当前频道
     */
    private int currentIndex = 0;

    /**
     * 音量
     */
    private int volume = 10;

}
