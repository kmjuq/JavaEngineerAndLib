package extra.mysql_binlog_connector;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.EventType;
import com.github.shyiko.mysql.binlog.event.deserialization.ByteArrayEventDataDeserializer;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;


@Slf4j
public class MysqlBinlogConnector {

    @Test
    public void demo1() throws IOException {
        BinaryLogClient client = new BinaryLogClient("localhost", 50600, "root", "123456");
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setEventDataDeserializer(EventType.EXT_DELETE_ROWS,
                new ByteArrayEventDataDeserializer());
        client.setEventDeserializer(eventDeserializer);


        client.registerEventListener((e) -> {
            switch (e.getHeader().getEventType()) {
                case EXT_WRITE_ROWS -> {
                    EventData data = e.getData();
                    log.info("写事件 {}", data);
                }
                case EXT_UPDATE_ROWS -> {
                    EventData data = e.getData();
                    log.info("更新事件 {}", data);
                }
                case EXT_DELETE_ROWS -> {
                    EventData data = e.getData();
                    log.info("删除事件 {}", data);
                }
                default -> {
                }
            }
        });
        client.connect();
    }
}
