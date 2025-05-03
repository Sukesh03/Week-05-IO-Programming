import java.io.*;
import java.util.*;

public class MergeCSV {
    public static void main(String[] args) {
        String file1 = "students1.csv";
        String file2 = "students2.csv";
        String outputFile = "merged_students.csv";

        Map<String, String[]> map1 = new HashMap<>();
        Map<String, String[]> map2 = new HashMap<>();

        try (
                BufferedReader br1 = new BufferedReader(new FileReader(file1));
                BufferedReader br2 = new BufferedReader(new FileReader(file2));
                FileWriter writer = new FileWriter(outputFile)
        ) {
            br1.readLine();
            String line;
            while ((line = br1.readLine()) != null) {
                String[] parts = line.split(",");
                map1.put(parts[0], parts);
            }

            br2.readLine();
            while ((line = br2.readLine()) != null) {
                String[] parts = line.split(",");
                map2.put(parts[0], parts);
            }

            writer.write("ID,Name,Age,Marks,Grade\n");

            for (String id : map1.keySet()) {
                String[] s1 = map1.get(id);
                String[] s2 = map2.get(id);
                if (s2 != null) {
                    writer.write(String.join(",", s1[0], s1[1], s1[2], s2[1], s2[2]) + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
