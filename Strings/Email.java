package Strings;

public class Email {

    /*

    Problema "dominio-email"
    Dado um email de uma pessoa, mostrar o nome de usuário e o domínio deste email. O programa deve também informar se o domínio é brasileiro (.br). Supor um email válido.

    Exemplo 1:
    Entrada
    Saída
    joao.silva23@yahoo.com.br
    Usuario: joao.silva23
    Dominio: yahoo.com.br
    Brasileiro: sim

    Exemplo 2:
    Entrada
    Saída
    maria123@gmail.com
    Usuario: maria123
    Dominio: gmail.com
    Brasileiro: nao

    Assinaturas:
    Java: public static EmailInfo extractEmailInformation(String email)
     */

    public record EmailInfo(String username, String domain, boolean isBrazilian) {

        @Override
            public String toString() {
                return "EmailInfo{" +
                       "username='" + username + '\'' +
                       ", domain='" + domain + '\'' +
                       ", isBrazilian=" + isBrazilian +
                       '}';
            }
        }

    public static EmailInfo extractEmailInformation(String email) {
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];
        boolean isBrazilian = domain.endsWith(".br");
        return new EmailInfo(username, domain, isBrazilian);
    }

    public static void main(String[] ignoredArgs) {
        EmailInfo info = extractEmailInformation("joao.silva23@yahoo.com.br");
        System.out.println("Usuario: " + info);

        info = extractEmailInformation("maria123@gmail.com");
        System.out.println("Usuario: " + info);
    }
}
