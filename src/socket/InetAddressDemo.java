package socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            InetAddress shiyanlou = InetAddress.getByName("www.shiyanlou.com");
            //输出主机名与IP地址
            System.out.println(shiyanlou.toString());
            //获取ip地址
            String ip = shiyanlou.toString().split("/")[1];
            //根据ip地址获取主机名
            InetAddress byAddress = InetAddress.getByName(ip);
            System.out.println("get hostname by IP address:" + byAddress.getHostName());
            System.out.println("localhost:" + InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
