package exercicios;

import java.util.*;

import static java.lang.System.nanoTime;
import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

public class IntersectionOfTwoArrays {

    /*

    349. Intersection of Two Arrays

    Given two integer arrays nums1 and nums2, return an array of their
    intersection.
    Each element in the result must be unique, and you may return the result in any order.

    Example 1:
    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2]

    Example 2:
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [9,4]
    Explanation: [4,9] is also accepted.


    Constraints:
    1 ≤ nums1.length, nums2.length ≤ 1000
    0 ≤ nums1[i], nums2[i] ≤ 1000

     */

    static int[] intersection(int[] nums1, int[] nums2) {
        // Cria um HashSet para armazenar os números únicos do primeiro array (nums1)
        // O HashSet é usado por ter busca O(1) e automaticamente eliminar duplicatas
        HashSet<Integer> set1 = new HashSet<>();

        // Percorre nums1 e adiciona cada número ao set1
        // Se houver números duplicados em nums1, o HashSet manterá apenas uma ocorrência
        for (int num : nums1) set1.add(num);

        // Cria outro HashSet para armazenar os números que aparecem em ambos os arrays
        // Este será nosso conjunto de interseção, mantendo apenas valores únicos
        HashSet<Integer> intersect = new HashSet<>();

        // Percorre nums2 verificando quais números também estão em nums1 (através do set1)
        for (int num : nums2) {
            // Verifica em O(1) se o número atual de nums2 existe em set1
            if (set1.contains(num)) {
                // Se existir, adiciona ao conjunto de interseção
                intersect.add(num);
            }
        }

        // Cria o array final com o tamanho exato do conjunto de interseção
        int[] result = new int[intersect.size()];
        // Índice para preencher o array result
        int i = 0;

        // Transfere os números do conjunto de interseção para o array result
        // A ordem dos números no resultado pode ser diferente da ordem original
        for (int num : intersect) result[i++] = num;

        // Retorna o array contendo todos os números que aparecem em ambos os arrays
        // Cada número aparece apenas uma vez no resultado
        return result;
    }

    static int[] intersection2(int[] nums1, int[] nums2) {
        // Ordena ambos os arrays para poder usar a técnica de dois ponteiros
        // Necessário para comparar elementos em ordem crescente
        sort(nums1);
        sort(nums2);

        // Cria um HashSet para armazenar a interseção, garantindo elementos únicos
        Set<Integer> intersect = new HashSet<>();
        // i é o ponteiro para nums1, j é o ponteiro para nums2
        int i = 0, j = 0;

        // Percorre ambos os arrays simultaneamente enquanto houver elementos
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                // Se elemento de nums1 for menor, avança ponteiro i
                i++;
            } else if (nums1[i] > nums2[j]) {
                // Se elemento de nums2 for menor, avança ponteiro j
                j++;
            } else {
                // Se elementos forem iguais, encontramos uma interseção
                // Adiciona ao set e avança ambos os ponteiros
                intersect.add(nums1[i]);
                i++;
                j++;
            }
        }

        // Cria array final com tamanho exato da interseção
        int[] result = new int[intersect.size()];
        int index = 0;

        // Transfere elementos do set para o array resultado
        for (int num : intersect) {
            result[index++] = num;
        }

        return result;
    }

    static int[] intersection3(int[] nums1, int[] nums2) {
        // Cria set para armazenar elementos únicos da interseção
        Set<Integer> set = new HashSet<>();
        // Ordena nums2 para poder usar busca binária
        sort(nums2);

        // Para cada elemento em nums1, verifica se existe em nums2 usando busca binária
        for (int num : nums1) {
            // Se encontrar o elemento em nums2, adiciona ao set
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }

        // Cria array resultado com tamanho exato da interseção
        int[] result = new int[set.size()];
        int i = 0;

        // Transfere elementos do set para o array resultado
        for (int num : set) {
            result[i++] = num;
        }

        return result;
    }

    private static boolean binarySearch(int[] nums, int target) {
        // Define limites inicial e final para a busca
        int left = 0, right = nums.length - 1;

        // Continua buscando enquanto houver intervalo válido
        while (left <= right) {
            // Calcula o meio do intervalo atual
            // Usa (right-left)/2 para evitar overflow em arrays grandes
            int mid = left + (right - left) / 2;

            // Se encontrou o elemento, retorna true
            if (nums[mid] == target) return true;

            // Se elemento do meio for menor que target,
            // busca na metade direita
            if (nums[mid] < target) left = mid + 1;
                // Se elemento do meio for maior que target,
                // busca na metade esquerda
            else right = mid - 1;
        }

        // Se não encontrou o elemento, retorna false
        return false;
    }

    static void testIntersection(int[] nums1, int[] nums2) {
        if (nums1.length < 100 && nums2.length < 100) {
            System.out.println("nums1: " + Arrays.toString(nums1));
            System.out.println("nums2: " + Arrays.toString(nums2));
        } else {
            System.out.println("Large arrays with sizes " + nums1.length + " and " + nums2.length);
        }

        long[] times = new long[3];
        int[][] results = new int[3][];

        // Test all three implementations
        long start = nanoTime();
        results[0] = intersection(nums1.clone(), nums2.clone());
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = intersection2(nums1.clone(), nums2.clone());
        times[1] = nanoTime() - start;

        start = nanoTime();
        results[2] = intersection3(nums1.clone(), nums2.clone());
        times[2] = nanoTime() - start;

        // Display results
        if (nums1.length < 100 && nums2.length < 100) {
            System.out.println("intersection result: " + Arrays.toString(results[0]));
            System.out.println("intersection2 result: " + Arrays.toString(results[1]));
            System.out.println("intersection3 result: " + Arrays.toString(results[2]));
        }

        int fastest = 0, slowest = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] < times[fastest]) fastest = i;
            if (times[i] > times[slowest]) slowest = i;
        }

        // Calculate and display statistics
        double ratio = (double) times[slowest] / times[fastest];
        System.out.println("intersection (HashSet)      runtime: " + times[0] + " ns");
        System.out.println("intersection2 (Two Pointers) runtime: " + times[1] + " ns");
        System.out.println("intersection3 (Binary Search) runtime: " + times[2] + " ns");
        System.out.printf("Ratio (slowest/fastest): %.2fx\n", ratio);
        System.out.println("Fastest method: intersection" + (fastest + 1));
        System.out.println();
    }

    private static int[] generateLargeArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    static class TestCase {
        int[] nums1;
        int[] nums2;

        TestCase(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;
        }
    }

    static void main(String[] ignoredArgs) {
        // Test cases
        List<TestCase> testCases = asList(
                new TestCase(new int[]{1, 2, 2, 1}, new int[]{2, 2}),                    // Basic case
                new TestCase(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}),             // Multiple common elements
                new TestCase(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10}),      // No intersection
                new TestCase(new int[]{1, 1, 1}, new int[]{1, 1}),                       // All same elements
                new TestCase(generateLargeArray(500), generateLargeArray(500)),           // Large arrays
                new TestCase(generateLargeArray(1000), generateLargeArray(1000))          // Very large arrays
        );

        // Run tests
        for (TestCase testCase : testCases) {
            testIntersection(testCase.nums1, testCase.nums2);
        }
    }
}
