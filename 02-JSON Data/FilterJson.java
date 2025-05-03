import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class FilterJson {
    public static void main(String[] args) throws Exception {
        String jsonString = "[{\"name\": \"John\", \"age\": 20}, {\"name\": \"Alice\", \"age\": 28}, {\"name\": \"Bob\", \"age\": 30}]";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);

        List<JsonNode> filteredList = new ArrayList<>();
        for (JsonNode node : rootNode) {
            if (node.get("age").asInt() > 25) {
                filteredList.add(node);
            }
        }

        System.out.println(filteredList);
    }
}
