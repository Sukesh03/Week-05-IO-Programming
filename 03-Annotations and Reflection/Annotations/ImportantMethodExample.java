import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH";
}

class TaskManager1 {

    @ImportantMethod(level = "HIGH")
    public void criticalTask() {
        System.out.println("Executing critical task...");
    }

    @ImportantMethod(level = "MEDIUM")
    public void routineTask() {
        System.out.println("Executing routine task...");
    }

    public void normalTask() {
        System.out.println("Executing normal task...");
    }
}

public class ImportantMethodExample {
    public static void main(String[] args) throws Exception {
        TaskManager1 manager = new TaskManager1();
        Method[] methods = manager.getClass().getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod importantMethod = method.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + method.getName() + ", Importance Level: " + importantMethod.level());
            }
        }
    }
}
