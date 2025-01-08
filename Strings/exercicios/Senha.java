package Strings.exercicios;

import java.util.Arrays;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import static java.lang.System.gc;
import static java.lang.System.nanoTime;

public class Senha {

    /*

    Problema "senha"

    Um sistema exige que, no cadastro, a senha do usuário tenha pelo menos 8 caracteres no total, pelo menos uma letra (maiúscula ou minúscula), pelo menos um dígito e pelo menos um caractere especial: @, #, &. Fazer um programa para dizer se uma dada senha é válida ou inválida.
    Exemplo 1:
    Entrada
    Saída
    amerca1@
    VALIDA
    Exemplo 2:
    Entrada
    Saída
    amrca154682
    INVALIDA
    Assinatura: public static boolean validatePassword(String str)

     */

    private static final int WARMUP_RUNS = 5;
    private static final int TEST_RUNS = 10;
    private static final int MIN_LENGTH = 8;
    private static final String SPECIAL_CHARS = "@#&";

    static boolean validatePassWordOriginal(String str) {
        if (str == null || str.isEmpty()) return false;

        if (str.length() < 8) return false;

        boolean hasLetter = false;
        for (int i = 0; i < str.length(); i++) {
            if (isLetter(str.charAt(i))) {
                hasLetter = true;
                break;
            }
        }
        if (!hasLetter) return false;

        boolean hasDigit = false;
        for (int i = 0; i < str.length(); i++) {
            if (isDigit(str.charAt(i))) {
                hasDigit = true;
                break;
            }
        }
        if (!hasDigit) return false;

        boolean hasSpecial = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '@' || c == '#' || c == '&') {
                hasSpecial = true;
                break;
            }
        }

        return hasSpecial;
    }

    // Versão Refatorada
    static boolean validatePassWordRefactored(String password) {
        if (password == null || password.isEmpty()) return false;

        return hasMinimumLength(password) &&
               containsLetter(password) &&
               containsDigit(password) &&
               containsSpecialChar(password);
    }

    private static boolean hasMinimumLength(String password) {
        return password.length() >= MIN_LENGTH;
    }

    private static boolean containsLetter(String password) {
        return password.chars().anyMatch(Character::isLetter);
    }

    private static boolean containsDigit(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }

    private static boolean containsSpecialChar(String password) {
        return password.chars().anyMatch(ch -> SPECIAL_CHARS.indexOf(ch) >= 0);
    }

    static void testPasswordValidators(String[] passwords) {
        System.out.println("Input: " + Arrays.toString(passwords));
        boolean[][] results = new boolean[2][passwords.length];
        long[] totalTimes = new long[2];

        // Warmup
        for (int i = 0; i < WARMUP_RUNS; i++) {
            for (String pwd : passwords) {
                validatePassWordOriginal(pwd);
                validatePassWordRefactored(pwd);
            }
        }

        // Real tests
        for (int run = 0; run < TEST_RUNS; run++) {
            gc();
            long start = nanoTime();
            for (int i = 0; i < passwords.length; i++) {
                results[0][i] = validatePassWordOriginal(passwords[i]);
            }
            totalTimes[0] += nanoTime() - start;

            gc();
            start = nanoTime();
            for (int i = 0; i < passwords.length; i++) {
                results[1][i] = validatePassWordRefactored(passwords[i]);
            }
            totalTimes[1] += nanoTime() - start;
        }

        // Calculate averages
        double[] avgTimes = new double[2];
        avgTimes[0] = totalTimes[0] / (double) TEST_RUNS;
        avgTimes[1] = totalTimes[1] / (double) TEST_RUNS;

        // Determine fastest
        int fastest = avgTimes[1] < avgTimes[0] ? 1 : 0;
        int slowest = fastest == 0 ? 1 : 0;
        double ratio = avgTimes[slowest] / avgTimes[fastest];

        // Print results
        System.out.println("Original implementation results: " + Arrays.toString(results[0]));
        System.out.println("Refactored implementation results: " + Arrays.toString(results[1]));
        System.out.printf("Average time Original: %.2f ns%n", avgTimes[0]);
        System.out.printf("Average time Refactored: %.2f ns%n", avgTimes[1]);
        System.out.printf("Ratio (slow/fast): %.2fx%n", ratio);
        System.out.println("Fastest implementation: " + (fastest == 0 ? "Original" : "Refactored"));
        System.out.println();
    }

    static void main(String[] ignoredArgs) {
        String[][] testCases = {
                {"Abc123@", "Short1@", "NoSpecial1", "OnlyLetters", "12345678@"},         // Basic cases
                {null, "", "A1@", "Ab1@" + "x".repeat(1000)},                       // Edge cases
                {"Ab1@".repeat(100)}                                                // Stress test
        };

        for (String[] testCase : testCases) {
            testPasswordValidators(testCase);
        }
    }
}
