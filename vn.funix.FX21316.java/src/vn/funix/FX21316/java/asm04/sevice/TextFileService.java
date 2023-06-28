package vn.funix.FX21316.java.asm04.sevice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";
    public static List<List<String>> readFile(String fileName) {
        List<List<String>> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] customerData = line.split(COMMA_DELIMITER);
                List<String> customerAttributes = new ArrayList<>();
                for (String attribute : customerData) {
                    customerAttributes.add(attribute.trim());
                }
                customers.add(customerAttributes);
            }
        } catch (IOException e) {
            System.out.println("Loi doc file hoac duong dan file khong dung");
        }

        return customers;
    }

}
