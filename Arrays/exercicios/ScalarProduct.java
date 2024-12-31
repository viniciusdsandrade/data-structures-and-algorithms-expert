package Arrays.exercicios;

import static java.lang.System.nanoTime;

import java.util.Arrays;

public class ScalarProduct {

    /*

    Produto escalar de dois vetores (dot-product-two-arrays) (Adaptado Leetcode)
    Dados dois arrays, calcule o seu produto escalar.

    Exemplo 1:
    entrada: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
    saída: 8
    Explicação: O produto escalar dos arrays acima pode ser encontrado pela expressão: (1 * 0) + (0 * 3) + (0 * 0) + (2 * 4) + (3 * 0) = 8

    Exemplo 2:
    entrada: nums1 = [0,1,0,0,0], nums2 = [0,1,0,0,0]
    saída: 0

    Exemplo 3:
    entrada: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
    saída: 0

     */

    // Original implementation
    static int scalarProduct(int[] nums1, int[] nums2) {
        int product = 0;
        for (int i = 0; i < nums1.length; i++) {
            product += nums1[i] * nums2[i];
        }
        return product;
    }

    // Alternative implementation using while loop
    static int scalarProduct2(int[] nums1, int[] nums2) {
        int product = 0;
        int i = 0;
        while (i < nums1.length) {
            product += nums1[i] * nums2[i];
            i++;
        }
        return product;
    }

    static void testScalarProduct(int[] nums1, int[] nums2) {
        if (nums1.length < 100) {
            System.out.println("Input 1: " + Arrays.toString(nums1));
            System.out.println("Input 2: " + Arrays.toString(nums2));
        }

        long[] times = new long[2];
        int[] results = new int[2];

        long start = nanoTime();
        results[0] = scalarProduct(nums1, nums2);
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = scalarProduct2(nums1, nums2);
        times[1] = nanoTime() - start;

        int fastest = 0, slowest = 0;
        if (times[1] < times[0]) fastest = 1;
        else slowest = 1;

        double ratio = (double) times[slowest] / times[fastest];

        System.out.println("scalarProduct1 result: " + results[0] + " runtime: " + times[0] + " ns");
        System.out.println("scalarProduct2 result: " + results[1] + " runtime: " + times[1] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: scalarProduct" + (fastest + 1));
        System.out.println();
    }

    public static void main(String[] ignoredArgs) {
        int[][] testCases1 = {
                {1, 2, 3},
                {1, 0, 1},
                {1, 1, 1},
                {0, 0, 0},
                new int[1000000]
        };

        int[][] testCases2 = {
                {4, 5, 6},
                {1, 1, 1},
                {2, 2, 2},
                {1, 2, 3},
                new int[1000000]
        };

        for (int i = 0; i < testCases1[4].length; i++) {
            testCases1[4][i] = i % 3;  // Some variation
            testCases2[4][i] = (i * 2) % 5; // Different variation
        }

        for (int i = 0; i < testCases1.length; i++) {
            testScalarProduct(testCases1[i], testCases2[i]);
        }
    }
}
