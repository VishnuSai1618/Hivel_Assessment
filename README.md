# Polynomial Roots Extraction

Simple Java utility that reads a JSON file describing polynomial roots (each with a numeric base and a value), decodes each value to decimal and prints the decoded roots with indices and base information.

## Repository layout
- `PolynomialRootExtractor.java` — main program to parse input and print decoded roots  
- `json-20210307.jar` — org.json library required for parsing (ensure present)  
- `input.json` — sample input file (see below)  
- `.gitignore` — optional to exclude compiled files  
- `README.md` — this document

## Problem summary
- Input JSON contains:
    - `keys`: object containing `n` (number of roots supplied) and `k` (minimum roots required, where k = m + 1 for polynomial degree m)
    - numbered entries (strings) mapping to `{ "base": "<base>", "value": "<value>" }`
- Task: parse the JSON, decode each value from its specified base to decimal, and print each root (with index, base, original value and decimal value). If an index between 1..n is missing, report it as not provided.

## How to compile and run

Place all files in the same folder.

Linux / macOS / WSL:
```
javac -cp ".:json-20210307.jar" PolynomialRootExtractor.java
java  -cp ".:json-20210307.jar" PolynomialRootExtractor
```

Windows (cmd / PowerShell):
```
javac -cp ".;json-20210307.jar" PolynomialRootExtractor.java
java  -cp ".;json-20210307.jar" PolynomialRootExtractor
```

## Sample input (`input.json`)
```json
{
        "keys": { "n": 4, "k": 3 },
        "1": { "base": "10", "value": "4" },
        "2": { "base": "2", "value": "111" },
        "3": { "base": "10", "value": "12" },
        "6": { "base": "4", "value": "213" }
}
```

## Expected output
```
Total Roots Given (n): 4
Minimum Roots Required (k): 3
Root #1 (base 10): 4  -> Decimal: 4
Root #2 (base 2): 111  -> Decimal: 7
Root #3 (base 10): 12  -> Decimal: 12
Root #4 not provided in JSON.
Root #6 (base 4): 213  -> Decimal: 35
```

## Notes
- The program works for any similar JSON; invalid bases or invalid digit characters should be handled or validated in the Java code to avoid NumberFormatException.
- Ensure `json-20210307.jar` is available in the same folder or adjust classpath accordingly.
- `k` equals polynomial degree + 1 (k = m + 1).
- Keep `input.json` filename in the working directory or modify the Java code to load a different filename.