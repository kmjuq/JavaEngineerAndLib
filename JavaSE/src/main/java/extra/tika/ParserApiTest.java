package extra.tika;

import cn.hutool.core.io.FileUtil;
import com.pivovarit.function.ThrowingConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;

import java.io.File;
import java.util.List;

@Slf4j
public class ParserApiTest {
    public static void main(String[] args) {
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
}
