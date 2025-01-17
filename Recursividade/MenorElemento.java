package Recursividade;

import java.util.List;

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

    static double minor(List<Double> list) {
        if (list.size() == 1) return list.getFirst();

        double minorOfRest = minor(list.subList(1, list.size()));
        return list.getFirst() < minorOfRest ? list.getFirst() : minorOfRest;
    }

    static void main(String[] ignoredArgs) {
        System.out.println(minor(of(10.0, 15.0, 20.0, 8.0, 30.0, 17.0)));
    }
}
