package socket20191022;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class EchoServer{
    //简单的Socket应用
    public static void main(String[] args){
        try{
            //服务器端需要用ServerSocket类
            ServerSocket serverSocket = new ServerSocket(6066);
            //Block 等待客户端连接
            Socket client = serverSocket.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String userIn;
            while ((userIn = in.readLine()) != null){
                System.out.println("收到客户端消息：" + userIn);
                System.out.println(userIn);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
