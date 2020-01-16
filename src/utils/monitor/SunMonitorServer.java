package utils.monitor;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SunMonitorServer {
    private static final String RESPONSE_MSG = "知道了";

    public static void server() {
        try {
            ServerSocket server = new ServerSocket(8002);
            Socket client  = server.accept();
            PrintStream out = new PrintStream(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String clientInput;
            while (null != (clientInput = in.readLine())) {
                System.out.println("server receiving:" + clientInput);
                out.println(RESPONSE_MSG);
            }

            // jdk8自动关闭连接，心跳包
            client.close();
            server.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(new Thread(() -> server()), 1, 1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
