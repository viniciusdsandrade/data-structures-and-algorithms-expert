package Recursividade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.DOWN;
import static java.util.Arrays.stream;

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

        // Converte a parte inteira para extenso
        String extensoParteInteira = numeroParaExtenso(valorInteiro);

        // Se for zero
        if (valorInteiro == 0 && centavos == 0) return "zero real";

        // Se for negativo apenas na parte inteira (já tratado recursivamente em numeroParaExtenso)
        // mas mantém a lógica normal na parte dos centavos.
        boolean isNegativo = valor.signum() < 0;

        // Montagem do texto final para a parte dos reais
        String extensoReais;
        if (valorInteiro == 0) {
            // Ex.: 0.35 => "zero real e trinta e cinco centavos"
            extensoReais = "zero real";
        } else {
            // -- REGRA DO "de reais" QUANDO FOR INTEIRO E TIVER "milhão"/"bilhão"/"trilhão" ETC. --
            // 1) Se for exatamente 1, continua "um real"
            // 2) Se não for 1, vamos verificar se precisa "de reais" ou apenas "reais"
            if (centavos == 0) {
                // Valor inteiro
                if (valorInteiro == 1) {
                    // Singular
                    extensoReais = extensoParteInteira + " real";
                } else {
                    // Plural, mas vamos checar se contém "milhão", "bilhão", etc.
                    if (contemEscalaMaiorQueMil(extensoParteInteira)) {
                        // Ex.: "um milhão de reais", "dois bilhões de reais", etc.
                        extensoReais = extensoParteInteira + " de reais";
                    } else {
                        // Ex.: "vinte e um reais", "mil reais", "duzentos reais", etc.
                        extensoReais = extensoParteInteira + " reais";
                    }
                }
            } else {
                // Valor não é inteiro (tem centavos), continua a lógica padrão
                if (valorInteiro == 1) {
                    // "um real"
                    extensoReais = extensoParteInteira + " real";
                } else {
                    // "dois reais", "vinte e um reais", "um milhão reais" etc.
                    // (nesse caso sem o "de", pois tem centavos)
                    extensoReais = extensoParteInteira + " reais";
                }
            }
        }

        // Monta o texto dos centavos, se houver
        String extensoCentavos = "";
        if (centavos > 0) {
            String centavosPorExtenso = numeroParaExtenso(centavos);
            extensoCentavos = (centavos == 1)
                    ? " e " + centavosPorExtenso + " centavo"
                    : " e " + centavosPorExtenso + " centavos";
        }

        // Retorna juntando tudo; se for negativo, adiciona "menos " na frente
        String resultadoFinal = extensoReais + extensoCentavos;
        if (isNegativo) {
            // Garante que se o valor for negativo, mas zero de reais, a lógica já
            // está contemplada dentro do numeroParaExtenso com "menos ...".
            // Se ainda assim quiser forçar na montagem final, seria algo como:
            // "menos " + resultadoFinal.replace("menos ", "").
            // Mas normalmente "numeroParaExtenso(-10)" já faz "menos dez", então aqui
            // já estamos com "menos " embutido. Ajuste se quiser outro comportamento.
            if (!resultadoFinal.startsWith("menos"))
                resultadoFinal = "menos " + resultadoFinal;
        }

        return resultadoFinal;
    }


    private static boolean contemEscalaMaiorQueMil(String texto) {
        // Se contém "milhão", "milhões", "bilhão", "bilhões", "trilhão", "trilhões", "quatrilhão", "quatrilhões"...
        // Então retorna true
        String minusculo = texto.toLowerCase();
        return minusculo.contains("milhão")
               || minusculo.contains("milhões")
               || minusculo.contains("bilhão")
               || minusculo.contains("bilhões")
               || minusculo.contains("trilhão")
               || minusculo.contains("trilhões")
               || minusculo.contains("quatrilhão")
               || minusculo.contains("quatrilhões");
    }

    private static String numeroParaExtenso(long valor) {
        if (valor == 0) return "zero";

        // Se for negativo, "menos" + recursão com positivo
        if (valor < 0) return "menos " + numeroParaExtenso(-valor);

        // Lista para guardar cada grupo de 3 dígitos
        List<Integer> grupos = new ArrayList<>();
        while (valor > 0) {
            grupos.add((int) (valor % 1000));
            valor /= 1000;
        }
        // Inverte para termos o grupo mais significativo primeiro
        Collections.reverse(grupos);

        List<String> partes = new ArrayList<>();
        int totalGrupos = grupos.size();

        for (int i = 0; i < totalGrupos; i++) {
            int grupo = grupos.get(i);

            if (grupo == 0) continue;

            // posicaoEscala = 0 → (unidades), 1 → (mil), 2 → (milhão)...
            int posicaoEscala = totalGrupos - 1 - i;

            String porExtenso;

            // Se for exatamente 1 no grupo dos "mil", dizemos apenas "mil", sem repetir "um mil"
            if (posicaoEscala == 1 && grupo == 1) {
                porExtenso = "mil";
            } else {
                porExtenso = numeroAte999(grupo);
                // Se não for o caso especial do "mil" == 1, adicionamos o qualificador
                if (posicaoEscala > 0) {
                    if (grupo == 1) {
                        porExtenso += " " + QUALIFICADORES_SINGULAR[posicaoEscala];
                    } else {
                        porExtenso += " " + QUALIFICADORES_PLURAL[posicaoEscala];
                    }
                }
            }

            partes.add(porExtenso);
        }

        if (partes.isEmpty()) return "";
        if (partes.size() == 1) return partes.getFirst();

        // Junta as partes usando ", " e substitui a última ", " por " e "
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < partes.size(); i++) {
            sb.append(partes.get(i));
            if (i < partes.size() - 1) sb.append(", ");
        }
        // Substitui a última vírgula por " e "
        int ultimaVirgula = sb.lastIndexOf(", ");

        if (ultimaVirgula != -1) sb.replace(ultimaVirgula, ultimaVirgula + 2, " e ");

        return sb.toString();
    }

    private static String numeroAte999(int valor) {
        if (valor == 0) return "";

        StringBuilder sb = new StringBuilder();

        int centenas = valor / 100;
        int resto = valor % 100;
        int dezenas = resto / 10;
        int unidades = resto % 10;

        // Trata as centenas
        if (centenas != 0) {
            if (centenas == 1 && resto > 0) {
                // 100..199 ⇾ "cem" ou "cento e ..."
                sb.append("cento");
            } else {
                sb.append(CENTENAS[centenas]);
            }
        }

        // Se já temos centenas e ainda há resto, adicionamos " e "
        if (!sb.isEmpty() && resto > 0) sb.append(" e ");

        // Trata de 0 a 19
        if (resto < 20) {
            sb.append(UNIDADES[resto]);
        } else {
            // Trata as dezenas (20,30,...,90)
            sb.append(DEZENAS[dezenas]);
            // Se há unidades, adiciona " e "
            if (dezenas != 0 && unidades != 0) sb.append(" e ");
            if (unidades != 0 && resto >= 20) sb.append(UNIDADES[unidades]);
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        BigDecimal[] valores = {
                new BigDecimal("0"),
                new BigDecimal("1"),
                new BigDecimal("2"),
                new BigDecimal("0.01"),
                new BigDecimal("0.10"),
                new BigDecimal("0.99"),
                new BigDecimal("21"),
                new BigDecimal("105"),
                new BigDecimal("199"),
                new BigDecimal("1000"),
                new BigDecimal("1001.05"),
                new BigDecimal("999999.99"),
                new BigDecimal("1000000.00"),
                new BigDecimal("1000001.05"),
                new BigDecimal("123456789.99"),
                new BigDecimal("1000000000.00"),      // um bilhão de reais
                new BigDecimal("1000000001.00"),      // um bilhão e um reais
                new BigDecimal("1000000000000.00"),   // um trilhão de reais
                new BigDecimal("1000000000001.01"),   // um trilhão e um reais e um centavo
                new BigDecimal("999999999999999.99"), // novecentos e noventa e nove trilhões...
                new BigDecimal("-10.50"),             // teste negativo
                new BigDecimal("-1.00"),              // menos um real
                new BigDecimal("-1000.00"),           // menos mil reais
                new BigDecimal("-1000000.00")       // menos um milhão de reai
        };

        stream(valores).map(val -> val + " = " + converter(val)).forEach(System.out::println);
    }
}
