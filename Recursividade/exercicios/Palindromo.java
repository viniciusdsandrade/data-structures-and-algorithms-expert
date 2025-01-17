package Recursividade.exercicios;

public class Palindromo {

    /*

    Problema "palindromo"
    Um texto é palíndromo se seu inverso é igual ao texto original. Crie uma função para determinar se uma data string é um palíndromo. Utilize uma função auxiliar com recursividade de cauda com parâmetros adicionais para evitar a necessidade de se instanciar substrings repetidamente nas chamadas recursivas.

    Exemplo 1:
    Entrada
    Saída
    ""
    true

    Exemplo 2:
    Entrada
    Saída
    "aba"
    true

    Exemplo 3:
    Entrada
    Saída
    "abccba"
    true

    Exemplo 4:
    Entrada
    Saída
    "abcfba"
    false

    Assinaturas:
    Java: static boolean isPalindrome(String text)

     */

    static boolean isPalindrome(String text) {
        return isPalindrome(text, 0, text.length() - 1);
    }

    static boolean isPalindrome(String text, int start, int end) {
        if (start >= end) return true;
        if (text.charAt(start) != text.charAt(end)) return false;
        return isPalindrome(text, start + 1, end - 1);
    }

    static void main(String[] ignoredArgs) {
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome("aba"));
        System.out.println(isPalindrome("abccba"));
        System.out.println(isPalindrome("abcfba"));
    }
}
