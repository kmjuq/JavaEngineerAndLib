package both.io.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *
 * </p>
 *
 * @author kemengjian@126.com 2021/10/5 20:10
 */
@Slf4j
public class FileChannelTest {

    @Test
    public void read() throws IOException {
        final RandomAccessFile rw = new RandomAccessFile("/Users/kmj/WorkSpace/git/znwd/Dockerfile", "rw");
        final FileChannel channel = rw.getChannel();

        final ByteBuffer allocate = ByteBuffer.allocate(1024);

        channel.read(allocate);
        allocate.flip();
        log.info(new String(allocate.array()) + "@_@.read");
        channel.close();
    }

    @Test
    public void write() throws IOException {
        final RandomAccessFile rw = new RandomAccessFile("/Users/kmj/WorkSpace/git/znwd/Dockerfile", "rw");
        final FileChannel channel = rw.getChannel();
        String content = "FROM alpine\n" +
                "RUN apk update \\\n" +
                " && apk --no-cache add openjdk11 \\\n" +
                " && jlink --output /opt/app_jre --add-modules java.base,java.logging,java.naming,java.desktop,java.management,jdk.unsupported \\\n" +
                " && apk del openjdk11\n" +
                "ENV JAVA_HOME=/opt/app_jre\n" +
                "ENV PATH=\"$PATH:$JAVA_HOME/bin\"\n" +
                "COPY ./target/znwd-0.0.1.jar app.jar\n" +
                "EXPOSE 8800\n" +
                "ENTRYPOINT [\"java\",\"-jar\",\"app.jar\"]@_@.write";

        final ByteBuffer buffer = ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);
        channel.close();
    }

    @Test
    public void readBigFile() throws IOException {
        final RandomAccessFile rw = new RandomAccessFile("/Users/kmj/OneDrive/kmj/temp/res.json", "rw");
        final FileChannel channel = rw.getChannel();

        final ByteBuffer allocate = ByteBuffer.allocate(1024);

        while (channel.read(allocate) >= 0 || allocate.position() != 0) {
            allocate.flip();
            System.out.println(new String(allocate.array()));
            allocate.clear();
        }
        channel.close();
    }

    @Test
    public void copyFileByByteBuffer() throws IOException {
        final RandomAccessFile r = new RandomAccessFile("/Users/kmj/OneDrive/kmj/temp/res.json", "rw");
        final RandomAccessFile w = new RandomAccessFile("/Users/kmj/OneDrive/kmj/temp/res1.json", "rw");
        final FileChannel readChannel = r.getChannel();
        final FileChannel writeChannel = w.getChannel();

        final ByteBuffer buf = ByteBuffer.allocate(1024);

        while (readChannel.read(buf) >= 0 || buf.position() != 0) {
            buf.flip();
            writeChannel.write(buf);
            buf.compact();
        }
        readChannel.close();
        writeChannel.close();
    }

    @Test
    public void copyFileByTransferTo() throws IOException {
        final RandomAccessFile r = new RandomAccessFile("/Users/kmj/OneDrive/kmj/temp/res.json", "rw");
        final RandomAccessFile w = new RandomAccessFile("/Users/kmj/OneDrive/kmj/temp/res2.json", "rw");
        final FileChannel readChannel = r.getChannel();
        final FileChannel writeChannel = w.getChannel();

        readChannel.transferTo(0, readChannel.size(), writeChannel);
        readChannel.close();
        writeChannel.close();
    }

}
