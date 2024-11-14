package se.io.net;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2019-09-19 09:48
 */
public class FileTest {

    @Test
    public void demo1() {
        System.out.println(System.getProperty("java.io.tmpdir"));
    }

    @Test
    public void demo2() {
        listFiles(new File("/Users/kmj/Software/startup"));
    }

    private static void listFiles(File dir) {
        File[] fileArray = dir.listFiles();
        //遍历
        if (fileArray != null) {
            for (File f : fileArray) {
                if (f.isFile()) {
                    System.out.println(f.getAbsolutePath());
                } else {
                    listFiles(f);
                }
            }
        }
    }

}
