package exercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.compare;
import static java.lang.Math.max;
import static java.lang.System.nanoTime;
import static java.util.Arrays.asList;
import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;
import static java.util.regex.Pattern.compile;

public class WordCount {

    /*

    Problema "word-count"

    Você deve fazer uma função que recebe um texto, e retorna o número de ocorrências de cada palavra no texto. Cada ocorrência deve ser representada por um objeto do tipo Rank, com os campos word e count. A saída da função deve ser uma lista de objetos tipo Rank. Você deve mostrar o resultado em letras minúsculas, ordenado da palavra mais frequente para a menos frequente, e as palavras com mesma frequência devem aparecer ordenadas alfabeticamente.
    Antes de analisar as frequências das palavras, você deve aplicar a seguinte função normalize ao texto, para remover símbolos de pontuação, remover espaços adicionais e converter tudo para minúsculo:

    Java:
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;
    public static String normalize(String text) {
    Pattern pattern = Pattern.compile("[^\\p{L}\\p{N}\\s]", Pattern.UNICODE_CHARACTER_CLASS);
    Matcher matcher = pattern.matcher(text);
    String words = matcher.replaceAll(" ");
    return words.replaceAll("\\s+", " ").trim().toLowerCase();
    }

    Exemplo:
    Entrada
    Saída
    O vento sussurra sons entre as árvores, sons que fazem animais correrem. A floresta e a natureza vibram com segredos e sons.
    sons: 3
    a: 2
    e: 2
    animais: 1
    árvores: 1
    as: 1
    com: 1
    correrem: 1
    entre: 1
    fazem: 1
    floresta: 1
    natureza: 1
    o: 1
    que: 1

     */

    // Classe Rank para armazenar (palavra, contagem)
    record Rank(String word, int count) {

        @Override
        public String toString() {
            // Formato de saída: "palavra: contagem"
            return word + ": " + count;
        }
    }

    /** Normaliza o texto removendo pontuações, múltiplos espaços e convertendo para minúsculo. */
    static String normalize(String text) {
        Pattern pattern = compile("[^\\p{L}\\p{N}\\s]", UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(text);
        String words = matcher.replaceAll(" ");
        return words.replaceAll("\\s+", " ").trim().toLowerCase();
    }

    static List<Rank> wordCount(String text) {
        // 1. Normaliza o texto
        String normalizedText = normalize(text);

        // 2. Separa as palavras pelo espaço
        String[] words = normalizedText.split(" ");

        // 3. Conta as ocorrências de cada palavra em um Map
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String w : words) {
            if (!w.isEmpty()) {
                frequencyMap.put(w, frequencyMap.getOrDefault(w, 0) + 1);
            }
        }

        // 4. Converte o Map em uma lista de Rank
        List<Rank> ranks = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            ranks.add(new Rank(entry.getKey(), entry.getValue()));
        }

        // 5. Ordena a lista:
        //    - primeiro pela contagem (decrescente)
        //    - depois pela palavra (crescente, ou seja, alfabética)
        ranks.sort((r1, r2) -> {
            int cmp = compare(r2.count(), r1.count());
            if (cmp == 0) {
                return r1.word().compareTo(r2.word());
            }
            return cmp;
        });

        return ranks;
    }

    static void testWordCount(String text) {
        // Verifica se o texto é pequeno para exibir detalhes
        if (text.length() < 100) {
            System.out.println("Texto de entrada: " + text);
        } else {
            System.out.println("Texto de entrada grande com " + text.length() + " caracteres.");
        }

        // Medir tempo de execução
        long start = nanoTime();
        List<Rank> result = wordCount(text);
        long elapsed = nanoTime() - start;

        // Exibe resultado se for texto pequeno
        if (text.length() < 100) {
            System.out.println("Saída (palavra: contagem):");
            for (Rank r : result) {
                System.out.println(r);
            }
        }

        // Exibe tempo
        System.out.println("Tempo de execução: " + elapsed + " ns");
        System.out.println();
    }

    static String generateLargeText(int size) {
        String base = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ";
        StringBuilder sb = new StringBuilder();
        sb.append(base.repeat(max(0, size)));
        return sb.toString();
    }

    static void main(String[] ignoredArgs) {
        // Define alguns casos de teste
        List<String> testCases = asList(
                // Caso 1: Texto pequeno
                "O vento sussurra sons entre as árvores, sons que fazem animais correrem. A floresta e a natureza vibram com segredos e sons.",
                // Caso 2: Texto pequeno / médio
                "Um pequeno teste para verificar o runtime de wordCount!",
                // Caso 3: Texto grande
                generateLargeText(1000),  // 1000 repetições
                generateLargeText(5000)   // 5000 repetições
        );

        for (String text : testCases) {
            testWordCount(text);
        }
    }
}
