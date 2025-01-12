package exercicios;


import java.util.Arrays;

import static java.lang.Math.min;
import static java.lang.System.nanoTime;
import static java.util.Arrays.fill;

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

    static int coinChange(int[] coins, int amount) {
        // Cria um array dp de tamanho amount+1, onde cada índice i representa um valor monetário
        // e dp[i] armazenará o número mínimo de moedas necessárias para formar o valor i
        int[] dp = new int[amount + 1];

        // Preenche o array dp com um valor maior que o máximo possível (amount + 1)
        // Este valor serve como "infinito" para o algoritmo, indicando valores ainda não processados
        fill(dp, amount + 1);

        // Define que para formar o valor 0 são necessárias 0 moedas
        // Este é o caso base do algoritmo de programação dinâmica
        dp[0] = 0;

        // Itera de 1 até amount, construindo as soluções de forma bottom-up
        // Para cada valor i, vamos descobrir o número mínimo de moedas necessárias
        for (int i = 1; i <= amount; i++) {
            // Para cada valor i, testamos todas as moedas disponíveis
            // Tentamos adicionar cada moeda à solução ótima do subproblema (i - coin)
            for (int coin : coins) {
                // Só podemos usar a moeda se ela não exceder o valor atual i
                // i - coin ≥ 0 garante que não tentaremos acessar índices negativos no array
                if (i - coin >= 0) {
                    // dp[i - coin] representa a solução ótima para o valor (i - coin)
                    // Adicionamos 1 para contar a moeda atual
                    // min() compara essa nova solução com a melhor solução encontrada até agora
                    dp[i] = min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // Se dp[amount] continuar maior que amount, significa que não encontramos solução
        // Neste caso, retornamos −1. Caso contrário, retornamos o número mínimo de moedas
        return dp[amount] > amount ? -1 : dp[amount];
    }

    static void testCoinChange(int[] coins, int amount) {
        System.out.println("\nInput:   coins = " + Arrays.toString(coins) + ", amount = " + amount);

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
        int[] coins = {1, 2, 5};
        int amount = 11;
        testCoinChange(coins, amount);

        int amount2 = 3;
        int[] coins2 = {2};
        testCoinChange(coins2, amount2);

        int amount3 = 0;
        int[] coins3 = {1};
        testCoinChange(coins3, amount3);
    }
}
