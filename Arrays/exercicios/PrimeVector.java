package Arrays.exercicios;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class PrimeVector {

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i <= sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> getPrimesBetween(int start, int end) {
        List<Integer> primes = new ArrayList<>();
        int lower = min(start, end);
        int upper = max(start, end);
        for (int num = lower; num <= upper; num++) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }
        return primes;
    }

    static void main(String[] ignoredArgs) {
        // Exemplo de uso
        List<Integer> primes = getPrimesBetween(10, 50);
        System.out.println("Primos entre 10 e 50: " + primes);
    }
}
