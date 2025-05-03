import java.io.FileWriter;
import java.io.IOException;

public class WriteCSV {
    public static void main(String[] args) {
        String file = "employees.csv";

        try (FileWriter writer = new FileWriter(file)) {
            writer.append("ID,Name,Department,Salary\n");
            writer.append("101,Alice Johnson,HR,50000\n");
            writer.append("102,Bob Smith,IT,60000\n");
            writer.append("103,Charlie Brown,Finance,55000\n");
            writer.append("104,Diana Prince,Marketing,58000\n");
            writer.append("105,Edward King,Sales,53000\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
