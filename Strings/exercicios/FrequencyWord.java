package Strings.exercicios;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class FrequencyWord {

    /**
     * Função que conta a frequência de cada token (palavra ou pontuação) presente em um texto.
     * Um token é definido como:
     * - Uma sequência de letras (a-z) possivelmente intercaladas por apóstrofos ou hífens, ou
     * - Uma sequência de caracteres de pontuação.
     *
     * @param text O texto de entrada para a contagem dos tokens.
     * @return Um HashMap onde cada chave é um token encontrado e o valor associado é a sua frequência.
     */
    static HashMap<String, Integer> countTokenFrequency(String text) {
        // Inicializa um HashMap para armazenar cada token e sua respectiva contagem.
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        // Verifica se o texto é nulo ou vazio.
        // Se for, retorna o mapa vazio, pois não há tokens para processar.
        if (text == null || text.isEmpty()) return frequencyMap;

        // Converte o texto para letras minúsculas para que a contagem não seja sensível a maiúsculas/minúsculas.
        text = text.toLowerCase();

        // Define o padrão (regex) para identificar os tokens no texto.
        // O padrão possui duas partes:
        // 1) ([a-z]+(?:['-][a-z]+)*) -> captura palavras formadas por letras (a-z) que podem conter apóstrofos ou hífens.
        // 2) (\\p{Punct}+) ⇾ captura sequências de caracteres de pontuação.
        Pattern pattern = compile("([a-z]+(?:['-][a-z]+)*)|(\\p{Punct}+)");

        // Cria um Matcher para encontrar todos os tokens no texto que correspondam ao padrão definido.
        Matcher matcher = pattern.matcher(text);

        // Itera enquanto o Matcher encontrar tokens que correspondam ao padrão.
        while (matcher.find()) {
            // Obtém o token completo encontrado (seja uma palavra ou uma sequência de pontuação).
            String token = matcher.group();
            // Atualiza a contagem do token no mapa:
            // Se o token já existe, incrementa sua contagem; caso contrário, adiciona-o com contagem 1.
            frequencyMap.put(token, frequencyMap.getOrDefault(token, 0) + 1);
        }

        // Retorna o mapa contendo os tokens e suas frequências.
        return frequencyMap;
    }

    static void main(String[] ignoredArgs) {
        String testText = "Hello!! ''hello-world-- Don't... it's a-test! Test? Test!";
        HashMap<String, Integer> frequency = countTokenFrequency(testText);
        System.out.println(frequency);
    }
}
