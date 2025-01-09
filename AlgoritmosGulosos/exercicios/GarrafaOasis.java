package exercicios;

import java.util.Arrays;

import static java.lang.System.gc;
import static java.lang.System.nanoTime;
import static java.util.Arrays.*;

public class GarrafaOasis {

    /*

    Problema "garrafas_oasis"

    Em sua jornada pelo deserto, a caravana de João Batista finalmente avistou um oásis e poderão parar para matar a sede e encher suas garrafas de água.
    Suponha que o poço do oásis tenha uma quantidade de água X, e que a caravana possui N garrafas de água, cada uma com uma capacidade máxima.
    Encontre a quantidade máxima de garrafas que a caravana poderá encher completamente.

    Entrada
        A primeira linha contém dois inteiros, e, o número de garrafas e a quantidade de água do oásis.
        A segunda linha contém N inteiros separados por um espaço, a capacidade de cada garrafa.

    Saída
        Imprima o número máximo de garrafas que é possível encher.

    Entrada 1
    5 10
    8 5 4 3 2

    Saída 1: 3

    Entrada 2
        3 10
        6 3 2

    Saída 2: 2

     */

    private static final int WARMUP_RUNS = 5;
    private static final int TEST_RUNS = 10;


    static int contarGarrafasPreenchidasOriginal(int[] capacidades, int X) {
        // Ordena as capacidades em ordem crescente
       sort(capacidades);

        int aguaRestante = X;
        int garrafasPreenchidas = 0;

        // Itera sobre as garrafas ordenadas e conta quantas podem ser preenchidas
        for (int capacidade : capacidades) {
            if (aguaRestante >= capacidade) {
                aguaRestante -= capacidade;
                garrafasPreenchidas++;
            } else {
                break; // Não há água suficiente para preencher a próxima garrafa
            }
        }

        return garrafasPreenchidas;
    }

    static int contarGarrafasPreenchidasAlternativa(int[] capacidades, int X) {
        // Cria uma cópia do array para não modificar o original
        int[] sortedCapacidades = capacidades.clone();
        sort(sortedCapacidades);

        int garrafasPreenchidas;
        final int[] aguaRestante = {X};

        // Utiliza Streams para iterar e contar
        garrafasPreenchidas = (int) stream(sortedCapacidades)
                .takeWhile(capacidade -> {
                    if (aguaRestante[0] >= capacidade) {
                        aguaRestante[0] -= capacidade;
                        return true;
                    }
                    return false;
                })
                .count();

        return garrafasPreenchidas;
    }


    static void printDetailedProcess(int[] capacidades, int X, int expected) {
        // Cria uma cópia e ordena para não modificar o array original
        int[] sorted = capacidades.clone();
        sort(sorted);
        System.out.println("Após ordenar: " + Arrays.toString(sorted));

        // Processo de preenchimento
        System.out.println("Processo:");
        int aguaRestante = X;
        int garrafasPreenchidas = 0;
        for (int capacidade : sorted) {
            if (aguaRestante >= capacidade) {
                aguaRestante -= capacidade;
                garrafasPreenchidas++;
                System.out.printf("- Preenche garrafa de %d (resta %d de água)%n", capacidade, aguaRestante);
            } else {
                System.out.printf("- Não pode preencher garrafa de %d%n", capacidade);
            }
        }

        // Resultado
        System.out.println("Resultado: " + garrafasPreenchidas + " garrafas\n");
    }

    static void testContarGarrafasPreenchidas(int[] capacidades, int X, int expected) {
        System.out.println("🔍 Benchmark das Implementações:");

        // Arrays para armazenar os resultados
        int[][] results = new int[2][TEST_RUNS];
        long[] totalTimes = new long[2];

        // Warmup
        for (int i = 0; i < WARMUP_RUNS; i++) {
            contarGarrafasPreenchidasOriginal(capacidades, X);
            contarGarrafasPreenchidasAlternativa(capacidades, X);
        }

        // Test runs para a implementação Original
        for (int run = 0; run < TEST_RUNS; run++) {
            gc(); // Sugerir coleta de lixo para minimizar interferências
            long start = nanoTime();
            int resultadoOriginal = contarGarrafasPreenchidasOriginal(capacidades, X);
            long duration = nanoTime() - start;
            totalTimes[0] += duration;
            results[0][run] = resultadoOriginal;
        }

        // Test runs para a implementação Alternativa
        for (int run = 0; run < TEST_RUNS; run++) {
            gc();
            long start = nanoTime();
            int resultadoAlternativa = contarGarrafasPreenchidasAlternativa(capacidades, X);
            long duration = nanoTime() - start;
            totalTimes[1] += duration;
            results[1][run] = resultadoAlternativa;
        }

        // Verificar se todos os resultados são iguais e correspondem ao esperado
        boolean allEqual = true;
        for (int run = 0; run < TEST_RUNS; run++) {
            if (results[0][run] != results[1][run] || results[0][run] != expected) {
                allEqual = false;
                System.out.printf("Diferença na execução %d: Original=%d, Alternativa=%d, Esperado=%d%n",
                        run + 1, results[0][run], results[1][run], expected);
            }
        }

        if (allEqual) {
            System.out.println("✅ Todos os resultados das implementações Original e Alternativa estão corretos.");
        } else {
            System.err.println("❌ Há diferenças nas implementações ou resultados incorretos.");
        }

        // Calcular médias
        double avgOriginal = totalTimes[0] / (double) TEST_RUNS;
        double avgAlternativa = totalTimes[1] / (double) TEST_RUNS;

        // Determinar qual implementação é mais rápida
        String maisRapida = avgOriginal < avgAlternativa ? "Original" : "Alternativa";
        double ratio = maisRapida.equals("Original") ?
                avgAlternativa / avgOriginal :
                avgOriginal / avgAlternativa;

        // Imprimir resultados
        System.out.printf("Tempo médio Original: %.2f ns%n", avgOriginal);
        System.out.printf("Tempo médio Alternativa: %.2f ns%n", avgAlternativa);
        System.out.printf("Razão (mais lenta / mais rápida): %.2fx%n", ratio);
        System.out.println("Implementação mais rápida: " + maisRapida);
        System.out.println("-------------------------------------------\n");
    }


    static void main(String[] ignoredArgs) {
        // Definir os dois casos de teste fornecidos

        // Caso 1
        int[] capacidades1 = {8, 5, 4, 3, 2};
        int X1 = 10;
        int expected1 = 3;

        // Caso 2
        int[] capacidades2 = {6, 3, 2};
        int X2 = 10;
        int expected2 = 2;

        // Executar benchmark para o Caso 1
        System.out.println("📘 Entrada 1:");
        System.out.println("5 10");
        System.out.println("8 5 4 3 2\n");

        System.out.println("🔍 Detalhamento do Processo para o Caso 1:");
        printDetailedProcess(capacidades1, X1, expected1);
        System.out.println("🔍 Benchmark para o Caso 1:");
        testContarGarrafasPreenchidas(capacidades1, X1, expected1);

        // Executar benchmark para o Caso 2
        System.out.println("📘 Entrada 2:");
        System.out.println("3 10");
        System.out.println("6 3 2\n");

        System.out.println("🔍 Detalhamento do Processo para o Caso 2:");
        printDetailedProcess(capacidades2, X2, expected2);
        System.out.println("🔍 Benchmark para o Caso 2:");
        testContarGarrafasPreenchidas(capacidades2, X2, expected2);
    }
}

