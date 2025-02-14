package Strings.exercicios;

import java.util.*;

import static java.lang.Math.min;
import static java.lang.System.nanoTime;
import static java.util.Arrays.asList;

public class GenerateParentheses {

    /*

    22. Generate Parentheses

    Given n pairs of parentheses, write a function to generate all
    combinations of well-formed parentheses.

    Example 1:
    Input: n = 3
    Output: ["((()))","(()())","(())()","()(())","()()()"]

    Example 2:
    Input: n = 1
    Output: ["()"]

    Constraints:
    1 ≤ n ≤ 8

     */

    /**
     * Gera todas as combinações possíveis de parênteses bem formados para um dado número n.
     * Uma string de parênteses é considerada bem formada quando:
     * - Possui igual número de parênteses de abertura e fechamento
     * - A cada posição, o número de parênteses de fechamento não excede o de abertura
     * Esta implementação utiliza recursão com concatenação de String.
     *
     * @param n O número de pares de parênteses a serem gerados
     * @return Uma lista contendo todas as combinações válidas de parênteses
     */
    static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        build(result, "", n, n);
        return result;
    }

    /**
     * Função auxiliar recursiva que constrói as combinações de parênteses.
     * Utiliza concatenação de String para formar as combinações, o que pode ser
     * menos eficiente em termos de memória para valores grandes de n.
     *
     * @param result  Lista onde são armazenadas todas as combinações válidas
     * @param current String atual sendo construída na recursão
     * @param open    Número de parênteses de abertura ainda disponíveis
     * @param close   Número de parênteses de fechamento ainda disponíveis
     */
    static void build(
            List<String> result,
            String current,
            int open,
            int close) {
        // Se não há mais parênteses disponíveis, adiciona a combinação atual à lista
        if (open == 0 && close == 0) {
            result.add(current);
            return;
        }
        // Adiciona parêntese de abertura se ainda houver disponível
        if (open > 0) build(result, current + "(", open - 1, close);
        // Adiciona parêntese de fechamento se seu número for maior que o de abertura
        if (close > open) build(result, current + ")", open, close - 1);
    }

    /**
     * Versão otimizada do gerador de parênteses que utiliza StringBuilder.
     * Funciona de maneira idêntica à primeira implementação, mas com melhor
     * eficiência de memória por evitar a criação de múltiplas Strings intermediárias.
     *
     * @param n O número de pares de parênteses a serem gerados
     * @return Uma lista contendo todas as combinações válidas de parênteses
     */
    static List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        build2(result, new StringBuilder(), n, n);
        return result;
    }

    /**
     * Função auxiliar recursiva que constrói as combinações de parênteses usando StringBuilder.
     * Utiliza a técnica de backtracking para modificar e restaurar o StringBuilder,
     * economizando memória ao reutilizar o mesmo objeto.
     *
     * @param result  Lista onde são armazenadas todas as combinações válidas
     * @param current StringBuilder atual sendo modificado na recursão
     * @param open    Número de parênteses de abertura ainda disponíveis
     * @param close   Número de parênteses de fechamento ainda disponíveis
     */
    static void build2(
            List<String> result,
            StringBuilder current,
            int open,
            int close) {
        // Se não há mais parênteses disponíveis, adiciona a combinação atual à lista
        if (open == 0 && close == 0) {
            result.add(current.toString());
            return;
        }

        // Adiciona parêntese de abertura se ainda houver disponível
        if (open > 0) {
            current.append("(");
            build2(result, current, open - 1, close);
            current.setLength(current.length() - 1); // Backtracking
        }

        // Adiciona parêntese de fechamento se seu número for maior que o de abertura
        if (close > open) {
            current.append(")");
            build2(result, current, open, close - 1);
            current.setLength(current.length() - 1); // Backtracking
        }
    }

    static void testParenthesesImplementations(int n) {
        System.out.println("\nTesting implementations for n=" + n);
        // Using List of Lists instead of an array
        List<List<String>> results = asList(new ArrayList<>(), new ArrayList<>());
        long[] totalTimes = new long[2];
        final int WARMUP_RUNS = 5;
        final int TEST_RUNS = 10;

        // JVM warmup
        for (int i = 0; i < WARMUP_RUNS; i++) {
            generateParenthesis(n);
            generateParenthesis2(n);
        }

        // Actual tests
        for (int run = 0; run < TEST_RUNS; run++) {
            System.gc();
            long start = nanoTime();
            results.set(0, generateParenthesis(n));
            totalTimes[0] += nanoTime() - start;

            System.gc();
            start = nanoTime();
            results.set(1, generateParenthesis2(n));
            totalTimes[1] += nanoTime() - start;
        }

        // Calculate averages
        double[] avgTimes = new double[2];
        avgTimes[0] = totalTimes[0] / (double) TEST_RUNS;
        avgTimes[1] = totalTimes[1] / (double) TEST_RUNS;

        // Determine fastest
        int fastest = avgTimes[1] < avgTimes[0] ? 1 : 0;
        int slowest = 1 - fastest;

        // Calculate ratio
        double ratio = avgTimes[slowest] / avgTimes[fastest];

        // Display results
        System.out.println("generateParenthesis result size: " + results.get(0).size());
        System.out.println("First few results: " + results.get(0).subList(0, min(1430, results.get(0).size())));
        System.out.println("generateParenthesis2 result size: " + results.get(1).size());
        System.out.println("First few results: " + results.get(1).subList(0, min(1430, results.get(1).size())));
        System.out.printf("Average time generateParenthesis: %.2f ns%n", avgTimes[0]);
        System.out.printf("Average time generateParenthesis2: %.2f ns%n", avgTimes[1]);
        System.out.printf("Ratio (slow/fast): %.2fx%n", ratio);
        System.out.println("Fastest method: generateParenthesis" + (fastest + 1));

        // Verify results match
        if (!results.get(0).equals(results.get(1))) {
            System.out.println("WARNING: Results don't match!");
            Set<String> set1 = new HashSet<>(results.get(0));
            Set<String> set2 = new HashSet<>(results.get(1));
            System.out.println("Unique to implementation 1: " +
                               new ArrayList<>(new HashSet<String>() {{
                                   addAll(set1);
                                   removeAll(set2);
                               }}));
            System.out.println("Unique to implementation 2: " +
                               new ArrayList<>(new HashSet<String>() {{
                                   addAll(set2);
                                   removeAll(set1);
                               }}));
        }
    }

    static void main(String[] ignoredArgs) {
        // Test cases from constraints: 1 ≤ n ≤ 8
        for (int n = 1; n <= 8; n++) {
            testParenthesesImplementations(n);
        }

        // Original test case
        System.out.println("\nOriginal test case (n=3):");
        System.out.println("Result: " + generateParenthesis(3));
    }
}
