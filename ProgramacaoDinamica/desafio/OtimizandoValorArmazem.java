package desafio;

import static java.lang.Math.max;
import static java.lang.System.nanoTime;

public class OtimizandoValorArmazem {

    /*

    DESAFIO: Otimizando Valor no Armazém

    A empresa de logística, Armazenamento Eficiente S.A., está enfrentando desafios com a otimização do
    espaço em seu novo armazém. O armazém, localizado estrategicamente perto de um grande centro
    comercial, tem capacidade para armazenar até C metros cúbicos de mercadorias.

    Você, como especialista em logística e otimização, foi chamado para resolver esse desafio. A empresa
    trabalha com uma variedade de fornecedores que oferecem produtos variados, cada um com um preço
    de venda específico e um tamanho definido. Para manter a eficiência e rentabilidade da operação, você
    precisa escolher cuidadosamente quais produtos devem ser armazenados no armazém.

    Para tal, você recebeu dados dos N produtos disponíveis. Cada produto i tem um preço de venda
    prices[i] e ocupa volume[i] metros cúbicos de espaço no armazém.

    Selecione os produtos de modo a maximizar o valor total armazenado no armazém, sem exceder sua
    capacidade máxima C.

    Regras
    Você não pode armazenar frações de produtos; ou um produto inteiro é armazenado, ou não é.
    Só se pode comprar uma unidade de cada produto listado.
    A soma dos tamanhos dos produtos armazenados não pode exceder a capacidade total do armazém C.
    Não é preciso considerar as dimensões dos produtos, apenas o volume ocupado.
    Entrada
        ● Um número inteiro C representando a capacidade total do armazém (em metros cúbicos).
        ● Um número inteiro N representando o número de produtos disponíveis.
        ● Um array de inteiros prices de tamanho N, onde prices[i] é o preço de venda do produto i.
        ● Um array de inteiros volume[i] de tamanho N, onde volume[i] é o tamanho do produto i (em metros cúbicos).
    Saída
    Um número inteiro representando o valor máximo dos produtos que podem ser armazenados no
    armazém sem exceder sua capacidade.

    Entrada 1
    {
        "C": 10,
        "N": 4,
        "prices": [5, 12, 8, 1],
        "volume": [4, 8, 5, 3]
    }
   Saída 1: 13

    Entrada 2 :
    {
        "C": 10,
        "N": 4,
        "prices": [5, 15, 8, 1],
        "volume": [4, 8, 5, 3]
    }
    Saída 2: 15

    Entrada 3
    {
        "C": 4,
        "N": 3,
        "prices": [1, 2, 3],
        "volume": [4, 5, 1]
    }
    Saída 3: 3

    Entrada 4
    {
        "C": 3,
        "N": 3,
        "prices": [1, 2, 3],
        "volume": [4, 5, 6]
    }
 Saída 4: 0

    Entrada 5:
    {
        "C": 0,
        "N": 3,
        "prices": [20, 30, 40],
        "volume": [10, 20, 30]
    }
    Saída 5: 0

    Entrada 6:
        {
            "C": 100,
            "N": 0,
            "prices": [],
            "volume": []
        }
    Saída 6: 0

    Entrada 7
    {
      "C": 1000,
      "N": 200,
      "prices": [
        100, 50, 150, 80, 120, 140, 200, 60, 110, 130,
        170, 190, 210, 230, 180, 220, 160, 90, 240, 70,
        250, 260, 300, 270, 280, 290, 310, 320, 330, 340,
        350, 360, 370, 380, 390, 400, 410, 420, 430, 440,
        450, 460, 470, 480, 490, 500, 510, 520, 530, 540,
        550, 560, 570, 580, 590, 600, 610, 620, 630, 640,
        650, 660, 670, 680, 690, 700, 710, 720, 730, 740,
        750, 760, 770, 780, 790, 800, 810, 820, 830, 840,
        850, 860, 870, 880, 890, 900, 910, 920, 930, 940,
        950, 960, 970, 980, 990, 1000, 1010, 1020, 1030, 1040,
        1050, 1060, 1070, 1080, 1090, 1100, 1110, 1120, 1130, 1140,
        1150, 1160, 1170, 1180, 1190, 1200, 1210, 1220, 1230, 1240,
        1250, 1260, 1270, 1280, 1290, 1300, 1310, 1320, 1330, 1340,
        1350, 1360, 1370, 1380, 1390, 1400, 1410, 1420, 1430, 1440,
        1450, 1460, 1470, 1480, 1490, 1500, 1510, 1520, 1530, 1540,
        1550, 1560, 1570, 1580, 1590, 1600, 1610, 1620, 1630, 1640,
        1650, 1660, 1670, 1680, 1690, 1700, 1710, 1720, 1730, 1740,
        1750, 1760, 1770, 1780, 1790, 1800, 1810, 1820, 1830, 1840,
        1850, 1860, 1870, 1880, 1890, 1900, 1910, 1920, 1930, 1940,
        1950, 1960, 1970, 1980, 1990, 2000, 2010, 2020, 2030, 2040
      ],
      "volume": [
        10, 20, 30, 15, 25, 35, 50, 10, 20, 30,
        40, 45, 55, 60, 70, 80, 90, 15, 25, 35,
        10, 20, 15, 25, 20, 30, 35, 45, 25, 15,
        30, 25, 35, 45, 50, 55, 60, 15, 25, 30,
        40, 20, 15, 25, 30, 40, 15, 35, 45, 50,
        55, 25, 30, 15, 35, 20, 25, 30, 20, 15,
        10, 15, 20, 25, 35, 40, 45, 50, 15, 25,
        10, 30, 40, 15, 35, 50, 20, 25, 30, 15,
        10, 20, 30, 15, 20, 25, 30, 40, 45, 50,
        15, 20, 25, 35, 30, 20, 15, 35, 40, 50,
        55, 60, 15, 25, 30, 40, 25, 15, 20, 30,
        35, 40, 45, 50, 15, 20, 25, 30, 35, 40,
        45, 50, 10, 20, 25, 30, 35, 40, 15, 25,
        30, 40, 15, 25, 30, 10, 15, 20, 25, 30,
        35, 40, 45, 50, 15, 25, 30, 40, 20, 15,
        30, 25, 35, 45, 50, 55, 60, 15, 25, 30,
        40, 20, 15, 25, 30, 40, 15, 35, 45, 50,
        55, 25, 30, 15, 35, 20, 25, 30, 20, 15,
        35, 40, 45, 50, 15, 20, 25, 30, 35, 40,
        45, 50, 10, 20, 25, 30, 35, 40, 15, 25
      ]
    }

    Saída 7: 78970


    Assinaturas:
    Java: public int maxWarehouseValue(int C, int N, int[] prices, int[] volume)

     */

