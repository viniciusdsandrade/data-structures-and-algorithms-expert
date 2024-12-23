package Recursividade;

import java.math.BigInteger;

import static java.lang.System.nanoTime;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

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

    public static long fibIterativo(int n) {
        if (n == 0) return 0L;
        else if (n == 1) return 1L;

        long a = 0, b = 1, c = 0;

        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return c;
    }

    public static BigInteger fibBigInteger(int n) {
        if (n == 0) return ZERO;
        else if (n == 1) return ONE;

        BigInteger a = ZERO;
        BigInteger b = ONE;
        BigInteger c = ZERO;

        for (int i = 2; i <= n; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }

        return c;
    }

    public static void main(String[] args) {
        testFibBigIntegerLimits(0, 1_000_000, 1_000);
        for (int i = 0; i < 10; i++) testFibonacci(i);
    }


    public static void testFibBigIntegerLimits(int minN, int maxN, int incremento) {
        System.out.println("Testando fibBigInteger com valores de n de " + minN + " até " + maxN + " com incremento de " + incremento + "...\n");

        // Captura de possíveis erros de memória
        try {
            for (int n = minN; n <= maxN; n += incremento) {
                long tempoInicio = nanoTime();

                // Calcular o Fibonacci usando BigInteger
                BigInteger fibVal = fibBigInteger(n);

                long tempoFim = nanoTime();
                long duracao = tempoFim - tempoInicio;

                // Quantos dígitos em decimal esse Fibonacci tem
                int numeroDigitos = fibVal.toString().length();

                // Converter duração para segundos
                double segundos = duracao / 1_000_000_000.0;

                // Exibir informações
                System.out.printf("Fib(%d) calculado! | #Digitos: %d | Tempo: %.3fs\n",
                        n, numeroDigitos, segundos);

                // Critério de parada: se demorar mais de 10 segundos para uma iteração
                if (segundos > 10.0) {
                    System.out.println("\nDemorou mais de 10 segundos para calcular Fib(" + n + "). Encerrando os testes...");
                    break;
                }
            }
        } catch (OutOfMemoryError e) {
            System.err.println("\nOutOfMemoryError atingido! Memória insuficiente para continuar os testes.");
        } catch (Exception e) {
            System.err.println("\nOcorreu um erro inesperado: " + e.getMessage());
            System.err.println(e.getMessage());
        }

        System.out.println("\nTeste finalizado.");
    }

    public static void testFibonacci(int n) {
        System.out.println("\nInput:   " + n);

        long resultadoRecursivo, tempoInicioRecursivo, tempoFimRecursivo, duracaoRecursivo;
        long resultadoIterativo, tempoInicioIterativo, tempoFimIterativo, duracaoIterativo;

        // Medir tempo para a versão iterativa
        tempoInicioIterativo = nanoTime();
        resultadoIterativo = fibIterativo(n);
        tempoFimIterativo = nanoTime();
        duracaoIterativo = tempoFimIterativo - tempoInicioIterativo;

        // Medir tempo para a versão recursiva
        tempoInicioRecursivo = nanoTime();
        resultadoRecursivo = fib(n);
        tempoFimRecursivo = nanoTime();
        duracaoRecursivo = tempoFimRecursivo - tempoInicioRecursivo;

        // Medir tempo para a versão BigInteger
        long tempoInicioBig, tempoFimBig, duracaoBig;
        BigInteger resultadoBig;

        tempoInicioBig = nanoTime();
        resultadoBig = fibBigInteger(n);
        tempoFimBig = nanoTime();
        duracaoBig = tempoFimBig - tempoInicioBig;

        // Imprimir resultados
        System.out.println("OutputRecursivo:   " + resultadoRecursivo);
        System.out.println("OutputIterativo:   " + resultadoIterativo);
        System.out.println("OutputBigInteger:  " + resultadoBig);

        // Exibir tempo de execução para a versão iterativa
        if (duracaoIterativo >= 1_000_000_000L) {
            double segundosIterativo = duracaoIterativo / 1_000_000_000.0;
            System.out.printf("Runtime Iterativo: %.3f s\n", segundosIterativo);
        } else {
            System.out.println("Runtime Iterativo: " + duracaoIterativo + " ns");
        }

        // Exibir tempo de execução para a versão recursiva
        if (duracaoRecursivo >= 1_000_000_000L) {
            double segundosRecursivo = duracaoRecursivo / 1_000_000_000.0;
            System.out.printf("Runtime Recursivo: %.3f s\n", segundosRecursivo);
        } else {
            System.out.println("Runtime Recursivo: " + duracaoRecursivo + " ns");
        }

        // Exibir tempo de execução para a versão BigInteger
        if (duracaoBig >= 1_000_000_000L) {
            double segundosBig = duracaoBig / 1_000_000_000.0;
            System.out.printf("Runtime BigInteger: %.3f s\n", segundosBig);
        } else {
            System.out.println("Runtime BigInteger: " + duracaoBig + " ns");
        }

        // Calcular a razão entre os tempos de execução (Recursivo / Iterativo)
        if (duracaoIterativo != 0) { // Evitar divisão por zero
            double razaoRecIter = (double) duracaoRecursivo / duracaoIterativo;
            System.out.printf("Razão (Recursivo / Iterativo): %.3f\n", razaoRecIter);
        } else {
            System.err.println("Não foi possível calcular a razão (Rec/Iter) devido a duração iterativa igual a zero.");
        }

        // Calcular a razão entre os tempos de execução (BigInteger / Iterativo)
        if (duracaoIterativo != 0) {
            double razaoBigIter = (double) duracaoBig / duracaoIterativo;
            System.out.printf("Razão (BigInteger / Iterativo): %.3f\n", razaoBigIter);
        } else {
            System.err.println("Não foi possível calcular a razão (Big/Iter) devido a duração iterativa igual a zero.");
        }
    }
}
