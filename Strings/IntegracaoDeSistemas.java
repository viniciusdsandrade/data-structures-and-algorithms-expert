package Strings;

import static java.lang.Integer.parseInt;

public class IntegracaoDeSistemas {

    /*
    DESAFIO: Integração de sistemas

    Forma de entrega: link do programa salvo no Gist do Github
    Linguagens aceitas: Javascript, Java, C#, Python

    Nota: este é um problema real que já foi utilizado em nosso sistema de área de membros.

    Você foi contratado(a) para construir um programa utilitário para facilitar a integração entre uma
    plataforma de vídeo (que está sendo utilizada para armazenar vídeo aulas) e uma área de membros
    onde os alunos estudam os cursos.

    Os vídeos são exportados pela plataforma de vídeo no formato CSV. Cada registro CSV contém o id
    da aula, o título da aula, bem como sua duração em segundos. O título da aula começa com uma
    numeração, que indica o capítulo e a aula neste capítulo (você pode considerar que essa numeração e o
    espaço antes do início do título sempre vai ter 6 caracteres, conforme exemplo abaixo).

    Você deverá construir uma função para converter os dados CSV para o formato JSON, pois este JSON
    será utilizado para alimentar uma API web da plataforma de ensino.
    Você deverá fazer alguns ajustes nos dados:
     A numeração da aula não deve aparecer no título do resultado final.
     O final ".mp4" dos títulos deve ser removido.

    Sua função deve receber uma lista de strings no formato CSV, e sua função deve retornar uma string
    com os dados no formato JSON, como mostrado no exemplo a seguir.
    Sua função deve obedecer à assinatura especificada no final deste documento.
    Você deve gerar o JSON programaticamente, sem utilizar uma biblioteca externa de JSON.
    Requisito não eliminatório: você deve retornar o JSON formatado com as devidas quebras de linha e
    indentação.
     */

    /*
    Entrada
    [
        "4668c219-296d-40de-a073-99b85026e977,01-01 Visão geral do capítulo.mp4,222",
        "81be4133-f81a-443a-96f3-0c30d7460ab8,01-02 Algoritmos e Lógica de Programação.mp4,396",
        "b82f4f7b-f8f1-43e2-974d-3821e6a59e45,01-03 Estruturas de dados é sobre o quê.mp4,567",
        "ccde5219-f2df-4a11-b981-e0cdfa3c6224,01-04 Precisa saber OO antes de ED.mp4,215",
        "e77cf174-9547-4d7f-8372-ef23f193938f,01-05 Esse curso também é para outras linguagens.mp4,219",
        "254ef676-7bc5-478d-8250-0ce2616c9731,01-06 Vamos falar sobre objetos e funções.mp4,94",
        "e030627b-a8e0-4bde-bda8-edc78503fc2c,02-01 Visão geral do capítulo.mp4,389",
        "c4be1d39-6c97-4721-afd5-3b63ca0abf0a,02-02 Aviso sobre os exercícios.mp4,107",
        "6364ff0f-bc34-4166-8b35-2f0d9cf1b062,02-03 Literais e expressões em Javascript PARTE 1.mp4,545",
        "e7245f74-0135-4af0-9b79-bf4927438cfc,02-04 Literais e expressões em Javascript PARTE 2.mp4,567",
        "a7fc8b41-ce2f-4751-a6a7-39d4db32fbe0,02-05 Imutabilidade de strings em Javascript.mp4,310",
        "a8071e3d-97fe-4cc8-b377-2a69f50844a6,02-06 Funções de string em Javascript PARTE 1.mp4,748",
        "b8624056-07ad-4caa-8deb-1db75e04a8f6,02-07 Funções de string em Javascript PARTE 2.mp4,797",
        "215a5a6a-4171-4f98-b803-4d02811da5ae,02-08 Funções de string em Javascript PARTE 3.mp4,374",
        "32029cca-af8a-4b20-971a-08ccf350d2f4,02-09 Expressões regulares.mp4,486"
    ]

    Saída

    [
        {
            "id": "4668c219-296d-40de-a073-99b85026e977",
            "title": "Visão geral do capítulo",
            "duration": 222
        },
        {
            "id": "81be4133-f81a-443a-96f3-0c30d7460ab8",
            "title": "Algoritmos e Lógica de Programação",
            "duration": 396
        },
        {
            "id": "b82f4f7b-f8f1-43e2-974d-3821e6a59e45",
            "title": "Estruturas de dados é sobre o quê",
            "duration": 567
        },
        {
            "id": "ccde5219-f2df-4a11-b981-e0cdfa3c6224",
            "title": "Precisa saber OO antes de ED",
            "duration": 215
        },
        {
            "id": "e77cf174-9547-4d7f-8372-ef23f193938f",
            "title": "Esse curso também é para outras linguagens",
            "duration": 219
        },
        {
            "id": "254ef676-7bc5-478d-8250-0ce2616c9731",
            "title": "Vamos falar sobre objetos e funções",
            "duration": 94
        },
        {
            "id": "e030627b-a8e0-4bde-bda8-edc78503fc2c",
            "title": "Visão geral do capítulo",
            "duration": 389
        },
        {
            "id": "c4be1d39-6c97-4721-afd5-3b63ca0abf0a",
            "title": "Aviso sobre os exercícios",
            "duration": 107
        },
        {
            "id": "6364ff0f-bc34-4166-8b35-2f0d9cf1b062",
            "title": "Literais e expressões em Javascript PARTE 1",
            "duration": 545
        },
        {
            "id": "e7245f74-0135-4af0-9b79-bf4927438cfc",
            "title": "Literais e expressões em Javascript PARTE 2",
            "duration": 567
        },
        {
            "id": "a7fc8b41-ce2f-4751-a6a7-39d4db32fbe0",
            "title": "Imutabilidade de strings em Javascript",
            "duration": 310
        },
        {
            "id": "a8071e3d-97fe-4cc8-b377-2a69f50844a6",
            "title": "Funções de string em Javascript PARTE 1",
            "duration": 748
        },
        {
            "id": "b8624056-07ad-4caa-8deb-1db75e04a8f6",
            "title": "Funções de string em Javascript PARTE 2",
            "duration": 797
        },
        {
            "id": "215a5a6a-4171-4f98-b803-4d02811da5ae",
            "title": "Funções de string em Javascript PARTE 3",
            "duration": 374
        },
        {
            "id": "32029cca-af8a-4b20-971a-08ccf350d2f4",
            "title": "Expressões regulares",
            "duration": 486
        }
    ]
     */


