package exercicios;

import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.System.nanoTime;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class Votacao {

    /*

    Problema "votacao"

    Na contagem de votos de uma eleição, são gerados vários registros de votação contendo o nome do candidato
    e a quantidade de votos (formato .csv) que ele obteve em uma urna de votação. Você deve fazer uma função para receber
    todos os registros de votação das urnas, e daí retornar um consolidado com os totais de cada candidato.
    O resultado pode ser mostrado em qualquer ordem.

    Exemplo:
    Entrada
    Saída
    [
        "Alex Blue,15",
        "Maria Green,22",
        "Bob Brown,21",
        "Alex Blue,30",
        "Bob Brown,15",
        "Maria Green,27",
        "Maria Green,22",
        "Bob Brown,25",
        "Alex Blue,31"
    ]
    [
        "Alex Blue,76",
        "Maria Green,71",
        "Bob Brown,61"
    ]

    Assinatura: public static List<String> counting(List<String> votes)

     */

    // Implementation using TreeMap for sorted output
    static List<String> counting(List<String> votes) {
        // Cria um TreeMap para armazenar os votos
        // TreeMap foi escolhido em vez de HashMap, pois mantém as chaves ordenadas alfabeticamente
        // A chave (String) é o nome do candidato e o valor (Integer) é o total de votos
        Map<String, Integer> map = new TreeMap<>();

        // Itera sobre cada voto na lista de entrada
        for (String vote : votes) {
            // Divide a string do voto em um array usando a vírgula como separador
            // fields[0] contém o nome do candidato
            // fields[1] contém a quantidade de votos como string
            String[] fields = vote.split(",");

            // Extrai o nome do candidato do array
            String candidate = fields[0];

            // Converte a string de votos para um número inteiro
            int count = parseInt(fields[1]);

            // Usa o metodo merge do Map para somar os votos
            // Se o candidato não existe no map, count será o valor inicial
            // Se já existe, soma o count atual com o valor existente usando Integer::sum
            // Integer::sum é uma referência ao metodo que soma dois inteiros
            map.merge(candidate, count, Integer::sum);
        }

        // Cria uma nova lista para armazenar o resultado final
        List<String> result = new ArrayList<>();

        // Itera sobre todas as entradas do map (pares chave-valor)
        // Como usamos TreeMap, a iteração acontece em ordem alfabética dos candidatos
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            // Para cada entrada, cria uma string no formato "candidato,totalVotos"
            // e adiciona à lista de resultado
            result.add(entry.getKey() + "," + entry.getValue());
        }

        // Retorna a lista final contendo uma string por candidato
        // com seu respectivo total de votos
        return result;
    }

    // Implementation using HashMap and Stream
    static List<String> counting2(List<String> votes) {
        Map<String, Integer> map = new HashMap<>();

        for (String vote : votes) {
            String[] fields = vote.split(",");
            String candidate = fields[0];
            int count = parseInt(fields[1]);
            map.put(candidate, map.getOrDefault(candidate, 0) + count);
        }

        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "," + entry.getValue())
                .collect(toList());
    }

    // Implementation using pure streams
    static List<String> counting3(List<String> votes) {
        return votes.stream()
                .map(vote -> vote.split(","))
                .collect(groupingBy(
                        fields -> fields[0],
                        summingInt(fields -> parseInt(fields[1]))))
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + "," + entry.getValue())
                .collect(toList());
    }

    static void testCounting(List<String> votes) {
        System.out.println("Input: " + votes);
        long[] times = new long[3];
        List<String>[] results = new List[3];

        long start = nanoTime();
        results[0] = counting(votes);
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = counting2(votes);
        times[1] = nanoTime() - start;

        start = nanoTime();
        results[2] = counting3(votes);
        times[2] = nanoTime() - start;

        System.out.println("counting1 result: " + results[0]);
        System.out.println("counting2 result: " + results[1]);
        System.out.println("counting3 result: " + results[2]);

        int fastest = 0, slowest = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] < times[fastest]) {
                fastest = i;
            }
            if (times[i] > times[slowest]) {
                slowest = i;
            }
        }

        double ratio = (double) times[slowest] / times[fastest];
        System.out.println("counting1 runtime: " + times[0] + " ns");
        System.out.println("counting2 runtime: " + times[1] + " ns");
        System.out.println("counting3 runtime: " + times[2] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: counting" + (fastest + 1));
        System.out.println();
    }

    public static void main(String[] args) {
        List<List<String>> testCases = asList(
                // Basic test case
                asList(
                        "John,3",
                        "Jane,2",
                        "John,1",
                        "Jane,1"
                ),
                // Single candidate
                asList(
                        "Alice,1",
                        "Alice,2",
                        "Alice,3"
                ),
                // Many candidates with single votes
                asList(
                        "A,1",
                        "B,1",
                        "C,1",
                        "D,1"
                ),
                // Large numbers
                asList(
                        "Candidate1,1000",
                        "Candidate2,2000",
                        "Candidate1,3000"
                ),
                // Large test case
                generateLargeTestCase(100)
        );

        // Run tests
        for (List<String> testCase : testCases) {
            testCounting(testCase);
        }
    }

    // Helper method to generate a large test case
    private static List<String> generateLargeTestCase(int size) {
        List<String> votes = new ArrayList<>();
        Random random = new Random();
        String[] candidates = {"Alice", "Bob", "Charlie", "David", "Eve"};

        for (int i = 0; i < size; i++) {
            String candidate = candidates[random.nextInt(candidates.length)];
            int voteCount = random.nextInt(100) + 1;
            votes.add(candidate + "," + voteCount);
        }

        return votes;
    }
}
