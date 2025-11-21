import java.io.*;
import java.math.BigInteger;
import org.json.JSONObject;

public class PolynomialRootExtractor {
    public static void main(String[] args) {
        try {
            String jsonText = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("input.json")));
            JSONObject json = new JSONObject(jsonText);

            JSONObject keys = json.getJSONObject("keys");
            int n = keys.getInt("n");
            int k = keys.getInt("k");

            System.out.println("Total Roots Given (n): " + n);
            System.out.println("Minimum Roots Required (k): " + k);

            BigInteger constantC = BigInteger.ONE;
            int rootCount = 0;
            StringBuilder decodedList = new StringBuilder();

            for (int i = 1; i <= n; i++) {
                String idx = String.valueOf(i);
                if (json.has(idx)) {
                    JSONObject obj = json.getJSONObject(idx);
                    int base = Integer.parseInt(obj.getString("base"));
                    String valueStr = obj.getString("value");
                    BigInteger rootValue = new BigInteger(valueStr, base);

                    decodedList.append("Root #" + idx + " (base " + base + "): " + valueStr + " -> Decimal: " + rootValue + "\n");

                    constantC = constantC.multiply(rootValue);
                    rootCount++;
                } else {
                    decodedList.append("Root #" + idx + " not provided in JSON.\n");
                }
            }

            // Apply sign (-1)^n
            if (rootCount % 2 != 0) {
                constantC = constantC.negate();
            }

            System.out.println(decodedList.toString());
            System.out.println("Constant value (C) of polynomial equation = " + constantC);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
