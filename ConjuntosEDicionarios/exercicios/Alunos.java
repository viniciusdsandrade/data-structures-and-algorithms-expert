package exercicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.lang.System.nanoTime;
import static java.util.Arrays.asList;
import static java.util.List.of;

public class Alunos {

    /*

    Problema "alunos"

        Em um portal de cursos online, cada aluno possui um código único, representado por um número inteiro.
        Cada instrutor do portal pode ter vários cursos, sendo que um mesmo aluno pode se matricular em quantos cursos quiser.
        Assim, o número total de alunos de um instrutor não é simplesmente a soma dos alunos de todos os cursos que ele possui,
        pois pode haver alunos repetidos em mais de um curso.

        Você deve criar uma função que, dada a lista de alunos de todos os cursos de um instrutor,
        a função deve retornar a quantidade total de alunos deste instrutor.

    Exemplo:
        Entrada
            [
                [15, 21, 80, 42],
                [21, 80, 47],
                [12, 21, 47, 35]
            ]
        Saída 7


    Assinaturas: static int studentsCount(List<List<Integer>> courses)

     */

    // Implementation using HashSet with addAll
    public static int studentsCount(List<List<Integer>> courses) {
        Set<Integer> set = new HashSet<>();
        for (List<Integer> course : courses) {
            set.addAll(course);
        }
        return set.size();
    }

    // Implementation using HashSet with individual adds
    public static int studentsCount2(List<List<Integer>> courses) {
        Set<Integer> set = new HashSet<>();
        for (List<Integer> course : courses) {
            for (Integer student : course) {
                set.add(student);
            }
        }
        return set.size();
    }

    // Implementation using streams
    public static int studentsCount3(List<List<Integer>> courses) {
        return courses.stream()
                .flatMap(List::stream)
                .collect(HashSet::new, Set::add, Set::addAll)
                .size();
    }

    static void testStudentsCount(List<List<Integer>> courses) {
        System.out.println("Input: " + courses);
        long[] times = new long[3];
        int[] results = new int[3];

        // Test all three methods
        long start = nanoTime();
        results[0] = studentsCount(courses);
        times[0] = nanoTime() - start;

        start = nanoTime();
        results[1] = studentsCount2(courses);
        times[1] = nanoTime() - start;

        start = nanoTime();
        results[2] = studentsCount3(courses);
        times[2] = nanoTime() - start;

        // Print results
        System.out.println("studentsCount1 result: " + results[0]);
        System.out.println("studentsCount2 result: " + results[1]);
        System.out.println("studentsCount3 result: " + results[2]);

        // Find fastest and slowest methods
        int fastest = 0, slowest = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] < times[fastest]) {
                fastest = i;
            }
            if (times[i] > times[slowest]) {
                slowest = i;
            }
        }

        double ratio = (double) times[slowest] / times[fastest];
        System.out.println("studentsCount1 runtime: " + times[0] + " ns");
        System.out.println("studentsCount2 runtime: " + times[1] + " ns");
        System.out.println("studentsCount3 runtime: " + times[2] + " ns");
        System.out.printf("Razão (lento/rápido): %.2fx\n", ratio);
        System.out.println("Método mais rápido: studentsCount" + (fastest + 1));
        System.out.println();
    }

    public static void main(String[] args) {
        List<List<List<Integer>>> testCases = asList(
                // Example from the problem
                asList(
                        asList(15, 21, 80, 42),
                        asList(21, 80, 47),
                        asList(12, 21, 47, 35)
                ),
                // Empty courses
                asList(
                        of(),
                        of(),
                        of()
                ),
                // Single student in multiple courses
                asList(
                        of(1),
                        of(1),
                        of(1)
                ),
                // No overlapping students
                asList(
                        asList(1, 2),
                        asList(3, 4),
                        asList(5, 6)
                ),
                // Large test case with many duplicates
                generateLargeTestCase(100, 5)
        );

        // Run tests
        for (List<List<Integer>> testCase : testCases) {
            testStudentsCount(testCase);
        }
    }

    // Helper method to generate a large test case
    private static List<List<Integer>> generateLargeTestCase(int numStudents, int numCourses) {
        List<List<Integer>> courses = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numCourses; i++) {
            List<Integer> course = new ArrayList<>();
            int courseSize = random.nextInt(numStudents / 2) + numStudents / 2;

            for (int j = 0; j < courseSize; j++) {
                course.add(random.nextInt(numStudents) + 1);
            }
            courses.add(course);
        }

        return courses;
    }
}
