static int iterationCount = 0;
static int comparisonCount = 0;

/**
 * Encontra dois números em um array que somam ao target especificado.
 * Complexidade Temporal: O(n) - apenas um loop com operações de HashMap O(1)
 * Complexidade Espacial: O(n) - HashMap pode armazenar até n elementos
 *
 * @param nums array de números para buscar
 * @param target soma alvo
 * @return array com os índices dos dois números que somam target, ou null se não encontrar
 */
static int[] twoSumLinear(int[] nums, int target) {
    iterationCount = 0;
    comparisonCount = 0;

    System.out.println("\n→ Iniciando busca por dois números que somam: " + target);
    System.out.println("→ Tamanho do array: " + nums.length);

    // HashMap para armazenar o complemento necessário para cada número
    HashMap<Integer, Integer> numMap = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        iterationCount++;
        int complemento = target - nums[i];

        System.out.println("\n[Iteração " + iterationCount + "]");
        System.out.println("  Número atual: nums[" + i + "] = " + nums[i]);
        System.out.println("  Complemento necessário: " + complemento);
        System.out.println("  Conteúdo do HashMap: " + numMap);

        comparisonCount++;

        // Verifica se o complemento já existe no HashMap
        if (numMap.containsKey(complemento)) {
            System.out.println("  ✓ Complemento encontrado no índice " + numMap.get(complemento));
            return new int[]{numMap.get(complemento), i};
        }

        // Se não encontrou, adiciona o número atual e seu índice ao HashMap
        numMap.put(nums[i], i);
        System.out.println("  → Adicionando " + nums[i] + " no índice " + i + " ao HashMap");
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

    int[] result = twoSumLinear(nums, target);

    System.out.println("\n=== RESULTADO FINAL ===");
    if (result != null) {
        System.out.println("✓ Solução encontrada!");
        System.out.println("  Índices: " + result[0] + ", " + result[1]);
        System.out.println("  Valores: " + nums[result[0]] + " + " +
                           nums[result[1]] + " = " + target);
    } else {
        System.out.println("✗ Nenhuma solução encontrada");
    }
    System.out.println("Total de iterações: " + iterationCount);
    System.out.println("Total de comparações no HashMap: " + comparisonCount);
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
