static int chamadaCount = 0;
static int nivelRecursao = 0;
static String indent = "";

/**
 * Calcula o n-ésimo número de Fibonacci de forma recursiva.
 * Complexidade Temporal: O(2^n) - cada chamada gera duas novas chamadas
 * Complexidade Espacial: O(n) - devido à pilha de recursão
 *
 * @param n número da sequência de Fibonacci a ser calculado
 * @return n-ésimo número de Fibonacci
 */
static int fibonacciExponential(int n) {
    chamadaCount++;
    nivelRecursao++;
    indent = "  ".repeat(nivelRecursao);

    System.out.println(indent + "→ [Chamada #" + chamadaCount + "] Calculando fibonacci(" + n + ")");

    // Caso base
    if (n <= 1) {
        System.out.println(indent + "✓ Caso base: fib(" + n + ") = " + n);
        nivelRecursao--;
        return n;
    }

    System.out.println(indent + "↓ Dividindo fib(" + n + ") em fib(" + (n - 1) + ") + fib(" + (n - 2) + ")");

    // Chamadas recursivas
    int fib1 = fibonacciExponential(n - 1);
    int fib2 = fibonacciExponential(n - 2);
    int resultado = fib1 + fib2;

    System.out.println(indent + "← Resultado: fib(" + n + ") = " + fib1 + " + " + fib2 + " = " + resultado);

    nivelRecursao--;
    return resultado;
}

static void executarTeste(int n) {
    System.out.println("\n========= TESTE FIBONACCI(" + n + ") =========");
    chamadaCount = 0;
    nivelRecursao = 0;

    long startTime = System.nanoTime();
    int resultado = fibonacciExponential(n);
    long endTime = System.nanoTime();

    System.out.println("\n=== RESULTADO FINAL ===");
    System.out.println("Fibonacci(" + n + ") = " + resultado);
    System.out.println("Total de chamadas recursivas: " + chamadaCount);
    System.out.println("Tempo de execução: " + ((endTime - startTime) / 1000000.0) + " ms");
    System.out.println("============================\n");
}

static void main(String[] ignoredArgs) {
    // Teste com diferentes valores para demonstrar o crescimento exponencial
    System.out.println("*** Demonstração de Complexidade Exponencial O(2^n) ***");
    System.out.println("Observe como o número de chamadas cresce exponencialmente");

    executarTeste(3);  // Pequeno
    executarTeste(15);  // Médio
    executarTeste(27);  // Grande
}
