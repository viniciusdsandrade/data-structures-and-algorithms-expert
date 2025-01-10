package desafio;

import java.util.TreeMap;

import static java.lang.System.nanoTime;
import static java.util.Arrays.sort;

public class DragaoAgualoo {

    /*

    DESAFIO: Dragão de Agualoo

    Certo dia o Reino de Agualoo foi atacado por um dragão cuspidor de fogo de várias cabeças,
    ameaçando queimar o reino até as cinzas. Preocupado, o rei chamou seus cavaleiros para derrotar o
    dragão e salvar o reino.

    Os cavaleiros explicaram: “Para derrotar o dragão, temos que cortar todas as suas cabeças. Cada
    cavaleiro consegue cortar só uma das cabeças do dragão. A ordem dos cavaleiros requer que, por
    cortar uma cabeça, um cavaleiro seja pago uma recompensa igual a uma moeda de ouro para cada
    centímetro da altura do cavaleiro.

    Haverá cavaleiros o suficiente para derrotar o dragão? O rei chamou seus conselheiros para     decidir quantos e quais cavaleiros contratar, minimizando o custo de derrotar o dragão. Como um de
    seus conselheiros, você deve ajudar o rei. Isto é muito sério: se você falhar, o reino inteiro será
    reduzido a cinzas!

    Entrada

    Obs: o formato aqui está diferente do vídeo explicativo, mas são os mesmos dados. O importante é fazer a função
    que receba os dados e produza a saída desejada. Veja as assinaturas das funções ao final deste documento.

     n: o número de cabeças do dragão.
     m: o número de cavaleiros no reino.
     diameters: uma lista de inteiros que representam o diâmetro das cabeças do dragão, em centímetros.
     heights: uma lista de inteiros que especifica a altura dos cavaleiros de Agualoo, também em centímetros.

    Saída

    Imprima uma linha contendo o número mínimo de moedas de ouro que o rei precisará pagar para
    derrotar o dragão. Se não for possível que os cavaleiros de Agualoo derrotem o dragão, imprima a
    linha “Agualoo esta condenada!”.

    Entrada 1
    {
        "n": 2,
        "m": 3,
        "diameters": [5, 4],
        "heights": [7, 8, 4]
    }
    Saída 1: 11

    Entrada 2
    {
        "n": 2,
        "m": 1,
        "diameters": [5, 5],
        "heights": [10]
    }
    Saída 2: Agualoo esta condenada!

    Entrada 3
    {
        "n": 2,
        "m": 4,
        "diameters": [7, 2],
        "heights": [4, 3, 1, 2]
    }
    Saída 3: Agualoo esta condenada!

    Entrada 4
    {
        "n": 2,
        "m": 4,
        "diameters": [7, 2],
        "heights": [2, 1, 8, 5]
    }
    Saída 4: 10

    Entrada 5
    {
        "n": 2,
        "m": 10,
        "diameters": [1234567, 2345],
        "heights": [12345610, 1, 123, 23564,
        123456, 123, 2, 3, 2, 1]
    }
    Saída 5: 12369174

    Assinatura: static void agualoo(int n, int m, int[] diameters, int[] heights)

     */

