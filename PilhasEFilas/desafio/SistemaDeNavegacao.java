package PilhasEFilas.desafio;

import java.util.Stack;

public class SistemaDeNavegacao {

    /*

    DESAFIO: Sistema de navegação

    Forma de entrega: link do programa salvo no Gist do Github

    Linguagens aceitas: Javascript, Java, C#, Python

    Você foi contratado para implementar um componente de navegação.
    Para isso, você deverá implementar a classe Browser, com as funções
    de acessar uma página, voltar e avançar, conforme projeto ao lado.
    Quando esse componente for instanciado, por padrão ele começa na
    página inicial da navegação, identificada pela string "home".
    Quando o usuário informar a URL para acessar uma página, a função
    access(url) deve ser chamada. Essa página passa então a ser a página
    atual (currentPage) da navegação. A página atual da navegação pode
    ser acessada pela função getCurrentPage().

    Deve ser armazenado um histórico das páginas anteriores, pois quando o usuário acionar a funcionalidade
    "voltar" em seu navegador, a função back() deve ser chamada. Esta função faz a navegação voltar para a última
    página anteriormente acessada. Se a função back() for chamada e não houver páginas anteriores no histórico,
    deve ocorrer um erro "Back error".

    Quando o usuário opta por "voltar" em pelo menos uma página, deve ser também armazenado um histórico de
    páginas futuras, pois o usuário pode acionar a funcionalidade de "avançar", que deve chamar a função forward().
    Esta função faz a navegação avançar para a última página futura a partir da qual o usuário voltou. Se a função
    forward() for chamada e não houver páginas futuras no histórico, deve ocorrer um erro "Forward error".
    Repare que, se o usuário acessar uma nova página diretamente informando sua URL, o histórico de páginas
    futuras deve ser esvaziado, pois o acesso a uma nova página invalida as páginas futuras das quais eventualmente
    o usuário tenha voltado.

    Você deve criar a classe Browser conforme especificação acima.
    Você deve também criar um programa para testar a classe Browser. Este programa deve instanciar um objeto
    Browser, e executar uma função testNavigation (veja assinatura da função ao final deste documento). Esta
    função deve receber um objeto browser e um array de strings representando uma sequência de comandos a
    serem chamados no objeto browser. A função testNavigation deve executar a sequência de comandos no objeto
    browser e, sempre que for dado um comando "get-current", deve ser exibido na saída a página atual
    (currentPage) da navegação. Veja os exemplos a seguir.

    Entrada 1 Saída 1
    [
        "get-current",
        "access,https://amazon.com",
        "access,https://cnn.com",
        "get-current",
        "back",
        "get-current",
        "back",
        "get-current",
        "back"
    ]
    home
    https://cnn.com
    https://amazon.com
    home
    Error: Back error

    Entrada 2 Saída 2
    [
        "access,https://amazon.com",
        "access,https://cnn.com",
        "get-current",
        "forward"
    ]
    https://cnn.com
    Error: Forward error

    Entrada 3 Saída 3
    [
        "access,https://amazon.com",
        "access,https://cnn.com",
        "access,https://gmail.com",
        "access,https://outlook.com",
        "get-current",
        "back",
        "back",
        "back",
        "get-current",
        "forward",
        "forward",
        "get-current"
    ]
    https://outlook.com
    https://amazon.com
    https://gmail.com

    Entrada 4 Saída 4
    [
        "access,https://amazon.com",
        "access,https://cnn.com",
        "access,https://gmail.com",
        "access,https://outlook.com",
        "get-current",
        "back",
        "back",
        "back",
        "get-current",
        "forward",
        "forward",
        "get-current",
        "access,https://devsuperior.com.br",
        "back",
        "forward",
        "get-current",
        "forward"
    ]
    https://outlook.com
    https://amazon.com
    https://gmail.com
    https://devsuperior.com.br
    Error: Forward error

    Assinatura:  static void testNavigation(Browser browser, String[] commands)

     */

    static class Browser {
        private final Stack<String> backStack;
        private final Stack<String> forwardStack;
        private String currentPage;

        public Browser() {
            this.backStack = new Stack<>();
            this.forwardStack = new Stack<>();
            this.currentPage = "home";
        }

        public void access(String url) {
            // Ao acessar uma nova página, a página atual vai para o histórico de back
            // e o histórico de forward é limpo
            backStack.push(currentPage);
            currentPage = url;
            forwardStack.clear();
        }

        public void back() {
            // Se não há páginas anteriores, lançar erro
            if (backStack.isEmpty()) {
                throw new RuntimeException("Back error");
            }
            // A página atual vai para o histórico de forward
            forwardStack.push(currentPage);
            // A última página do back se torna a página atual
            currentPage = backStack.pop();
        }

        public void forward() {
            // Se não há páginas futuras, lançar erro
            if (forwardStack.isEmpty()) {
                throw new RuntimeException("Forward error");
            }
            // A página atual vai para o histórico de back
            backStack.push(currentPage);
            // A última página do forward se torna a página atual
            currentPage = forwardStack.pop();
        }

        public String getCurrentPage() {
            return currentPage;
        }
    }

    static void testNavigation(Browser browser, String[] commands) {
        for (String cmd : commands) {
            try {
                if (cmd.startsWith("access,")) {
                    String url = cmd.substring("access,".length());
                    browser.access(url);
                } else if (cmd.equals("back")) {
                    browser.back();
                } else if (cmd.equals("forward")) {
                    browser.forward();
                } else if (cmd.equals("get-current")) {
                    System.out.println(browser.getCurrentPage());
                } else {
                    System.out.println("Error: Unknown command \"" + cmd + "\"");
                }
            } catch (RuntimeException e) {
                // Alterei para System.out para corresponder exatamente ao formato de saída esperado
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static void main(String[] ignoredArgs) {
        // Caso de Teste 1
        String[] commands1 = {
                "get-current",
                "access,https://amazon.com",
                "access,https://cnn.com",
                "get-current",
                "back",
                "get-current",
                "back",
                "get-current",
                "back"
        };

        // Caso de Teste 2
        String[] commands2 = {
                "access,https://amazon.com",
                "access,https://cnn.com",
                "get-current",
                "forward"
        };

        // Caso de Teste 3
        String[] commands3 = {
                "access,https://amazon.com",
                "access,https://cnn.com",
                "access,https://gmail.com",
                "access,https://outlook.com",
                "get-current",
                "back",
                "back",
                "back",
                "get-current",
                "forward",
                "forward",
                "get-current"
        };

        // Caso de Teste 4
        String[] commands4 = {
                "access,https://amazon.com",
                "access,https://cnn.com",
                "access,https://gmail.com",
                "access,https://outlook.com",
                "get-current",
                "back",
                "back",
                "back",
                "get-current",
                "forward",
                "forward",
                "get-current",
                "access,https://devsuperior.com.br",
                "back",
                "forward",
                "get-current",
                "forward"
        };

        // Executando os Casos de Teste
        System.out.println("\n\nSaída para Entrada 1:");
        Browser browser1 = new Browser();
        testNavigation(browser1, commands1);
        System.out.println();

        System.out.println("Saída para Entrada 2:");
        Browser browser2 = new Browser();
        testNavigation(browser2, commands2);
        System.out.println();

        System.out.println("Saída para Entrada 3:");
        Browser browser3 = new Browser();
        testNavigation(browser3, commands3);
        System.out.println();

        System.out.println("Saída para Entrada 4:");
        Browser browser4 = new Browser();
        testNavigation(browser4, commands4);
    }
}
