import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {
}

class ServiceA {
    public void performAction() {
        System.out.println("ServiceA action performed.");
    }
}

class ServiceB {
    @Inject
    private ServiceA serviceA;

    public void execute() {
        serviceA.performAction();
        System.out.println("ServiceB execution completed.");
    }
}

class DIContainer {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    public void register(Class<?> clazz) throws Exception {
        Object instance = clazz.getDeclaredConstructor().newInstance();
        instances.put(clazz, instance);
    }

    public void inject() throws IllegalAccessException {
        for (Object instance : instances.values()) {
            for (Field field : instance.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    Object dependency = instances.get(field.getType());
                    field.set(instance, dependency);
                }
            }
        }
    }

    public <T> T get(Class<T> clazz) {
        return clazz.cast(instances.get(clazz));
    }
}

public class DependencyInjection {
    public static void main(String[] args) throws Exception {
        DIContainer container = new DIContainer();

        container.register(ServiceA.class);
        container.register(ServiceB.class);

        container.inject();

        ServiceB serviceB = container.get(ServiceB.class);
        serviceB.execute();
    }
}
