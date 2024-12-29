package Recursividade.exercicios;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.lang.System.nanoTime;

public class Fibonacci {

    /*

    Problema "fibonacci"

    A sequência de Fibonacci começa com 0, 1, e depois cada número é
    a soma de seus dois antecessores: 0 1 1 2 3 5 8 13...

    Faça uma função para retornar o valor de uma dada posição da
    sequência de Fibonacci.

    Exemplo 1:
    Entrada: 0
    Saída:   0

    Exemplo 2:
    Entrada: 0
    Saída:   1

    Exemplo 3:
    Entrada: 6
    Saída:   8

    Assinatura: public static int fib(int n)

     */

    static long fib(int n) {
        if (n == 0) return 0L;
        else if (n == 1) return 1L;

        return fib(n - 1) + fib(n - 2);
    }

    static long fibIterativo(int n) {
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

    static BigInteger fibBigInteger(int n) {
        if (n == 0) return BigInteger.ZERO;
        else if (n == 1) return BigInteger.ONE;

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        BigInteger c = BigInteger.ZERO;

        for (int i = 2; i <= n; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }

        return c;
    }

    static BigDecimal fibBigDecimal(int n) {
        if (n == 0) return BigDecimal.ZERO;
        else if (n == 1) return BigDecimal.ONE;

        BigDecimal a = BigDecimal.ZERO;
        BigDecimal b = BigDecimal.ONE;
        BigDecimal c = BigDecimal.ZERO;

        for (int i = 2; i <= n; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }

        return c;
    }

    static void main(String[] ignoredArgs) {
        testFibBigIntegerLimits(100_000, 1_000_000, 10_000);
        testFibBigDecimalLimits(100_000, 1_000_000, 10_000);
        for (int i = 0; i < 1_000; i++) testFibonacci(i);
    }

    static void testFibBigIntegerLimits(int minN, int maxN, int incremento) {
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

                // Critério de parada: se demorar mais de 15 segundos para uma iteração
                if (segundos > 15.0) {
                    System.out.println("\nDemorou mais de 15 segundos para calcular Fib(" + n + "). Encerrando os testes...");
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

    static void testFibBigDecimalLimits(int minN, int maxN, int incremento) {
        System.out.println("Testando figBigDecimal com valores de n de "
                           + minN + " até " + maxN + " com incremento de " + incremento + "...\n");

        try {
            for (int n = minN; n <= maxN; n += incremento) {
                long tempoInicio = nanoTime();

                // Calcular o Fibonacci usando BigDecimal
                BigDecimal fibVal = fibBigDecimal(n);

                long tempoFim = nanoTime();
                long duracao = tempoFim - tempoInicio;

                // Quantos dígitos em decimal esse Fibonacci tem
                // Obs.: usamos toPlainString() para evitar notação científica em BigDecimal.
                int numeroDigitos = fibVal.toPlainString().length();

                // Converter duração para segundos
                double segundos = duracao / 1_000_000_000.0;

                // Exibir informações
                System.out.printf("Fib(%d) calculado! | #Digitos: %d | Tempo: %.3fs%n",
                        n, numeroDigitos, segundos);

                // Critério de parada: se demorar mais de 15 segundos para uma iteração
                if (segundos > 15.0) {
                    System.out.println("\nDemorou mais de 15 segundos para calcular Fib("
                                       + n + "). Encerrando os testes...");
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

    static void testFibonacci(int n) {
        System.out.println("\nInput: " + n);

        // ---------------------------
        // VERSÃO ITERATIVA (long)
        // ---------------------------
        long tempoInicioIterativo = nanoTime();
        long resultadoIterativo = fibIterativo(n);
        long tempoFimIterativo = nanoTime();
        long duracaoIterativo = tempoFimIterativo - tempoInicioIterativo;

        // ---------------------------
        // VERSÃO RECURSIVA (long)
        // ---------------------------
        long tempoInicioRecursivo = nanoTime();
        long resultadoRecursivo = fib(n);
        long tempoFimRecursivo = nanoTime();
        long duracaoRecursivo = tempoFimRecursivo - tempoInicioRecursivo;

        // ---------------------------
        // VERSÃO BIGINTEGER
        // ---------------------------
        long tempoInicioBig = nanoTime();
        BigInteger resultadoBig = fibBigInteger(n);
        long tempoFimBig = nanoTime();
        long duracaoBig = tempoFimBig - tempoInicioBig;

        // ---------------------------
        // VERSÃO BIGDECIMAL
        // ---------------------------
        long tempoInicioBigDecimal = nanoTime();
        BigDecimal resultadoBigDecimal = fibBigDecimal(n);
        long tempoFimBigDecimal = nanoTime();
        long duracaoBigDecimal = tempoFimBigDecimal - tempoInicioBigDecimal;

        // ---------------------------
        // IMPRIMIR RESULTADOS
        // ---------------------------
        System.out.println("OutputRecursivo:   " + resultadoRecursivo);
        System.out.println("OutputIterativo:   " + resultadoIterativo);
        System.out.println("OutputBigInteger:  " + resultadoBig);
        System.out.println("OutputBigDecimal:  " + resultadoBigDecimal);

        // ---------------------------
        // TEMPO DE EXECUÇÃO (ITERATIVO)
        // ---------------------------
        if (duracaoIterativo >= 1_000_000_000L) {
            double segundosIterativo = duracaoIterativo / 1_000_000_000.0;
            System.out.printf("Runtime Iterativo: %.3f s\n", segundosIterativo);
        } else {
            System.out.println("Runtime Iterativo: " + duracaoIterativo + " ns");
        }

        // ---------------------------
        // TEMPO DE EXECUÇÃO (RECURSIVO)
        // ---------------------------
        if (duracaoRecursivo >= 1_000_000_000L) {
            double segundosRecursivo = duracaoRecursivo / 1_000_000_000.0;
            System.out.printf("Runtime Recursivo: %.3f s\n", segundosRecursivo);
        } else {
            System.out.println("Runtime Recursivo: " + duracaoRecursivo + " ns");
        }

        // ---------------------------
        // TEMPO DE EXECUÇÃO (BIGINTEGER)
        // ---------------------------
        if (duracaoBig >= 1_000_000_000L) {
            double segundosBig = duracaoBig / 1_000_000_000.0;
            System.out.printf("Runtime BigInteger: %.3f s\n", segundosBig);
        } else {
            System.out.println("Runtime BigInteger: " + duracaoBig + " ns");
        }

        // ---------------------------
        // TEMPO DE EXECUÇÃO (BIGDECIMAL)
        // ---------------------------
        if (duracaoBigDecimal >= 1_000_000_000L) {
            double segundosBigDecimal = duracaoBigDecimal / 1_000_000_000.0;
            System.out.printf("Runtime BigDecimal: %.3f s\n", segundosBigDecimal);
        } else {
            System.out.println("Runtime BigDecimal: " + duracaoBigDecimal + " ns");
        }

        // ---------------------------
        // RAZÕES (RECURSIVO / ITERATIVO)
        // ---------------------------
        if (duracaoIterativo != 0) {
            double razaoRecIter = (double) duracaoRecursivo / duracaoIterativo;
            System.out.printf("Razão (Recursivo / Iterativo): %.3f\n", razaoRecIter);
        } else {
            System.err.println("Não foi possível calcular a razão (Rec/Iter) pois a duração iterativa foi zero.");
        }

        // ---------------------------
        // RAZÕES (BIGINTEGER / ITERATIVO)
        // ---------------------------
        if (duracaoIterativo != 0) {
            double razaoBigIter = (double) duracaoBig / duracaoIterativo;
            System.out.printf("Razão (BigInteger / Iterativo): %.3f\n", razaoBigIter);
        } else {
            System.err.println("Não foi possível calcular a razão (Big/Iter) pois a duração iterativa foi zero.");
        }

        // ---------------------------
        // RAZÕES (BIGDECIMAL / ITERATIVO)
        // ---------------------------
        if (duracaoIterativo != 0) {
            double razaoBigDecimalIter = (double) duracaoBigDecimal / duracaoIterativo;
            System.out.printf("Razão (BigDecimal / Iterativo): %.3f\n", razaoBigDecimalIter);
        } else {
            System.err.println("Não foi possível calcular a razão (BigDecimal/Iter) pois a duração iterativa foi zero.");
        }
    }
}
