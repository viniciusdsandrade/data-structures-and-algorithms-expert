package Recursividade.exercicios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.nanoTime;
import static java.util.Arrays.asList;
import static java.util.List.of;


public class MenorElemento {

    /*

    Problema "menor-elemento"
    Faça uma função recursiva para retornar o menor elemento de uma lista de números.
    Supor que a lista tenha pelo menos um elemento.

    Exemplo:
    Entrada: [10, 15, 20, 8, 30, 17]
    Saída: 8

    Assinaturas:
    Java: static double minor(List<Double> list)

     */

    static double minorRecursive(List<Double> list) {
        if (list.isEmpty())
            throw new IllegalArgumentException("A lista não pode estar vazia.");

        if (list.size() == 1) return list.getFirst();

        double minorOfRest = minorRecursive(list.subList(1, list.size()));

        return list.getFirst() < minorOfRest
                ? list.getFirst()
                : minorOfRest;
    }

    static double minor(List<Double> list) {
        if (list.isEmpty())
            throw new IllegalArgumentException("A lista não pode estar vazia.");

        if (list.size() == 1) return list.getFirst();

        double min = list.getFirst();

        for (Double num : list) {
            if (num < min) {
                min = num;
            }
        }

        return min;
    }

    // Função de teste comparando recursiva e iterativa
    static void testMinor(List<Double> list, String listType) {
        System.out.println("Tipo de Lista: " + listType);
        System.out.println("Lista: " + list);

        long[] times = new long[2];
        double[] results = new double[2];

        // Teste da versão recursiva
        long start = nanoTime();
        results[0] = minorRecursive(list);
        times[0] = nanoTime() - start;

        // Teste da versão iterativa
        start = nanoTime();
        results[1] = minor(list);
        times[1] = nanoTime() - start;

        System.out.println("Resultado Recursivo: " + results[0]);
        System.out.println("Resultado Iterativo: " + results[1]);

        // Determinar qual é mais rápido
        int fastest = times[1] < times[0] ? 1 : 0;
        int slowest = fastest == 0 ? 1 : 0;

        double ratio = (double) times[slowest] / times[fastest];
        System.out.println("Versão Recursiva runtime: " + times[0] + " ns");
        System.out.println("Versão Iterativa runtime: " + times[1] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: " + (fastest == 0 ? "Recursivo" : "Iterativo"));
        System.out.println();
    }

    static void main(String[] ignoredArgs) {
        // Usando ArrayList
        List<Double> arrayList1 = new ArrayList<>(asList(10.0, 15.0, 20.0, 8.0, 30.0, 17.0));
        List<Double> arrayList2 = new ArrayList<>(of(5.0));
        List<Double> arrayList3 = new ArrayList<>(asList(3.2, 4.5, 1.1, 7.8));
        List<Double> arrayList4 = new ArrayList<>(asList(2.2, 2.2, 2.2, 2.2));
        List<Double> arrayList5 = new ArrayList<>(asList(-1.0, -5.5, 0.0, 3.3));
        List<Double> arrayList6 = new ArrayList<>(asList(100.0, 200.0, 50.0, 75.0, 25.0));
        List<Double> arrayList7 = new ArrayList<>(asList(8.8, 7.7, 6.6, 5.5, 4.4, 3.3, 2.2, 1.1));

        // Usando LinkedList
        List<Double> linkedList1 = new LinkedList<>(asList(10.0, 15.0, 20.0, 8.0, 30.0, 17.0));
        List<Double> linkedList2 = new LinkedList<>(of(5.0));
        List<Double> linkedList3 = new LinkedList<>(asList(3.2, 4.5, 1.1, 7.8));
        List<Double> linkedList4 = new LinkedList<>(asList(2.2, 2.2, 2.2, 2.2));
        List<Double> linkedList5 = new LinkedList<>(asList(-1.0, -5.5, 0.0, 3.3));
        List<Double> linkedList6 = new LinkedList<>(asList(100.0, 200.0, 50.0, 75.0, 25.0));
        List<Double> linkedList7 = new LinkedList<>(asList(8.8, 7.7, 6.6, 5.5, 4.4, 3.3, 2.2, 1.1));

        // Lista de Listas para ArrayList
        List<List<Double>> arrayListTestCases = asList(
                arrayList1,
                arrayList2,
                arrayList3,
                arrayList4,
                arrayList5,
                arrayList6,
                arrayList7
        );

        // Lista de Listas para LinkedList
        List<List<Double>> linkedListTestCases = asList(
                linkedList1,
                linkedList2,
                linkedList3,
                linkedList4,
                linkedList5,
                linkedList6,
                linkedList7
        );

        System.out.println("===== Testando com ArrayList =====\n");
        for (List<Double> testCase : arrayListTestCases) {
            testMinor(testCase, "ArrayList");
        }

        System.out.println("===== Testando com LinkedList =====\n");
        for (List<Double> testCase : linkedListTestCases) {
            testMinor(testCase, "LinkedList");
        }
    }
}
