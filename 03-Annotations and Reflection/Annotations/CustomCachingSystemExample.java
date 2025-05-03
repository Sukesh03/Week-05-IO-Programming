import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {}

class ExpensiveComputation {

    private Map<String, Object> cache = new HashMap<>();

    @CacheResult
    public int compute(int x) {
        String key = "compute_" + x;
        if (cache.containsKey(key)) {
            System.out.println("Returning cached result for " + x);
            return (int) cache.get(key);
        }

        System.out.println("Computing result for " + x);
        int result = x * x;  // Simulating an expensive computation (e.g., squaring the number)
        cache.put(key, result);
        return result;
    }
}

public class CustomCachingSystemExample {
    public static void main(String[] args) throws Exception {
        ExpensiveComputation computation = new ExpensiveComputation();

        // First call: will compute and cache result
        System.out.println("Result: " + computation.compute(5));

        // Second call: will return cached result
        System.out.println("Result: " + computation.compute(5));

        // Call with different input: will compute and cache result
        System.out.println("Result: " + computation.compute(10));

        // Call with the same input: will return cached result
        System.out.println("Result: " + computation.compute(10));
    }
}
