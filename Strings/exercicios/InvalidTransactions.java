package Strings.exercicios;

import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.System.gc;
import static java.lang.System.nanoTime;

public class InvalidTransactions {

    /*

    1169. Invalid Transactions

    A transaction is possibly invalid if:

    the amount exceeds $1000, or;
    if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
    You are given an array of strings transaction where transactions[i] consists of comma-separated
    values representing the name, time (in minutes), amount, and city of the transaction.

    Return a list of transactions that are possibly invalid. You may return the answer in any order.

    Example 1:
    Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
    Output: ["alice,20,800,mtv","alice,50,100,beijing"]
    Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes,
    have the same name and is in a different city. Similarly, the second one is invalid too.

    Example 2:
    Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
    Output: ["alice,50,1200,mtv"]

    Example 3:
    Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
    Output: ["bob,50,1200,mtv"]

    Constraints:
    transactions.length <= 1000
    Each transactions[i] takes the form "{name},{time},{amount},{city}"
    Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
    Each {time} consists of digits, and represents an integer between 0 and 1000.
    Each {amount} consists of digits, and represents an integer between 0 and 2000.

     */

    // Classe auxiliar para parser as transações
    static class Transaction {
        String name;
        int time;
        int amount;
        String city;
        int originalIndex;

        Transaction(String transaction, int index) {
            String[] parts = transaction.split(",");
            this.name = parts[0];
            this.time = parseInt(parts[1]);
            this.amount = parseInt(parts[2]);
            this.city = parts[3];
            this.originalIndex = index;
        }
    }

    static List<String> invalidTransactions(String[] transactions) {
        // Obtém o tamanho do array de transações para usar nas iterações
        int n = transactions.length;

        // Cria um array de objetos Transaction para facilitar o acesso aos dados
        // Este array vai guardar as transações já parseadas em um formato mais fácil de manipular
        Transaction[] transaction = new Transaction[n];

        // Cria um Set para armazenar os índices das transações inválidas
        // Usa HashSet para evitar duplicatas e ter acesso O(1)
        Set<Integer> invalid = new HashSet<>();

        // Converte cada string de transação em um objeto Transaction
        // Isso é feito uma única vez para evitar parsing repetido
        for (int i = 0; i < n; i++) {
            // O índice original (i) é guardado para podermos recuperar a string original depois
            transaction[i] = new Transaction(transactions[i], i);
        }

        // Loop principal que verifica cada transação
        for (int i = 0; i < n; i++) {
            // Primeira regra de invalidação: valor acima de 1000
            // Se a transação tem valor maior que 1000, é automaticamente inválida
            if (transaction[i].amount > 1000) {
                invalid.add(i);
            }

            // Segunda regra: verifica se existem transações suspeitas do mesmo usuário
            // Para isso, precisa comparar com todas as outras transações
            for (int j = 0; j < n; j++) {
                // Verifica se:
                // 1. Não é a mesma transação (i != j)
                // 2. É do mesmo usuário (mesmo nome)
                // 3. Está em cidade diferente
                // 4. Ocorreu em um intervalo de 60 minutos
                if (i != j &&
                    transaction[i].name.equals(transaction[j].name) &&
                    !transaction[i].city.equals(transaction[j].city) &&
                    Math.abs(transaction[i].time - transaction[j].time) <= 60) {
                    // Se todas as condições são verdadeiras, ambas as transações são inválidas
                    invalid.add(i);
                    invalid.add(j);
                }
            }
        }

        // Cria a lista final de resultado
        List<String> result = new ArrayList<>();

        // Converte os índices armazenados de volta para as strings originais
        // usando o array original de transações
        for (int idx : invalid) {
            result.add(transactions[idx]);
        }

        return result;
    }

