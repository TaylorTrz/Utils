package socket20191022;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) {
        try {
            String hosts = InetAddress.getLocalHost().getHostAddress();
            InetAddress baidu = InetAddress.getByName("www.baidu.com");
            String ip = baidu.toString().split("/")[1];
            InetAddress address = InetAddress.getByName(ip);
            System.out.println(baidu.toString());
            System.out.println(ip);
            System.out.println(address.getHostName());
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String hostname = InetAddress.getLocalHost().getHostAddress();
            int port = 6066;
            Scanner inputMsg = new Scanner(System.in);

            Socket client = new Socket(hostname, port);
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String msg;
            System.out.println("输入...");
            while(!(msg = inputMsg.nextLine()).equals("exit")) {
                out.println(msg);
                System.out.println("收到服务端响应： " + in.readLine());
            }
            client.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
