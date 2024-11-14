package extra.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ByteBufTest {

    @Test
    public void demo1() {
        ByteBuf buffer = Unpooled.buffer();
        String write = "wirte";
        buffer.writeInt(write.length());
        buffer.writeBytes(write.getBytes(StandardCharsets.UTF_8));
        int strLength = buffer.readInt();
        ByteBuf byteBuf = buffer.readBytes(strLength);
        log.info("bytebuf 里面的内容为{}", new String(byteBuf.array()));
    }


}
