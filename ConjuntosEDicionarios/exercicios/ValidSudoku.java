package exercicios;

import java.util.*;

import static java.lang.System.nanoTime;
import static java.util.Arrays.fill;

public class ValidSudoku {

    /*

    36. Valid Sudoku

    Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

    Each row must contain the digits 1-9 without repetition.
    Each column must contain the digits 1-9 without repetition.
    Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition

    Note:
    A Sudoku board (partially filled) could be valid but is not necessarily solvable.
    Only the filled cells need to be validated according to the mentioned rules.


    Example 1:
    Input: board =
    [["5","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    Output: true

    Example 2:
    Input: board =
    [["8","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    Output: false

    Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8.
    Since there are two 8's in the top left 3x3 sub-box, it is invalid.

     */

    // Primeira implementação: usando arrays de boolean
    static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(board, i) ||
                !isValidColumn(board, i) ||
                !isValidBox(board, i)) {
                return false;
            }
        }
        return true;
    }

    static boolean isValidRow(char[][] board, int row) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 9; i++) {
            char c = board[row][i];
            if (c == '.') continue;
            int n = c - '1';
            if (seen[n]) return false;
            seen[n] = true;
        }
        return true;
    }

    static boolean isValidColumn(char[][] board, int column) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 9; i++) {
            char c = board[i][column];
            if (c == '.') continue;
            int n = c - '1';
            if (seen[n]) return false;
            seen[n] = true;
        }
        return true;
    }

    static boolean isValidBox(char[][] board, int box) {
        boolean[] seen = new boolean[9];
        int row = (box / 3) * 3;
        int column = (box % 3) * 3;
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int n = c - '1';
                if (seen[n]) return false;
                seen[n] = true;
            }
        }
        return true;
    }

    // Segunda implementação: usando HashSet
    static boolean isValidSudoku2(char[][] board) {
        // Inicializa listas para as linhas, colunas e caixas
        List<Set<String>> rows = new ArrayList<>(9);
        List<Set<String>> columns = new ArrayList<>(9);
        List<Set<String>> boxes = new ArrayList<>(9);

        // Preenche cada lista com um HashSet vazio
        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            columns.add(new HashSet<>());
            boxes.add(new HashSet<>());
        }

        // Percorre o tabuleiro
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num == '.') continue;

                // Calcula o índice da caixa
                int boxIndex = (i / 3) * 3 + j / 3;

                // Verifica se o número já existe na linha, coluna ou caixa correspondente
                if (!rows.get(i).add(String.valueOf(num)) ||
                    !columns.get(j).add(String.valueOf(num)) ||
                    !boxes.get(boxIndex).add(String.valueOf(num))) {
                    return false;
                }
            }
        }
        return true;
    }

    static void testValidSudoku(char[][] board, String testName) {
        System.out.println("Teste: " + testName);
        printBoard(board);

        long[] times = new long[2];
        boolean[] results = new boolean[2];

        // Teste das duas implementações
        long start = nanoTime();
        results[0] = isValidSudoku(board);
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = isValidSudoku2(board);
        times[1] = nanoTime() - start;

        // Exibição dos resultados
        System.out.println("isValidSudoku1 resultado: " + results[0]);
        System.out.println("isValidSudoku2 resultado: " + results[1]);

        int fastest = times[0] < times[1] ? 0 : 1;
        int slowest = fastest == 0 ? 1 : 0;

        double ratio = (double) times[slowest] / times[fastest];
        System.out.println("isValidSudoku1 (Boolean Array) runtime: " + times[0] + " ns");
        System.out.println("isValidSudoku2 (HashSet) runtime: " + times[1] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: isValidSudoku" + (fastest + 1));
        System.out.println();
    }

    static void printBoard(char[][] board) {
        System.out.println("Board:");
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Caso de teste 1: Sudoku válido
        char[][] validBoard = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        // Caso de teste 2: Sudoku inválido (8 duplicado)
        char[][] invalidBoard = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        // Caso de teste 3: Sudoku vazio (válido)
        char[][] emptyBoard = new char[9][9];
        for (char[] row : emptyBoard) {
            fill(row, '.');
        }

        // Caso de teste 4: Sudoku completamente preenchido e válido
        char[][] fullBoard = generateValidFullBoard();

        // Executar testes
        testValidSudoku(validBoard, "Sudoku Válido");
        testValidSudoku(invalidBoard, "Sudoku Inválido");
        testValidSudoku(emptyBoard, "Sudoku Vazio");
        testValidSudoku(fullBoard, "Sudoku Completo");
    }

    // Método auxiliar para gerar um Sudoku completo válido
    private static char[][] generateValidFullBoard() {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = (char) ('1' + ((i * 3 + i / 3 + j) % 9));
            }
        }
        return board;
    }
}
