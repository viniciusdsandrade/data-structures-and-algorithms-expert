package Strings.exercicios;

public class LengthOfLastWord {

    /*

    58. Length of Last Word

    Given a string s consisting of words and spaces,
    return the length of the last word in the string.

    A word is a maximal substring
    consisting of non-space characters only.

    Example 1:
    Input: s = "Hello World"
    Output: 5
    Explanation: The last word is "World" with length 5.

    Example 2:
    Input: s = "   fly me   to   the moon  "
    Output: 4
    Explanation: The last word is "moon" with length 4.

    Example 3:
    Input: s = "luffy is still joyboy"
    Output: 6
    Explanation: The last word is "joyboy" with length 6.


    Constraints:
    1 ≤ s.length ≤ 104
    s consists of only English letters and spaces ' '.
    There will be at least one word in s.

     */

    // Esta função calcula o comprimento da última palavra em uma string.
    static int lengthOfLastWord(String s) {
        // Verifica se a string é nula ou vazia. Se sim, retorna 0 imediatamente.
        if (s == null || s.isEmpty()) return 0;

        int length = 0; // Inicializa a variável que armazenará o comprimento da última palavra.
        int i = s.length() - 1; // Inicia o índice 'i' no último caractere da string.

        // Primeiro loop: Ignora espaços em branco no final da string.
        // Percorre a string de trás para frente até encontrar um caractere que não seja espaço.
        while (i >= 0 && s.charAt(i) == ' ') i--;

        // Segundo loop: Conta os caracteres da última palavra.
        // Continua percorrendo de trás para frente enquanto houver caracteres não espaços.
        while (i >= 0 && s.charAt(i) != ' ') {
            length++; // Incrementa o contador de comprimento.
            i--;      // Move para o caractere anterior.
        }

        // Retorna o comprimento calculado da última palavra.
        return length;
    }

    static int lengthOfLastWordWithDebug(String s) {
        System.out.println("Iniciando o cálculo do comprimento da última palavra.");
        System.out.println("String original: \"" + s + "\"");

        // Verifica se a string é nula ou vazia. Se sim, retorna 0 imediatamente.
        if (s == null || s.isEmpty()) {
            System.out.println("A string é nula ou vazia. Retornando 0.");
            return 0;
        }

        // Inicializa a variável que armazenará o comprimento da última palavra.
        int length = 0;
        // Inicia o índice 'i' no último caractere da string.
        int i = s.length() - 1;
        System.out.println("Índice inicial (i): " + i);

        // Primeiro loop: Ignora espaços em branco no final da string.
        // Percorre a string de trás para frente até encontrar um caractere que não seja espaço.
        System.out.println("Ignorando espaços em branco no final da string...");
        while (i >= 0 && s.charAt(i) == ' ') {
            System.out.println("Caractere em i=" + i + " é espaço. Decrementando i.");
            i--;
        }

        if (i < 0) {
            System.out.println("A string contém apenas espaços. Retornando 0.");
            return 0;
        }

        // Segundo loop: Conta os caracteres da última palavra.
        // Continua percorrendo de trás para frente enquanto houver caracteres não espaços.
        System.out.println("Contando os caracteres da última palavra...");
        while (i >= 0 && s.charAt(i) != ' ') {
            System.out.println("Caractere em i=" + i + " é '" + s.charAt(i) + "'. Incrementando comprimento.");
            length++;
            i--;
        }

        // Retorna o comprimento calculado da última palavra.
        System.out.println("Comprimento da última palavra: " + length + "\n");
        return length;
    }

    static void main(String[] ignoredArgs) {
        System.out.println("Teste 1:");
        lengthOfLastWord("Hello World"); // Saída esperada: 5

        System.out.println("Teste 2:");
        lengthOfLastWord("   fly me   to   the moon  "); // Saída esperada: 4

        System.out.println("Teste 3:");
        lengthOfLastWord("luffy is still joyboy"); // Saída esperada: 6

        System.out.println("Teste 4:");
        lengthOfLastWord(""); // Saída esperada: 0

        System.out.println("Teste 5:");
        lengthOfLastWord(" "); // Saída esperada: 0

        System.out.println("Teste 6:");
        lengthOfLastWord(null); // Saída esperada: 0

        System.out.println("Teste 7:");
        lengthOfLastWord("a"); // Saída esperada: 1

        System.out.println("Teste 8:");
        lengthOfLastWord("a "); // Saída esperada: 1

        System.out.println("Teste 9:");
        lengthOfLastWord("day"); // Saída esperada: 3

        System.out.println("Teste 10:");
        lengthOfLastWord("day   "); // Saída esperada: 3
    }
}
