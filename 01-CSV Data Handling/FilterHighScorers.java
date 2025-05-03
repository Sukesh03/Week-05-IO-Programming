import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilterHighScorers {
    public static void main(String[] args) {
        String file = "students.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int marks = Integer.parseInt(data[3]);
                if (marks > 80) {
                    System.out.printf("ID: %s, Name: %s, Age: %s, Marks: %s%n",
                            data[0], data[1], data[2], data[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
