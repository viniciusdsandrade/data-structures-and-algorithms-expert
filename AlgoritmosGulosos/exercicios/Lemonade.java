package exercicios;

import java.util.Arrays;

import static java.lang.System.gc;
import static java.lang.System.nanoTime;

public class Lemonade {

    /*

    Problema "lemonade"

    Em uma barraquinha de limonada, cada limonada custa 5 reais. Os clientes estão em fila para comprar, um de cada vez. Cada cliente comprará apenas uma limonada e pagará com uma nota de 5, 10 ou 20 reais. Você deve dar o troco correto para que o cliente seja cobrado apenas 5 reais.
    No entanto, no começo você não tem nenhuma nota para dar o troco. Você só poderá dar notas que conseguir com os clientes anteriores.
    Dado um vetor de inteiros de tamanho N, onde cada elemento nessa ordem é a nota que o i-ésimo consumidor pagará, imprima “Verdadeiro”, se você consegue dar o troco correto para todos os clientes, ou “Falso” caso contrário.

    Entrada
    A primeira linha contém um inteiro N, indicando a quantidade de clientes.
    Na linha seguinte seguem N inteiros, cade um indicando a nota que o i-ésimo cliente pagará.

    Saída
    Imprima “Verdadeiro”, se for possível dar o troco correto para todos os clientes,
    ou “Falso” caso contrário.

    Exemplo:
    Entrada 1 : 5
    Saída 1   : 5 5 5 10 20

    Verdadeiro

   Explicação: Dos primeiros três clientes, coletamos três notas de 5 reais. Do quarto cliente, coletamos 10 reais e damos de volta 5. Para o quinto cliente, damos uma nota de 10 e uma de 5.
    Entrada 2 : 5
    Saída 2   : 5 5 10 10 20
    Falso

    Explicação: Dos primeiros dois clientes na fila, coletamos duas notas de 5 reais.
    Dos próximos dois clientes na ordem, coletamos uma nota de 10 e damos uma nota de 5.
    Para o último cliente, não podemos dar 15 reais de volta, porque temos apenas duas notas de 10.

     */

    private static final int WARMUP_RUNS = 5;
    private static final int TEST_RUNS = 10;

