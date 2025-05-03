import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

class Student {
    public int id;
    public String name;
    public int age;
    public double marks;

    public Student() {}

    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public String toString() {
        return id + "," + name + "," + age + "," + marks;
    }
}

public class ConvertJsonCsv {

    public static void main(String[] args) {
        String jsonFile = "students.json";
        String csvFile = "students.csv";
        String newJsonFile = "students_from_csv.json";

        try {
            ObjectMapper mapper = new ObjectMapper();

            Student[] students = mapper.readValue(new File(jsonFile), Student[].class);
            FileWriter writer = new FileWriter(csvFile);
            writer.write("ID,Name,Age,Marks\n");
            for (Student s : students) {
                writer.write(s.id + "," + s.name + "," + s.age + "," + s.marks + "\n");
            }
            writer.close();
            System.out.println("CSV file written successfully.");

            List<Student> studentList = new ArrayList<>();
            try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                String[] nextLine;
                reader.readNext();
                while ((nextLine = reader.readNext()) != null) {
                    int id = Integer.parseInt(nextLine[0]);
                    String name = nextLine[1];
                    int age = Integer.parseInt(nextLine[2]);
                    double marks = Double.parseDouble(nextLine[3]);
                    studentList.add(new Student(id, name, age, marks));
                }
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(newJsonFile), studentList);
            System.out.println("JSON file written successfully from CSV.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
