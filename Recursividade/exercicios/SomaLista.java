package Recursividade.exercicios;

import java.util.List;

public class SomaLista {

    /*

    Problema "soma-lista"

    Crie uma função recursiva que retorne a soma de todos os elementos em uma lista de números.
    Se a lista for vazia, a função deve retornar o valor 0 (zero).

    Exemplo 1:
    Entrada: []
    Saída: 0

    Exemplo 2:
    Entrada: [4, 5, 3]
    Saída: 12

    Assinaturas:
    Java: static double sumList(List<Double> list)

     */

    static double sumList(List<Double> list) {
        if (list.isEmpty()) return 0;

        return list.getFirst() + sumList(list.subList(1, list.size()));
    }

    static void main(String[] ignoredArgs) {
        System.out.println(sumList(List.of(4.0, 5.0, 3.0)));
    }
}