    /// Converts an array of CSV-formatted strings (representing video data) into a formatted JSON array.
    ///
    /// Each element in the `videos` array should be a single CSV line with the following format:
    /// ```text
    /// id,titleWithPrefixAndSuffix.mp4,duration
    /// ```
    ///
    /// The method performs the following adjustments on each video's data:
    ///
    /// - **ID**: Included as-is from the CSV.
    /// - **Title**: The first 6 characters represent chapter and lesson numbering (e.g., `01-01 `).
    ///   These characters are removed, and the trailing `.mp4` extension is also stripped. The resulting
    ///   title excludes numeric prefixes and the file extension.
    /// - **Duration**: Parsed from the CSV and included as an integer.
    ///
    /// Example transformation:
    ///
    /// Given an input line (CSV):
    /// ```text
    /// 4668c219-296d-40de-a073-99b85026e977,01-01 Visão geral do capítulo.mp4,222
    /// ```
    /// The resulting JSON object will be:
    /// ```json
    /// {
    ///   "id": "4668c219-296d-40de-a073-99b85026e977",
    ///   "title": "Visão geral do capítulo",
    ///   "duration": 222
    /// }
    /// ```
    ///
    /// The entire output is a JSON array containing all such objects:
    /// ```json
    /// [
    ///   {
    ///     "id": "4668c219-296d-40de-a073-99b85026e977",
    ///     "title": "Visão geral do capítulo",
    ///     "duration": 222
    ///   },
    ///   {
    ///     "id": "81be4133-f81a-443a-96f3-0c30d7460ab8",
    ///     "title": "Algoritmos e Lógica de Programação",
    ///     "duration": 396
    ///   },
    ///   ...
    /// ]
    /// ```
    ///
    /// @param videos an array of CSV lines, each including an ID, a title with a numeric prefix and `.mp4` suffix, and a duration.
    /// @return a `String` containing the formatted JSON array with the cleaned `id`, `title`, and `duration` fields.
    public static String csvToJson(String[] videos) {
        StringBuilder json = new StringBuilder("[\n");

        String[] videoData;
        String id, title;
        int duration;

        for (String video : videos) {
            videoData = video.split(",");
            id = videoData[0];
            title = videoData[1].substring(6, videoData[1].length() - 4);
            duration = parseInt(videoData[2]);

            json.append("    {\n");
            json.append("        \"id\": \"").append(id).append("\",\n");
            json.append("        \"title\": \"").append(title).append("\",\n");
            json.append("        \"duration\": ").append(duration).append("\n");
            json.append("    },\n");
        }

        json.deleteCharAt(json.length() - 2);
        json.append("]");

        return json.toString();
    }

    public static void main(String[] ignoredArgs) {

        String[] videos = {
                "4668c219-296d-40de-a073-99b85026e977,01-01 Visão geral do capítulo.mp4,222",
                "81be4133-f81a-443a-96f3-0c30d7460ab8,01-02 Algoritmos e Lógica de Programação.mp4,396",
                "b82f4f7b-f8f1-43e2-974d-3821e6a59e45,01-03 Estruturas de dados é sobre o quê.mp4,567",
                "ccde5219-f2df-4a11-b981-e0cdfa3c6224,01-04 Precisa saber OO antes de ED.mp4,215",
                "e77cf174-9547-4d7f-8372-ef23f193938f,01-05 Esse curso também é para outras linguagens.mp4,219",
                "254ef676-7bc5-478d-8250-0ce2616c9731,01-06 Vamos falar sobre objetos e funções.mp4,94",
                "e030627b-a8e0-4bde-bda8-edc78503fc2c,02-01 Visão geral do capítulo.mp4,389",
                "c4be1d39-6c97-4721-afd5-3b63ca0abf0a,02-02 Aviso sobre os exercícios.mp4,107",
                "6364ff0f-bc34-4166-8b35-2f0d9cf1b062,02-03 Literais e expressões em Javascript PARTE 1.mp4,545",
                "e7245f74-0135-4af0-9b79-bf4927438cfc,02-04 Literais e expressões em Javascript PARTE 2.mp4,567",
                "a7fc8b41-ce2f-4751-a6a7-39d4db32fbe0,02-05 Imutabilidade de strings em Javascript.mp4,310",
                "a8071e3d-97fe-4cc8-b377-2a69f50844a6,02-06 Funções de string em Javascript PARTE 1.mp4,748",
                "b8624056-07ad-4caa-8deb-1db75e04a8f6,02-07 Funções de string em Javascript PARTE 2.mp4,797",
                "215a5a6a-4171-4f98-b803-4d02811da5ae,02-08 Funções de string em Javascript PARTE 3.mp4,374",
                "32029cca-af8a-4b20-971a-08ccf350d2f4,02-09 Expressões regulares.mp4,486"
        };

        String json = csvToJson(videos);
        System.out.println(json);
    }
}
