import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

class BugTracker {

    @BugReport(description = "NullPointerException when input is empty")
    @BugReport(description = "ArrayIndexOutOfBoundsException when index is negative")
    public void processTask() {
        System.out.println("Processing task...");
    }
}

public class RepeatableAnnotationExample {
    public static void main(String[] args) throws Exception {
        BugTracker tracker = new BugTracker();
        Method method = tracker.getClass().getMethod("processTask");

        if (method.isAnnotationPresent(BugReports.class)) {
            BugReports bugReports = method.getAnnotation(BugReports.class);
            for (BugReport bugReport : bugReports.value()) {
                System.out.println("Bug Report: " + bugReport.description());
            }
        }

        tracker.processTask();
    }
}
