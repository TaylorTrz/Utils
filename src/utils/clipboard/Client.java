package utils.clipboard;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author taoruizhe
 * @date 2020/09/11
 */
public class Client {
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public void listening(String address, String port) throws Exception {
        // 获取本机信息
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(String.format("Client: %s", inetAddress.toString()));

        // start listening
        Socket socket = new Socket(address, Integer.parseInt(port));
        this.socket = socket;

        // get message
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


    private void write() throws IOException {
        System.out.println("Welcome to Taylor clipboard!\r\n" + "Copy your text to remote host now...");
        String message = null;
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        if (!"".equals(message = scanner.readLine())) {
            out.println(message);
        }
        socket.close();
    }


    private void read() throws IOException {
        System.out.println("Welcome to Taylor clipboard!\r\n" + "Print text to stdout...");
        String message = null;
        // System.setIn(socket.getInputStream());
        if ((message = in.readLine()) != null) {
            System.out.println(message);
        }
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();

        if (args.length != 3) {
            throw new RuntimeException("args num not satisfied");
        }
        String address = args[0];
        String port = args[1];
        client.listening(address, port);

        switch (args[2]) {
            case "-w": client.write(); break;
            case "-r": client.read(); break;
            default: break;
        }
        // System.out.println(System.getProperty("name", "port"));

    }

}
