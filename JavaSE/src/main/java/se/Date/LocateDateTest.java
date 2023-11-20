package se.Date;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;

public class LocateDateTest {

    /**
     * 当前日期格式化
     */
    @Test
    public void LocateDateDemo1() {
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        String mm = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        System.out.println(mm.compareTo("12"));
        String[] test = {"04", "03", "09", "07", "12"};
        Arrays.sort(test, String::compareTo);
        System.out.println(Arrays.toString(test));
        String halfYear = "";
        if ("07".compareTo(mm) > 0) {
            halfYear = "FIRST_HALF";
        } else {
            halfYear = "SECOND_HALF";
        }
        System.out.println(halfYear);
    }

    @Test
    public void demo2() {
        System.out.println(new Date().getTime());
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime past = LocalDateTime.ofInstant(Instant.ofEpochMilli(1605422055922L), ZoneId.systemDefault());
        final LocalDateTime pastPlus30Seconds = past.plus(30, ChronoUnit.SECONDS);
        if (now.isAfter(pastPlus30Seconds)) {
            System.out.println("超时30秒");
        }
    }

}
