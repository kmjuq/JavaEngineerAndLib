package extra.spring.eventbus;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/3 08:40
 */
@Slf4j
public class LogEvent implements EventSource {

    private final String logContent;

    public LogEvent(String logContent) {
        this.logContent = logContent;
    }

    @Override
    public void print() {
        log.info("LogEvent {}", logContent);
    }
}
