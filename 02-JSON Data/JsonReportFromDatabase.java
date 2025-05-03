import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JsonReportFromDatabase {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/empoloyees";
        String user = "root";
        String password = "";

        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "SELECT id, name, department, salary FROM employees";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();


        JSONArray jsonArray = new JSONArray();

        while (resultSet.next()) {
            JSONObject employee = new JSONObject();
            employee.put("id", resultSet.getInt("id"));
            employee.put("name", resultSet.getString("name"));
            employee.put("department", resultSet.getString("department"));
            employee.put("salary", resultSet.getDouble("salary"));
            jsonArray.put(employee);
        }

        System.out.println(jsonArray.toString(4));

        resultSet.close();
        statement.close();
        connection.close();
    }
}
