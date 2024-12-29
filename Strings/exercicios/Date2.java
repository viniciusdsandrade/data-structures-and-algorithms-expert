package Strings.exercicios;

import static java.lang.String.format;
import static java.lang.System.nanoTime;

public class Date2 {

    /*

    Problema "data2"

    Dados um dia, mês e ano, produzir uma string no formato dd/mm/aaaa,
    preenchendo com zeros à esquerda o dia e mês, se necessário.

    Exemplo:

    Entrada :
    {
        "day": 21,
        "month": 7,
        "year": 2010
    }
    Saída: 21/07/2010


    Assinatura:  public static String formatDate(int day, int month, int year)

     */

    static String formatDate(int day, int month, int year) {
        return format("%02d/%02d/%d", day, month, year);
    }

    static void testFormatDate(int day, int month, int year) {
        System.out.println("\nInput:   " + day + "/" + month + "/" + year);

        long startTime, endTime, runtTime;
        String result;

        startTime = nanoTime();
        result = formatDate(day, month, year);
        endTime = nanoTime();

        runtTime = endTime - startTime;

        System.out.println("Output : " + result);
        System.out.println("Runtime: " + runtTime + " ns");
    }

    public static void main(String[] ignoredArgs) {
        testFormatDate(21, 7, 2010);
        testFormatDate(1, 1, 2020);
        testFormatDate(31, 12, 2020);
        testFormatDate(1, 10, 2020);
    }
}