    static int maxWarehouseValue(int C, int N, int[] prices, int[] volume) {
        int[][] dp = new int[N + 1][C + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                if (volume[i - 1] <= j) {
                    dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - volume[i - 1]] + prices[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][C];
    }

    // Second implementation using 1D DP array (space optimized)
    static int maxWarehouseValue2(int C, int N, int[] prices, int[] volume) {
        if (C <= 0 || N == 0) return 0;
        int[] dp = new int[C + 1];

        for (int i = 0; i < N; i++) {
            for (int w = C; w >= volume[i]; w--) {
                dp[w] = max(dp[w], dp[w - volume[i]] + prices[i]);
            }
        }
        return dp[C];
    }

    static void testWarehouseImplementations(int C, int N, int[] prices, int[] volume) {
        System.out.println("\nTesting implementations for C=" + C + ", N=" + N);

        int[] results = new int[2];
        long[] totalTimes = new long[2];
        final int WARMUP_RUNS = 5;
        final int TEST_RUNS = 10;

        // JVM warmup
        for (int i = 0; i < WARMUP_RUNS; i++) {
            maxWarehouseValue(C, N, prices.clone(), volume.clone());
            maxWarehouseValue2(C, N, prices.clone(), volume.clone());
        }

        // Actual tests
        for (int run = 0; run < TEST_RUNS; run++) {
            System.gc();
            long start = nanoTime();
            results[0] = maxWarehouseValue(C, N, prices.clone(), volume.clone());
            totalTimes[0] += nanoTime() - start;

            System.gc();
            start = nanoTime();
            results[1] = maxWarehouseValue2(C, N, prices.clone(), volume.clone());
            totalTimes[1] += nanoTime() - start;
        }

        // Calculate averages
        double[] avgTimes = new double[2];
        avgTimes[0] = totalTimes[0] / (double)TEST_RUNS;
        avgTimes[1] = totalTimes[1] / (double)TEST_RUNS;

        // Determine fastest
        int fastest = avgTimes[1] < avgTimes[0] ? 1 : 0;
        int slowest = 1 - fastest;

        // Calculate ratio
        double ratio = avgTimes[slowest] / avgTimes[fastest];

        // Display results
        System.out.println("maxWarehouseValue1 result: " + results[0]);
        System.out.println("maxWarehouseValue2 result: " + results[1]);
        System.out.printf("Average time maxWarehouseValue1: %.2f ns%n", avgTimes[0]);
        System.out.printf("Average time maxWarehouseValue2: %.2f ns%n", avgTimes[1]);
        System.out.printf("Ratio (slow/fast): %.2fx%n", ratio);
        System.out.println("Fastest method: maxWarehouseValue" + (fastest + 1));

        // Verify results match
        if (results[0] != results[1]) {
            System.out.println("WARNING: Results don't match!");
        }
    }

    static void main(String[] ignoredArgs) {
        // Teste 1
        testWarehouseImplementations(
                10, 4,
                new int[]{5, 12, 8, 1},
                new int[]{4, 8, 5, 3}
        );

        // Teste 2
        testWarehouseImplementations(
                10, 4,
                new int[]{5, 15, 8, 1},
                new int[]{4, 8, 5, 3}
        );

        // Teste 3
        testWarehouseImplementations(
                4, 3,
                new int[]{1, 2, 3},
                new int[]{4, 5, 1}
        );

        // Teste 4
        testWarehouseImplementations(
                3, 3,
                new int[]{1, 2, 3},
                new int[]{4, 5, 6}
        );

        // Teste 5
        testWarehouseImplementations(
                0, 3,
                new int[]{20, 30, 40},
                new int[]{10, 20, 30}
        );

        // Teste 6
        testWarehouseImplementations(
                100, 0,
                new int[]{},
                new int[]{}
        );

        // Teste 7 (grande entrada)
        int[] prices7 = {
                100, 50, 150, 80, 120, 140, 200, 60, 110, 130, 170, 190, 210, 230, 180,
                220, 160, 90, 240, 70, 250, 260, 300, 270, 280, 290, 310, 320, 330, 340, 350,
                360, 370, 380, 390, 400, 410, 420, 430, 440, 450, 460, 470, 480, 490, 500, 510,
                520, 530, 540, 550, 560, 570, 580, 590, 600, 610, 620, 630, 640, 650, 660, 670,
                680, 690, 700, 710, 720, 730, 740, 750, 760, 770, 780, 790, 800, 810, 820, 830,
                840, 850, 860, 870, 880, 890, 900, 910, 920, 930, 940, 950, 960, 970, 980, 990,
                1000, 1010, 1020, 1030, 1040, 1050, 1060, 1070, 1080, 1090, 1100, 1110, 1120,
                1130, 1140, 1150, 1160, 1170, 1180, 1190, 1200, 1210, 1220, 1230, 1240, 1250,
                1260, 1270, 1280, 1290, 1300, 1310, 1320, 1330, 1340, 1350, 1360, 1370, 1380,
                1390, 1400, 1410, 1420, 1430, 1440, 1450, 1460, 1470, 1480, 1490, 1500, 1510,
                1520, 1530, 1540, 1550, 1560, 1570, 1580, 1590, 1600, 1610, 1620, 1630, 1640,
                1650, 1660, 1670, 1680, 1690, 1700, 1710, 1720, 1730, 1740, 1750, 1760, 1770,
                1780, 1790, 1800, 1810, 1820, 1830, 1840, 1850, 1860, 1870, 1880, 1890, 1900,
                1910, 1920, 1930, 1940, 1950, 1960, 1970, 1980, 1990, 2000, 2010, 2020, 2030, 2040
        };

        int[] volume7 = {
                10, 20, 30, 15, 25, 35, 50, 10, 20, 30, 40, 45, 55, 60, 70, 80, 90,
                15, 25, 35, 10, 20, 15, 25, 20, 30, 35, 45, 25, 15, 30, 25, 35, 45, 50, 55,
                60, 15, 25, 30, 40, 20, 15, 25, 30, 40, 15, 35, 45, 50, 55, 25, 30, 15, 35,
                20, 25, 30, 20, 15, 10, 15, 20, 25, 35, 40, 45, 50, 15, 25, 10, 30, 40, 15,
                35, 50, 20, 25, 30, 15, 10, 20, 30, 15, 20, 25, 30, 40, 45, 50, 15, 20, 25,
                35, 30, 20, 15, 35, 40, 50, 55, 60, 15, 25, 30, 40, 25, 15, 20, 30, 35, 40,
                45, 50, 15, 20, 25, 30, 35, 40, 45, 50, 10, 20, 25, 30, 35, 40, 15, 25, 30,
                40, 15, 25, 30, 10, 15, 20, 25, 30, 35, 40, 45, 50, 15, 25, 30, 40, 20, 15,
                30, 25, 35, 45, 50, 55, 60, 15, 25, 30, 40, 20, 15, 25, 30, 40, 15, 35, 45,
                50, 55, 25, 30, 15, 35, 20, 25, 30, 20, 15, 35, 40, 45, 50, 15, 20, 25, 30,
                35, 40, 45, 50, 10, 20, 25, 30, 35, 40, 15, 25
        };

        testWarehouseImplementations(
                1000, 200,
                prices7,
                volume7
        );
    }

    static void printParameters(int C, int N, int[] prices, int[] volume) {
        StringBuilder json = new StringBuilder();
        json.append("Entrada: {\n");
        json.append("    \"C\": ").append(C).append(",\n");
        json.append("    \"N\": ").append(N).append(",\n");

        // Add prices array
        json.append("    \"prices\": [");
        for (int i = 0; i < prices.length; i++) {
            json.append(prices[i]);
            if (i < prices.length - 1) {
                json.append(", ");
            }
        }
        json.append("],\n");

        // Add volume array
        json.append("    \"volume\": [");
        for (int i = 0; i < volume.length; i++) {
            json.append(volume[i]);
            if (i < volume.length - 1) {
                json.append(", ");
            }
        }
        json.append("]\n");

        json.append("}");

        System.out.println(json);
    }

    static void testMaxWarehouseValue(int C, int N, int[] prices, int[] volume) {
        printParameters(C, N, prices, volume);

        long startTime = nanoTime();
        int result = maxWarehouseValue(C, N, prices, volume);
        long endTime = nanoTime();

        System.out.println("Saída: " + result);
        System.out.println("Tempo de execução: " + (endTime - startTime) + "ns");
    }
}
