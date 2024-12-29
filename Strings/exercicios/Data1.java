package Strings.exercicios;

import static java.lang.Integer.parseInt;
import static java.lang.System.nanoTime;

public class Data1 {

    /*

    Problema "data1"

    Dada uma data que poderá estar no formado dia/mês/ano,
    sendo que o dia e mês menor que 10 pode ou não ter zero à esquerda,
    mostrar o dia, mês e ano separadamente, na forma numérica.
    Supor uma data válida.

    Exemplo:

    Entrada: "21/07/2010"
    Saída:
    "
        Dia: 21
        Mês: 7
        Ano: 2010
    "

    Assinatura:  public static DateInfo extractDateData(String date)

     */

    static class DateInfo {
        int day;
        int month;
        int year;

        @Override
        public String toString() {
            return "Dia: " + day + "\nMês: " + month + "\nAno: " + year;
        }
    }

    static DateInfo extractDateData(String date) {
        DateInfo info = new DateInfo();
        String[] vet = date.split("/");

        info.day = parseInt(vet[0]);
        info.month = parseInt(vet[1]);
        info.year = parseInt(vet[2]);

        return info;
    }

    static void testExtractDateData(String date) {
        System.out.println("\nInput: " + date);

        long startTime, endTime, runtTime;
        DateInfo result;

        startTime = nanoTime();
        result = extractDateData(date);
        endTime = nanoTime();

        runtTime = endTime - startTime;

        System.out.println("Output:  \n" + result);
        System.out.println("Runtime: " + runtTime + "ns\n");
    }

    static void main(String[] ignoredArgs) {
        String date1 = "21/07/2010";
        String date2 = "01/01/2021";
        String date3 = "31/12/2021";

        testExtractDateData(date1);
        testExtractDateData(date2);
        testExtractDateData(date3);
    }
}
