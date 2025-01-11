package Recursividade.exercicios;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.lang.System.nanoTime;

public class Fatorial {

    /*

    Problema "fatorial"

    O fatorial de um número natural N é a multiplicação de 1 até N,
    exceto para o valor 0 (zero), cujo fatorial por definição é 1.
    Faça uma função para retornar o fatorial de um dado número.

    Exemplo 1:
    Entrada: 0
    Saída:   1

    Exemplo 2:
    Entrada: 3
    Saída:   6

    Exemplo 3:
    Entrada: 4
    Saída:   24

    Assinatura: public static int factorial(int n)

     */

    static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Fatorial não definido para números negativos.");
        return n == 0 ? 1L : n * factorial(n - 1);
    }

    static long factorialIterativo(int n) {
        if (n < 0) throw new IllegalArgumentException("Fatorial não definido para números negativos.");
        long resultado = 1L;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    static BigInteger factorialBigInteger(int n) {
        if (n < 0) throw new IllegalArgumentException("Fatorial não definido para números negativos.");
        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }

    static BigDecimal factorialBigDecimal(int n) {
        if (n < 0) throw new IllegalArgumentException("Fatorial não definido para números negativos.");
        BigDecimal resultado = BigDecimal.ONE;
        for (int i = 2; i <= n; i++) {
            resultado = resultado.multiply(BigDecimal.valueOf(i));
        }
        return resultado;
    }

    public static void main(String[] ignoredArgs) {
        testFactorialBigIntegerLimits(1000, 10000, 1000);
        testFactorialBigDecimalLimits(1000, 10000, 1000);
        for (int i = 0; i <= 20; i++) {
            testFatorial(i);
        }
    }

    static void testFactorialBigIntegerLimits(int minN, int maxN, int incremento) {
        System.out.println("Testando factorialBigInteger com valores de n de " + minN + " até " + maxN + " com incremento de " + incremento + "...\n");

        try {
            for (int n = minN; n <= maxN; n += incremento) {
                long tempoInicio = nanoTime();

                // Calcular o Fatorial usando BigInteger
                BigInteger fatVal = factorialBigInteger(n);

                long tempoFim = nanoTime();
                long duracao = tempoFim - tempoInicio;

                // Quantos dígitos em decimal esse fatorial tem
                int numeroDigitos = fatVal.toString().length();

                // Converter duração para segundos
                double segundos = duracao / 1_000_000_000.0;

                // Exibir informações
                System.out.printf("Fatorial(%d) calculado! | #Digitos: %d | Tempo: %.3fs\n",
                        n, numeroDigitos, segundos);

                // Critério de parada: se demorar mais de 15 segundos para uma iteração
                if (segundos > 15.0) {
                    System.out.println("\nDemorou mais de 15 segundos para calcular Fatorial(" + n + "). Encerrando os testes...");
                    break;
                }
            }
        } catch (OutOfMemoryError e) {
            System.err.println("\nOutOfMemoryError atingido! Memória insuficiente para continuar os testes.");
        } catch (Exception e) {
            System.err.println("\nOcorreu um erro inesperado: " + e.getMessage());
        }

        System.out.println("\nTeste finalizado.");
    }

    static void testFactorialBigDecimalLimits(int minN, int maxN, int incremento) {
        System.out.println("Testando factorialBigDecimal com valores de n de "
                           + minN + " até " + maxN + " com incremento de " + incremento + "...\n");

        try {
            for (int n = minN; n <= maxN; n += incremento) {
                long tempoInicio = nanoTime();

                // Calcular o Fatorial usando BigDecimal
                BigDecimal fatVal = factorialBigDecimal(n);

                long tempoFim = nanoTime();
                long duracao = tempoFim - tempoInicio;

                // Quantos dígitos em decimal esse fatorial tem
                // Usamos toPlainString() para evitar notação científica em BigDecimal.
                int numeroDigitos = fatVal.toPlainString().length();

                // Converter duração para segundos
                double segundos = duracao / 1_000_000_000.0;

                // Exibir informações
                System.out.printf("Fatorial(%d) calculado! | #Digitos: %d | Tempo: %.3fs%n",
                        n, numeroDigitos, segundos);

                // Critério de parada: se demorar mais de 15 segundos para uma iteração
                if (segundos > 15.0) {
                    System.out.println("\nDemorou mais de 15 segundos para calcular Fatorial("
                                       + n + "). Encerrando os testes...");
                    break;
                }
            }
        } catch (OutOfMemoryError e) {
            System.err.println("\nOutOfMemoryError atingido! Memória insuficiente para continuar os testes.");
        } catch (Exception e) {
            System.err.println("\nOcorreu um erro inesperado: " + e.getMessage());
        }

        System.out.println("\nTeste finalizado.");
    }

    static void testFatorial(int n) {
        System.out.println("\nInput: " + n);

        // ---------------------------
        // VERSÃO ITERATIVA (long)
        // ---------------------------
        long tempoInicioIterativo = nanoTime();
        long resultadoIterativo = factorialIterativo(n);
        long tempoFimIterativo = nanoTime();
        long duracaoIterativo = tempoFimIterativo - tempoInicioIterativo;

        // ---------------------------
        // VERSÃO RECURSIVA (long)
        // ---------------------------
        long tempoInicioRecursivo = nanoTime();
        long resultadoRecursivo = factorial(n);
        long tempoFimRecursivo = nanoTime();
        long duracaoRecursivo = tempoFimRecursivo - tempoInicioRecursivo;

        // ---------------------------
        // VERSÃO BIGINTEGER
        // ---------------------------
        long tempoInicioBig = nanoTime();
        BigInteger resultadoBig = factorialBigInteger(n);
        long tempoFimBig = nanoTime();
        long duracaoBig = tempoFimBig - tempoInicioBig;

        // ---------------------------
        // VERSÃO BIGDECIMAL
        // ---------------------------
        long tempoInicioBigDecimal = nanoTime();
        BigDecimal resultadoBigDecimal = factorialBigDecimal(n);
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