    static void agualoo(int n, int m, int[] diameters, int[] heights) {
        // Verificação inicial: se o número de cabeças for maior que o número de cavaleiros,
        // não é possível atribuir um cavaleiro para cada cabeça.
        if (n > m) {
            System.out.println("Agualoo esta condenada!");
            return;
        }

        // Passo 1: Ordenar os diâmetros das cabeças em ordem decrescente.
        // Isso garante que as cabeças maiores sejam tratadas primeiro.
        sort(diameters);
        // Inverter o array para ter a ordem decrescente.
        for (int i = 0; i < diameters.length / 2; i++) {
            int temp = diameters[i];
            diameters[i] = diameters[diameters.length - 1 - i];
            diameters[diameters.length - 1 - i] = temp;
        }

        // Passo 2: Ordenar as alturas dos cavaleiros em ordem crescente.
        // Isso facilita a seleção do cavaleiro mais baixo possível que ainda pode cortar a cabeça atual,
        // minimizando assim o custo total.
        sort(heights);

        // Passo 3: Utilizar um TreeMap para simular um multiset.
        // O TreeMap armazena as alturas dos cavaleiros como chaves e a quantidade de cavaleiros
        // com aquela altura como valores.
        TreeMap<Integer, Integer> heightsMap = new TreeMap<>();
        for (int height : heights) {
            heightsMap.put(height, heightsMap.getOrDefault(height, 0) + 1);
        }

        // Variável para acumular o custo total em moedas de ouro.
        long totalGold = 0;

        // Passo 4: Atribuir cada cabeça a um cavaleiro adequado.
        for (int diameter : diameters) {
            // Encontrar a menor altura de cavaleiro que é ≥ ao diâmetro da cabeça atual.
            Integer key = heightsMap.ceilingKey(diameter);
            if (key == null) {
                // Se não houver nenhum cavaleiro capaz de cortar esta cabeça,
                // significa que não é possível derrotar o dragão.
                System.out.println("Agualoo esta condenada!");
                return;
            } else {
                // Adicionar a altura do cavaleiro ao custo total.
                totalGold += key;
                // Atualizar o TreeMap para refletir que este cavaleiro foi utilizado.
                if (heightsMap.get(key) == 1) {
                    // Se era o único cavaleiro com esta altura, remover a entrada do mapa.
                    heightsMap.remove(key);
                } else {
                    // Caso contrário, decrementar a contagem de cavaleiros com esta altura.
                    heightsMap.put(key, heightsMap.get(key) - 1);
                }
            }
        }

        // Após atribuir todas as cabeças, imprimir o custo total.
        System.out.println(totalGold);
    }

    static void testAgualoo(int n, int m, int[] diameters, int[] heights) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("    \"n\": ").append(n).append(",\n");
        sb.append("    \"m\": ").append(m).append(",\n");

        // Formatar e adicionar o array de diâmetros com indentação.
        sb.append("    \"diameters\": [");
        for (int i = 0; i < diameters.length; i++) {
            sb.append(diameters[i]);
            if (i != diameters.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("],\n");

        // Formatar e adicionar o array de alturas com indentação.
        sb.append("    \"heights\": [");
        for (int i = 0; i < heights.length; i++) {
            sb.append(heights[i]);
            if (i != heights.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]\n");
        sb.append("}");

        System.out.println(sb);

        long startTime, endTime, runTime;

        startTime = nanoTime();
        agualoo(n, m, diameters, heights);
        endTime = nanoTime();

        runTime = endTime - startTime;

        System.out.println("Runtime: " + runTime + " ns\n");
    }

    static void main(String[] ignoredArgs) {

        // Caso 1
        int n1 = 2;
        int m1 = 3;
        int[] diameters1 = {5, 4};
        int[] heights1 = {7, 8, 4};

        // Caso 2
        int n2 = 2;
        int m2 = 1;
        int[] diameters2 = {5, 5};
        int[] heights2 = {10};

        // Caso 3
        int n3 = 2;
        int m3 = 4;
        int[] diameters3 = {7, 2};
        int[] heights3 = {4, 3, 1, 2};

        // Caso 4
        int n4 = 2;
        int m4 = 4;
        int[] diameters4 = {7, 2};
        int[] heights4 = {2, 1, 8, 5};

        // Caso 5
        int n5 = 2;
        int m5 = 10;
        int[] diameters5 = {1234567, 2345};
        int[] heights5 = {12345610, 1, 123, 23564, 123456, 123, 2, 3, 2, 1};

        // Executar testes
        testAgualoo(n1, m1, diameters1, heights1);
        testAgualoo(n2, m2, diameters2, heights2);
        testAgualoo(n3, m3, diameters3, heights3);
        testAgualoo(n4, m4, diameters4, heights4);
        testAgualoo(n5, m5, diameters5, heights5);
    }
}
