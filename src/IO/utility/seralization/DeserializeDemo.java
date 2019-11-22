package IO.utility.seralization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeDemo {
    public static void main(String[] args) {
        Employee employee = null;
        try {
            FileInputStream fileIn = new FileInputStream("D://ideaProject/FilePractise/tmp/employee.ser");
            ObjectInputStream in  = new ObjectInputStream(fileIn);
            employee = (Employee)in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException io) {
            io.printStackTrace();
            return;
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            return;
        }
        System.out.println("Deserialized Employee...");
        System.out.println("disk address: " + employee);
        System.out.println("Name: " + employee.name);
        System.out.println("Address: " + employee.address);
        System.out.println("SSN: " + employee.SSN);
        System.out.println("Number: " + employee.number);
    }
}
