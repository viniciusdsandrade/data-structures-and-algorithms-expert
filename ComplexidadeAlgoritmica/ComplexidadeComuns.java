/**
 * Exemplo de complexidade O(1).
 * <p>
 * Como chegamos na complexidade:
 * - Executamos apenas uma operação simples (uma soma e/ou retorno de valor).
 * - Independente do valor de n, a quantidade de operações não se altera.
 * <p>
 * Complexidade temporal: O(1)
 * - Sempre a mesma quantidade de operações, independentemente do input.
 * <p>
 * Complexidade espacial: O(1)
 * - Utiliza apenas algumas variáveis locais, não cresce com a entrada.
 * <p>
 * Implicações técnicas:
 * - Métodos de O(1) são muito rápidos e não escalam conforme o tamanho do problema.
 * - Bom para acesso direto a um elemento em um array ou soma de duas variáveis, por exemplo.
 */
static int constantTimeExample(int a, int b) {
    // operação constante
    return a + b;
}

/**
 * Exemplo de complexidade O(log n).
 * <p>
 * Como chegamos na complexidade:
 * - Aqui, utilizamos um exemplo de busca binária em um array ordenado.
 * - A cada iteração, descartamos metade do array.
 * - Assim, o número de comparações cresce em proporção ao log2(n).
 * <p>
 * Complexidade temporal: O(log n)
 * - A cada passo, o espaço de busca é reduzido à metade.
 * <p>
 * Complexidade espacial: O(1)
 * - Só usamos algumas variáveis de controle (left, right, mid).
 * <p>
 * Implicações técnicas:
 * - Métodos de busca binária, por exemplo, são muito eficientes para excesso de dados
 * quando podemos manter uma coleção ordenada.
 */
public static int logarithmicTimeExample(int[] sortedArray, int target) {
    int left = 0;
    int right = sortedArray.length - 1;

    while (left <= right) {
        int mid = (left + right) / 2;
        if (sortedArray[mid] == target) {
            return mid; // Encontrou o elemento
        } else if (sortedArray[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1; // Não encontrou
}

/**
 * Exemplo de complexidade O(n).
 * <p>
 * Como chegamos na complexidade:
 * - Percorremos toda a lista, elemento por elemento.
 * - Com isso, temos uma quantidade de passos proporcional a n.
 * <p>
 * Complexidade temporal: O(n)
 * - Para cada elemento, fazemos uma operação constante (comparar e somar).
 * <p>
 * Complexidade espacial: O(1)
 * - Usamos apenas uma variável para acumular a soma.
 * <p>
 * Implicações técnicas:
 * - Bastante comum na realidade para processar cada elemento de uma coleção.
 * - Escala linearmente com o tamanho do input.
 */
public static int linearTimeExample(int[] array) {
    int sum = 0;
    for (int value : array) {
        sum += value; // operação constante para cada elemento
    }
    return sum;
}

/**
 * Exemplo de complexidade O(n log n).
 * <p>
 * Como chegamos na complexidade:
 * - Usaremos o Merge Sort como exemplo. O Merge Sort "divide" o array em duas partes (recursão)
 * e depois mescla (merge) ordenadamente.
 * - O custo total de merge sort é O(n log n) pois a divisão acontece log n vezes
 * e o merge de cada nível custa O(n).
 * <p>
 * Complexidade temporal: O(n log n)
 * - Típico de algoritmos de ordenação eficientes (MergeSort, QuickSort).
 * <p>
 * Complexidade espacial: O(n)
 * - Precisamos de um array auxiliar do mesmo tamanho para a operação de merge.
 * <p>
 * Implicações técnicas:
 * - Boa escalabilidade para ordenação de excesso de dados.
 * - Comum em bibliotecas padrão de muitas linguagens de programação.
 */
public static void nLognTimeExample(int[] array) {
    mergeSort(array, 0, array.length - 1);
}

// Funções auxiliares para o Merge Sort
private static void mergeSort(int[] array, int left, int right) {
    if (left < right) {
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }
}

private static void merge(int[] array, int left, int mid, int right) {
    int[] temp = new int[right - left + 1];
    int i = left;
    int j = mid + 1;
    int k = 0;

    while (i <= mid && j <= right) {
        if (array[i] <= array[j]) {
            temp[k++] = array[i++];
        } else {
            temp[k++] = array[j++];
        }
    }

    while (i <= mid) {
        temp[k++] = array[i++];
    }

    while (j <= right) {
        temp[k++] = array[j++];
    }

    for (int t = 0; t < temp.length; t++) {
        array[left + t] = temp[t];
    }
}

/**
 * Exemplo de complexidade O(n^2).
 * <p>
 * Como chegamos na complexidade:
 * - Temos dois loops aninhados, cada um rodando até n.
 * - Para cada i (n possibilidades), percorrermos j (n possibilidades).
 * <p>
 * Complexidade temporal: O(n^2)
 * - Ex.: percorrer uma matriz n x n, ou realizar comparações par-a-par.
 * <p>
 * Complexidade espacial: O(1)
 * - Apenas algumas variáveis de controle i e j, independentemente de n.
 * <p>
 * Implicações técnicas:
 * - Podemos ficar lento rapidamente para valores grandes de n (ex.: 10.000^2 = 100 milhões de iterações).
 */
public static long nSquaredTimeExample(int n) {
    long count = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            count++;
        }
    }
    return count;
}

/**
 * Exemplo de complexidade O(n^3).
 * <p>
 * Como chegamos na complexidade:
 * - Três loops aninhados, cada um rodando até n.
 * - Para cada i (n possibilidades), para cada j (n possibilidades), para cada k (n possibilidades).
 * <p>
 * Complexidade temporal: O(n^3)
 * - Ex.: percorrer uma matriz 3D de tamanho n x n x n.
 * <p>
 * Complexidade espacial: O(1)
 * - A quantidade de variáveis de loop não aumenta com n (apenas i, j, k).
 * <p>
 * Implicações técnicas:
 * - Cresce ainda mais rapidamente que O(n^2). Cuidado ao n ficar grande!
 */
public static long nCubedTimeExample(int n) {
    long count = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                count++;
            }
        }
    }
    return count;
}

