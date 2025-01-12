package exercicios;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.lang.System.nanoTime;

public class CoinChange {

    /*

    322. Coin Change

    You are given an integer array coins representing coins of different denominations
    and an integer amount representing a total amount of money.

    Return the fewest number of coins that you need to make up that amount.
    If that amount of money cannot be made up by any combination of the coins, return -1.

    You may assume that you have an infinite number of each kind of coin.

    Example 1:
    Input: coins = [1,2,5], amount = 11
    Output: 3
    Explanation: 11 = 5 + 5 + 1

    Example 2:
    Input: coins = [2], amount = 3
    Output: -1

    Example 3:
    Input: coins = [1], amount = 0
    Output: 0

    Constraints:
    1 ≤ coins.length ≤ 12
    1 ≤ coins[i] ≤ 2^31 - 1
    0 ≤ amount ≤ 10^4

     */

    // Função que calcula o número mínimo de moedas necessárias para formar um valor
    static int coinChange(int[] coins, int amount) {
        // Cria um array para armazenar as soluções dos subproblemas
        // O índice representa o valor e o conteúdo representa o mínimo de moedas
        int[] dp = new int[amount + 1];

        // Itera por cada valor possível de 1 até o valor desejado
        for (int i = 1; i <= amount; i++) {
            // Inicializa cada posição com valor máximo
            // Isso ajuda a identificar valores impossíveis de serem formados
            dp[i] = MAX_VALUE;

            // Para cada valor i, testa todas as moedas disponíveis
            for (int coin : coins) {
                // Verifica se é possível usar a moeda atual (se não passa do valor)
                if (i - coin >= 0) {
                    // Atualiza dp[i] com o mínimo entre:
                    // - valor atual de dp[i]
                    // - número de moedas necessárias para (i-coin) + 1 moeda atual
                    dp[i] = min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // Retorna −1 se não for possível formar o valor (dp[amount] continua MAX_VALUE)
        // Caso contrário, retorna o número mínimo de moedas necessárias
        return dp[amount] == MAX_VALUE ? -1 : dp[amount];
    }

    static void testCoinChange(int[] coins, int amount) {
        System.out.println("\nInput:   coins = " + java.util.Arrays.toString(coins) + ", amount = " + amount);

        long startTime, endTime, runtime;
        int result;

        startTime = nanoTime();
        result = coinChange(coins, amount);
        endTime = nanoTime();

        runtime = endTime - startTime;

        System.out.println("Output:  " + result);
        System.out.println("Runtime: " + runtime + " ns");
    }

     static void main(String[] ignoredArgs) {
        testCoinChange(new int[]{1, 2, 5}, 11);
        testCoinChange(new int[]{2}, 3);
        testCoinChange(new int[]{1}, 0);
    }
}
