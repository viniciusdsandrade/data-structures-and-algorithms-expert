import java.util.Arrays;

import static java.lang.System.nanoTime;

/**
 * Classe responsável por realizar a busca binária em arrays ordenados
 * e exibir os resultados com detalhamento das operações realizadas.
 *
 * <p>Este programa implementa duas versões da busca binária:
 * <ul>
 *   <li><strong>Versão Didática (`binarySearch`):</strong> Inclui prints para demonstrar o funcionamento passo a passo.</li>
 *   <li><strong>Versão Otimizada (`binarySearchSemPrint`):</strong> Sem prints, para medições de tempo mais precisas.</li>
 * </ul>
 *
 * <p>Inclui análises de complexidade algorítmica (Big O, Big Omega, Big Theta)
 * para cada método, além de métodos para executar testes com diferentes tamanhos de arrays.</p>
 */
public class BinarySearchOrdemLogaritmica {

    // Contador de operações (comparações)
    private static int operacaoCount = 0;
    private static final String indent = "  ";

    /**
     * Realiza a busca binária em um array ordenado para encontrar o elemento alvo.
     * Esta versão inclui prints para fins didáticos.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(log n)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(log n)</li>
     * </ul>
     * </p>
     *
     * <p>
     * <strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * A busca binária divide o espaço de busca pela metade a cada iteração, resultando
     * em uma complexidade temporal logarítmica. O espaço adicional utilizado é constante,
     * pois apenas algumas variáveis auxiliares são necessárias.
     * </p>
     *
     * @param array  Array ordenado onde será realizada a busca.
     * @param target Elemento a ser buscado.
     * @return Índice do elemento alvo no array ou -1 se não encontrado.
     */
    static int binarySearch(int[] array, int target) {
        operacaoCount = 0;

        System.out.println("\n→ Iniciando busca binária para o elemento " + target);

        int esquerda = 0;
        int direita = array.length - 1;
        int meio;

        while (esquerda <= direita) {
            meio = esquerda + (direita - esquerda) / 2;
            operacaoCount++;

            System.out.println(indent + "Comparação " + operacaoCount + ":");
            System.out.println(indent + "  Esquerda = " + esquerda + ", Direita = " + direita + ", Meio = " + meio);
            System.out.println(indent + "  Elemento no meio = " + array[meio]);

            if (array[meio] == target) {
                System.out.println(indent + "✓ Elemento encontrado no índice " + meio);
                return meio;
            }

            if (array[meio] < target) {
                System.out.println(indent + "↳ Elemento alvo é maior. Atualizando 'esquerda' para " + (meio + 1));
                esquerda = meio + 1;
            } else {
                System.out.println(indent + "↳ Elemento alvo é menor. Atualizando 'direita' para " + (meio - 1));
                direita = meio - 1;
            }
        }

        System.out.println(indent + "✗ Elemento não encontrado no array.");
        return -1;
    }

