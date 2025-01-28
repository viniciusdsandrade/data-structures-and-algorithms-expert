package Strings.exercicios;

public class RotateString {

    /*

    Problema "rotateString"

    Função que recebe uma String s e um inteiro k e retorna a string s rotacionada k vezes à direita.

    Exemplo 1:
    Entrada: "abcdefg", 3
    Saída:   "efgabcd"

    Exemplo 2:
    Entrada: "abcdefg", 2
    Saída:   "fgabcde"
     */

    static String rotateString(String s, int k) {
        // Verifica se a string é nula ou vazia. Se for, retorna a string original sem alterações.
        if (s == null || s.isEmpty()) return s;

        // Se o valor de k for 0, não há necessidade de rotacionar a string. Retorna a string original.
        if (k == 0) return s;

        // Se k for negativo, ajusta o valor de k para uma rotação equivalente à direita.
        // Adiciona o comprimento da string a k para torná-lo positivo.
        if (k < 0) return rotateString(s, s.length() + k);

        // Se k for maior que o comprimento da string, utiliza o módulo para reduzir k.
        // Isso evita rotações desnecessárias, já que rotacionar uma string n vezes é o mesmo que não rotacionar.
        if (k > s.length()) k = k % s.length();

        // Realiza a rotação da string:
        // - Obtém a substring dos últimos k caracteres da string.
        // - Concatena essa substring com a parte restante da string (do início até o ponto de rotação).
        return s.substring(s.length() - k) + s.substring(0, s.length() - k);
    }

    static String rotateStringWithDebug(String s, int k) {
        System.out.println("Iniciando a rotação da string.");
        System.out.println("String original: \"" + s + "\"");
        System.out.println("Valor de k: " + k);

        // Verifica se a string é nula ou vazia. Se for, retorna a string original sem alterações.
        if (s == null || s.isEmpty()) {
            System.out.println("A string é nula ou vazia. Retornando a string original.");
            return s;
        }

        // Se o valor de k for 0, não há necessidade de rotacionar a string. Retorna a string original.
        if (k == 0) {
            System.out.println("O valor de k é 0. Não há necessidade de rotacionar. Retornando a string original.");
            return s;
        }

        // Se k for negativo, ajusta o valor de k para uma rotação equivalente à direita.
        // Adiciona o comprimento da string a k para torná-lo positivo.
        if (k < 0) {
            System.out.println("O valor de k é negativo. Ajustando k para uma rotação equivalente à direita.");
            int newK = s.length() + k;
            System.out.println("Novo valor de k após ajuste: " + newK);
            return rotateStringWithDebug(s, newK);
        }

        // Se k for maior que o comprimento da string, utiliza o módulo para reduzir k.
        // Isso evita rotações desnecessárias, já que rotacionar uma string n vezes é o mesmo que não rotacionar.
        if (k > s.length()) {
            System.out.println("O valor de k é maior que o comprimento da string. Reduzindo k usando módulo.");
            k = k % s.length();
            System.out.println("Novo valor de k após módulo: " + k);
        }

        // Realiza a rotação da string:
        // - Obtém a substring dos últimos k caracteres da string.
        // - Concatena essa substring com a parte restante da string (do início até o ponto de rotação).
        String part1 = s.substring(s.length() - k);
        String part2 = s.substring(0, s.length() - k);
        System.out.println("Substring dos últimos " + k + " caracteres: \"" + part1 + "\"");
        System.out.println("Substring da parte restante: \"" + part2 + "\"");
        String rotatedString = part1 + part2;
        System.out.println("String rotacionada: \"" + rotatedString + "\"");
        System.out.println("Rotação concluída.\n");
        return rotatedString;
    }

    static void main(String[] ignoredArgs) {
        System.out.println("Teste 1:");
        rotateStringWithDebug("abcdefg", 3); // Saída esperada: "efgabcd"

        System.out.println("Teste 2:");
        rotateStringWithDebug("abcdefg", 2); // Saída esperada: "fgabcde"

        System.out.println("Teste 3:");
        rotateStringWithDebug("abcdefg", -2); // Rotação à esquerda equivalente a k = 5 ⇾ Saída: "cdefgab"

        System.out.println("Teste 4:");
        rotateStringWithDebug("abcdefg", 0); // Sem rotação, saída: "abcdefg"

        System.out.println("Teste 5:");
        rotateStringWithDebug("", 3); // String vazia, saída: ""

        System.out.println("Teste 6:");
        rotateStringWithDebug(null, 3); // String nula, saída: null

        System.out.println("Teste 7:");
        rotateStringWithDebug("abcdefg", 10); // k > comprimento, k = 3 ⇾ Saída: "efgabcd"
    }
}
