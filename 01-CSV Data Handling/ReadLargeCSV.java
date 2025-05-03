import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadLargeCSV {
    public static void main(String[] args) {
        String file = "large_students.csv";
        int chunkSize = 100;
        int totalCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                int count = 0;
                while (line != null && count < chunkSize) {
                    line = br.readLine();
                    if (line != null) {
                        count++;
                        totalCount++;
                    }
                }
                System.out.println("Processed " + count + " records");
            }
            System.out.println("Total records processed: " + totalCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
