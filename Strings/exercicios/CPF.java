package Strings.exercicios;

import static java.lang.Character.isDigit;
import static java.lang.System.nanoTime;
import static java.util.Arrays.stream;

public class CPF {

    /*

    Problema "cpf"
    Dado o CPF de uma pessoa, o qual pode conter pontos ou traços como separadores, retorne o CPF contendo somente dígitos.

    Exemplo 1:
    Entrada 87409217293
    Saída   87409217293

    Exemplo 2:
    Entrada 874092172-93
    Saída   87409217293

    Exemplo 3:
    Entrada 874.092.172-93
    Saída   87409217293

    Assinatura: public static String removeNonDigits(String str)

     */

    // Method 1: Using StringBuilder
    static String removeNonDigitsUsingStringBuilder(String str) {
        if (str == null) return null;
        if (str.isEmpty()) return "";

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (currentChar == '.' || currentChar == '-') continue;
            result.append(currentChar);
        }
        return result.toString();
    }

    // Method 2: Using String.replace()
    static String removeNonDigitsUsingReplace(String str) {
        if (str == null) return null;
        return str.replace(".", "").replace("-", "");
    }

    // Method 3: Using String.replaceAll() with regex
    static String removeNonDigitsUsingReplaceAll(String str) {
        if (str == null) return null;
        return str.replaceAll("[.-]", "");
    }

    // Method 4: Using Character.isDigit()
    static String removeNonDigitsUsingIsDigit(String str) {
        if (str == null) return null;
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (isDigit(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void testAllMethods(String str) {
        System.out.println("\nTesting string: " + str);
        System.out.println("------------------------------------------------");

        // Arrays to store results
        String[] methods = {"StringBuilder", "String.replace()", "String.replaceAll()", "Character.isDigit()"};
        long[] runtimes = new long[4];
        String[] outputs = new String[4];

        // Test 1: StringBuilder
        long startTime = nanoTime();
        outputs[0] = removeNonDigitsUsingStringBuilder(str);
        runtimes[0] = nanoTime() - startTime;

        // Test 2: String.replace()
        startTime = nanoTime();
        outputs[1] = removeNonDigitsUsingReplace(str);
        runtimes[1] = nanoTime() - startTime;

        // Test 3: String.replaceAll()
        startTime = nanoTime();
        outputs[2] = removeNonDigitsUsingReplaceAll(str);
        runtimes[2] = nanoTime() - startTime;

        // Test 4: Character.isDigit()
        startTime = nanoTime();
        outputs[3] = removeNonDigitsUsingIsDigit(str);
        runtimes[3] = nanoTime() - startTime;

        // Print results in organized format
        System.out.println("Method               Runtime (ns)      Output");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%-20s %-16d %s%n",
                    methods[i],
                    runtimes[i],
                    outputs[i]);
        }
        System.out.println("------------------------------------------------\n");

        // Find and display the fastest method
        int fastestIndex = 0;
        for (int i = 1; i < runtimes.length; i++) {
            if (runtimes[i] < runtimes[fastestIndex]) {
                fastestIndex = i;
            }
        }
        System.out.println("Fastest method: " + methods[fastestIndex]);
        System.out.println("Runtime: " + runtimes[fastestIndex] + " ns\n");
    }


    public static void main(String[] args) {
        String[] testStrings = {
                "123.456-7890",
                "987-65.4321",
                "no-digits-here",
                "phone.number-1234",
                null,
                ""
        };

        System.out.println("=================== RUNTIME TESTS ===================");
        stream(testStrings).forEach(CPF::testAllMethods);
        System.out.println("==================================================");
    }
}
