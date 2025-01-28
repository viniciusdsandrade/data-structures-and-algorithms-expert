package Recursividade.exercicios;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.System.nanoTime;
import static java.util.List.of;

public class MesclarListas {

    /*

    Problema "mesclar-listas"
    Faça uma função recursiva que mescle os elementos de duas listas A e B, retornando a lista resultante. As listas A e B podem ser de qualquer tipo, mas as listas A e B devem ser do mesmo tipo. As listas não precisam ser do mesmo tamanho.

    Exemplo 1:

    Entrada
        {
            "a": [10, 20, 30],
            "b": [5, 8, 7]
        }
    Saída: [10, 5, 20, 8, 30, 7]


    Exemplo 2:

    Entrada
        {
            "a": ["ana", "maria"],
            "b": ["joao", "bob", "alex", "leo"]
        }
    Saída: ["ana", "joao", "maria", "bob", "alex", "leo"]


    Assinaturas: static <T> List<T> mergeLists(List<T> a, List<T> b)

     */

    // Versão 1: Recursiva (O(n + m))
    static <T> List<T> mergeListsRec(List<T> a, List<T> b) {
        if (a.isEmpty()) return new ArrayList<>(b);
        if (b.isEmpty()) return new ArrayList<>(a);

        // Pega o primeiro de cada lista
        T firstA = a.getFirst();
        T firstB = b.getFirst();

        // Cria a lista com primeiro de A e primeiro de B
        List<T> merged = new ArrayList<>();
        merged.add(firstA);
        merged.add(firstB);

        // Faz a chamada recursiva para o restante
        // subList(1, a.size()) não cria cópia profunda; mas é suficiente para nosso exemplo
        merged.addAll(mergeListsRec(a.subList(1, a.size()), b.subList(1, b.size())));
        return merged;
    }

    // Versão 2: Iterativa Simples (O(n + m))
    // Percorre ambas as listas e vai adicionando alternadamente seus elementos.
    static <T> List<T> mergeListsIter(List<T> a, List<T> b) {
        List<T> merged = new ArrayList<>();
        int i = 0, j = 0;

        // Enquanto houver elementos em A e B, adicione alternadamente
        while (i < a.size() && j < b.size()) {
            merged.add(a.get(i++));
            merged.add(b.get(j++));
        }

        while (i < a.size()) merged.add(a.get(i++)); // Se ainda houver elementos em A, adiciona o restante
        while (j < b.size()) merged.add(b.get(j++)); // Se ainda houver elementos em B, adiciona o restante

        return merged;
    }

    // Versão 3: “Alternada” por índice (também O(n + m))
    // Parecida com a iterativa simples, mas demonstra outra forma de intercalar.
    static <T> List<T> mergeListsIndex(List<T> a, List<T> b) {
        List<T> merged = new ArrayList<>();
        int maxSize = max(a.size(), b.size());

        for (int i = 0; i < maxSize; i++) {
            if (i < a.size()) merged.add(a.get(i));
            if (i < b.size()) merged.add(b.get(i));
        }
        return merged;
    }

    static <T> void testMergeLists(List<T> a, List<T> b) {
        System.out.println("A = " + a);
        System.out.println("B = " + b);

        // Armazena tempos e resultados
        long[] times = new long[3];
        List<List<T>> results = new ArrayList<>(3);

        // Inicializa a lista de resultados com elementos nulos
        for (int i = 0; i < 3; i++) {
            results.add(null);
        }

        // Definição das complexidades (todas são O(n + m) para fins didáticos)
        String[] complexities = {
                "O(n + m)",  // mergeListsRec
                "O(n + m)",  // mergeListsIter
                "O(n + m)"   // mergeListsIndex
        };

        // Versão 1: Recursiva
        long start = nanoTime();
        results.set(0, mergeListsRec(a, b));
        times[0] = nanoTime() - start;

        // Versão 2: Iterativa
        start = nanoTime();
        results.set(1, mergeListsIter(a, b));
        times[1] = nanoTime() - start;

        // Versão 3: “Alternada” por índice
        start = nanoTime();
        results.set(2, mergeListsIndex(a, b));
        times[2] = nanoTime() - start;

        // Verifica se todas retornaram o mesmo resultado
        boolean allEqual = areAllEqual(results.get(0), results.get(1), results.get(2));
        if (!allEqual) {
            System.out.println("ERRO: As versões retornaram resultados diferentes!");
            System.out.println("mergeListsRec : " + results.get(0));
            System.out.println("mergeListsIter: " + results.get(1));
            System.out.println("mergeListsIndex: " + results.get(2));
        } else {
            System.out.println("Resultado Mesclado: " + results.getFirst());

            int fastestIndex = 0;
            for (int i = 1; i < times.length; i++) {
                if (times[i] < times[fastestIndex]) {
                    fastestIndex = i;
                }
            }

            System.out.println("Tempos de execução (em nanossegundos):");
            for (int i = 0; i < times.length; i++) {
                double ratio = (double) times[i] / times[fastestIndex];
                System.out.printf("%s (%s): %d ns (%.2fx mais lento que o mais rápido)\n",
                        getMethodName(i), complexities[i], times[i], ratio);
            }

            System.out.println("Método mais rápido: " + getMethodName(fastestIndex)
                               + " (" + complexities[fastestIndex] + ")");
        }
        System.out.println("------------------------------------------------\n");
    }

    static <T> boolean areAllEqual(List<T> r1, List<T> r2, List<T> r3) {
        return r1.equals(r2) && r1.equals(r3);
    }

    static String getMethodName(int index) {
        return switch (index) {
            case 0 -> "mergeListsRec";
            case 1 -> "mergeListsIter";
            case 2 -> "mergeListsIndex";
            default -> "UnknownMethod";
        };
    }

    static void main(String[] ignoredArgs) {
        // Exemplos de testes:
        testMergeLists(of(10, 20, 30), of(5, 8, 7));
        testMergeLists(of("ana", "maria"), of("joao", "bob", "alex", "leo"));

        // Outros casos de teste
        testMergeLists(of(), of("um", "dois"));           // A vazio
        testMergeLists(of("x", "y", "z"), of());          // B vazio
        testMergeLists(of(1), of(2, 3, 4, 5, 6));         // A menor
        testMergeLists(of(1, 2, 3, 4, 5), of(10));        // B menor
    }
}
