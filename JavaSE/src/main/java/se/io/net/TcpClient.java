package se.io.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class TcpServer {

    public static void main(String[] args) {
        System.out.println("socket tcp服务器端启动....");
        // 等待客户端请求
        try (
                ServerSocket serverSocket = new ServerSocket(2354);
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();
                OutputStream outputStream = accept.getOutputStream()
        ) {
            // 转换成string类型
            byte[] buf = new byte[1024];
            int len = inputStream.read(buf);
            String str = new String(buf, 0, len);
            System.out.printf("server,接收的数据: %s", str);
            outputStream.write("server,发送的数据".getBytes());
        } catch (Exception ignored) {
        }
    }

}

public class TcpClient {
    public static void main(String[] args) {
        System.out.println("socket tcp 客户端启动....");
        try (
                Socket socket = new Socket("127.0.0.1", 2354);
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream()
        ) {
            outputStream.write("client,发送的数据".getBytes());
            // 转换成string类型
            byte[] buf = new byte[1024];
            int len = inputStream.read(buf);
            String str = new String(buf, 0, len);
            System.out.printf("client,接受的数据: %s", str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

