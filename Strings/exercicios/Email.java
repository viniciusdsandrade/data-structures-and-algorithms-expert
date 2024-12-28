package Strings.exercicios;

import static java.lang.System.nanoTime;

public class Email {

    /*

    Problema "dominio-email"
    Dado um email de uma pessoa, mostrar o nome de usuário e o domínio deste email.
    O programa deve também informar se o domínio é brasileiro (.br). Supor um email válido.

    Exemplo 1:
    Entrada
         joao.silva23@yahoo.com.br
        Usuario: joao.silva23
        Dominio: yahoo.com.br
    Saída
        Brasileiro: sim

    Exemplo 2:
    Entrada
        maria123@gmail.com
        Usuario: maria123
        Dominio: gmail.com
    Saída
        Brasileiro: nao

    Assinatura: public static EmailInfo extractEmailInformation(String email)

     */

    record EmailInfo(String username, String domain, boolean isBrazilian) {

        @Override
        public String toString() {
            return "EmailInfo{" +
                   "username='" + username + '\'' +
                   ", domain='" + domain + '\'' +
                   ", isBrazilian=" + isBrazilian +
                   '}';
        }
    }

    static EmailInfo extractEmailInformation(String email) {
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];
        boolean isBrazilian = domain.endsWith(".br");
        return new EmailInfo(username, domain, isBrazilian);
    }

    static void testExtractEmailInformation(String email) {
        System.out.println("\nInput:   " + email);

        long startTime, endTime, runtime;
        EmailInfo result;

        startTime = nanoTime();
        result = extractEmailInformation(email);
        endTime = nanoTime();

        runtime = endTime - startTime;

        System.out.println("Output:  " + result.isBrazilian());
        System.out.println("Runtime: " + runtime + " ns\n");
    }

    static void main(String[] ignoredArgs) {
        String email1 = "joao.silva23@yahoo.com.br";
        String email2 = "maria123@gmail.com";

        testExtractEmailInformation(email1);
        testExtractEmailInformation(email2);
    }
}
