package Recursividade.exercicios;

public class ContagemProgressiva {

    static void main(String[] ignoredArgs) {
        countUp(1, 100);
    }

    static void countUp(int start, int end) {
        if (start > end) return;
        System.out.println(start);
        countUp(start + 1, end);
    }
}
