import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
    String value();
}

class User {

    private String role;

    public User(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

class DocumentManager {

    @RoleAllowed("ADMIN")
    public void deleteDocument() {
        System.out.println("Document deleted successfully.");
    }

    public void viewDocument() {
        System.out.println("Document viewed.");
    }
}

public class RoleBasedAccessControlExample {
    public static void main(String[] args) throws Exception {
        User adminUser = new User("ADMIN");
        User normalUser = new User("USER");

        DocumentManager docManager = new DocumentManager();

        // Admin user tries to access
        checkAccess(docManager, "deleteDocument", adminUser);

        // Non-admin user tries to access
        checkAccess(docManager, "deleteDocument", normalUser);
    }

    public static void checkAccess(DocumentManager docManager, String methodName, User user) throws Exception {
        Method method = docManager.getClass().getMethod(methodName);

        if (method.isAnnotationPresent(RoleAllowed.class)) {
            RoleAllowed roleAllowed = method.getAnnotation(RoleAllowed.class);
            if (!roleAllowed.value().equals(user.getRole())) {
                System.out.println("Access Denied!");
                return;
            }
        }
        method.invoke(docManager);
    }
}
