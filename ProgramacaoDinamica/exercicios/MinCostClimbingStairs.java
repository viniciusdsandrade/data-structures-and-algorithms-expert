package exercicios;

import static java.lang.Math.min;
import static java.lang.System.nanoTime;

public class MinCostClimbingStairs {

    /*

    746. Min Cost Climbing Stairs

    You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
    Once you pay the cost, you can either climb one or two steps.

    You can either start from the step with index 0, or the step with index 1.

    Return the minimum cost to reach the top of the floor.

    Example 1:
    Input: cost = [10,15,20]
    Output: 15
    Explanation: You will start at index 1.
    - Pay 15 and climb two steps to reach the top.
    The total cost is 15.

    Example 2:
    Input: cost = [1,100,1,1,1,100,1,1,100,1]
    Output: 6
    Explanation: You will start at index 0.
    - Pay 1 and climb two steps to reach index 2.
    - Pay 1 and climb two steps to reach index 4.
    - Pay 1 and climb two steps to reach index 6.
    - Pay 1 and climb one step to reach index 7.
    - Pay 1 and climb two steps to reach index 9.
    - Pay 1 and climb one step to reach the top.
    The total cost is 6.

    Constraints:
    2 ≤ cost.length ≤ 1000
    0 ≤ cost[i] ≤ 999

     */

    static int minCostClimbingStairs(int[] cost) {
        // Obtém o número total de degraus na escada
        int n = cost.length;

        // Cria um array dp de tamanho (n + 1) para armazenar o custo mínimo para cada degrau
        // dp[i] representará o custo mínimo para alcançar o degrau i
        int[] dp = new int[n + 1];

        // Inicializa o custo para alcançar o degrau 0 como 0, já que começamos antes do primeiro degrau
        dp[0] = 0;

        // Inicializa o custo para alcançar o degrau 1 como 0, pois podemos escolher iniciar no degrau 0 ou 1 sem custo
        dp[1] = 0;

        // Itera a partir do degrau 2 até o degrau n (topo da escada)
        for (int i = 2; i <= n; i++) {
            /*
             * Calcula o custo mínimo para alcançar o degrau i.
             * Existem duas opções para chegar ao degrau i:
             * 1. Subir um degrau a partir do degrau (i - 1) e pagar o custo de cost[i - 1].
             * 2. Subir dois degraus a partir do degrau (i - 2) e pagar o custo de cost[i - 2].
             * Escolhemos a opção com o menor custo.
             */
            dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        // Retorna o custo mínimo para alcançar o topo da escada, que está armazenado em dp[n]
        return dp[n];
    }

    static int minCostClimbingStairs2(int[] cost) {
        // Obtém o número total de degraus na escada
        int n = cost.length;

        // Inicializa duas variáveis para armazenar os custos mínimos dos dois degraus anteriores
        // 'first' representa dp[i - 2] e 'second' representa dp[i - 1]
        int first = 0;
        int second = 0;

        // Itera a partir do degrau 2 até o degrau n (topo da escada)
        for (int i = 2; i <= n; i++) {
            /*
             * Calcula o custo mínimo para alcançar o degrau i.
             * Similar à abordagem com array, mas usando apenas duas variáveis para otimizar o espaço.
             * 'current' armazena o custo mínimo para o degrau atual.
             */
            int current = Math.min(second + cost[i - 1], first + cost[i - 2]);

            // Atualiza 'first' e 'second' para os próximos degraus
            first = second;    // 'first' agora representa dp[i - 1]
            second = current;  // 'second' agora representa dp[i]
        }

        // Retorna o custo mínimo para alcançar o topo da escada, armazenado em 'second'
        return second;
    }

    static void testMinCostClimbingStairs(int[] cost) {
        System.out.println("\nInput: cost = " + arrayToString(cost));
        long[] times = new long[2];
        int[] results = new int[2];

        // Medir o tempo para minCostClimbingStairs
        long start = nanoTime();
        results[0] = minCostClimbingStairs(cost);
        times[0] = nanoTime() - start;

        // Medir o tempo para minCostClimbingStairs2
        start = nanoTime();
        results[1] = minCostClimbingStairs2(cost);
        times[1] = nanoTime() - start;

        // Verificar se os resultados são iguais
        boolean correct = results[0] == results[1];
        System.out.println("minCostClimbingStairs result: " + results[0]);
        System.out.println("minCostClimbingStairs2 result: " + results[1]);
        System.out.println("Resultados iguais: " + correct);

        int fastest = 0, slowest = 0;

        if (times[1] < times[0]) fastest = 1;
        else slowest = 1;

        // Calcular a razão de desempenho
        double ratio = (double) times[slowest] / times[fastest];
        System.out.println("minCostClimbingStairs runtime: " + times[0] + " ns");
        System.out.println("minCostClimbingStairs2 runtime: " + times[1] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: minCostClimbingStairs" + (fastest + 1));
        System.out.println();
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    static void main(String[] ignoredArgs) {
        // Exemplo 1
        int[] cost1 = {10, 15, 20};
        testMinCostClimbingStairs(cost1);
        // Saída Esperada: 15

        // Exemplo 2
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        testMinCostClimbingStairs(cost2);
        // Saída Esperada: 6

        // Teste Adicional
        int[] cost3 = {0, 0, 0, 0};
        testMinCostClimbingStairs(cost3);
        // Saída Esperada: 0

        // Teste com Custos Altos
        int[] cost4 = {999, 999, 999, 999, 999};
        testMinCostClimbingStairs(cost4);
        // Saída Esperada: 1998

        // Teste com um Grande Número de Degraus
        int[] cost5 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            cost5[i] = i % 100; // Custos variando entre 0 e 99
        }
        testMinCostClimbingStairs(cost5);
        // Saída: Depende dos valores, mas deve executar rapidamente
    }
}