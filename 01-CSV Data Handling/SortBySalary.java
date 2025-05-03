import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortBySalary {
    public static void main(String[] args) {
        String file = "employees.csv";
        List<String[]> records = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String header = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                records.add(data);
            }

            records.sort((a, b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])));

            System.out.println(header);
            for (int i = 0; i < Math.min(5, records.size()); i++) {
                String[] data = records.get(i);
                System.out.println(String.join(",", data));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
