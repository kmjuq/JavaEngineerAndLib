package extra.spring.eventbus;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/3 08:41
 */
@Slf4j
public class LogListener implements EventListener {
    @Override
    public void listenEvent(EventSource es) {
        log.info("事件监听");
    }
}
