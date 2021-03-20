package databaseCon20190816;

/**
 * 数据库连接
 */

import java.sql.*;
import java.util.*;

public class DatabaseCon {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cps_test?serverTimezone=CST&useSSL=false", "root", "tianyat7");
            System.out.println("连接数据库...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String handle = "SELECT * FROM tb_employee WHERE person like 'taoruizhe'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(handle);

            while (rs.next()) {
                System.out.println("person" + rs.getString("person"));
                System.out.println("grade" + rs.getString("gender"));
                System.out.println("grade" + rs.getInt("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
                System.out.println("数据库关闭...farewell!");
            }
        } catch (SQLException e) {
            System.out.println(e + "\t 数据库关闭");
        }


    }
}
