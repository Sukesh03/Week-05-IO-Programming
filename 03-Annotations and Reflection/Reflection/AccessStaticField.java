import java.lang.reflect.Field;

class Configuration {
    private static String API_KEY = "original_key";
}

public class AccessStaticField {
    public static void main(String[] args) {
        try {
            Class<?> cls = Configuration.class;
            Field field = cls.getDeclaredField("API_KEY");
            field.setAccessible(true);

            String original = (String) field.get(null);
            System.out.println("Original API_KEY: " + original);

            field.set(null, "updated_key");
            String updated = (String) field.get(null);
            System.out.println("Updated API_KEY: " + updated);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
