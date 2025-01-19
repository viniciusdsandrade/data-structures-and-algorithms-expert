import static java.lang.System.nanoTime;

/*

    547. Number of Provinces

    There are n cities. Some of them are connected, while some are not.
    If city 'a' is connected directly with city 'b', and city 'b' is connected directly with city 'c',
    then city 'a' is connected indirectly with city 'c'.

    A province is a group of directly or indirectly connected cities and no other cities outside the group.

    You are given an n x n matrix isConnected where isConnected[i][j] = 1
    if the ith city and the jth city are directly connected,
    and isConnected[i][j] = 0 otherwise.

    Return the total number of provinces.

    Example 1:
    Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
    Output: 2

    Example 2:
    Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
    Output: 3

    Constraints:
    1 ≤ n ≤ 200
    n == isConnected.length
    n == isConnected[i].length
    isConnected[i][j] is 1 or 0.
    isConnected[i][i] == 1
    isConnected[i][j] == isConnected[j][i]

     */

static int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;
    boolean[] visited = new boolean[n];
    int provinces = 0;

    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            // Found a new province
            provinces++;
            // DFS to mark all connected cities starting from city i
            dfs(isConnected, visited, i);
        }
    }

    return provinces;
}

private static void dfs(int[][] isConnected, boolean[] visited, int city) {
    visited[city] = true;

    // Explore neighbours
    for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
        // If there's a direct connection, and it's not visited yet, recurse
        if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
            dfs(isConnected, visited, neighbor);
        }
    }
}

static int findCircleNumUnionFind(int[][] isConnected) {
    int n = isConnected.length;
    UnionFind uf = new UnionFind(n);

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) { // Evita redundância
            if (isConnected[i][j] == 1) {
                uf.union(i, j);
            }
        }
    }

    return uf.getCount();
}

static void testFindCircleNum(int[][] isConnected) {
    System.out.println("Input: isConnected = ");
    printMatrix(isConnected);

    long[] times = new long[2];
    int[] results = new int[2];

    long start = nanoTime();
    results[0] = findCircleNum(isConnected);
    times[0] = nanoTime() - start;

    start = nanoTime();
    results[1] = findCircleNumUnionFind(isConnected);
    times[1] = nanoTime() - start;

    // Verificar consistência dos resultados
    boolean consistent = true;
    for (int i = 1; i < results.length; i++) {
        if (results[i] != results[0]) {
            consistent = false;
            break;
        }
    }

    if (!consistent) {
        System.out.println("Erro: Os métodos retornaram resultados diferentes!");
        System.out.println("findCircleNum result:          " + results[0]);
        System.out.println("findCircleNumUnionFind result: " + results[1]);
        return;
    }

    int fastest = 0, slowest = 0;
    for (int i = 1; i < times.length; i++) {
        if (times[i] < times[fastest]) fastest = i;
        if (times[i] > times[slowest]) slowest = i;
    }

    double ratio = (double) times[slowest] / times[fastest];

    // Exibir resultados
    System.out.println("findCircleNum          result: " + results[0] + " runtime: " + times[0] + " ns");
    System.out.println("findCircleNumUnionFind result: " + results[1] + " runtime: " + times[1] + " ns");
    System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
    String[] methodNames = {"findCircleNum", "findCircleNumUnionFind"};
    System.out.println("Método mais rápido: " + methodNames[fastest]);
    System.out.println();
}

static void main(String[] ignoredArgs) {
    // Exemplo 1
    int[][] isConnected1 = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
    };
    testFindCircleNum(isConnected1); // Output esperado: 2

    // Exemplo 2
    int[][] isConnected2 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
    };
    testFindCircleNum(isConnected2); // Output esperado: 3

    // Exemplo 3
    int[][] isConnected3 = {
            {1, 1, 1, 0},
            {1, 1, 0, 0},
            {1, 0, 1, 1},
            {0, 0, 1, 1}
    };
    testFindCircleNum(isConnected3); // Output esperado: 1
}

static void printMatrix(int[][] matrix) {
    System.out.println("[");
    for (int i = 0; i < matrix.length; i++) {
        System.out.print(" [");
        for (int j = 0; j < matrix[i].length; j++) {
            System.out.print(matrix[i][j]);
            if (j < matrix[i].length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        if (i < matrix.length - 1) {
            System.out.println(",");
        } else {
            System.out.println();
        }
    }
    System.out.println("]");
}


// Classe auxiliar para Union-Find
static class UnionFind {
    private final int[] parent;
    private final int[] rank;
    private int count; // Número de conjuntos distintos

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n; // Inicialmente, cada nó é seu próprio conjunto

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // Union by rank
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            count--; // Reduz o número de conjuntos
        }
    }

    public int getCount() {
        return count;
    }
}
