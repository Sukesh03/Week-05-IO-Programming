import com.fasterxml.jackson.databind.ObjectMapper;

class Car {
    public String make;
    public String model;
    public int year;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
}

public class CarToJson {
    public static void main(String[] args) throws Exception {
        Car car = new Car("Tesla", "Model S", 2023);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(car);

        System.out.println(jsonString);
    }
}
