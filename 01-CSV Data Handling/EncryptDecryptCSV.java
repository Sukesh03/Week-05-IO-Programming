import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.*;
import java.util.Base64;

class Employee {
    public int id;
    public String name;
    public String email;
    public double salary;

    public Employee(int id, String name, String email, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
    }
}

public class EncryptDecryptCSV {

    private static final String KEY = "1234567890123456"; // 16-byte AES key

    public static void main(String[] args) throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "alice@example.com", 65000),
                new Employee(2, "Bob", "bob@example.com", 72000),
                new Employee(3, "Charlie", "charlie@example.com", 81000)
        );

        String filePath = "encrypted_employees.csv";

        writeEncryptedCSV(employees, filePath);
        readAndDecryptCSV(filePath);
    }

    private static void writeEncryptedCSV(List<Employee> employees, String filePath) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("ID,Name,Email,Salary\n");

        for (Employee emp : employees) {
            String encryptedEmail = encrypt(emp.email);
            String encryptedSalary = encrypt(String.valueOf(emp.salary));
            writer.write(emp.id + "," + emp.name + "," + encryptedEmail + "," + encryptedSalary + "\n");
        }

        writer.close();
        System.out.println("Encrypted CSV written to " + filePath);
    }

    private static void readAndDecryptCSV(String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        boolean isHeader = true;

        System.out.println("\nDecrypted Records:");
        while ((line = reader.readLine()) != null) {
            if (isHeader) {
                isHeader = false;
                continue;
            }

            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String email = decrypt(parts[2]);
            double salary = Double.parseDouble(decrypt(parts[3]));

            System.out.println(id + " | " + name + " | " + email + " | $" + salary);
        }

        reader.close();
    }

    private static String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    private static String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(encryptedText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}
