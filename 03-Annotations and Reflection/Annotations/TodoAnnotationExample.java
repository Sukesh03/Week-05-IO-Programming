import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

class Project {

    @Todo(task = "Implement user authentication", assignedTo = "Alice", priority = "HIGH")
    public void userAuthentication() {
        System.out.println("User authentication feature...");
    }

    @Todo(task = "Create database schema", assignedTo = "Bob")
    public void databaseSchema() {
        System.out.println("Database schema feature...");
    }

    @Todo(task = "Design UI layout", assignedTo = "Charlie", priority = "LOW")
    public void designUILayout() {
        System.out.println("Designing UI layout...");
    }
}

public class TodoAnnotationExample {
    public static void main(String[] args) throws Exception {
        Project project = new Project();
        Method[] methods = project.getClass().getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Todo.class)) {
                Todo todo = method.getAnnotation(Todo.class);
                System.out.println("Task: " + todo.task() + ", Assigned To: " + todo.assignedTo() + ", Priority: " + todo.priority());
            }
        }
    }
}
