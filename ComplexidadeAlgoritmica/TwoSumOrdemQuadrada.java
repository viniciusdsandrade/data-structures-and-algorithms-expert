static int iterationCount = 0;
static int comparisonCount = 0;

/**
 * Encontra dois números em um array que somam ao target especificado.
 * Complexidade Temporal: O(n²) - dois loops aninhados
 * Complexidade Espacial: O(1) - apenas variáveis simples
 *
 * @param nums   array de números para buscar
 * @param target soma alvo
 * @return array com os índices dos dois números que somam target, ou null se não encontrar
 */
static int[] twoSumDescription(int[] nums, int target) {
    iterationCount = 0;
    comparisonCount = 0;

    System.out.println("\n→ Iniciando busca por dois números que somam: " + target);
    System.out.println("→ Tamanho do array: " + nums.length);

    for (int i = 0; i < nums.length - 1; i++) {
        iterationCount++;
        System.out.println("\n[Loop Externo] Iteração " + iterationCount);
        System.out.println("[Loop Externo] Índice i=" + i + ", Valor=" + nums[i]);

        for (int j = i + 1; j < nums.length; j++) {
            comparisonCount++;
            System.out.println("  [Loop Interno] Comparação " + comparisonCount);
            System.out.println("  [Loop Interno] Comparando: " +
                               "nums[" + i + "]=" + nums[i] + " + " +
                               "nums[" + j + "]=" + nums[j] + " = " +
                               (nums[i] + nums[j]));

            if (nums[i] + nums[j] == target) {
                System.out.println("  ✓ Encontrou! Índices " + i + " e " + j +
                                   " somam " + target);
                return new int[]{i, j};
            }
        }
        System.out.println("  ✗ Nenhuma combinação encontrada com nums[" + i + "]=" +
                           nums[i]);
    }
    System.out.println("\n✗ Nenhuma solução encontrada para o target " + target);
    return null;
}

static void printResult(int[] nums, int target, String caso) {
    System.out.println("\n========= " + caso + " =========");
    System.out.println("Array de entrada: ");
    System.out.print("Índices: ");
    for (int i = 0; i < nums.length; i++) {
        System.out.printf("%4d", i);
    }
    System.out.print("\nValores: ");
    for (int num : nums) {
        System.out.printf("%4d", num);
    }
    System.out.println("\nValor alvo (target): " + target);
    System.out.println("--------------------------------");

    int[] result = twoSumDescription(nums, target);

    System.out.println("\n=== RESULTADO FINAL ===");
    if (result != null) {
        System.out.println("✓ Solução encontrada!");
        System.out.println("  Índices: " + result[0] + ", " + result[1]);
        System.out.println("  Valores: " + nums[result[0]] + " + " +
                           nums[result[1]] + " = " + target);
    } else {
        System.out.println("✗ Nenhuma solução encontrada");
    }
    System.out.println("Total de iterações do loop externo: " + iterationCount);
    System.out.println("Total de comparações realizadas: " + comparisonCount);
    System.out.println("============================\n");
}

static void main(String[] ignoredArgs) {
    int[] nums = {2, 7, 11, 15, 3, 6, 8, 19};

    // Melhor caso: soma dos dois primeiros elementos (2 + 7 = 9)
    printResult(nums, 9, "MELHOR CASO");

    // Caso médio: soma de elementos no meio do array (11 + 3 = 14)
    printResult(nums, 14, "CASO MÉDIO");

    // Pior caso: soma dos últimos elementos possíveis (8 + 19 = 27)
    printResult(nums, 27, "PIOR CASO");
}

