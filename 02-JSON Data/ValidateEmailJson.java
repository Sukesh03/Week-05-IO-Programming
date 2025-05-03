import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileInputStream;

public class ValidateEmailJson {
    public static void main(String[] args) throws Exception {
        JSONObject json = new JSONObject(new JSONTokener(new FileInputStream("user.json")));
        JSONObject schemaJson = new JSONObject(new JSONTokener(new FileInputStream("schema.json")));

        Schema schema = SchemaLoader.load(schemaJson);
        schema.validate(json);

        System.out.println("Valid email.");
    }
}
