package Recursividade.exercicios;

public class SomaNaturais {

    /*

    Problema "soma-naturais"

    Faça uma função que, dado um número natural N,
    retorne a soma dos números de 0 até N.

    Exemplo 1:
    Entrada:    0
    Saída:      0

    Exemplo 2:
    Entrada:  2
    Saída:    3

    Exemplo 3:
    Entrada:  4
    Saída:    10

    Assinatura: public static int sumNaturals(int n)

     */

    static int sumNaturals(int n) {
        if (n == 0) return 0;
        else return n + sumNaturals(n - 1);
    }

    static void main(String[] ignoredArgs) {
        System.out.println(sumNaturals(0)); // 0
        System.out.println(sumNaturals(2)); // 3
        System.out.println(sumNaturals(4)); // 10
    }
}
