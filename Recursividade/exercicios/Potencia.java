package Recursividade.exercicios;

import static java.lang.System.nanoTime;

public class Potencia {

    /*

    Problema "potencia"

    Escreva uma função recursiva que calcule a potência de um número dado uma base A e um expoente B,
    ambos inteiros. Lembre-se que qualquer número elevado ao expoente 0 é igual a 1.

    Exemplo 1:
    Entrada
    {
        "a": 5,
        "b": 0
    }
    Saída 1

    Exemplo 2:
    Entrada
    {
        "a": 3,
        "b": 4
    }
    Saída 81


    Java: public static int power(int a, int b)

     */


    public static int power(int a, int b) {
        return b == 0
                ? 1
                : a * power(a, b - 1);
    }

    public static int power2(int a, int b) {
        int result = 1;
        for (int i = 0; i < b; i++)
            result *= a;
        return result;
    }

    static void testPower(int base, int exponent) {
        System.out.println("Input: base = " + base + ", exponent = " + exponent);
        long[] times = new long[2];
        int[] results = new int[2];

        long start = nanoTime();
        results[0] = power(base, exponent);
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = power2(base, exponent);
        times[1] = nanoTime() - start;

        System.out.println("power1 result: " + results[0]);
        System.out.println("power2 result: " + results[1]);

        int fastest = 0, slowest = 0;

        if (times[1] < times[0]) fastest = 1;
        else slowest = 1;

        double ratio = (double) times[slowest] / times[fastest];
        System.out.println("power1 runtime: " + times[0] + " ns");
        System.out.println("power2 runtime: " + times[1] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: power" + (fastest + 1));
        System.out.println();
    }

    static void main(String[] ignoredArgs) {
        // Test cases array with {base, exponent} pairs
        int[][] testCases = {
                {5, 0},    // Base case with exponent 0
                {3, 4},    // Regular case
                {2, 10},   // Larger exponent
                {10, 3},   // Larger base
                {1, 20},   // Base 1 with large exponent
                {7, 7},    // Same base and exponent
                {2, 15},    // Large result case
        };

        // Run tests
        for (int[] testCase : testCases) {
            testPower(testCase[0], testCase[1]);
        }
    }
}
