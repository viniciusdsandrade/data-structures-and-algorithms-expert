import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Visitantes {
    /*
    Problema "visitantes"
    Fazer uma função que receba uma lista de registros de log de acesso a um website,
    e retorne a quantidade de usuários únicos que acessaram o site.
    Cada registro de log está no formato CSV (valores separados por vírgula) que contém:
    nome de usuário, momento de acesso e URL acessada.
    Exemplo 1:
    Entrada
     [
        "ana,2024-07-04T21:42:40.353283800Z,https://blog.com/login",
        "bob,2024-07-04T21:42:44.571283800Z,https://blog.com/news",
        "maria,2024-07-04T21:42:46.394283800Z,https://blog.com/shop",
        "ana,2024-07-04T21:42:50.026283800Z,https://blog.com/news"
    ]
    Saída : 3

    Entrada
    Saída
    Arquivo visitantes-input.json no Github do curso (contém 100000 registros)
    30680

    Java: public static int total(List<string> visitors)
     */

    public static int total(List<String> visitors) {
        Set<String> set = new HashSet<>();
        for (String s : visitors) {
            String[] fields = s.split(",");
            set.add(fields[0]);
        }
        return set.size();
    }

    public static int totalFromJsonFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Lê o arquivo JSON para uma List<String>
            List<String> visitors = mapper.readValue(new File(filePath), new TypeReference<List<String>>() {
            });
            // Aplica a função existente para contar visitantes únicos
            return total(visitors);
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Em caso de erro, retorna -1 ou lança uma exceção
        }
    }

    public static void main(String[] args) {
        List<String> visitors = List.of(
                "ana,2024-07-04T21:42:40.353283800Z,https://blog.com/login",
                "bob,2024-07-04T21:42:44.571283800Z,https://blog.com/news",
                "maria,2024-07-04T21:42:46.394283800Z,https://blog.com/shop",
                "ana,2024-07-04T21:42:50.026283800Z,https://blog.com/news"
        );
        System.out.println(total(visitors));


        String s = "C:\\Users\\Vinícius Andrade\\Desktop\\data-structures-and-algorithms-expert\\ConjuntosEDicionarios\\visitantes-input.json";
        int uniqueVisitors = totalFromJsonFile(s);
        System.out.println("Quantidade de usuários únicos: " + uniqueVisitors);
    }
}
