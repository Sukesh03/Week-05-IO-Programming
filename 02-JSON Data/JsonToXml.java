import org.json.JSONObject;
import org.json.XML;
import org.json.JSONTokener;
import java.io.FileInputStream;

public class JsonToXml {
    public static void main(String[] args) throws Exception {

        JSONObject json = new JSONObject(new JSONTokener(new FileInputStream("datanew.json")));

        String xml = XML.toString(json);

        System.out.println(xml);
    }
}
