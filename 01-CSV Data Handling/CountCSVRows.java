import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountCSVRows {
    public static void main(String[] args) {
        String file = "employees.csv";
        int count = 0;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    count++;
                }
            }
            System.out.println("Number of records: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
