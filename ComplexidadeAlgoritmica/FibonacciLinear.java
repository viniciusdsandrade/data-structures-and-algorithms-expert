private static int operacaoCount = 0;
private static final String indent = "  ";

/**
 * Calcula o n-ésimo número de Fibonacci usando programação dinâmica (bottom-up).
 * Complexidade Temporal: O(n) - percorre cada número apenas uma vez
 * Complexidade Espacial: O(1) - usa apenas duas variáveis para armazenar estados
 *
 * @param n número da sequência de Fibonacci a ser calculado
 * @return n-ésimo número de Fibonacci
 */
static long fibonacciLinear(int n) {
    operacaoCount = 0;

    System.out.println("\n→ Iniciando cálculo de Fibonacci(" + n + ") com método linear");

    if (n <= 1) {
        System.out.println(indent + "✓ Caso base: fib(" + n + ") = " + n);
        return n;
    }

    long fibAnterior = 0;
    long fibAtual = 1;

    System.out.println(indent + "Iniciando com:");
    System.out.println(indent + "fib(0) = " + fibAnterior);
    System.out.println(indent + "fib(1) = " + fibAtual);

    for (int i = 2; i <= n; i++) {
        operacaoCount++;

        System.out.println("\n" + indent + "Iteração " + i + ":");
        System.out.println(indent + "  Valores atuais: fibAnterior = " + fibAnterior + ", fibAtual = " + fibAtual);

        long novoFib = fibAnterior + fibAtual;

        System.out.println(indent + "  Calculando fib(" + i + ") = " + fibAnterior + " + " + fibAtual + " = " + novoFib);

        // Atualiza os valores para a próxima iteração
        fibAnterior = fibAtual;
        fibAtual = novoFib;

        System.out.println(indent + "  Preparando próxima iteração: fibAnterior = " + fibAnterior + ", fibAtual = " + fibAtual);
    }

    return fibAtual;
}

static void executarTeste(int n) {
    System.out.println("\n========= TESTE FIBONACCI LINEAR(" + n + ") =========");

    long startTime = System.nanoTime();
    long resultado = fibonacciLinear(n);
    long endTime = System.nanoTime();

    System.out.println("\n=== RESULTADO FINAL ===");
    System.out.println("Fibonacci(" + n + ") = " + resultado);
    System.out.println("Total de operações: " + operacaoCount);
    System.out.println("Tempo de execução: " + ((endTime - startTime) / 1000000.0) + " ms");
    System.out.println("============================\n");
}

static void main(String[] ignoredArgs) {
    System.out.println("*** Demonstração de Complexidade Linear O(n) ***");
    System.out.println("Observe como o número de operações cresce linearmente");

    executarTeste(3);    // Pequeno
    executarTeste(15);   // Médio
    executarTeste(45);   // Grande - muito maior que a versão exponencial!
}
