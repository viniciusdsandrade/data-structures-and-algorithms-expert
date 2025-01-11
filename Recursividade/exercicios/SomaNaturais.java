package Recursividade.exercicios;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.lang.System.nanoTime;

public class SomaNaturais {

    /*

    Problema "soma-naturais"

    Faça uma função que, dado um número natural N,
    retorne a soma dos números de 0 até N.

    Exemplo 1:
    Entrada:    0
    Saída:      0

    Exemplo 2:
    Entrada:  2
    Saída:    3

    Exemplo 3:
    Entrada:  4
    Saída:    10

    Assinatura: public static int sumNaturals(int n)

     */

    static long sumNaturals(int n) {
        if (n < 0) throw new IllegalArgumentException("Soma não definida para números negativos.");
        return n == 0 ? 0L : n + sumNaturals(n - 1);
    }

    static long sumNaturalsIterativo(int n) {
        if (n < 0) throw new IllegalArgumentException("Soma não definida para números negativos.");
        long soma = 0L;
        for (int i = 1; i <= n; i++) {
            soma += i;
        }
        return soma;
    }

    static BigInteger sumNaturalsBigInteger(int n) {
        if (n < 0) throw new IllegalArgumentException("Soma não definida para números negativos.");
        BigInteger soma = BigInteger.ZERO;
        for (int i = 1; i <= n; i++) {
            soma = soma.add(BigInteger.valueOf(i));
        }
        return soma;
    }

    static BigDecimal sumNaturalsBigDecimal(int n) {
        if (n < 0) throw new IllegalArgumentException("Soma não definida para números negativos.");
        BigDecimal soma = BigDecimal.ZERO;
        for (int i = 1; i <= n; i++) {
            soma = soma.add(BigDecimal.valueOf(i));
        }
        return soma;
    }

    public static void main(String[] ignoredArgs) {
        testSumNaturalsBigIntegerLimits(100_000, 1_000_000, 100_000);
        testSumNaturalsBigDecimalLimits(100_000, 1_000_000, 100_000);
        for (int i = 0; i <= 100; i += 10) {
            testSomaNaturais(i);
        }
    }

    static void testSumNaturalsBigIntegerLimits(int minN, int maxN, int incremento) {
        System.out.println("Testando sumNaturalsBigInteger com valores de n de " + minN + " até " + maxN + " com incremento de " + incremento + "...\n");

        try {
            for (int n = minN; n <= maxN; n += incremento) {
                long tempoInicio = nanoTime();

                // Calcular a soma usando BigInteger
                BigInteger somaVal = sumNaturalsBigInteger(n);

                long tempoFim = nanoTime();
                long duracao = tempoFim - tempoInicio;

                // Quantos dígitos em decimal essa soma tem
                int numeroDigitos = somaVal.toString().length();

                // Converter duração para segundos
                double segundos = duracao / 1_000_000_000.0;

                // Exibir informações
                System.out.printf("SumNaturals(%d) calculado! | #Digitos: %d | Tempo: %.3fs\n",
                        n, numeroDigitos, segundos);

                // Critério de parada: se demorar mais de 15 segundos para uma iteração
                if (segundos > 15.0) {
                    System.out.println("\nDemorou mais de 15 segundos para calcular SumNaturals(" + n + "). Encerrando os testes...");
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

    static void testSumNaturalsBigDecimalLimits(int minN, int maxN, int incremento) {
        System.out.println("Testando sumNaturalsBigDecimal com valores de n de "
                           + minN + " até " + maxN + " com incremento de " + incremento + "...\n");

        try {
            for (int n = minN; n <= maxN; n += incremento) {
                long tempoInicio = nanoTime();

                // Calcular a soma usando BigDecimal
                BigDecimal somaVal = sumNaturalsBigDecimal(n);

                long tempoFim = nanoTime();
                long duracao = tempoFim - tempoInicio;

                // Quantos dígitos em decimal essa soma tem
                // Usamos toPlainString() para evitar notação científica em BigDecimal.
                int numeroDigitos = somaVal.toPlainString().length();

                // Converter duração para segundos
                double segundos = duracao / 1_000_000_000.0;

                // Exibir informações
                System.out.printf("SumNaturals(%d) calculado! | #Digitos: %d | Tempo: %.3fs%n",
                        n, numeroDigitos, segundos);

                // Critério de parada: se demorar mais de 15 segundos para uma iteração
                if (segundos > 15.0) {
                    System.out.println("\nDemorou mais de 15 segundos para calcular SumNaturals("
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

    static void testSomaNaturais(int n) {
        System.out.println("\nInput: " + n);

        // ---------------------------
        // VERSÃO ITERATIVA (long)
        // ---------------------------
        long tempoInicioIterativo = nanoTime();
        long resultadoIterativo = sumNaturalsIterativo(n);
        long tempoFimIterativo = nanoTime();
        long duracaoIterativo = tempoFimIterativo - tempoInicioIterativo;

        // ---------------------------
        // VERSÃO RECURSIVA (long)
        // ---------------------------
        long tempoInicioRecursivo = nanoTime();
        long resultadoRecursivo = sumNaturals(n);
        long tempoFimRecursivo = nanoTime();
        long duracaoRecursivo = tempoFimRecursivo - tempoInicioRecursivo;

        // ---------------------------
        // VERSÃO BIGINTEGER
        // ---------------------------
        long tempoInicioBig = nanoTime();
        BigInteger resultadoBig = sumNaturalsBigInteger(n);
        long tempoFimBig = nanoTime();
        long duracaoBig = tempoFimBig - tempoInicioBig;

        // ---------------------------
        // VERSÃO BIGDECIMAL
        // ---------------------------
        long tempoInicioBigDecimal = nanoTime();
        BigDecimal resultadoBigDecimal = sumNaturalsBigDecimal(n);
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
