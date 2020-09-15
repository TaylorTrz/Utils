package utils.clipboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author taoruizhe
 * @date 2020/09/11
 */

public class SocketServer {
    private ConcurrentHashMap<String, Socket> socketMap = new ConcurrentHashMap<>();

    private static final SocketServer server = new SocketServer();

    private SocketServer() {}

    public static SocketServer getServer() {
        return server;
    }

    public void listening(String port) throws Exception{
        // 获取本机信息
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(String.format("Server: %s", inetAddress.toString()));

        // start listening
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
        Socket socket = serverSocket.accept();
        socketMap.put("socket", socket);

        // get message
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while(true) {
                try {
                    String message = null;
                    if ((message = in.readLine()) == null) {
                        Thread.sleep(1000);
                    }
                    System.out.println(String.format("from client: %s", message));
                    notifyAll();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        new Thread(() -> {
            while(true) {
                try {
                    String message = "";
                    if ((message = scanner.nextLine()) == null) {
                        Thread.sleep(1000);
                    }
                    out.println(message);
                    notifyAll();
                    socket.close();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

    }


    public void stop() {
        if (!socketMap.isEmpty()) {
            Socket socket = socketMap.get("socket");
            try {
                socket.close();
            } catch (IOException e) {
                e.getStackTrace();
            }

        }
    }


    public static void main(String[] args) throws Exception{
        SocketServer.getServer().listening("8848");
    }

}
