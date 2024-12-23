package Recursividade;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.DOWN;

public class NumeroPorExtenso {

    // Vetores para as unidades, dezenas e centenas
    private static final String[] UNIDADES = {
            "", "um", "dois", "três", "quatro", "cinco",
            "seis", "sete", "oito", "nove", "dez",
            "onze", "doze", "treze", "quatorze", "quinze",
            "dezesseis", "dezessete", "dezoito", "dezenove"
    };

    private static final String[] DEZENAS = {
            "", "", "vinte", "trinta", "quarenta",
            "cinquenta", "sessenta", "setenta", "oitenta", "noventa"
    };

    private static final String[] CENTENAS = {
            "", "cem", "duzentos", "trezentos", "quatrocentos",
            "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"
    };

    // Escalas (mil, milhão, etc.)
    private static final String[] QUALIFICADORES_SINGULAR = {
            "", "mil", "milhão", "bilhão", "trilhão", "quatrilhão"
    };

    private static final String[] QUALIFICADORES_PLURAL = {
            "", "mil", "milhões", "bilhões", "trilhões", "quatrilhões"
    };

    /**
     * Metodo principal para converter BigDecimal em extenso, já
     * formatado como valor monetário (Reais e Centavos).
     *
     * @param valor BigDecimal
     * @return String por extenso
     */
    public static String converter(BigDecimal valor) {
        // Arredonda para duas casas decimais (se for monetário)
        valor = valor.setScale(2, DOWN);

        // Parte inteira (reais)
        long valorInteiro = valor.longValue();

        // Parte decimal (centavos)
        int centavos = valor
                .remainder(ONE)
                .movePointRight(2)
                .intValue();

        String extensoReais;
        if (valorInteiro == 0) {
            extensoReais = "zero real";
        } else {
            extensoReais = numeroParaExtenso(valorInteiro);
            extensoReais += (valorInteiro == 1) ? " real" : " reais";
        }

        String extensoCentavos = "";
        if (centavos > 0) {
            String centavosPorExtenso = numeroParaExtenso(centavos);
            extensoCentavos = (centavos == 1)
                    ? " e " + centavosPorExtenso + " centavo"
                    : " e " + centavosPorExtenso + " centavos";
        }

        return extensoReais + extensoCentavos;
    }

    /**
     * Converte um valor inteiro (até o limite de long) para sua forma por extenso em português.
     *
     * @param valor valor em long
     * @return número por extenso (sem adicionar “real”, “reais”, etc.)
     */
    private static String numeroParaExtenso(long valor) {
        if (valor == 0) return "zero";


        // Trabalha só com valor positivo
        if (valor < 0) return "menos " + numeroParaExtenso(-valor);


        StringBuilder sb = new StringBuilder();

        // Quebra em grupos de 3 dígitos (000..999)
        int posicaoGrupo = 0; // indica se é unidade, mil, milhão, etc.
        while (valor > 0) {
            int grupo = (int) (valor % 1000); // últimos 3 dígitos
            valor /= 1000;                    // remove os últimos 3 dígitos

            if (grupo != 0) {
                String grupoPorExtenso = numeroAte999(grupo);
                String qualificador = "";

                if (posicaoGrupo > 0) {
                    if (grupo == 1) {
                        qualificador = QUALIFICADORES_SINGULAR[posicaoGrupo];
                    } else {
                        qualificador = QUALIFICADORES_PLURAL[posicaoGrupo];
                    }
                }

                if (!sb.isEmpty()) sb.insert(0, " ");

                // Adiciona o qualificador na frente
                if (!qualificador.isEmpty()) grupoPorExtenso += " " + qualificador;

                // Insere no início (pois estamos processando da menor escala para a maior)
                sb.insert(0, grupoPorExtenso);
            }
            posicaoGrupo++;
        }

        return sb.toString().trim();
    }

    /**
     * Converte valores entre 0 e 999 para extenso em português.
     *
     * @param valor valor inteiro entre 0 e 999
     * @return texto por extenso
     */
    private static String numeroAte999(int valor) {
        if (valor == 0) return "";

        StringBuilder sb = new StringBuilder();

        int centenas = valor / 100;
        int resto = valor % 100;
        int dezenas = resto / 10;
        int unidades = resto % 10;

        // Trata as centenas
        if (centenas != 0) {
            // Caso especial de 100 a 199 ("cem" / "cento e ...")
            if (centenas == 1 && resto > 0) {
                sb.append("cento");
            } else {
                sb.append(CENTENAS[centenas]);
            }
        }

        if (!sb.isEmpty() && resto > 0) {
            sb.append(" e ");
        }

        // Trata de 0 a 19
        if (resto < 20) {
            sb.append(UNIDADES[resto]);
        } else {
            sb.append(DEZENAS[dezenas]);
            if (dezenas != 0 && unidades != 0) {
                sb.append(" e ");
            }
            if (unidades != 0 && resto >= 20) {
                sb.append(UNIDADES[unidades]);
            }
        }

        return sb.toString().trim();
    }

    // ------------------ TESTE RÁPIDO ------------------
    public static void main(String[] args) {
        BigDecimal[] valores = {
                new BigDecimal("0"),
                new BigDecimal("1"),
                new BigDecimal("21"),
                new BigDecimal("105"),
                new BigDecimal("199"),
                new BigDecimal("1000"),
                new BigDecimal("1001.05"),
                new BigDecimal("999999.99"),
                new BigDecimal("1000000.00"),
                new BigDecimal("1000001.05"),
                new BigDecimal("123456789.99"),
                new BigDecimal("-10.50") // teste negativo
        };

        for (BigDecimal val : valores) {
            System.out.println(val + " = " + converter(val));
        }
    }
}