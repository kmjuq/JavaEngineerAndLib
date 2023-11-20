package extra.spring.eventbus;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/11/3 08:43
 */
public class LogPublisher implements EventPublisher {

    public List<EventListener> els = Lists.newArrayList(new LogListener());

    @Override
    public void publishEvent(EventSource es) {
        els.forEach(el -> el.listenEvent(es));
    }
}
