import java.io.*;
import org.json.JSONObject;

public class PolynomialRootExtractor {
    public static void main(String[] args) {
        try {
            // Read JSON file
            String jsonText = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("input.json")));
            JSONObject json = new JSONObject(jsonText);

            JSONObject keys = json.getJSONObject("keys");
            int n = keys.getInt("n");
            int k = keys.getInt("k");

            System.out.println("Total Roots Given (n): " + n);
            System.out.println("Minimum Roots Required (k): " + k);

            // Decode roots and compute product
            long constantC = 1;
            int rootCount = 0;
            StringBuilder decodedList = new StringBuilder();

            for (int i = 1; i <= n; i++) {
                String idx = String.valueOf(i);
                if (json.has(idx)) {
                    JSONObject obj = json.getJSONObject(idx);
                    int base = Integer.parseInt(obj.getString("base"));
                    String valueStr = obj.getString("value");
                    long rootValue = Long.parseLong(valueStr, base);

                    decodedList.append("Root #" + idx + " (base " + base + "): " + valueStr + " -> Decimal: " + rootValue + "\n");

                    constantC *= rootValue;
                    rootCount++;
                } else {
                    decodedList.append("Root #" + idx + " not provided in JSON.\n");
                }
            }

            // Apply sign (-1)^n
            if (rootCount % 2 != 0) {
                constantC = -constantC;
            }

            // Output decoded roots and the constant C
            System.out.println(decodedList.toString());
            System.out.println("Constant value (C) of polynomial equation = " + constantC);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