    // Segunda implementação usando HashMap para otimização
    static List<String> invalidTransactions2(String[] transactions) {
        Map<String, List<Transaction>> nameToTrans = new HashMap<>();
        Set<String> invalid = new HashSet<>();

        // Agrupa transações por nome
        for (int i = 0; i < transactions.length; i++) {
            Transaction t = new Transaction(transactions[i], i);
            nameToTrans.computeIfAbsent(t.name, _ -> new ArrayList<>()).add(t);
        }

        // Verifica transações inválidas
        for (String trans : transactions) {
            Transaction curr = new Transaction(trans, 0);

            // Regra 1: quantidade maior que 1000
            if (curr.amount > 1000) {
                invalid.add(trans);
                continue;
            }

            // Regra 2: mesma cidade em 60 minutos
            List<Transaction> sameName = nameToTrans.get(curr.name);
            for (Transaction t : sameName) {
                if (!t.city.equals(curr.city) &&
                    abs(t.time - curr.time) <= 60) {
                    invalid.add(trans);
                    invalid.add(transactions[t.originalIndex]);
                }
            }
        }

        return new ArrayList<>(invalid);
    }

    static void testInvalidTransactions(String[] transactions) {
        System.out.println("Input: " + Arrays.toString(transactions));

        List<List<String>> results = new ArrayList<>(2);
        results.add(null); // Inicializa com dois elementos
        results.add(null);
        long[] totalTimes = new long[2];
        final int WARMUP_RUNS = 5;
        final int TEST_RUNS = 10;

        // Aquecimento do JVM
        for (int i = 0; i < WARMUP_RUNS; i++) {
            invalidTransactions(transactions.clone());
            invalidTransactions2(transactions.clone());
        }

        // Testes reais
        for (int run = 0; run < TEST_RUNS; run++) {
            gc();
            long start = nanoTime();
            results.set(0, invalidTransactions(transactions.clone()));
            totalTimes[0] += nanoTime() - start;

            gc();
            start = nanoTime();
            results.set(1, invalidTransactions2(transactions.clone()));
            totalTimes[1] += nanoTime() - start;
        }

        // Calcula médias
        double[] avgTimes = new double[2];  // Mudado para double para maior precisão
        avgTimes[0] = totalTimes[0] / (double) TEST_RUNS;
        avgTimes[1] = totalTimes[1] / (double) TEST_RUNS;

        // Determina o mais rápido
        int fastest = 0, slowest = 0;
        if (avgTimes[1] < avgTimes[0]) {
            fastest = 1;
        } else {
            slowest = 1;
        }

        // Calcula a razão corretamente
        double ratio = avgTimes[slowest] / avgTimes[fastest];

        // Exibe resultados
        System.out.println("invalidTransactions1 result: " + results.get(0));
        System.out.println("invalidTransactions2 result: " + results.get(1));
        System.out.printf("Tempo médio invalidTransactions1: %.2f ns%n", avgTimes[0]);
        System.out.printf("Tempo médio invalidTransactions2: %.2f ns%n", avgTimes[1]);
        System.out.printf("Razão (lento/rápido): %.2fx%n", ratio);
        System.out.println("Método mais rápido: invalidTransactions" + (fastest + 1));
        System.out.println();
    }

    public static void main(String[] ignoredArgs) {
        // Casos de teste variados
        String[][] testCases = {
                // Caso básico
                {"alice,20,800,mtv", "alice,50,100,beijing"},
                // Caso com transação inválida por valor
                {"alice,20,800,mtv", "alice,50,1200,mtv"},
                // Caso com usuários diferentes
                {"alice,20,800,mtv", "bob,50,1200,mtv"},
                // Caso maior para teste de performance
                new String[100]
        };

        // Preenche o caso de teste maior
        for (int i = 0; i < testCases[3].length; i++) {
            testCases[3][i] = "user" + (i % 10) + "," +
                              (i * 10) + "," +
                              ((i % 2 == 0) ? 1200 : 800) + "," +
                              "city" + (i % 5);
        }

        // Executa os testes
        for (String[] testCase : testCases) {
            testInvalidTransactions(testCase);
        }
    }
}