/**
 * Exemplo de complexidade O(n^4).
 * <p>
 * Como chegamos na complexidade:
 * - Quatro loops aninhados. Cada loop roda até n, totalizando n^4 iterações.
 * <p>
 * Complexidade temporal: O(n^4)
 * - Muito utilizado em problemas que precisam percorrer estruturas 4D, ou testes de "força bruta"
 * com 4 parâmetros.
 * <p>
 * Complexidade espacial: O(1)
 * - Novamente, apenas variáveis de controle.
 * <p>
 * Implicações técnicas:
 * - Cresce muito rapidamente. Para n grande, se torna impraticável em termos de tempo de execução.
 */
public static long nToTheFourthTimeExample(int n) {
    long count = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                for (int w = 0; w < n; w++) {
                    count++;
                }
            }
        }
    }
    return count;
}

/**
 * Exemplo de complexidade O(2^n).
 * <p>
 * Como chegamos na complexidade:
 * - Exemplo típico: gerar todos os subconjuntos de um conjunto de n elementos
 * (o "power set"), cada elemento pode estar ou não estar (2 opções) em cada subconjunto.
 * - A recursão (ou iterações) cresce exponencialmente, pois a cada elemento podemos ter 2 escolhas.
 * <p>
 * Complexidade temporal: O(2^n)
 * <p>
 * Complexidade espacial: O(n) a O(2^n)
 * - Dependendo de como armazenamos todos os subconjuntos, podemos ter até 2^n subconjuntos (cada um
 * de tamanho até n).
 * <p>
 * Implicações técnicas:
 * - Métodos exponenciais são ineficientes para n grande (muito grande mesmo).
 * - Muitas vezes temos que usar heurísticas ou algoritmos aproximados.
 */
public static List<List<Integer>> twoToTheNExample(int[] array) {
    List<List<Integer>> result = new ArrayList<>();
    backtrackTwoPowN(array, 0, new ArrayList<>(), result);
    return result;
}

