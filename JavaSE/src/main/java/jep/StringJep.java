package jep;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StringJep {

    @Test
    public void demo1() {
        log.info(
                "fruit"
                        .transform(str -> str + ": banana")
                        .transform(String::toUpperCase)
        );
    }

    @Test
    public void demo2() {
        log.info(
                """
                        <html>
                            <body>
                                <p>Hello, world</p>
                            </body>
                        </html>
                        """
        );
    }

}
