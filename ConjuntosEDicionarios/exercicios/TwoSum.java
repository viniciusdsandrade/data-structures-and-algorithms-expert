package exercicios;

import java.util.*;

import static java.lang.System.nanoTime;
import static java.util.Arrays.asList;
import static java.util.Arrays.sort;
import static java.util.Comparator.comparingInt;

public class TwoSum {

    /*

    1. Two Sum

    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.
    You can return the answer in any order.

    Example 1:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

    Example 2:
    Input: nums = [3,2,4], target = 6
    Output: [1,2]

    Example 3:
    Input: nums = [3,3], target = 6
    Output: [0,1]

    Constraints:
    2 ≤ nums.length ≤ 10^4
    -10^9 ≤ nums[i] ≤ 10^9
    -10^9 ≤ target ≤ 10^9
    Only one valid answer exists.

    Follow-up: Can you come up with an algorithm that is less than O(n^2) time complexity?

     */

    static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }
        return new int[]{};
    }

    // Implementação com Two Pointers
    static int[] twoSum3(int[] nums, int target) {
        int[][] numWithIndex = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            numWithIndex[i] = new int[]{nums[i], i};
        }

        sort(numWithIndex, comparingInt(a -> a[0]));

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = numWithIndex[left][0] + numWithIndex[right][0];
            if (sum == target) {
                return new int[]{numWithIndex[left][1], numWithIndex[right][1]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{};
    }

    static void testTwoSum(int[] nums, int target) {
        if (nums.length < 100) {
            System.out.println("Array de entrada: " + Arrays.toString(nums));
            System.out.println("Target: " + target);
        } else {
            System.out.println("Array grande com " + nums.length + " elementos");
        }

        long[] times = new long[3];
        int[][] results = new int[3][];

        // Teste das três implementações
        long start = nanoTime();
        results[0] = twoSum(nums.clone(), target);
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = twoSum2(nums.clone(), target);
        times[1] = nanoTime() - start;

        start = nanoTime();
        results[2] = twoSum3(nums.clone(), target);
        times[2] = nanoTime() - start;

        // Exibição dos resultados
        if (nums.length < 100) {
            System.out.println("twoSum1 resultado: " + Arrays.toString(results[0]));
            System.out.println("twoSum2 resultado: " + Arrays.toString(results[1]));
            System.out.println("twoSum3 resultado: " + Arrays.toString(results[2]));
        }

        int fastest = 0, slowest = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] < times[fastest]) fastest = i;
            if (times[i] > times[slowest]) slowest = i;
        }

        // Calcular e exibir estatísticas
        double ratio = (double) times[slowest] / times[fastest];
        System.out.println("twoSum1 (Força Bruta)  runtime: " + times[0] + " ns");
        System.out.println("twoSum2 (HashMap)      runtime: " + times[1] + " ns");
        System.out.println("twoSum3 (Two Pointers) runtime: " + times[2] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: twoSum" + (fastest + 1));
        System.out.println();
    }

    static void main(String[] ignoredArgs) {
        // Casos de teste
        List<TestCase> testCases = asList(
                new TestCase(new int[]{2, 7, 11, 15}, 9),              // Caso básico
                new TestCase(new int[]{3, 2, 4}, 6),                   // Array pequeno
                new TestCase(new int[]{3, 3}, 6),                      // Números iguais
                new TestCase(new int[]{1, 2, 3, 4, 5}, 9),            // Sem solução
                new TestCase(generateLargeArray(1000), 1999),          // Array grande
                new TestCase(generateLargeArray(10000), 19999)         // Array muito grande
        );

        // Executar testes
        for (TestCase testCase : testCases) {
            testTwoSum(testCase.nums, testCase.target);
        }
    }

    // Classe auxiliar para casos de teste
    static class TestCase {
        int[] nums;
        int target;

        TestCase(int[] nums, int target) {
            this.nums = nums;
            this.target = target;
        }
    }

    private static int[] generateLargeArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        // Garantir que existe pelo menos uma solução
        arr[size - 2] = 999;
        arr[size - 1] = 1000;
        return arr;
    }
}
