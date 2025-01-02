package Arrays.exercicios;

import static java.lang.System.arraycopy;
import static java.lang.System.nanoTime;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.sort;

import java.util.Arrays;

public class MergeSortedArray {

    /*

    88. Merge Sorted Array

    You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and
    two integers m and n, representing the number of elements in nums1 and nums2 respectively.

    Merge nums1 and nums2 into a single array sorted in non-decreasing order.

    The final sorted array should not be returned by the function,
    but instead be stored inside the array nums1.
    To accommodate this, nums1 has a length of m + n,
    where the first m elements denote the elements that should be merged,
    and the last n elements are set to 0 and should be ignored.
    nums2 has a length of n.

    Example 1:
    Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    Output: [1,2,2,3,5,6]
    Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
    The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.

    Example 2:
    Input: nums1 = [1], m = 1, nums2 = [], n = 0
    Output: [1]
    Explanation: The arrays we are merging are [1] and [].
    The result of the merge is [1].

    Example 3:
    Input: nums1 = [0], m = 0, nums2 = [1], n = 1
    Output: [1]
    Explanation: The arrays we are merging are [] and [1].
    The result of the merge is [1].
    Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.

    Constraints:
        nums1.length == m + n
        nums2.length == n
        0 ≤ m, n ≤ 200
        1 ≤ m + n ≤ 200
        -10^9 ≤ nums1[i], nums2[j] ≤ 10^9


    Follow up: Can you come up with an algorithm that runs in O(m + n) time?

     */

    static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

    static void merge2(int[] nums1, int m, int[] nums2, int n) {
        arraycopy(nums2, 0, nums1, m, n);
        sort(nums1);
    }

    static void testMerge(int[] nums1Original, int m, int[] nums2, int n) {
        if (m + n < 100) {
            System.out.println("Input: nums1 = " + Arrays.toString(copyOf(nums1Original, m + n)));
            System.out.println("m = " + m);
            System.out.println("nums2 = " + Arrays.toString(nums2));
            System.out.println("n = " + n);
        }

        int[][] results = new int[2][];
        long[] times = new long[2];

        // Test merge1
        results[0] = copyOf(nums1Original, m + n);
        long start = nanoTime();
        merge(results[0], m, nums2.clone(), n);
        times[0] = nanoTime() - start;

        // Test merge2
        results[1] = copyOf(nums1Original, m + n);
        start = nanoTime();
        merge2(results[1], m, nums2.clone(), n);
        times[1] = nanoTime() - start;

        if (m + n < 100) {
            System.out.println("merge1 result: " + Arrays.toString(results[0]));
            System.out.println("merge2 result: " + Arrays.toString(results[1]));
        }

        int fastest = 0, slowest = 0;

        if (times[1] < times[0]) fastest = 1;
        else slowest = 1;

        double ratio = (double) times[slowest] / times[fastest];

        System.out.println("merge1 runtime: " + times[0] + " ns");
        System.out.println("merge2 runtime: " + times[1] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: merge" + (fastest + 1));
        System.out.println();
    }

    public static void main(String[] ignoredArgs) {
        // Test case 1: Regular merge
        int[] nums1_1 = {1, 2, 3, 0, 0, 0};
        int[] nums2_1 = {2, 5, 6};
        testMerge(nums1_1, 3, nums2_1, 3);

        // Test case 2: Single elements
        int[] nums1_2 = {1, 0};
        int[] nums2_2 = {2};
        testMerge(nums1_2, 1, nums2_2, 1);

        // Test case 3: Empty nums1
        int[] nums1_3 = new int[5];
        int[] nums2_3 = {1, 2, 3, 4, 5};
        testMerge(nums1_3, 0, nums2_3, 5);

        // Test case 4: Large arrays
        int[] nums1_4 = new int[10000];
        int[] nums2_4 = new int[5000];
        for (int i = 0; i < 5000; i++) {
            nums1_4[i] = i * 2;
            nums2_4[i] = i * 2 + 1;
        }
        testMerge(nums1_4, 5000, nums2_4, 5000);
    }
}
