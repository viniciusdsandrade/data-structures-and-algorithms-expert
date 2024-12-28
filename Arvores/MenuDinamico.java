import java.util.*;

public class MenuDinamico {

    /*
    DESAFIO: Menu dinâmico

    Você está trabalhando em uma biblioteca que permite criação de menus dinâmicos em
    aplicações webs.

    Cada item de menu deve ser representado por um objeto do tipo MenuItem, conforme
    projeto ao lado. Cada item de menu possui um texto que vai aparecer para o usuário, e
    uma rota de navegação web para quando o usuário selecionar este item de menu.

    Você deve implementar uma função generateTree que recebe um array com os dados de cada item do menu em
    formato CSV. A função deverá então criar e retornar a árvore genérica (veja as assinaturas da função ao final
    deste documento). Cada elemento da árvore será um objeto do tipo MenuItem.
    Cada registro CSV contém, nesta ordem: id do item de menu no banco de dados, texto do item de menu, rota do
    item de menu, e id do pai.

    O primeiro registro CSV será a raiz da árvore, que será apenas o "título" da árvore, e não terá rota, nem pai.
    Os registros CSV estarão ordenados, ou seja, é garantido que o id do pai já foi o id de um dos itens anteriores.
    Repare nos exemplos a seguir que os itens de menu que não são folhas geralmente não possuem rotas web a
    serem seguidas, pois os itens de menu internos são apenas intermediários para se organizar a hierarquia do
    menu, então o usuário apenas navega por eles, sem selecioná-los.

    Você deverá também criar um programa que chama a função generateTree, e imprime na saída o menu de
    forma hierárquica, mostrando cada item de menu e sua rota quando houver, conforme exemplos.
    Dica: use um dicionário (mapa) para armazenar as posições da árvore já instanciadas, e utilize o id do item de
    menu como chave deste dicionário. Isso vai te ajudar, pois quando você for processar um novo item de menu,
    vai ficar fácil acessar a posição do pai dele pelo id.
     */

    static class GenericTree<T> implements Cloneable {
        private final T root;

        public GenericTree(T root) {
            this.root = root;
        }

        public T getRoot() {
            return root;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;

            GenericTree<?> that = (GenericTree<?>) o;

            return Objects.equals(this.root, that.root);
        }

        @Override
        public final int hashCode() {
            return Objects.hash(root);
        }

        @Override
        public final String toString() {
            return "GenericTree{" +
                   "root=" + root +
                   '}';
        }

        public GenericTree(GenericTree<T> tree) {
            this.root = tree.root;
        }

        @Override
        @SuppressWarnings("MethodDoesntCallSuperMethod")
        public GenericTree<T> clone() {
            GenericTree<T> clone = null;
            try {
                clone = new GenericTree<>(this);
            } catch (Exception ignored) {
            }
            return clone;
        }
    }

    static class MenuItem implements Cloneable {
        String id;
        String text;
        String route;
        List<MenuItem> children;

        MenuItem(String id, String text, String route) {
            this.id = id;
            this.text = text;
            this.route = route;
            this.children = new ArrayList<>();
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;

            MenuItem that = (MenuItem) o;

            return Objects.equals(this.id, that.id) &&
                   Objects.equals(this.text, that.text) &&
                   Objects.equals(this.route, that.route) &&
                   Objects.equals(this.children, that.children);
        }

        @Override
        public final int hashCode() {
            return Objects.hash(id, text, route, children);
        }

        @Override
        public final String toString() {
            return "MenuItem{" +
                   "id='" + id + '\'' +
                   ", text='" + text + '\'' +
                   ", route='" + route + '\'' +
                   ", children=" + children +
                   '}';
        }

        public MenuItem(MenuItem menuItem) {
            this.id = menuItem.id;
            this.text = menuItem.text;
            this.route = menuItem.route;
            this.children = new ArrayList<>(menuItem.children.size());
            menuItem.children.forEach(child -> this.children.add(child.clone()));
        }

        @Override
        @SuppressWarnings("MethodDoesntCallSuperMethod")
        public MenuItem clone() {
            MenuItem clone = null;

            try {
                clone = new MenuItem(this);
            } catch (Exception ignored) {
            }

            return clone;
        }
    }

    static GenericTree<MenuItem> generateTree(String[] records) {
        Map<String, MenuItem> menuMap = new HashMap<>();
        MenuItem root = null;

        for (String record : records) {
            String[] parts = record.split(",", -1);
            String id = parts[0];
            String text = parts[1];
            String route = parts[2];
            String parentId = parts.length > 3 ? parts[3] : "";

            MenuItem menuItem = new MenuItem(id, text, route.isEmpty() ? null : route);
            menuMap.put(id, menuItem);

            if (parentId.isEmpty()) {
                root = menuItem;
            } else {
                MenuItem parent = menuMap.get(parentId);
                parent.children.add(menuItem);
            }
        }

        return new GenericTree<>(root);
    }

    static void printTree(MenuItem node, String indent) {
        System.out.println(indent + node.text + " (" + (node.route != null ? node.route : "null") + ")");
        node.children.forEach(child -> printTree(child, indent + "    "));
    }

    public static void main(String[] args) {
        System.out.println("Exemplo 1:");
        String[] input1 = {
                "31,Site de investimentos,,",
                "33,Notícias,,31",
                "47,Nacionais,/noticias-nacionais,33",
                "49,Internacionais,/noticias-internacionais,33",
                "53,Economia,,31",
                "57,Bolsa de valores,,53",
                "61,Ações,/acoes,57",
                "65,Fundos imobiliários,/fii,57",
                "72,Indicadores,/indicadores,53",
                "75,Blog,/blog,53"
        };

        GenericTree<MenuItem> tree1 = generateTree(input1);
        printTree(tree1.getRoot(), "");

        System.out.println("\nExemplo 2:");
        String[] input2 = {
                "722,Sistema de contabilidade,,",
                "812,Início,/,722",
                "825,Clientes,,722",
                "831,Cadastro,/clients,825",
                "835,Relatórios,/clients/reports,825",
                "903,Financeiro,,722",
                "912,Resumo,/fin/summary,903",
                "928,Relatórios,/fin/reports,903"
        };

        GenericTree<MenuItem> tree2 = generateTree(input2);
        printTree(tree2.getRoot(), "");
    }
}
