import static java.lang.String.valueOf;

/**
 * Classe responsável pela multiplicação de matrizes de diferentes dimensões
 * e exibição dos resultados com formatação adequada.
 *
 * <p>Este programa contém funções específicas para multiplicar matrizes de
 * dimensões 2x2, 3x3, 4x4, 5x5, além de uma função genérica para multiplicação
 * de matrizes n x n. Também inclui funções para exibir as matrizes de forma
 * alinhada e organizada.</p>
 *
 * <p>As análises de complexidade algorítmica (Big O, Big Omega, Big Theta)
 * estão incluídas nos comentários para cada função.</p>
 */
public class MultiplicacaoMatrizes {

    /**
     * Multiplica duas matrizes 2x2.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * Como as matrizes são de tamanho fixo 2x2, o número de operações é constante,
     * independentemente do contexto de execução. O espaço utilizado também é
     * constante, já que o tamanho das matrizes não varia.
     * </p>
     *
     * @param A Matriz A de dimensão 2x2.
     * @param B Matriz B de dimensão 2x2.
     * @return Matriz resultante da multiplicação AxB.
     */
    static int[][] multiplicar2x2(int[][] A, int[][] B) {
        int[][] C = new int[2][2];
        for (int i = 0; i < 2; i++) { // Loop externo
            for (int j = 0; j < 2; j++) { // Loop intermediário
                C[i][j] = 0;
                for (int k = 0; k < 2; k++) { // Loop interno
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    /**
     * Multiplica duas matrizes 3x3.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * Como as matrizes são de tamanho fixo 3x3, o número de operações é constante,
     * independentemente do contexto de execução. O espaço utilizado também é
     * constante, já que o tamanho das matrizes não varia.
     * </p>
     *
     * @param A Matriz A de dimensão 3x3.
     * @param B Matriz B de dimensão 3x3.
     * @return Matriz resultante da multiplicação AxB.
     */
    static int[][] multiplicar3x3(int[][] A, int[][] B) {
        int[][] C = new int[3][3];
        for (int i = 0; i < 3; i++) { // Loop externo
            for (int j = 0; j < 3; j++) { // Loop intermediário
                C[i][j] = 0;
                for (int k = 0; k < 3; k++) { // Loop interno
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    /**
     * Multiplica duas matrizes 4x4.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * Como as matrizes são de tamanho fixo 4x4, o número de operações é constante,
     * independentemente do contexto de execução. O espaço utilizado também é
     * constante, já que o tamanho das matrizes não varia.
     * </p>
     *
     * @param A Matriz A de dimensão 4x4.
     * @param B Matriz B de dimensão 4x4.
     * @return Matriz resultante da multiplicação AxB.
     */
    static int[][] multiplicar4x4(int[][] A, int[][] B) {
        int[][] C = new int[4][4];
        for (int i = 0; i < 4; i++) { // Loop externo
            for (int j = 0; j < 4; j++) { // Loop intermediário
                C[i][j] = 0;
                for (int k = 0; k < 4; k++) { // Loop interno
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    /**
     * Multiplica duas matrizes 5x5.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(1)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(1)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(1)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * Como as matrizes são de tamanho fixo 5x5, o número de operações é constante,
     * independentemente do contexto de execução. O espaço utilizado também é
     * constante, já que o tamanho das matrizes não varia.
     * </p>
     *
     * @param A Matriz A de dimensão 5x5.
     * @param B Matriz B de dimensão 5x5.
     * @return Matriz resultante da multiplicação AxB.
     */
    static int[][] multiplicar5x5(int[][] A, int[][] B) {
        int[][] C = new int[5][5];
        for (int i = 0; i < 5; i++) { // Loop externo
            for (int j = 0; j < 5; j++) { // Loop intermediário
                C[i][j] = 0;
                for (int k = 0; k < 5; k++) { // Loop interno
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    /**
     * Multiplica duas matrizes n x n de forma genérica.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(n³)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(n³)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(n³)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(n²)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(n²)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(n²)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * O algoritmo utiliza três loops aninhados, cada um com n iterações,
     * resultando em um número de operações proporcional a n³.
     * Independentemente dos valores das matrizes, todos os loops são executados completamente.
     * O espaço adicional utilizado é proporcional a n² devido à criação da matriz resultante.
     * </p>
     *
     * @param A Matriz A de dimensão n x n.
     * @param B Matriz B de dimensão n x n.
     * @param n Dimensão das matrizes.
     * @return Matriz resultante da multiplicação AxB.
     */
    static int[][] multiplicarNxN(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) { // Loop externo
            for (int j = 0; j < n; j++) { // Loop intermediário
                C[i][j] = 0;
                for (int k = 0; k < n; k++) { // Loop interno
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    /**
     * Exibe duas matrizes (A e B) antes da multiplicação.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(n²)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(n²)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(n²)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(n)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(n)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(n)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * A função chama `exibirMatriz` duas vezes, cada uma com complexidade O(n²) para o tempo
     * e O(n) para o espaço (necessário para armazenar as larguras das colunas).
     * Portanto, a complexidade total é O(n²) para o tempo e O(n) para o espaço.
     * </p>
     *
     * @param A Matriz A a ser exibida.
     * @param B Matriz B a ser exibida.
     */
    static void exibirMatrizesAntesMultiplicacao(int[][] A, int[][] B) {
        System.out.println("Matriz A:");
        exibirMatriz(A);
        System.out.println("\nMatriz B:");
        exibirMatriz(B);
    }

    /**
     * Exibe uma matriz com alinhamento adequado, considerando a variação no tamanho dos elementos.
     *
     * <p>
     * <strong>Complexidade Temporal:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(n²)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(n²)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(n²)</li>
     * </ul>
     * </p>
     *
     * <p>
     * <strong>Complexidade Espacial:</strong>
     * <ul>
     *   <li><strong>Big O (O):</strong> O(n)</li>
     *   <li><strong>Big Omega (Ω):</strong> Ω(n)</li>
     *   <li><strong>Big Theta (Θ):</strong> Θ(n)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * A função percorre todos os elementos da matriz duas vezes:
     * uma para determinar a largura máxima de cada coluna (O(n²)),
     * e outra para imprimir os elementos formatados (O(n²)).
     * Além disso, utiliza um array de tamanho n para armazenar as larguras das colunas (O(n)).
     * Portanto, a complexidade temporal é O(n²) e a complexidade espacial é O(n).
     * </p>
     *
     * @param matriz Matriz a ser exibida.
     */
    static void exibirMatriz(int[][] matriz) {
        // Primeiro, determinar a largura máxima necessária para cada coluna
        int[] larguraColunas = new int[matriz[0].length];

        for (int j = 0; j < matriz[0].length; j++) { // Itera sobre colunas
            int maxLargura = 0;
            for (int[] linha : matriz) { // Itera sobre linhas para cada coluna
                int tamanho = valueOf(linha[j]).length();
                if (tamanho > maxLargura) {
                    maxLargura = tamanho;
                }
            }
            // Adiciona espaço adicional para melhor visualização
            larguraColunas[j] = maxLargura + 2;
        }

        // Agora, imprimir a matriz com formatação adequada
        for (int[] linha : matriz) { // Itera sobre linhas
            for (int j = 0; j < linha.length; j++) { // Itera sobre colunas
                // Formata cada elemento com a largura determinada
                System.out.printf("%" + larguraColunas[j] + "d", linha[j]);
            }
            System.out.println(); // Nova linha após cada linha da matriz
        }
    }

    /**
     * Função principal que executa a multiplicação de matrizes pré-definidas
     * de diferentes dimensões e exibe os resultados.
     *
     * <p>
     * <strong>Complexidade Temporal Total:</strong>
     * <ul>
     *   <li>Para multiplicações específicas (2x2, 3x3, 4x4, 5x5): O(1)</li>
     *   <li>Para multiplicação genérica (n x n): O(n³)</li>
     *   <li>Para exibição das matrizes: O(n²)</li>
     * </ul>
     * </p>
     *
     * <p>
     * <strong>Complexidade Espacial Total:</strong>
     * <ul>
     *   <li>Para multiplicações específicas: O(1)</li>
     *   <li>Para multiplicação genérica: O(n²)</li>
     *   <li>Para exibição das matrizes: O(n)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Justificativa:</strong>
     * As multiplicações específicas têm complexidade temporal e espacial constantes.
     * A multiplicação genérica domina a complexidade temporal total com O(n³) e espacial com O(n²).
     * A exibição das matrizes contribui com O(n²) para o tempo e O(n) para o espaço.
     * </p>
     *
     * @param ignoredArgs Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] ignoredArgs) {
        // Matrizes pré-definidas

        // 2x2
        int[][] A2x2 = {
                {1, 2},
                {3, 4}
        };
        int[][] B2x2 = {
                {5, 6},
                {7, 8}
        };

        // 3x3
        int[][] A3x3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] B3x3 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        // 4x4
        int[][] A4x4 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[][] B4x4 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };

        // 5x5
        int[][] A5x5 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        int[][] B5x5 = {
                {25, 24, 23, 22, 21},
                {20, 19, 18, 17, 16},
                {15, 14, 13, 12, 11},
                {10, 9, 8, 7, 6},
                {5, 4, 3, 2, 1}
        };

        // Matrizes genéricas n x n
        int n = 3;
        int[][] AnxN = {
                {2, 0, 1},
                {3, 5, 2},
                {1, 2, 4}
        };
        int[][] BnxN = {
                {1, 3, 2},
                {4, 0, 1},
                {5, 2, 2}
        };

        // Multiplicação e exibição das matrizes 2x2
        System.out.println("Multiplicação de Matrizes 2x2:");
        exibirMatrizesAntesMultiplicacao(A2x2, B2x2);
        int[][] C2x2 = multiplicar2x2(A2x2, B2x2);
        System.out.println("\nMatriz C (resultado da multiplicação AxB):");
        exibirMatriz(C2x2);
        System.out.println("\n-----------------------------\n");

        // Multiplicação e exibição das matrizes 3x3
        System.out.println("Multiplicação de Matrizes 3x3:");
        exibirMatrizesAntesMultiplicacao(A3x3, B3x3);
        int[][] C3x3 = multiplicar3x3(A3x3, B3x3);
        System.out.println("\nMatriz C (resultado da multiplicação AxB):");
        exibirMatriz(C3x3);
        System.out.println("\n-----------------------------\n");

        // Multiplicação e exibição das matrizes 4x4
        System.out.println("Multiplicação de Matrizes 4x4:");
        exibirMatrizesAntesMultiplicacao(A4x4, B4x4);
        int[][] C4x4 = multiplicar4x4(A4x4, B4x4);
        System.out.println("\nMatriz C (resultado da multiplicação AxB):");
        exibirMatriz(C4x4);
        System.out.println("\n-----------------------------\n");

        // Multiplicação e exibição das matrizes 5x5
        System.out.println("Multiplicação de Matrizes 5x5:");
        exibirMatrizesAntesMultiplicacao(A5x5, B5x5);
        int[][] C5x5 = multiplicar5x5(A5x5, B5x5);
        System.out.println("\nMatriz C (resultado da multiplicação AxB):");
        exibirMatriz(C5x5);
        System.out.println("\n-----------------------------\n");

        // Multiplicação e exibição das matrizes n x n (genérica)
        System.out.println("Multiplicação de Matrizes " + n + "x" + n + " (Genérica):");
        exibirMatrizesAntesMultiplicacao(AnxN, BnxN);
        int[][] CnxN = multiplicarNxN(AnxN, BnxN, n);
        System.out.println("\nMatriz C (resultado da multiplicação AxB):");
        exibirMatriz(CnxN);
        System.out.println();
    }
}
