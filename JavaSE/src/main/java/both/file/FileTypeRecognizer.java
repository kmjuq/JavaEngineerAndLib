package both.file;

import cn.hutool.core.io.FileUtil;
import com.pivovarit.function.ThrowingConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class FileTypeRecognizer {

    @Test
    public void demo1() {
        List<File> loopFiles = FileUtil.loopFiles("/Users/kmj/Downloads");
        loopFiles.forEach(ThrowingConsumer.unchecked(file -> {
            log.info("{},{}", file.getAbsoluteFile(), Files.probeContentType(Path.of(file.getAbsolutePath())));
        }));
    }

    @Test
    public void demo2() {
        Tika tika = new Tika();

        List<File> loopFiles = FileUtil.loopFiles("/Users/kmj/Downloads");
        loopFiles.forEach(
                ThrowingConsumer.unchecked(
                        file ->
                                log.info(
                                        "{}\n{}",
                                        file.getAbsolutePath(),
                                        tika.detect(file.getAbsolutePath())
                                )
                )
        );
    }

    @Test
    public void demo3() {
        List<File> loopFiles = FileUtil.loopFiles("/Users/kmj/Downloads");
        loopFiles.forEach(
                ThrowingConsumer.unchecked(
                        file -> {
                            String contentType = file.toURI().toURL().openConnection().getContentType();
                            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                            log.info("{}", file.getAbsoluteFile());
                            log.info("{}\t{}", contentType, mimeType);
                        }
                )
        );
    }

    @Test
    public void demo4() {

    }

}