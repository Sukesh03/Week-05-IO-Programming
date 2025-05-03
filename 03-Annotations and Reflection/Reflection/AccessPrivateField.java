import java.lang.reflect.Field;

class Person {
    private int age = 25;
}

public class AccessPrivateField {
    public static void main(String[] args) {
        try {
            Person person = new Person();

            Field ageField = Person.class.getDeclaredField("age");
            ageField.setAccessible(true);

            int currentAge = (int) ageField.get(person);
            System.out.println("Original age: " + currentAge);

            ageField.set(person, 40);
            int updatedAge = (int) ageField.get(person);
            System.out.println("Updated age: " + updatedAge);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
