package Strings.exercicios;

import static java.lang.System.nanoTime;
import static java.lang.System.out;

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

    static void testIsAnagram(String s, String t) {
        out.println("\nInput: " + s + ", " + t);

        long startTime, endTime, runtTime;
        boolean result;

        startTime = nanoTime();
        result = isAnagram(s, t);
        endTime = nanoTime();

        runtTime = endTime - startTime;

        out.println("Output: " + result);
        out.println("Runtime: " + runtTime + " ns");
    }

    static void main(String[] ignoredArgs) {
        testIsAnagram("anagram", "nagaram");
        testIsAnagram("rat", "car");
    }
}
