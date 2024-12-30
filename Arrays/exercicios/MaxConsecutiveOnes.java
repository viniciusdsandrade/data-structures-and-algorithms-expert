package Arrays.exercicios;

import static java.lang.Math.max;
import static java.lang.System.nanoTime;

import java.util.Arrays;


public class MaxConsecutiveOnes {

    /*

    485. Max Consecutive Ones

    Given a binary array nums, return the maximum number of consecutive 1's in the array.

    Example 1:
    Input: nums = [1,1,0,1,1,1]
    Output: 3
    Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

    Example 2:
    Input: nums = [1,0,1,1,0,1]
    Output: 2

    Constraints:
    1 <= nums.length <= 10^5
    nums[i] is either 0 or 1.

     */

    // Original implementation
    static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                max = max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }

    // Alternative implementation using while loop
    static int findMaxConsecutiveOnes2(int[] nums) {
        int maxOnes = 0;
        int i = 0;
        while (i < nums.length) {
            int currentOnes = 0;
            while (i < nums.length && nums[i] == 1) {
                currentOnes++;
                i++;
            }
            maxOnes = max(maxOnes, currentOnes);
            i++;
        }
        return maxOnes;
    }

    static void testFindMaxConsecutiveOnes(int[] nums) {
        if (nums.length < 100) System.out.println("Input: " + Arrays.toString(nums));

        long[] times = new long[2];
        int[] results = new int[2];

        // Test all methods
        long start = nanoTime();
        results[0] = findMaxConsecutiveOnes(nums);
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = findMaxConsecutiveOnes2(nums);
        times[1] = nanoTime() - start;

        // Find fastest and slowest
        int fastest = 0, slowest = 0;
        if (times[1] < times[0]) fastest = 1;

        double ratio = (double) times[slowest] / times[fastest];

        System.out.println("findMaxConsecutiveOnes1 result: " + results[0] + " runtime: " + times[0] + " ns");
        System.out.println("findMaxConsecutiveOnes2 result: " + results[1] + " runtime: " + times[1] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: findMaxConsecutiveOnes" + (fastest + 1));
        System.out.println();
    }

    public static void main(String[] ignoredArgs) {
        int[][] testCases = {
                {1, 1, 0, 1, 1, 1},
                {1, 0, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0},
                new int[1000000] // Large test case
        };

        // Fill large test case with alternating 1s and 0s
        for (int i = 0; i < testCases[4].length; i++) {
            testCases[4][i] = i % 2;
        }

        for (int[] test : testCases) {
            testFindMaxConsecutiveOnes(test);
        }
    }
}
