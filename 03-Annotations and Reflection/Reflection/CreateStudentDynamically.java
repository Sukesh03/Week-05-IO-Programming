import java.lang.reflect.Constructor;

class Student {
    public Student() {
        System.out.println("Student object created.");
    }
}

public class CreateStudentDynamically {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("Student");
            Constructor<?> constructor = cls.getDeclaredConstructor();
            Object student = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
