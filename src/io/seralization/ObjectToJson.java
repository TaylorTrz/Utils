package io.seralization;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class ObjectToJson {
    @Test
    public void objectToJson() {
        try {
            Employee employee = new Employee();
//            employee.name = "Taylor";
            employee.address = "Backer Street 101A";
            employee.SSN = 12306;
            employee.number = 157;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            employee.birthday = dateFormat.parse("2019/11/14");

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(employee);
            mapper.writeValue(new File("tmp/employee.ser"), employee);
            System.out.println(jsonString);
        } catch (ParseException e) {
            e.getErrorOffset();
        } catch (JsonProcessingException jp) {
            jp.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void jsonToObject() throws ParseException, IOException {
        String jsonString = "{\"name\":\"Taylor\",\"address\":\"Backer Street 101A\",\"number\":157,\"birthday\":1573660800000}";
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(new File("tmp/employee.ser"), Employee.class);
//        Employee employee = mapper.readValue(jsonString, Employee.class);
        System.out.println(employee.birthday);
    }

    @Test
    public void stringArrayJson() throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String[] stringArray = {"a", "b", "b"};
        String jsonString = mapper.writeValueAsString(stringArray);
        System.out.println("serializing to string: " + jsonString);

        String[] newArray = mapper.readValue(jsonString, String[].class);
        System.out.println("serializing back to Object: " + Arrays.toString(newArray));
    }

}
