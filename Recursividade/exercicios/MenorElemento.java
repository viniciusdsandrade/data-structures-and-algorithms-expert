package Recursividade.exercicios;

import java.util.List;

import static java.lang.Double.MAX_VALUE;
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

    static double minorRec(List<Double> list) {
        if (list.size() == 1) return list.getFirst();

        double minorOfRest = minorRec(list.subList(1, list.size()));

        return list.getFirst() < minorOfRest
                ? list.getFirst()
                : minorOfRest;
    }

    static double minorIt(List<Double> list) {
        double minValue = MAX_VALUE;
        for (Double value : list) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    static void testMinor(List<Double> list) {
        System.out.println("Lista: " + list);

        long start = nanoTime();
        double resultRec = minorRec(list);
        long timeRec = nanoTime() - start;

        start = nanoTime();
        double resultIt = minorIt(list);
        long timeIt = nanoTime() - start;

        System.out.println("minorRec result: " + resultRec);
        System.out.println("minorIt  result: " + resultIt);

        int fastest = 0;

        if (timeIt < timeRec) fastest = 1;

        double ratio = (double) (fastest == 0 ? timeRec : timeIt) / (fastest == 1 ? timeRec : timeIt);

        System.out.println("minorRec runtime: " + timeRec + " ns");
        System.out.println("minorIt  runtime: " + timeIt + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: " + (fastest == 0 ? "minorRec" : "minorIt"));
        System.out.println();
    }

    // Exemplo de uso
    static void main(String[] ignoredArgs) {
        // Algumas listas de teste
        List<Double> list1 = asList(10.0, 15.0, 20.0, 8.0, 30.0, 17.0);
        List<Double> list2 = asList(100.5, 99.9, 100.0, 101.0);
        List<Double> list3 = of(3.14);
        List<Double> list4 = asList(5.0, 4.0, 3.0, 2.0, 1.0);

        testMinor(list1);
        testMinor(list2);
        testMinor(list3);
        testMinor(list4);
    }
}
