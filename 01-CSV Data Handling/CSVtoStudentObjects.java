import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Student {
    private int id;
    private String name;
    private int age;
    private int marks;

    public Student(int id, String name, int age, int marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Marks: " + marks;
    }
}

public class CSVtoStudentObjects {
    public static void main(String[] args) {
        String file = "students.csv";
        List<Student> students = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                int marks = Integer.parseInt(data[3]);
                students.add(new Student(id, name, age, marks));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }
}
