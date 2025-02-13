package Strings.exercicios;

import java.math.BigDecimal;
import java.util.Scanner;

import static java.lang.System.in;
import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.HALF_UP;

class ConversorHoras {

    // Classe para armazenar os resultados da conversão
    static class HorasConvertidas {
        int horas;
        int minutos;
        int segundos;
        int milissegundos;
        int microsegundos;
        int nanosegundos;

        public HorasConvertidas(
                int horas,
                int minutos,
                int segundos,
                int milissegundos,
                int microsegundos,
                int nanosegundos
        ) {
            this.horas = horas;
            this.minutos = minutos;
            this.segundos = segundos;
            this.milissegundos = milissegundos;
            this.microsegundos = microsegundos;
            this.nanosegundos = nanosegundos;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(horas).append(" horas, ")
                    .append(minutos).append(" minutos, ")
                    .append(segundos).append(" segundos, ")
                    .append(milissegundos).append(" milissegundos");

            // Adiciona microsegundos apenas se for diferente de zero
            if (microsegundos > 0) sb.append(", ").append(microsegundos).append(" microsegundos");

            // Adiciona nanosegundos apenas se for diferente de zero
            if (nanosegundos > 0) sb.append(", ").append(nanosegundos).append(" nanosegundos");

            return sb.toString();
        }
    }

    static HorasConvertidas converterHoras(String entrada) {
        // Substitui vírgula por ponto para padronizar
        String padronizada = entrada.replace(',', '.');

        // Converte a entrada para BigDecimal
        BigDecimal horasDecimal = new BigDecimal(padronizada);

        // Extrai a parte inteira das horas
        BigDecimal[] partesHoras = horasDecimal.divideAndRemainder(ONE);
        int horas = partesHoras[0].intValue();

        // Calcula os minutos
        BigDecimal minutosDecimal = partesHoras[1].multiply(new BigDecimal(60));
        BigDecimal[] partesMinutos = minutosDecimal.divideAndRemainder(ONE);
        int minutos = partesMinutos[0].intValue();

        // Calcula os segundos
        BigDecimal segundosDecimal = partesMinutos[1].multiply(new BigDecimal(60));
        BigDecimal[] partesSegundos = segundosDecimal.divideAndRemainder(ONE);
        int segundos = partesSegundos[0].intValue();

        // Calcula os milissegundos
        BigDecimal milissegundosDecimal = partesSegundos[1].multiply(new BigDecimal(1000));
        BigDecimal[] partesMilissegundos = milissegundosDecimal.divideAndRemainder(ONE);
        int milissegundos = partesMilissegundos[0].intValue();

        // Calcula os microsegundos
        BigDecimal microsegundosDecimal = partesMilissegundos[1].multiply(new BigDecimal(1000));
        BigDecimal[] partesMicrosegundos = microsegundosDecimal.divideAndRemainder(ONE);
        int microsegundos = partesMicrosegundos[0].intValue();

        // Calcula os nanosegundos
        BigDecimal nanosegundosDecimal = partesMicrosegundos[1].multiply(new BigDecimal(1000));
        int nanosegundos = nanosegundosDecimal.setScale(0, HALF_UP).intValue();

        return new HorasConvertidas(
                horas,
                minutos,
                segundos,
                milissegundos,
                microsegundos,
                nanosegundos
        );
    }

    static void main(String[] ignoredArgs) {
        Scanner scanner = new Scanner(in);
        System.out.print("Digite o número de horas (use ponto ou vírgula como decimal): ");
        String entrada = scanner.nextLine();
        scanner.close();

        try {
            HorasConvertidas resultado = converterHoras(entrada);
            System.out.println("Conversão:");
            System.out.println(resultado);
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida. Por favor, insira um número válido.");
        }
    }
}
