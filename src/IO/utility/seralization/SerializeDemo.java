package IO.utility.seralization;

import java.io.*;

public class SerializeDemo {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.name = "Taylor";
        employee.address = "Backer Street 101A";
        employee.SSN = 12306;
        employee.number = 157;
        try {
            File file = new File("D://ideaProject/FilePractise/tmp/employee.ser");
            if (file.createNewFile()) {
                System.out.println("Serializing in /tmp/employee.ser ...");
            }
            FileOutputStream fileOut = new FileOutputStream("D://ideaProject/FilePractise/tmp/employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(employee);
            out.close();
            fileOut.close();
            System.out.println("Data has been serialized!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
