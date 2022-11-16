import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        String file = "new_data.json";

        String json = readString(file);
        System.out.printf("%s \n", json);

        List<Employee> list = jsonToList(json);
        list.forEach(System.out::println);
    }

    // getting json from a file
    protected static String readString(String file) {
        StringBuilder buff = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = reader.readLine()) != null) {
                buff.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toString().replaceAll("\\s", "");
    }

    // converting a string to a list of employees
    protected static List<Employee> jsonToList(String json) throws ParseException {
        List<Employee> employees = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONArray list = (JSONArray) parser.parse(json);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        for (Object str : list) {
            Employee employee = gson.fromJson(str.toString(), Employee.class);
            employees.add(employee);
        }
        return employees;
    }
}
