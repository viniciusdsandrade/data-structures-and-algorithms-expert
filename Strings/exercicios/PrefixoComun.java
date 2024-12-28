package Strings.exercicios;

import java.util.Arrays;

import static java.lang.System.nanoTime;

public class PrefixoComun {

    /*

    Problema "prefixo-comum" (ref: Leetcode longest-common-prefix)
    Empresas: Apple, Google, Amazon, Microsoft, Adobe, Accenture, etc.

    Write a function to find the longest common prefix string amongst an array of strings.
    If there is no common prefix, return an empty string "".

    Constraints:
        ● 1 <= strs.length <= 200
        ● 0 <= strs[i].length <= 200
        ● strs[i] consists of only lowercase English letters.

    Exemplo 1:
    Entrada: ["flowers","flow","flight"]
    Saída: "fl"

    Exemplo 2:
    Entrada: ["dog","racecar","car"]
    Saída: ""

    Assinatura: public static String longestCommonPrefix(String[] v)

     */

    public static String longestCommonPrefix(String[] v) {
        // Verifica se o array é nulo ou vazio
        // Se for, retorna uma string vazia, pois não há prefixos para comparar
        if (v == null || v.length == 0) return "";

        // Inicializa o prefixo com a primeira string do array
        // Esta será nossa string de referência para comparação
        String prefixo = v[0];

        // Itera sobre todas as strings do array a partir da segunda posição (índice 1)
        // Compara cada string com o prefixo atual
        for (int i = 1; i < v.length; i++) {

            // Enquanto a string atual não começar com o prefixo
            // Remove um caractere por vez do final até encontrar um prefixo válido
            while (!v[i].startsWith(prefixo))
                prefixo = prefixo.substring(0, prefixo.length() - 1);

            // Se o prefixo ficar vazio durante o processo
            // significa que não há prefixo comum entre as strings
            if (prefixo.isEmpty()) return "";
        }

        // Retorna o maior prefixo comum encontrado entre todas as strings
        return prefixo;
    }

    static void main(String[] ignoredArgs) {
        String[] v1 = {"flowers", "flow", "flight"};
        testLongestCommunPrefix(v1);

        String[] v2 = {"dog", "racecar", "car"};
        testLongestCommunPrefix(v2);
    }

    static void testLongestCommunPrefix(String[] v) {
        System.out.println("\nInput:   " + Arrays.toString(v));

        long startTime, endTime, runtime;
        String result;

        startTime = nanoTime();
        result = longestCommonPrefix(v);
        endTime = nanoTime();

        runtime = endTime - startTime;

        System.out.println("Output:  " + result);
        System.out.println("Runtime: " + runtime + "ns\n");
    }
}
