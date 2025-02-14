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

    // First implementation: Recursive with String concatenation
    static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        build1(result, "", n, n);
        return result;
    }

    static void build1(
            List<String> result,
            String current,
            int open,
            int close
    ) {
        if (open == 0 && close == 0) {
            result.add(current);
            return;
        }

        if (open > 0) build1(result, current + "(", open - 1, close);
        if (close > open) build1(result, current + ")", open, close - 1);
    }

    // Second implementation: Recursive with StringBuilder for better memory efficiency
    static List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        build2(result, new StringBuilder(), n, n);
        return result;
    }

    static void build2(List<String> result, StringBuilder current, int open, int close) {
        if (open == 0 && close == 0) {
            result.add(current.toString());
            return;
        }

        if (open > 0) {
            current.append("(");
            build2(result, current, open - 1, close);
            current.setLength(current.length() - 1);
        }

        if (close > open) {
            current.append(")");
            build2(result, current, open, close - 1);
            current.setLength(current.length() - 1);
        }
    }

    static void testParenthesesImplementations(int n) {
        System.out.println("\nTesting implementations for n=" + n);
        // Using List of Lists instead of array
        List<List<String>> results = asList(new ArrayList<>(), new ArrayList<>());
        long[] totalTimes = new long[2];
        final int WARMUP_RUNS = 5;
        final int TEST_RUNS = 10;

        // JVM warmup
        for (int i = 0; i < WARMUP_RUNS; i++) {
            generateParenthesis1(n);
            generateParenthesis2(n);
        }

        // Actual tests
        for (int run = 0; run < TEST_RUNS; run++) {
            System.gc();
            long start = nanoTime();
            results.set(0, generateParenthesis1(n));
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
        System.out.println("generateParenthesis1 result size: " + results.get(0).size());
        System.out.println("First few results: " + results.get(0).subList(0, min(1430, results.get(0).size())));
        System.out.println("generateParenthesis2 result size: " + results.get(1).size());
        System.out.println("First few results: " + results.get(1).subList(0, min(1430, results.get(1).size())));
        System.out.printf("Average time generateParenthesis1: %.2f ns%n", avgTimes[0]);
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

    public static void main(String[] args) {
        // Test cases from constraints: 1 ≤ n ≤ 8
        for (int n = 1; n <= 8; n++) {
            testParenthesesImplementations(n);
        }

        // Original test case
        System.out.println("\nOriginal test case (n=3):");
        System.out.println("Result: " + generateParenthesis1(3));
    }
}
