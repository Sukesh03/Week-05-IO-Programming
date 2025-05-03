import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DetectDuplicates {
    public static void main(String[] args) {
        String file = "students.csv";
        String line;
        Set<String> uniqueIds = new HashSet<>();
        List<String> duplicateRecords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();  // Skip header row
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];

                if (!uniqueIds.add(id)) {  // If ID already exists, it's a duplicate
                    duplicateRecords.add(line);
                }
            }

            if (!duplicateRecords.isEmpty()) {
                System.out.println("Duplicate records:");
                for (String record : duplicateRecords) {
                    System.out.println(record);
                }
            } else {
                System.out.println("No duplicates found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
