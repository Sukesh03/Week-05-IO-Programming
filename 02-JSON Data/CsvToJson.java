import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvToJson {
    public static void main(String[] args) throws IOException {
        String csvFile = "data.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        String[] headers = br.readLine().split(","); // Read header
        JSONArray jsonArray = new JSONArray();

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < headers.length; i++) {
                jsonObject.put(headers[i], values[i]);
            }
            jsonArray.put(jsonObject);
        }

        System.out.println(jsonArray.toString(4));
        br.close();
    }
}
