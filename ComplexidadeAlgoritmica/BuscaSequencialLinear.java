import static java.lang.System.nanoTime;

import java.util.Arrays;

/*
    Análise de Complexidade Temporal

    Caso Pior
    Descrição: O valor procurado não está presente no array ou está na última posição.
    Operações: O algoritmo precisa percorrer todos os n elementos do array.
    Complexidade Temporal: O(n)

    Caso Médio
    Descrição: O valor está distribuído aleatoriamente no array.
    Operações: Em média, o algoritmo verifica metade dos elementos do array.
    Complexidade Temporal: Θ(n)

    Melhor Caso
    Descrição: O valor procurado está na primeira posição do array.
    Operações: O algoritmo encontra o valor na primeira iteração.
    Complexidade Temporal: Ω(1)

    Resumo da Complexidade Temporal
    Caso	        Complexidade Temporal
    Melhor Caso	        Ω(1)
    Caso Médio	        Θ(n)
    Pior Caso	        O(n)

 */

/*

    Análise da complexidade de espaço:

    O algoritmo de busca sequencial não utiliza espaço adicional, além das variáveis locais,
    portanto, a complexidade de espaço é O(1).

    f(n) = 1 (função constante)

*/

static int buscaSequencial(int[] vetor, int valor) {
    for (int i = 0; i < vetor.length; i++) {
        if (vetor[i] == valor) {
            return i;
        }
    }
    return -1;
}

static void testBuscaSequencial(int[] vetor, int valor) {
    System.out.println("\nVetor: " + Arrays.toString(vetor));

    long startTime, endTime, duration;

    startTime = nanoTime();
    int posicao = buscaSequencial(vetor, valor);
    endTime = nanoTime();

    duration = (endTime - startTime);

    System.out.println("Valor procurado: " + valor);

    if (posicao != -1) System.out.println("Valor encontrado na posição: " + posicao);
    else System.err.println("Valor não encontrado");

    System.out.println("Tempo de execução: " + duration + "ns\n");
}

static void main(String[] ignoredArgs) {
    int[] vetor = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int valor = 5;
    testBuscaSequencial(vetor, valor);

    valor = 10;
    testBuscaSequencial(vetor, valor);

    valor = 1;
    testBuscaSequencial(vetor, valor);
}
