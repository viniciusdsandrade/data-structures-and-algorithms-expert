package Strings.exercicios;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class FrequencyWord {

     static HashMap<String, Integer> countTokenFrequency(String text) {
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        if (text == null || text.isEmpty()) return frequencyMap;

        text = text.toLowerCase();

        Pattern pattern = compile("([a-z]+(?:['-][a-z]+)*)|(\\p{Punct}+)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            // matcher.group() retorna o token completo (seja ele palavra ou pontuação)
            String token = matcher.group();
            frequencyMap.put(token, frequencyMap.getOrDefault(token, 0) + 1);
        }
        return frequencyMap;
    }

     static void main(String[] ignoredArgs) {
         String testText = "Hello!! ''hello-world-- Don't... it's a-test! Test? Test!";
         HashMap<String, Integer> frequency = countTokenFrequency(testText);
         System.out.println(frequency);
    }
}
