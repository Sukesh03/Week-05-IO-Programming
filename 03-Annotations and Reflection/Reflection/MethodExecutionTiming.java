import java.lang.reflect.Method;

class ExampleClass {
    public void method1() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Method 1 executed");
    }

    public void method2() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Method 2 executed");
    }
}

public class MethodExecutionTiming {
    public static void main(String[] args) {
        try {
            ExampleClass example = new ExampleClass();
            Class<?> cls = example.getClass();

            for (Method method : cls.getDeclaredMethods()) {
                long startTime = System.nanoTime();
                method.setAccessible(true);
                method.invoke(example);
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                System.out.println("Execution time of " + method.getName() + ": " + duration + " nanoseconds");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
