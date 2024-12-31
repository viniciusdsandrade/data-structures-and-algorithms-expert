package Arrays.exercicios;

import static java.lang.System.nanoTime;
import static java.util.Arrays.sort;

import java.util.Arrays;

public class SquaresSortedArray {

    /*

    977. Squares of a Sorted Array

    Given an integer array nums sorted in non-decreasing order, return an array of the squares of
    each number sorted in non-decreasing order.

    Example 1:
    Input: nums = [-4,-1,0,3,10]
    Output: [0,1,9,16,100]
    Explanation: After squaring, the array becomes [16,1,0,9,100].
    After sorting, it becomes [0,1,9,16,100].

    Example 2:
    Input: nums = [-7,-3,2,3,11]
    Output: [4,9,9,49,121]

    Constraints:
    1 <= nums.length <= 10^4
    -10^4 <= nums[i] <= 10^4
    nums is sorted in non-decreasing order.

    Follow up: Squaring each element and sorting the new array is very trivial,
    could you find an O(n) solution using a different approach?

     */

    static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int i = n - 1;
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            if (leftSquare > rightSquare) {
                result[i] = leftSquare;
                left++;
            } else {
                result[i] = rightSquare;
                right--;
            }
            i--;
        }
        return result;
    }

    // Trivial solution
    static int[] sortedSquares2(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] * nums[i];
        }
        sort(result);
        return result;
    }

    static void testSortedSquares(int[] nums) {

        if (nums.length < 100) System.out.println("Input: " + Arrays.toString(nums));

        long[] times = new long[2];
        int[][] results = new int[2][];

        long start = nanoTime();
        results[0] = sortedSquares(nums.clone());
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = sortedSquares2(nums.clone());
        times[1] = nanoTime() - start;

        if (nums.length < 100) {
            System.out.println("sortedSquares1 result: " + Arrays.toString(results[0]));
            System.out.println("sortedSquares2 result: " + Arrays.toString(results[1]));
        }

        int fastest = 0, slowest = 0;
        if (times[1] < times[0]) {
            fastest = 1;
        } else {
            slowest = 1;
        }

        double ratio = (double) times[slowest] / times[fastest];

        System.out.println("sortedSquares1 runtime: " + times[0] + " ns");
        System.out.println("sortedSquares2 runtime: " + times[1] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: sortedSquares" + (fastest + 1));
        System.out.println();
    }

    public static void main(String[] ignoredArgs) {
        int[][] testCases = {
                {-4, -1, 0, 3, 10},           // Example 1 from problem
                {-7, -3, 2, 3, 11},           // Example 2 from problem
                {-2, -1},                      // Small array with negatives
                {1, 2, 3, 4, 5},              // All positive numbers
                {-5, -4, -3, -2, -1},         // All negative numbers
                {0, 0, 0, 0},                 // All zeros
                new int[10000]                // Large array
        };

        // Fill the large array with sorted numbers
        for (int i = 0; i < testCases[6].length; i++) {
            testCases[6][i] = i % 100 - 50; // Creates a range of -50 to 49
        }

        sort(testCases[6]); // Ensure it's sorted

        // Run tests
        for (int[] testCase : testCases) {
            testSortedSquares(testCase);
        }
    }
}
