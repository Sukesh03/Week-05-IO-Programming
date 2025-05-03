import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MergeJsonObjects {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String json1 = "{\"name\": \"John\", \"age\": 20}";
        String json2 = "{\"email\": \"john@example.com\", \"city\": \"New York\"}";

        JsonNode jsonNode1 = objectMapper.readTree(json1);
        JsonNode jsonNode2 = objectMapper.readTree(json2);

        JsonNode merged = jsonNode1.deepCopy();
        ((ObjectNode) merged).setAll((ObjectNode) jsonNode2);

        System.out.println(merged.toString());
    }
}
