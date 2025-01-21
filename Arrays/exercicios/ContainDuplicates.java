package Arrays.exercicios;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.System.nanoTime;
import static java.util.Arrays.sort;

public class ContainDuplicates {

    /*

    217. Contains Duplicate

    Given an integer array nums, return true if any value appears at least
    twice in the array, and return false if every element is distinct.

    Example 1:
    Input: nums = [1,2,3,1]
    Output: true
    Explanation:

    The element 1 occurs at the indices 0 and 3.

    Example 2:
    Input: nums = [1,2,3,4]
    Output: false
    Explanation: All elements are distinct.

    Example 3:
    Input: nums = [1,1,1,3,3,4,3,2,4,2]
    Output: true


    Constraints:
    1 ≤ nums.length ≤ 10^5
    -10^9 ≤ nums[i] ≤ 10^9

     */

    // Versão 1: Força bruta (O(n^2))
    static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) return false;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (num == nums[j])
                    return true;
            }
        }
        return false;
    }

    // Versão 2: Utilizando HashSet (O(n))
    static boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length < 2) return false;

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }

    // Versão 3: Ordenando o array primeiro (O(n log n))
    static boolean containsDuplicate3(int[] nums) {
        if (nums == null || nums.length < 2) return false;

        // Ordena o array
        sort(nums);

        // Verifica elementos adjacentes para duplicatas
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }

    static void testContainsDuplicate(int[] nums) {
        System.out.println("Test case: " + arrayToString(nums));

        // Inicializa arrays para armazenar tempos e resultados para as 3 versões
        long[] times = new long[3];
        boolean[] results = new boolean[3];

        // Definição das complexidades temporais correspondentes a cada método
        String[] complexities = {
                "O(n^2)",      // containsDuplicate
                "O(n)",        // containsDuplicate2
                "O(n log n)"   // containsDuplicate3
        };

        // Testando containsDuplicate (Versão 1)
        long start = nanoTime();
        results[0] = containsDuplicate(nums);
        times[0] = nanoTime() - start;

        // Testando containsDuplicate2 (Versão 2)
        start = nanoTime();
        results[1] = containsDuplicate2(nums);
        times[1] = nanoTime() - start;

        // Testando containsDuplicate3 (Versão 3)
        start = nanoTime();
        results[2] = containsDuplicate3(nums);
        times[2] = nanoTime() - start;

        // Verificando se todos os métodos retornaram o mesmo resultado
        boolean allEqual = results[0] == results[1] && results[0] == results[2];
        if (!allEqual) {
            System.out.println("Erro: Os métodos retornaram resultados diferentes!");
            System.out.println("containsDuplicate: " + results[0]);
            System.out.println("containsDuplicate2: " + results[1]);
            System.out.println("containsDuplicate3: " + results[2]);
        } else {
            System.out.println("Resultado: " + results[0]);

            int fastestIndex = 0;
            for (int i = 1; i < times.length; i++) {
                if (times[i] < times[fastestIndex]) {
                    fastestIndex = i;
                }
            }

            // Calcular as razões de tempo em relação ao mais rápido
            System.out.println("Tempos de execução (em nanossegundos):");
            for (int i = 0; i < times.length; i++) {
                double ratio = (double) times[i] / times[fastestIndex];
                System.out.printf("%s (%s) : %d ns (%.2fx mais lento que o mais rápido)\n",
                        getMethodName(i), complexities[i], times[i], ratio);
            }

            System.out.println("Método mais rápido: " + getMethodName(fastestIndex) + " (" + complexities[fastestIndex] + ")");
            System.out.println();
        }
    }

    static String getMethodName(int index) {
        return switch (index) {
            case 0 -> "containsDuplicate";
            case 1 -> "containsDuplicate2";
            case 2 -> "containsDuplicate3";
            default -> "UnknownMethod";
        };
    }

    static String arrayToString(int[] nums) {
        if (nums == null || nums.length == 0) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i != nums.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    static void main(String[] ignoredArgs) {
        // Casos de teste para containsDuplicate
        int[][] testCases = {
                {1, 2, 3, 1}, // true
                {1, 2, 3, 4}, // false
                {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, // true
                {}, // false (vazio)
                {1}, // false
                {2, 2}, // true
                {MAX_VALUE, MIN_VALUE}, // false
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, // false
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1, 15}, // false
        };

        // Executando os testes
        for (int[] testCase : testCases) {
            testContainsDuplicate(testCase);
        }
    }

    static int[] generateLargeArrayWithDuplicates(int size) {
        int[] nums = new int[size];
        for (int i = 0; i < size - 1; i++) {
            nums[i] = i;
        }
        nums[size - 1] = 0; // Duplica o primeiro elemento
        return nums;
    }

    static int[] generateLargeArrayUnique(int size) {
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = i;
        }
        return nums;
    }
}
