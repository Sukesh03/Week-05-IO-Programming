import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class User {

    @MaxLength(10)
    private String username;

    public User(String username) {
        this.username = username;
        validateMaxLength();
    }

    private void validateMaxLength() {
        try {
            Field field = this.getClass().getDeclaredField("username");
            MaxLength maxLength = field.getAnnotation(MaxLength.class);
            if (maxLength != null && username.length() > maxLength.value()) {
                throw new IllegalArgumentException("Username exceeds maximum length of " + maxLength.value());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }
}

public class MaxLengthAnnotationExample {
    public static void main(String[] args) {
        try {
            User user1 = new User("shortname");
            System.out.println("User 1 created with username: " + user1.getUsername());

            User user2 = new User("thisusernameistoolong");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
