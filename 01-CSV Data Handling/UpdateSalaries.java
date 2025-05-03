import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateSalaries {
    public static void main(String[] args) {
        String inputFile = "employees.csv";
        String outputFile = "updated_employees.csv";
        String line;

        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                FileWriter writer = new FileWriter(outputFile)
        ) {
            String header = br.readLine();
            writer.write(header + "\n");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String department = data[2];
                double salary = Double.parseDouble(data[3]);

                if (department.equalsIgnoreCase("IT")) {
                    salary *= 1.10;
                    data[3] = String.format("%.2f", salary);
                }

                writer.write(String.join(",", data) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
