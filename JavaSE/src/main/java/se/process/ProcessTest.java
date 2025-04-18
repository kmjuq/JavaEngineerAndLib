package se.process;

import org.junit.jupiter.api.Test;

import java.io.*;


public class ProcessTest {

    @Test
    public void demo1() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("sh","/Users/kmj/Temp/shell/test.sh");
        Process start = processBuilder.start();
        InputStream errorStream = start.getErrorStream();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

        // 打印错误流内容
        String line;
        while ((line = errorReader.readLine()) != null) {
            System.err.println("ERROR: " + line);
        }

        int exitCode = start.waitFor();
        System.out.println("Process exited with code: " + exitCode);
    }

}
