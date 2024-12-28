package Strings.exercicios;

import static java.lang.Character.isDigit;

public class CPF {
    /*

    Problema "cpf"
    Dado o CPF de uma pessoa, o qual pode conter pontos ou traços como separadores, retorne o CPF contendo somente dígitos.

    Exemplo 1:
    Entrada 87409217293
    Saída 87409217293

    Exemplo 2:
    Entrada 874092172-93
    Saída 87409217293

    Exemplo 3:
    Entrada 874.092.172-93
    Saída 87409217293

    Assinaturas:
    Java: public static String removeNonDigits(String str)
     */

    // Method 1: Using StringBuilder
    public static String removeNonDigits2(String str) {
        if (str == null) return null;
        if (str.isEmpty()) return "";

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (currentChar == '.' || currentChar == '-') {
                continue;
            }
            result.append(currentChar);
        }
        return result.toString();
    }

    // Method 2: Using String.replace()
    public static String removeNonDigitsUsingReplace(String str) {
        if (str == null) return null;
        return str.replace(".", "").replace("-", "");
    }

    // Method 3: Using String.replaceAll() with regex
    public static String removeNonDigitsUsingReplaceAll(String str) {
        if (str == null) return null;
        return str.replaceAll("[.-]", "");
    }

    // Method 4: Using Character.isDigit()
    public static String removeNonDigitsUsingIsDigit(String str) {
        if (str == null) return null;
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (isDigit(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    // Main method for testing
    public static void main(String[] ignoredArgs) {
        String[] testStrings = {
                "123.456-7890",
                "987-65.4321",
                "no-digits-here",
                "phone.number-1234",
                null,
                ""
        };

        for (String test : testStrings) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Original:            " + test);
            System.out.println("Using StringBuilder: " + removeNonDigits2(test));
            System.out.println("Using replace():     " + removeNonDigitsUsingReplace(test));
            System.out.println("Using replaceAll():  " + removeNonDigitsUsingReplaceAll(test));
            System.out.println("Using isDigit():     " + removeNonDigitsUsingIsDigit(test));
            System.out.println("-----------------------------------------------------------------");
        }
    }
}
