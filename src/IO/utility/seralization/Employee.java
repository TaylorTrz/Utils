package IO.utility.seralization;

import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    public String name;
    public String address;
    public transient int SSN;
    public int number;
    public Date birthday;
    public void mailCheck() {
        System.out.println("Mailing a check to" + name + " " + address);
    }

}

