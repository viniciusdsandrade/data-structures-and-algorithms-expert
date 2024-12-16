package Strings;

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

    public static String removeNonDigits(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (isDigit(str.charAt(i))) {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    public static String removeNonDigits2(String str) {
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

    public static void main(String[] ignoredArgs) {
        System.out.println(removeNonDigits("87409217293"));
        System.out.println(removeNonDigits("874092172-93"));
        System.out.println(removeNonDigits("874.092.172-93"));

        System.out.println(removeNonDigits2("87409217293"));
        System.out.println(removeNonDigits2("874092172-93"));
        System.out.println(removeNonDigits2("874.092.172-93"));
    }
}
