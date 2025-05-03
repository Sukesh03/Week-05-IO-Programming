import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

class Student {
    public String name;
    public int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class ListToJsonArray {
    public static void main(String[] args) throws Exception {
        List<Student> students = Arrays.asList(
                new Student("John", 20),
                new Student("Alice", 22),
                new Student("Bob", 24)
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(students);

        System.out.println(jsonString);
    }
}
