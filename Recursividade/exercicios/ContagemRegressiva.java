package Recursividade.exercicios;

public class ContagemRegressiva {

    /*

    Problema "contagem-regressiva"

    Implemente uma função recursiva que exiba uma contagem
    regressiva de um número natural qualquer até zero.
    A função não deve retornar nada, ou seja, apenas imprima os
    valores no console.

    Exemplo:
    Entrada: 5
    Saída (impressão no console)
    5
    4
    3
    2
    1
    0

    Assinaturas:
    Java: static void countdown(int n)

     */

    static void countdown(int n) {
        if (n < 0) return;

        System.out.println(n);
        countdown(n - 1);
    }

    static void main(String[] ignoredArgs) {
        countdown(5);
    }
}
