import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;

public class MergeJsonFiles {
    public static void main(String[] args) throws Exception {

        JSONObject json1 = new JSONObject(new JSONTokener(new FileInputStream("file1.json")));

        JSONObject json2 = new JSONObject(new JSONTokener(new FileInputStream("file2.json")));

        JSONObject mergedJson = new JSONObject();
        mergedJson.put("file1", json1);
        mergedJson.put("file2", json2);

        System.out.println(mergedJson.toString(4));
    }
}
