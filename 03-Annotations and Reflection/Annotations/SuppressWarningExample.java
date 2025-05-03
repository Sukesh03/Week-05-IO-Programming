import java.util.ArrayList;
import java.util.List;

public class SuppressWarningExample {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List rawList = new ArrayList();  // Raw use of generic class
        rawList.add("Hello");
        rawList.add(123);

        for (Object item : rawList) {
            System.out.println(item);
        }
    }
}
