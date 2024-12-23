package Recursividade;

public class Fibonacci {

    /*
    Problema "fibonacci"

    A sequência de Fibonacci começa com 0, 1, e depois cada número é a soma de seus dois antecessores: 0 1 1 2 3 5 8 13...

    Faça uma função para retornar o valor de uma dada posição da sequência de Fibonacci.

    Exemplo 1:
    Entrada 0
    Saída   0

    Exemplo 2:
    Entrada 0
    Saída   1

    Exemplo 3:
    Entrada 6
    Saída   8

    Assinaturas:
    Java: public static int fib(int n)
     */

    public static long fib(int n) {
        if (n == 0) return 0L;
        else if (n == 1) return 1L;

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 46; i++) testFibonacci(i);
    }

    public static void testFibonacci(int n) {
        System.out.println("\nInput:   " + n);

        long res, start, end, runtime;

        start = System.nanoTime();
        res = fib(n);
        end = System.nanoTime();

        runtime = end - start;

        System.out.println("Output:  " + res);

        if (runtime >= 1_000_000_000L) {
            double segundos = runtime / 1_000_000_000.0;
            System.out.printf("Runtime: %.3f s\n\n", segundos);
        } else {
            System.out.println("Runtime: " + runtime + " ns\n");
        }
    }
}
