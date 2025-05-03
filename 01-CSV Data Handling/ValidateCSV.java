import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class ValidateCSV {
    public static void main(String[] args) {
        String file = "contacts.csv";
        String line;
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String header = br.readLine();
            int lineNumber = 2;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String email = data[2];
                String phone = data[3];
                boolean validEmail = emailPattern.matcher(email).matches();
                boolean validPhone = phonePattern.matcher(phone).matches();

                if (!validEmail || !validPhone) {
                    System.out.print("Invalid record at line " + lineNumber + ": ");
                    System.out.println(String.join(",", data));
                    if (!validEmail) System.out.println("  Error: Invalid email - " + email);
                    if (!validPhone) System.out.println("  Error: Invalid phone - " + phone);
                }

                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}