    /**
     * Realiza a busca binária em um array ordenado para encontrar o elemento alvo.
     * Esta versão não inclui prints, ideal para medições de tempo precisas.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(log n)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(log n)</li>
     * </ul>
     * </p>
     *
     * <p>
     * <strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * A lógica permanece a mesma da versão didática, mas sem a sobrecarga dos prints,
     * permitindo medições de tempo mais precisas. A complexidade permanece logarítmica
     * no tempo e constante no espaço.
     * </p>
     *
     * @param array  Array ordenado onde será realizada a busca.
     * @param target Elemento a ser buscado.
     * @return Índice do elemento alvo no array ou -1 se não encontrado.
     */
    static int binarySearchSemPrint(int[] array, int target) {
        operacaoCount = 0;

        int esquerda = 0;
        int direita = array.length - 1;
        int meio;

        while (esquerda <= direita) {
            meio = esquerda + (direita - esquerda) / 2;
            operacaoCount++;

            if (array[meio] == target) {
                return meio;
            }

            if (array[meio] < target) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return -1;
    }

    /**
     * Executa um teste de busca binária na versão didática (com prints).
     *
     * <p>
     * <strong>Descrição:</strong>
     * Este metodo prepara um array ordenado, define um elemento alvo para busca,
     * executa a busca binária didática e exibe os resultados, incluindo o número de operações
     * realizadas e o tempo de execução.
     * </p>
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li>Busca binária: O(log n)</li>
     *   <li>Preparação do array (ordenamento): O(n) - já ordenado</li>
     * </ul>
     * </p>
     *
     * <p>
     * <strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li>O(n) para armazenar o array.</li>
     * </ul>
     * </p>
     *
     * @param arraySize Tamanho do array a ser gerado para o teste.
     * @param target    Elemento a ser buscado no array.
     * @param exists    Indica se o elemento alvo está presente no array.
     */
    static void executarTesteDidatico(int arraySize, int target, boolean exists) {
        System.out.println("\n========= TESTE BUSCA BINÁRIA DIDÁTICA(" + arraySize + ") =========");

        // Gerar um array ordenado de tamanho 'arraySize'
        int[] array = gerarArrayOrdenado(arraySize, exists ? target : -1);

        // Definir o elemento alvo
        if (!exists) {
            // Se o elemento não deve existir, garantir que esteja fora do intervalo
            target = arraySize + 1;
        }

        // Exibir o array
        System.out.println("\nArray:");
        exibirMatriz(array);

        // Medir o tempo de execução
        long startTime = nanoTime();
        int resultado = binarySearch(array, target);
        long endTime = nanoTime();

        // Exibir o resultado
        System.out.println("\n=== RESULTADO FINAL ===");
        if (resultado != -1) {
            System.out.println("Elemento " + target + " encontrado no índice: " + resultado);
        } else {
            System.out.println("Elemento " + target + " não encontrado no array.");
        }
        System.out.println("Total de operações (comparações): " + operacaoCount);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
        System.out.println("=====================================================================================\n");
    }

    /**
     * Executa um teste de busca binária na versão otimizada (sem prints).
     *
     * <p>
     * <strong>Descrição:</strong>
     * Este metodo prepara um array ordenado, define um elemento alvo para busca,
     * executa a busca binária otimizada e exibe os resultados, incluindo o número de operações
     * realizadas e o tempo de execução.
     * </p>
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li>Busca binária: O(log n)</li>
     *   <li>Preparação do array (ordenamento): O(n) - já ordenado</li>
     * </ul>
     * </p>
     *
     * <p>
     * <strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li>O(n) para armazenar o array.</li>
     * </ul>
     * </p>
     *
     * @param arraySize Tamanho do array a ser gerado para o teste.
     * @param target    Elemento a ser buscado no array.
     * @param exists    Indica se o elemento alvo está presente no array.
     */
    static void executarTesteOtimizado(int arraySize, int target, boolean exists) {
        System.out.println("\n========= TESTE BUSCA BINÁRIA OTIMIZADA(" + arraySize + ") ==========================");

        // Gerar um array ordenado de tamanho 'arraySize'
        int[] array = gerarArrayOrdenado(arraySize, exists ? target : -1);

        // Definir o elemento alvo
        if (!exists) {
            // Se o elemento não deve existir, garantir que esteja fora do intervalo
            target = arraySize + 1;
        }

        // Medir o tempo de execução
        long startTime = nanoTime();
        int resultado = binarySearchSemPrint(array, target);
        long endTime = nanoTime();

        // Exibir o resultado
        System.out.println("\n=== RESULTADO FINAL ===");
        if (resultado != -1) {
            System.out.println("Elemento " + target + " encontrado no índice: " + resultado);
        } else {
            System.out.println("Elemento " + target + " não encontrado no array.");
        }
        System.out.println("Total de operações (comparações): " + operacaoCount);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
        System.out.println("=====================================================================================\n");
    }

    /**
     * Gera um array ordenado de tamanho especificado.
     *
     * <p>
     * <strong>Descrição:</strong>
     * Este metodo gera um array de inteiros ordenados de 0 a arraySize - 1.
     * Se 'insertTarget' for diferente de -1, o elemento alvo será inserido no array.
     * </p>
     *
     * @param arraySize    Tamanho do array a ser gerado.
     * @param insertTarget Elemento a ser inserido no array. Use -1 se não desejar inserir.
     * @return Array ordenado gerado.
     */
    static int[] gerarArrayOrdenado(int arraySize, int insertTarget) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = i;
        }

        // Se insertTarget estiver definido e não estiver no array, inserir mantendo a ordenação
        if (insertTarget >= 0 && insertTarget < arraySize) {
            array[insertTarget] = insertTarget; // Já está no array
        }

        return array;
    }

    /**
     * Exibe o conteúdo de um array de forma formatada.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * O(n) - percorre cada elemento do array uma vez.
     * </p>
     *
     * <p>
     * <strong>Complexidade Espacial:</strong>
     * O(1) - utiliza espaço constante além do array.
     * </p>
     *
     * @param array Array a ser exibido.
     */
    static void exibirMatriz(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Função principal que executa os testes de busca binária nas versões didática e otimizada.
     *
     * <p>
     * <strong>Descrição:</strong>
     * Executa múltiplos testes de busca binária com arrays de diferentes tamanhos e
     * elementos existentes ou não existentes, tanto na versão didática quanto na otimizada.
     * </p>
     *
     * @param ignoredArgs Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] ignoredArgs) {
        System.out.println("*** Demonstração de Complexidade Logarítmica O(log n) ***");
        System.out.println("Observe como o número de operações cresce logaritmicamente com o tamanho do array.");

        // Testes com arrays pequenos, médios e grandes para a versão didática
        executarTesteDidatico(10, 5, true);      // Pequeno: Elemento existente
        executarTesteDidatico(100, 50, true);    // Médio: Elemento existente
        executarTesteDidatico(1000, 999, true);  // Grande: Elemento existente
        executarTesteDidatico(10, 15, false);    // Pequeno: Elemento não existente
        executarTesteDidatico(100, 150, false);  // Médio: Elemento não existente
        executarTesteDidatico(1000, 1500, false);// Grande: Elemento não existente

        // Testes com arrays pequenos, médios e grandes para a versão otimizada
        executarTesteOtimizado(10, 5, true);      // Pequeno: Elemento existente
        executarTesteOtimizado(100, 50, true);    // Médio: Elemento existente
        executarTesteOtimizado(1000, 999, true);  // Grande: Elemento existente
        executarTesteOtimizado(10, 15, false);    // Pequeno: Elemento não existente
        executarTesteOtimizado(100, 150, false);  // Médio: Elemento não existente
        executarTesteOtimizado(1000, 1500, false);// Grande: Elemento não existente
    }
}
