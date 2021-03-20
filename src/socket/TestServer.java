package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6066);
            Socket server = serverSocket.accept();
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("client message: " + msg);
                out.println(msg);
            }

            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
