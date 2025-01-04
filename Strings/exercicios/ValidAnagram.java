package Strings.exercicios;

import java.util.Arrays;

import static java.lang.System.nanoTime;
import static java.lang.System.out;
import static java.util.Arrays.sort;

public class ValidAnagram {

    /*

    Problema "valid-anagram" (ref: Leetcode valid-anagram)

    Dadas duas palavras s e t, retornar true se t é um anagrama de s, ou false caso contrário.
    Um anagrama é um texto formado pelo rearanjo das letras em um texto diferente,
    tipicamente utilizando todas as letras originais exatamente uma vez.

    Limitações:
        ● 1 ≤ s.length, t.length ≤ 5 * 104
        ● s e t são letras minúsculas da Língua Inglesa.

    Exemplo 1:
    Entrada: anagram nagaram
    Saída:   true

    Exemplo 2:
    Entrada: rat car
    Saída:   false

    Assinatura: public static boolean isAnagram(String s, String t)

     */

    static boolean isAnagram(String s, String t) {
        // Verifica se as strings têm o mesmo tamanho
        // Se não tiverem, não podem ser anagramas
        if (s.length() != t.length()) return false;

        // Cria um array de 26 posições (uma para cada letra do alfabeto)
        // Cada posição vai armazenar a diferença de frequência das letras entre as strings
        int[] count = new int[26];

        // Percorre as strings simultaneamente
        // Para cada caractere da string s, incrementa o contador na posição correspondente
        // Para cada caractere da string t, decrementa o contador na posição correspondente
        // Ex: Para 'a', posição 0 (a - 'a' = 0)
        //     Para 'b', posição 1 (b - 'a' = 1)
        //     E assim por diante...
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        // Verifica se todas as posições do array count são zero
        // Se alguma posição for diferente de zero, significa que as strings
        // têm frequências diferentes de alguma letra, logo não são anagramas
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0)
                return false;
        }

        // Se chegou até aqui, significa que todas as letras têm a mesma
        // frequência nas duas strings, portanto são anagramas
        return true;
    }

    // Implementação alternativa usando ordenação
    static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        sort(sChars);
        sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    static void testIsAnagram(String s, String t) {
        out.println("Input 1: " + s);
        out.println("Input 2: " + t);

        long[] times = new long[2];
        boolean[] results = new boolean[2];

        // Teste da primeira implementação
        long start = nanoTime();
        results[0] = isAnagram(s, t);
        times[0] = nanoTime() - start;

        // Teste da segunda implementação
        start = nanoTime();
        results[1] = isAnagram2(s, t);
        times[1] = nanoTime() - start;

        int fastest = 0, slowest = 0;

        if (times[1] < times[0]) {
            fastest = 1;
        } else {
            slowest = 1;
        }

        double ratio = (double) times[slowest] / times[fastest];

        out.println("isAnagram1 result: " + results[0] + " runtime: " + times[0] + " ns");
        out.println("isAnagram2 result: " + results[1] + " runtime: " + times[1] + " ns");
        out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        out.println("Método mais rápido: isAnagram" + (fastest + 1));
        out.println();
    }

    public static void main(String[] ignoredArgs) {
        // Casos de teste variados
        String[][] testCases = {
                {"anagram", "nagaram"},          // Anagrama verdadeiro
                {"rat", "car"},                  // Não é anagrama
                {"", ""},                        // Strings vazias
                {"a", "a"},                      // String de um caractere
                {"aaaaaa", "aaaaaa"},           // Caracteres repetidos
                {"silent", "listen"},           // Outro anagrama verdadeiro
                {"hello", "world"},             // Mesmo tamanho, não anagrama
                {"pneumonoultramicroscopicsilicovolcanoconiosis",
                        "pneumonoultramicroscopicsilicovolcanoconiosis"}, // Palavra muito longa
                {"abcdefghijklmnopqrstuvwxyz",  // Todas as letras do alfabeto
                        "zyxwvutsrqponmlkjihgfedcba"}
        };

        // Executa os testes
        for (String[] testCase : testCases) {
            testIsAnagram(testCase[0], testCase[1]);
        }
    }
}
