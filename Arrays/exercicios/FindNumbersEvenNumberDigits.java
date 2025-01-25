package Arrays.exercicios;

import java.util.Arrays;

import static java.lang.String.valueOf;
import static java.lang.System.nanoTime;

public class FindNumbersEvenNumberDigits {

    /*

    1295. Find Numbers with Even Number of Digits

    Given an array nums of integers, return how many of them contain an even number of digits.

    Example 1:
    Input: nums = [12,345,2,6,7896]
    Output: 2

    Explanation:
    12 contains 2 digits (even number of digits).
    345 contains 3 digits (odd number of digits).
    2 contains 1 digit (odd number of digits).
    6 contains 1 digit (odd number of digits).
    7896 contains 4 digits (even number of digits).
    Therefore, only 12 and 7896 contain an even number of digits.

    Example 2:
    Input: nums = [555,901,482,1771]
    Output: 1
    Explanation:
    Only 1771 contains an even number of digits.


    Constraints:
    1 <= nums.length <= 500
    1 <= nums[i] <= 10^5

     */

    static int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (valueOf(num).length() % 2 == 0)
                count++;
        }
        return count;
    }

    static int findNumbers2(int[] nums) {
        int count = 0;
        for (int num : nums) {
            int digits = 1;
            int n = num;
            while (n >= 10) {
                n /= 10;
                digits++;
            }
            if (digits % 2 == 0) count++;
        }
        return count;
    }

    static int findNumbers3(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if ((num >= 10 && num < 100) || (num >= 1_000 && num < 10_000) || num == 100_000)
                count++;
        }
        return count;
    }

    static void testFindNumbers(int[] nums) {
        System.out.println("Input: nums = " + Arrays.toString(nums));

        long[] times = new long[3];
        int[] results = new int[3];

        // Test all methods
        long start = nanoTime();
        results[0] = findNumbers(nums);
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = findNumbers2(nums);
        times[1] = nanoTime() - start;

        start = nanoTime();
        results[2] = findNumbers3(nums);
        times[2] = nanoTime() - start;

        // Find the fastest and slowest
        int fastest = 0, slowest = 0;
        for (int i = 1; i < 3; i++) {
            if (times[i] < times[fastest]) fastest = i;
            if (times[i] > times[slowest]) slowest = i;
        }

        double ratio = (double) times[slowest] / times[fastest];

        System.out.println("findNumbers1 result: " + results[0] + " runtime: " + times[0] + " ns");
        System.out.println("findNumbers2 result: " + results[1] + " runtime: " + times[1] + " ns");
        System.out.println("findNumbers3 result: " + results[2] + " runtime: " + times[2] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: findNumbers" + (fastest + 1));
        System.out.println();
    }

    static void main(String[] ignoredArgs) {
        int[] nums = {12, 345, 2, 6, 7896};
        int[] nums2 = {555, 901, 482, 1771};

        testFindNumbers(nums);
        testFindNumbers(nums2);
    }
}
