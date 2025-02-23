package Recursividade.desafio;

import java.util.List;

import static java.lang.Double.parseDouble;
import static java.util.Arrays.asList;

public class VendasDepartamento {

    /*

    DESAFIO: Vendas do departamento

    Você tem à disposição dados de registros de venda, onde cada registro está no formato CSV, e possui
    os campos: id da venda, data, preço total, e departamento onde a venda foi realizada.
    Você deve criar uma função que receba uma lista de registros de venda, e o nome de um departamento.

    A função deve retornar a quantidade e a soma total das vendas desse departamento. Utilize um array
    para representar esses dois dados de retorno. Veja as assinaturas da função ao final deste documento.
    Sua solução deve ser recursiva. Você pode ou não utilizar uma função recursiva auxiliar, conforme
    você achar mais conveniente.
    Seu programa deve imprimir os valores com mensagens auxiliares, conforme exemplo.

    Exemplo:
    Entrada Saída
    {
        "sales": [
            "8349,14/09/2024,899.9,ESPORTE",
            "4837,17/09/2024,530.0,VESTUARIO",
            "15281,21/09/2024,1253.99,ESPORTE",
            "15344,27/09/2024,1000.9,VESTUARIO",
            "18317,04/10/2024,250.4,VESTUARIO",
            "18972,11/10/2024,385.5,JARDINAGEM"
        ],
    "department": "VESTUARIO"
    }

    3 VENDAS
    TOTAL = $ 1781.30

     */

    static double[] totalSales(List<String> sales, String department) {
        return totalSalesHelper(sales, department, 0, 0.0);
    }

    // Helper recursive function
    static double[] totalSalesHelper(List<String> sales, String department, int index, double currentTotal) {

        if (index == sales.size()) return new double[]{0.0, 0.0};

        String sale = sales.get(index);
        String[] parts = sale.split(",");
        String saleDepartment = parts[3].trim();
        double saleAmount = parseDouble(parts[2].trim());

        double[] resultFromRest = totalSalesHelper(sales, department, index + 1, currentTotal);

        if (saleDepartment.equalsIgnoreCase(department)) {
            resultFromRest[0] += 1;          // Increment count
            resultFromRest[1] += saleAmount; // Add to total
        }

        return resultFromRest;
    }

    static void main(String[] ignoredArgs) {
        List<String> sales = asList(
                "8349,14/09/2024,899.9,ESPORTE",
                "4837,17/09/2024,530.0,VESTUARIO",
                "15281,21/09/2024,1253.99,ESPORTE",
                "15344,27/09/2024,1000.9,VESTUARIO",
                "18317,04/10/2024,250.4,VESTUARIO",
                "18972,11/10/2024,385.5,JARDINAGEM"
        );

        String department = "VESTUARIO";

        double[] result = totalSales(sales, department);

        System.out.println((int) result[0] + " VENDAS");
        System.out.printf("TOTAL = $ %.2f\n", result[1]);
    }
}
