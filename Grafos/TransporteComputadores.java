import static java.lang.Integer.compare;
import static java.lang.String.valueOf;

/*

    DESAFIO: Transporte de computadores

    A empresa PPC (Programadores Procrastinadores Compulsivos), faz entregas de computadores de
    última geração aos seus clientes, localizados em todos o país.

    Para manter um tratamento VIP com todos seus clientes, para cada entrega que a empresa faz, ela
    atribui um único entregador, que fará também a instalação da máquina. O entregador procura sempre o
    menor caminho possível, de modo a minimizar as distâncias percorridas.

    Há uma lista de clientes interessados em comprar um dos computadores da PPC, mas o gerente não
    sabe qual será o custo total de enviar entregadores para todos eles. Você precisa ajudá-lo, fazendo um
    programa que informe qual é o custo mínimo de fazer todas essas entregas VIP, dada uma lista que
    informa a cidade dos clientes interessados.

    Você recebe um inteiro n, indicando que existem n cidades estão numeradas de 0 e a n - 1, e também
    uma lista connections onde connections[i] = [xi, yi, costi] indica que o custo conectando as cidades xi
    e yi (conexão bidirecional) é costi.

    Adicionalmente, você recebe também uma lista locations onde locations[i] = [li], indicando a cidade de
    rótulo li onde o cliente i reside. A empresa está sempre localizada na cidade 0.

    Seu programa deverá calcular o custo total de enviar um entregador para a cidade de cada um dos
    clientes interessados, de forma que essa distância percorrida seja mínima.

    Obs: considere que tomando uma cidade qualquer, sempre é possível atingir todas as outras (grafo
    conectado).

    Entrada 1
    {
        "n": 6,
        "connections": [
            [0, 1, 1],
            [0, 2, 2],
            [0, 3, 3],
            [1, 4, 3],
            [2, 4, 2],
            [3, 4, 3],
            [4, 5, 4]
    ],
    "locations": [1, 2, 3, 4, 5]
    }
    Saída 1: 18
    Explicação: Os menores custos para entregar nas cidades dos clientes são, respectivamente, 1, 2, 3, 4 e
    8, somando esses valores obtemos 18.

    Entrada 2 :
    {
        "n": 6,
        "connections": [
            [0, 1, 1],
            [0, 2, 2],
            [0, 3, 3],
            [1, 4, 3],
            [2, 4, 2],
            [3, 4, 3],
            [4, 5, 4]
    ],
    "locations": [2, 4, 5, 5]
    }
    Saída 2: 22

    Explicação: Os menores custos para entregar nas cidades dos clientes são, respectivamente, 2, 4, 8, 8,
    somando esses valores obtemos 22.
     */

static String[] totalDeliveryCost(int n, int[][] connections, int[] locations) {
    // Create adjacency list representation of the graph
    List<List<Edge>> graph = new ArrayList<>();

    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

    // Build undirected graph
    for (int[] connection : connections) {
        int city1 = connection[0];
        int city2 = connection[1];
        int cost = connection[2];
        graph.get(city1).add(new Edge(city2, cost));
        graph.get(city2).add(new Edge(city1, cost));
    }

    // Find the shortest path costs from city 0 to all other cities
    int[] shortestPaths = dijkstra(graph, 0, n);

    // Calculate total cost for all deliveries
    long totalCost = 0;
    for (int location : locations) totalCost += shortestPaths[location];

    // Return result as a String array with a single element
    return new String[]{valueOf(totalCost)};
}

static class Edge implements Cloneable {
    int destination;
    int cost;

    Edge(int destination, int cost) {
        this.destination = destination;
        this.cost = cost;
    }

    // Copy constructor
    public Edge(Edge edge) {
        this.destination = edge.destination;
        this.cost = edge.cost;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Edge that = (Edge) o;

        return this.destination == that.destination &&
               this.cost == that.cost;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash *= prime + destination;
        hash *= prime + cost;

        if (hash < 0) hash = -hash;

        return hash;
    }

    @Override
    public final String toString() {
        return "Edge{" +
               "destination=" + destination +
               ", cost=" + cost +
               '}';
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Edge clone() {
        Edge clone = null;
        try {
            clone = new Edge(this);
        } catch (Exception ignored) {
        }
        return clone;
    }
}

static class Node implements Comparable<Node>, Cloneable {
    int city;
    int distance;

    Node(int city, int distance) {
        this.city = city;
        this.distance = distance;
    }

    // Copy constructor
    public Node(Node node) {
        this.city = node.city;
        this.distance = node.distance;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Node that = (Node) o;

        return this.city == that.city &&
               this.distance == that.distance;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash *= prime + city;
        hash *= prime + distance;

        if (hash < 0) hash = -hash;

        return hash;
    }

    @Override
    public final String toString() {
        return "Node{" +
               "city=" + city +
               ", distance=" + distance +
               '}';
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Node clone() {
        Node clone = null;
        try {
            clone = new Node(this);
        } catch (Exception ignored) {
        }
        return clone;
    }

    @Override
    public int compareTo(Node other) {
        return compare(this.distance, other.distance);
    }
}

static int[] dijkstra(List<List<Edge>> graph, int start, int n) {
    // Initialize distances array
    int[] distances = new int[n];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[start] = 0;

    // Priority queue to get the minimum distance node
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));

    while (!pq.isEmpty()) {
        Node current = pq.poll();
        int currentCity = current.city;
        int currentDistance = current.distance;

        // Skip if we've found a better path already
        if (currentDistance > distances[currentCity]) {
            continue;
        }

        // Check all neighboring cities
        for (Edge edge : graph.get(currentCity)) {
            int newDistance = currentDistance + edge.cost;

            // Update distance if we found a shorter path
            if (newDistance < distances[edge.destination]) {
                distances[edge.destination] = newDistance;
                pq.offer(new Node(edge.destination, newDistance));
            }
        }
    }

    return distances;
}

static void main(String[] ignoredArgs) {
    // Test Case 1
    System.out.println("Test Case 1:");
    int n1 = 6;
    int[][] connections1 = {
            {0, 1, 1},
            {0, 2, 2},
            {0, 3, 3},
            {1, 4, 3},
            {2, 4, 2},
            {3, 4, 3},
            {4, 5, 4}
    };
    int[] locations1 = {1, 2, 3, 4, 5};

    String[] result1 = totalDeliveryCost(n1, connections1, locations1);
    System.out.println("Expected: 18");
    System.out.println("Got: " + result1[0]);
    System.out.println("Test Case 1 " + (result1[0].equals("18") ? "PASSED" : "FAILED"));
    System.out.println();

    // Test Case 2
    System.out.println("Test Case 2:");
    int n2 = 6;
    int[][] connections2 = {
            {0, 1, 1},
            {0, 2, 2},
            {0, 3, 3},
            {1, 4, 3},
            {2, 4, 2},
            {3, 4, 3},
            {4, 5, 4}
    };
    int[] locations2 = {2, 4, 5, 5};

    String[] result2 = totalDeliveryCost(n2, connections2, locations2);
    System.out.println("Expected: 22");
    System.out.println("Got: " + result2[0]);
    System.out.println("Test Case 2 " + (result2[0].equals("22") ? "PASSED" : "FAILED"));
}
