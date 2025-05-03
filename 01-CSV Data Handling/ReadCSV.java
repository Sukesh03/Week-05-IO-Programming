import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
    public static void main(String[] args) {
        String file = "students.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String age = data[2];
                String marks = data[3];
                System.out.printf("ID: %s, Name: %s, Age: %s, Marks: %s%n", id, name, age, marks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
