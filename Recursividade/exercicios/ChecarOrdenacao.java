package Recursividade.exercicios;

import java.util.List;

import static java.util.List.of;

public class ChecarOrdenacao {

    /*

    Problema "checar-ordenação"
    Faça uma função recursiva para verificar se uma dada lista de números está ordenada.
    Exemplo 1:
    Entrada
    Saída
    []
    true
    Exemplo 2:
    Entrada
    Saída
    [15, 20, 22, 31, 40]
    true
    Exemplo 3:
    Entrada
    Saída
    [15, 20, 22, 21, 40]
    false
    Assinaturas:
    Java: static boolean isSorted(List<Double> list)

     */

    static boolean isSorted(List<Double> list) {
        if (list.size() <= 1) return true;

        return list.getFirst() <= list.get(1) &&
               isSorted(list.subList(1, list.size()));
    }

    public static void main(String[] ignoredArgs) {
        System.out.println(isSorted(of()));
        System.out.println(isSorted(of(15.0, 20.0, 22.0, 31.0, 40.0)));
        System.out.println(isSorted(of(15.0, 20.0, 22.0, 21.0, 40.0)));
    }
}