private static void backtrackTwoPowN(int[] array, int start, List<Integer> current, List<List<Integer>> result) {
    if (start == array.length) {
        result.add(new ArrayList<>(current));
        return;
    }
    // Não incluir o elemento
    backtrackTwoPowN(array, start + 1, current, result);

    // Incluir o elemento
    current.add(array[start]);
    backtrackTwoPowN(array, start + 1, current, result);
    current.removeLast();
}

/**
 * Exemplo de complexidade O(3^n).
 * <p>
 * Como chegamos na complexidade:
 * - Semelhante ao caso de 2^n, mas agora cada escolha gera 3 possibilidades.
 * - Por exemplo, imagine um algoritmo recursivo que, para cada elemento, escolhe entre 3 caminhos diferentes.
 * - A árvore de recursão terá um fator de ramificação 3, gerando 3^n caminhos.
 * <p>
 * Complexidade temporal: O(3^n)
 * <p>
 * Complexidade espacial: O(n) a O(3^n)
 * - Em uma implementação recursiva, a profundidade da pilha é O(n).
 * - Se armazenar todas as combinações, pode chegar a 3^n registros.
 * <p>
 * Implicações técnicas:
 * - Assim como 2^n, 3^n cresce ainda mais rapidamente; geralmente inviável para n grande.
 */
public static void threeToTheNExample(int n) {
    // Exemplo: somente para demonstrar a recursão 3^n.
    // Cada chamado gera 3 chamados recursivos.
    threeToTheNRecursive(n, 0);
}

private static void threeToTheNRecursive(int n, int step) {
    // ponto base
    if (step == n) return;

    // Cada nível, disparamos 3 chamadas:
    threeToTheNRecursive(n, step + 1);
    threeToTheNRecursive(n, step + 1);
    threeToTheNRecursive(n, step + 1);
}

/**
 * Exemplo de complexidade O(n^n).
 * <p>
 * Como chegamos na complexidade:
 * - Aqui, em cada passo, você tem n opções, e existem n passos (ou algo análogo).
 * - Cada "nível" de recursão ou iteração pode gerar n ramificações, totalizando n * n * ... * n = n^n.
 * <p>
 * Complexidade temporal: O(n^n)
 * <p>
 * Complexidade espacial: pode ser imenso também, dependendo do que se armazena.
 * <p>
 * Implicações técnicas:
 * - Cresce mais rápido do que exponenciais 2^n ou 3^n para valores grandes de n.
 * - Geralmente impraticável, mesmo para n moderado.
 */
public static void nToTheNExample(int n) {
    nToTheNRecursive(n, 0);
}

private static void nToTheNRecursive(int n, int step) {
    if (step == n) return;

    // Disparamos n chamadas recursivas
    for (int i = 0; i < n; i++) {
        nToTheNRecursive(n, step + 1);
    }
}

// =============================================================
static void main(String[] ignoredArgs) {

    // O(1)
    System.out.println("O(1) => " + constantTimeExample(2, 6));

    // O(log n)
    int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
    System.out.println("O(log n) => index of 4 = "
                       + logarithmicTimeExample(sortedArray, 4));

    // O(n)
    int[] array = {10, 20, 30, 40, 50};
    System.out.println("O(n) => soma do array = " + linearTimeExample(array));

    // O(n log n)
    int[] arrayDesordenado = {5, 3, 8, 4, 2, 1, 7};
    nLognTimeExample(arrayDesordenado);
    System.out.println("O(n log n) => array ordenado = " + Arrays.toString(arrayDesordenado));

    // O(n^2)
    System.out.println("O(n^2) => " + nSquaredTimeExample(5));

    // O(n^3)
    System.out.println("O(n^3) => " + nCubedTimeExample(3));

    // O(n^4)
    System.out.println("O(n^4) => " + nToTheFourthTimeExample(2));

    // O(2^n)
    List<List<Integer>> subsets = twoToTheNExample(new int[]{1, 2, 3});
    System.out.println("O(2^n) => subconjuntos de {1, 2, 3} = " + subsets);

    // O(3^n)
    threeToTheNExample(3);
    System.out.println("O(3^n) => método executado para n=3 (veja logs se adicionados).");

    // O(n^n)
    nToTheNExample(2);
    System.out.println("O(n^n) => método executado para n=2 (veja logs se adicionados).");
}
