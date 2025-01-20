package Recursividade.exercicios;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.nanoTime;
import static java.util.List.of;

public class Reverso {

    /*

    Problema "reverse"

    Faça uma função que receba uma lista (de qualquer tipo) e retorne a lista reversa.

    Exemplo 1:
    Entrada: []
    Saída: []

    Exemplo 2:
    Entrada: ["azul"]
    Saída: ["azul"]

    Exemplo 3:
    Entrada: ["azul", "verde", "preto", "rosa"]
    Saída: ["rosa", "preto, "verde", "azul"]

    Assinaturas: static <T> List<T> reverse(List<T> list)

     */

    static <T> List<T> reverseRec(List<T> list) {
        // Caso base: se a lista tiver 0 ou 1 elementos, retorna uma nova lista mutável
        if (list.size() <= 1) return new ArrayList<>(list);

        // Pega o primeiro elemento
        T first = list.getFirst();

        // Chamada recursiva para reverter o restante
        List<T> reversed = reverseRec(list.subList(1, list.size()));

        // Adiciona o primeiro elemento ao final da lista reversa
        reversed.add(first);

        return reversed;
    }

    static <T> List<T> reverseIter(List<T> list) {
        List<T> result = new ArrayList<>(list.size());

        // Percorre a lista de trás para frente e adiciona em uma nova lista
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }

        return result;
    }

    static <T> void testReverse(List<T> input) {
        System.out.println("Input: " + input);

        long[] times = new long[2];
        List<List<T>> results = new ArrayList<>(2);
        // Inicializa a lista com dois elementos nulos
        results.add(null);
        results.add(null);

        // Teste da versão recursiva
        long start = nanoTime();
        List<T> reversedRec = reverseRec(input);
        times[0] = nanoTime() - start;
        results.set(0, reversedRec);

        // Teste da versão iterativa
        start = nanoTime();
        List<T> reversedIter = reverseIter(input);
        times[1] = nanoTime() - start;
        results.set(1, reversedIter);

        // Exibe resultados
        System.out.println("reverseRec  result: " + results.get(0));
        System.out.println("reverseIter result: " + results.get(1));

        // Descobre qual foi mais rápido
        int fastest = 0, slowest = 1;
        if (times[1] < times[0]) {
            fastest = 1;
            slowest = 0;
        }

        double ratio = (double) times[slowest] / times[fastest];
        System.out.println("reverseRec  runtime: " + times[0] + " ns");
        System.out.println("reverseIter runtime: " + times[1] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: " + (fastest == 0 ? "reverseRec" : "reverseIter"));
        System.out.println();
    }


    static void main(String[] ignoredArgs) {
        // Exemplos de listas para teste.
        // Usamos List.of(...) mas lembrando que isso cria listas imutáveis.
        // Isso não é um problema porque passamos essa lista como "entrada",
        // e as funções reverseRec/reverseIter vão criar novas listas mutáveis.

        List<List<String>> testCases = of(
                of(), // Lista vazia
                of("azul"), // Lista com 1 elemento
                of("azul", "verde", "preto", "rosa"), // Vários elementos
                of("a", "b", "c", "d", "e", "f", "g") // Mais elementos
        );

        // Executa os testes
        for (List<String> testCase : testCases) {
            testReverse(testCase);
        }
    }
}
