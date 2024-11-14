package design.pattern.behave.command;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TVReceiver {

    private TV tv;

    public TVReceiver(TV tv) {
        this.tv = tv;
    }

    public void volumnPlus() {
        tv.setVolume(tv.getVolume() + 1);
        log.info("volumnPlus {}", tv);
    }

    public void volumnMinus() {
        tv.setVolume(tv.getVolume() - 1);
        log.info("volumnMinus {}", tv);
    }

    public void nextIndex() {
        tv.setCurrentIndex(tv.getCurrentIndex() + 1);
        log.info("channelNext {}", tv);
    }

    public void prevIndex() {
        tv.setCurrentIndex(tv.getCurrentIndex() - 1);
        log.info("channelPrev {}", tv);
    }
}
