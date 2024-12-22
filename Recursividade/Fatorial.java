package Recursividade;

public class Fatorial {

    /*
    Problema "fatorial"

    O fatorial de um número natural N é a multiplicação de 1 até N,
    exceto para o valor 0 (zero), cujo fatorial por definição é 1.
    Faça uma função para retornar o fatorial de um dado número.

    Exemplo 1:
    Entrada 0
    Saída 1

    Exemplo 2:
    Entrada 3
    Saída 6
    Exemplo 3:
    Entrada 4
    Saída   24

    Assinaturas:
    Java: public static int factorial(int n)
     */

    public static int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(factorial(0)); // 1
        System.out.println(factorial(3)); // 6
        System.out.println(factorial(4)); // 24
    }
}
