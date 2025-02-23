import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.nanoTime;
import static java.util.Arrays.copyOf;

/*

    DESAFIO: Sort benchmark

    Forma de entrega: link do programa salvo no Gist do Github
    Linguagens aceitas: Javascript, Java, C#, Python
    Você deve criar um programa de teste para efetuar um benchmark (teste de performance) nos seguintes
    algoritmos de busca:
         Insertion sort
         Bubble sort
         Quick sort

    Seu programa deve ler um array de strings contido no Gist do link abaixo do exemplo de entrada. Seu
    programa deverá então efetuar a ordenação do array usando os três métodos de ordenação citados,
    medir o tempo de execução de cada um em milissegundos, e produzir um relatório conforme exemplo
    de saída abaixo.

    Exemplo:
    (favor pegar os dados no link) https://gist.github.com/acenelio/59418b088d462b5de89ca50844a0b0f1
    Entrada Saída
    Insertion sort: 166ms
    Bubble sort:    517ms
    Quick sort:     8ms

*/

public static void main(String[] ignoredArgs) {

    // Ajuste o caminho absoluto do seu arquivo JSON conforme necessário:
    String jsonPath = "C:\\Users\\Vinícius Andrade\\Desktop\\data-structures-and-algorithms-expert\\BuscaEOrdenacao\\desafio\\input.json";

    try {
        // 1. Ler o array de strings do arquivo JSON
        ObjectMapper mapper = new ObjectMapper();
        String[] originalArray = mapper.readValue(new File(jsonPath), String[].class);

        // Clonamos o array original para cada teste,
        String[] arrayForInsertion = copyOf(originalArray, originalArray.length);
        String[] arrayForBubble = copyOf(originalArray, originalArray.length);
        String[] arrayForQuick = copyOf(originalArray, originalArray.length);

        // 2. Medir tempo - Insertion Sort
        long startInsertion = nanoTime();
        insertionSort(arrayForInsertion);
        long endInsertion = nanoTime();

        long insertionTime = endInsertion - startInsertion;

        // 3. Medir tempo - Bubble Sort
        long startBubble = nanoTime();
        bubbleSort(arrayForBubble);
        long endBubble = nanoTime();

        long bubbleTime = endBubble - startBubble;

        // 4. Medir tempo - Quick Sort
        long startQuick = nanoTime();
        quickSort(arrayForQuick, 0, arrayForQuick.length - 1);
        long endQuick = nanoTime();

        long quickTime = endQuick - startQuick;

        // 5. Conversão para milissegundos com 3 casas decimais
        double insertionTimeMs = insertionTime / 1_000_000.0;
        double bubbleTimeMs = bubbleTime / 1_000_000.0;
        double quickTimeMs = quickTime / 1_000_000.0;

        // 6. Exibir relatório
        System.out.printf("Insertion sort: %.3f ms%n", insertionTimeMs);
        System.out.printf("Bubble sort: %.3f ms%n", bubbleTimeMs);
        System.out.printf("Quick sort: %.3f ms%n", quickTimeMs);
    } catch (IOException e) {
        System.err.println(e.getMessage());
    }
}

// ------------- MÉTODOS DE ORDENAÇÃO ----------------

// Insertion Sort
public static void insertionSort(String[] array) {
    for (int i = 1; i < array.length; i++) {
        String key = array[i];
        int j = i - 1;

        // Enquanto o elemento anterior for maior (lexicograficamente), empurra para frente
        while (j >= 0 && array[j].compareTo(key) > 0) {
            array[j + 1] = array[j];
            j--;
        }

        array[j + 1] = key;
    }
}

// Bubble Sort
public static void bubbleSort(String[] array) {
    int n = array.length;
    boolean swapped;

    for (int i = 0; i < n - 1; i++) {
        swapped = false;
        for (int j = 0; j < n - i - 1; j++) {
            if (array[j].compareTo(array[j + 1]) > 0) {
                // Troca
                String temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
                swapped = true;
            }
        }
        // Se não houve troca, significa que já está ordenado
        if (!swapped) break;
    }
}

// Quick Sort
public static void quickSort(String[] array, int left, int right) {
    if (left < right) {
        int pivotIndex = partition(array, left, right);
        quickSort(array, left, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, right);
    }
}

private static int partition(String[] array, int left, int right) {
    // Escolha simples de pivô (último elemento)
    String pivot = array[right];
    int i = left - 1;

    for (int j = left; j < right; j++) {
        // Se o elemento atual é menor que o pivô (lexicograficamente)
        if (array[j].compareTo(pivot) < 0) {
            i++;
            swap(array, i, j);
        }
    }
    swap(array, i + 1, right);
    return i + 1;
}

private static void swap(String[] array, int i, int j) {
    String temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}