    // ---------------------------------------------------
    // 1) Implementação Original
    // ---------------------------------------------------
    /**
     * Versão "Original" para verificar se é possível dar troco para todos os clientes.
     * Cada limonada custa 5 reais. O array de bills indica a nota que cada cliente paga.
     * Retorna true se for possível dar troco corretamente para todos os clientes,
     * ou false caso contrário.
     */
    static boolean canGiveChangeOriginal(int[] bills) {
        int fiveCount = 0;  // Quantidade de notas de 5
        int tenCount = 0;   // Quantidade de notas de 10

        for (int bill : bills) {
            if (bill == 5) {
                fiveCount++;
            }
            else if (bill == 10) {
                if (fiveCount == 0) {
                    return false;
                }
                fiveCount--;
                tenCount++;
            }
            else { // bill == 20
                // Precisamos dar 15 de troco
                // Tentar primeiro usar (10 + 5)
                if (tenCount > 0 && fiveCount > 0) {
                    tenCount--;
                    fiveCount--;
                }
                // Ou então usar 3 notas de 5
                else if (fiveCount >= 3) {
                    fiveCount -= 3;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Versão "Alternativa" para verificar se é possível dar troco para todos os clientes.
     * Utiliza, por exemplo, um estilo de código ligeiramente diferente (ou qualquer mudança
     * que desejar) para ilustrar que a lógica se mantém, mas podemos comparar desempenhos.
     * <p>
     * Aqui, faremos algo bem parecido, mas usando um loop for-each de outra forma,
     * apenas para demonstrar que é a "alternativa".
     */
    static boolean canGiveChangeAlternativa(int[] bills) {
        int fiveCount = 0;
        int tenCount = 0;

        for (int bill : bills) {
            switch (bill) {
                case 5:
                    fiveCount++;
                    break;
                case 10:
                    if (fiveCount == 0) return false;
                    fiveCount--;
                    tenCount++;
                    break;
                default: // bill == 20
                    if (tenCount > 0 && fiveCount > 0) {
                        tenCount--;
                        fiveCount--;
                    } else if (fiveCount >= 3) {
                        fiveCount -= 3;
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }


    /**
     * Imprime passo a passo como cada cliente é atendido, mostrando como o troco é dado,
     * o número de notas de 5 e 10 após cada passo, etc.
     */
    static void printDetailedProcess(int[] bills) {
        int fiveCount = 0;
        int tenCount = 0;

        System.out.println("Processo de atendimento (ordem das notas pagas): "
                           + Arrays.toString(bills));

        for (int i = 0; i < bills.length; i++) {
            int bill = bills[i];
            System.out.printf("Cliente %d paga R$%d. ", (i + 1), bill);

            if (bill == 5) {
                fiveCount++;
                System.out.printf("Não precisa dar troco. (fiveCount=%d, tenCount=%d)%n",
                        fiveCount, tenCount);
            }
            else if (bill == 10) {
                if (fiveCount == 0) {
                    System.out.printf("FALHA: não há nota de 5 para dar troco de R$5.%n");
                    return;
                }
                fiveCount--;
                tenCount++;
                System.out.printf("Troco dado: R$5. (fiveCount=%d, tenCount=%d)%n",
                        fiveCount, tenCount);
            }
            else { // bill == 20
                // Preferir 10 + 5
                if (tenCount > 0 && fiveCount > 0) {
                    tenCount--;
                    fiveCount--;
                    System.out.printf("Troco dado: R$10 + R$5. (fiveCount=%d, tenCount=%d)%n",
                            fiveCount, tenCount);
                }
                else if (fiveCount >= 3) {
                    fiveCount -= 3;
                    System.out.printf("Troco dado: 3xR$5. (fiveCount=%d, tenCount=%d)%n",
                            fiveCount, tenCount);
                }
                else {
                    System.out.printf("FALHA: não há troco suficiente (faltam R$15).%n");
                    return;
                }
            }
        }

        System.out.println("Conseguimos dar troco para todos os clientes!\n");
    }


    /**
     * Executa testes de desempenho e de corretude para as duas implementações:
     * Original e Alternativa.
     *
     * @param bills    Array de notas que cada cliente paga.
     * @param expected Valor esperado (true ou false) se é possível dar troco a todos.
     */
    static void testCanGiveChange(int[] bills, boolean expected) {
        System.out.println("🔍 Benchmark das Implementações:");

        // Arrays para armazenar os resultados
        boolean[][] results = new boolean[2][TEST_RUNS];
        long[] totalTimes = new long[2];

        // Warmup
        for (int i = 0; i < WARMUP_RUNS; i++) {
            canGiveChangeOriginal(bills);
            canGiveChangeAlternativa(bills);
        }

        // Test runs para a implementação Original
        for (int run = 0; run < TEST_RUNS; run++) {
            gc(); // Sugerir coleta de lixo para minimizar interferências
            long start = nanoTime();
            boolean resultadoOriginal = canGiveChangeOriginal(bills);
            long duration = nanoTime() - start;
            totalTimes[0] += duration;
            results[0][run] = resultadoOriginal;
        }

        // Test runs para a implementação Alternativa
        for (int run = 0; run < TEST_RUNS; run++) {
            gc();
            long start = nanoTime();
            boolean resultadoAlternativa = canGiveChangeAlternativa(bills);
            long duration = nanoTime() - start;
            totalTimes[1] += duration;
            results[1][run] = resultadoAlternativa;
        }

        // Verificar se todos os resultados são iguais e correspondem ao esperado
        boolean allEqual = true;
        for (int run = 0; run < TEST_RUNS; run++) {
            if (results[0][run] != results[1][run] || results[0][run] != expected) {
                allEqual = false;
                System.out.printf("Diferença na execução %d: Original=%b, Alternativa=%b, Esperado=%b%n",
                        run + 1, results[0][run], results[1][run], expected);
            }
        }

        if (allEqual) System.out.println("✅ Todos os resultados das implementações Original e Alternativa estão corretos.");
        else System.err.println("❌ Há diferenças nas implementações ou resultados incorretos.");

        // Calcular tempos médios
        double avgOriginal = totalTimes[0] / (double) TEST_RUNS;
        double avgAlternativa = totalTimes[1] / (double) TEST_RUNS;

        // Determinar qual implementação é mais rápida
        String maisRapida = (avgOriginal < avgAlternativa) ? "Original" : "Alternativa";
        double ratio = maisRapida.equals("Original")
                ? (avgAlternativa / avgOriginal)
                : (avgOriginal / avgAlternativa);

        // Imprimir resultados
        System.out.printf("Tempo médio Original   : %.2f ns%n", avgOriginal);
        System.out.printf("Tempo médio Alternativa: %.2f ns%n", avgAlternativa);
        System.out.printf("Razão (mais lenta / mais rápida): %.2fx%n", ratio);
        System.out.println("Implementação mais rápida: " + maisRapida);
        System.out.println("-------------------------------------------\n");
    }

    static void main(String[] ignoredArgs) {
        // EXEMPLO 1 (consegue dar troco)
        int[] bills1 = {5, 5, 5, 10, 20};
        boolean expected1 = true; // É possível dar troco

        System.out.println("📘 Entrada 1:");
        System.out.println("N = 5");
        System.out.println("Notas: 5, 5, 5, 10, 20\n");

        System.out.println("🔍 Detalhamento do Processo para o Caso 1:");
        printDetailedProcess(bills1);

        System.out.println("🔍 Benchmark para o Caso 1:");
        testCanGiveChange(bills1, expected1);

        // EXEMPLO 2 (não consegue dar troco)
        int[] bills2 = {5, 5, 10, 10, 20};
        boolean expected2 = false; // Não é possível dar troco

        System.out.println("📘 Entrada 2:");
        System.out.println("N = 5");
        System.out.println("Notas: 5, 5, 10, 10, 20\n");

        System.out.println("🔍 Detalhamento do Processo para o Caso 2:");
        printDetailedProcess(bills2);

        System.out.println("🔍 Benchmark para o Caso 2:");
        testCanGiveChange(bills2, expected2);
    }
}
