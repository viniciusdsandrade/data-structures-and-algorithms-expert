package exercicios;

import static java.lang.System.nanoTime;

public class ClimbingStairs {

    /*

    Problema "climbing_stairs" (Adaptado de Leetcode 1971)

    Você está subindo uma escada.
    São necessários n degraus para chegar ao topo.
    A cada vez, você pode subir 1 ou 2 degraus.
    De quantas maneiras distintas você pode subir até o topo?

    Restrições:  1 ≤ n ≤ 45

        Exemplo 1:
        Entrada 1
            {
                "n": 2
            }
        Saída 1: 2

        Explicação: Há duas formas de subir ao topo.
        1. 1 degrau + 1 degrau
        2. 2 degraus


        Exemplo 2:
        Entrada 2:
            {
                "n": 3
            }
        Saída 2: 3

        Explicação: Há três formas de subir ao topo.
        1. 1 degrau + 1 degrau + 1 degrau
        2. 1 degrau + 2 degraus
        3. 2 degraus + 1 degrau

        Exemplo 3:
        Entrada 3
            {
               "n": 45
            }
        Saída 3 :  1836311903


        Assinaturas:
        Java: public static int climbStairs(int n)

     */

    public static void main(String[] ignoredArgs) {
        for (int i = 4; i < 7; i++) {
            testClimbStairs(i);
        }
    }

    public static int climbStairs(int n) {
        if (n == 1) return 1;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

    public static void testClimbStairs(int n) {
        System.out.println("\nInput:   " + n);

        long startTime, endTime, runtime;
        int result;

        startTime = nanoTime();
        result = climbStairs(n);
        endTime = nanoTime();

        runtime = endTime - startTime;

        System.out.println("Output:  " + result);
        System.out.println("Runtime: " + runtime + " ns\n");
    }
}
