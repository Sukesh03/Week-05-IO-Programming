import java.lang.reflect.Field;

class Person_one {
    private String name;
    private int age;

    public Person_one(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class GenerateJsonRepresentation {
    public static String toJson(Object obj) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                json.append("\"").append(fields[i].getName()).append("\": ");
                json.append("\"").append(fields[i].get(obj)).append("\"");
                if (i < fields.length - 1) {
                    json.append(", ");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        json.append("}");
        return json.toString();
    }

    public static void main(String[] args) {
        Person_one person = new Person_one("John Doe", 30);
        String json = toJson(person);
        System.out.println(json);
    }
}
