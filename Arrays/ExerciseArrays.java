package Arrays;

public class ExerciseArrays {
    /*
    Exercícios:
    Máximo 1s consecutivos (max-consecutive-ones) (LeetCode)
    Dado um array binário nums, retorne o número máximo de 1s consecutivos no array.

    Exemplo 1:

    entrada: nums = [1,1,0,1,1,1]
    saída: 3
    Explicação: Os dois primeiros dígitos ou os últimos três são 1s consecutivos. O número máximo de 1s consecutiovs é 3.

    Exemplo 2:

    entrada: nums = [1,0,1,1,0,1]
    saída: 2
    -------------------------------------------------------------------------------------------------
    Produto escalar de dois vetores (dot-product-two-arrays) (Adaptado Leetcode)
    Dados dois arrays, calcule o seu produto escalar.

    Exemplo 1:

    entrada: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
    saída: 8
    Explicação: O produto escalar dos arrays acima pode ser encontrado pela expressão: (1 * 0) + (0 * 3) + (0 * 0) + (2 * 4) + (3 * 0) = 8

    Exemplo 2:

    entrada: nums1 = [0,1,0,0,0], nums2 = [0,1,0,0,0]
    saída: 0
    Exemplo 3:

    entrada: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
    saída: 0

    ----------------------------------------------------------------------------------------------------
    Encontre números com número par de dígitos (find-even-number-digits) (Leetcode)
    Dado um array nums de inteiros, retorne quantos deles contêm um número par de dígitos.

    Exemplo 1:

    entrada: nums = [12,345,2,6,7896]
    saída: 2
    Explicação:
    12 contém 2 dígitos (número par de dígitos).
    345 contém 3 dígitos (número ímpar de dígitos).
    2 contém 1 dígito (número ímpar de dígitos).
    6 contém 1 dígito (número ímpar de dígitos).
    7896 contém 4 dígitos (número par de dígitos).
    Portanto, apenas 12 e 7896 contêm um número par de dígitos.

    Exemplo 2:

    entrada: nums = [555,901,482,1771]
    saída: 1
    Explicação:
    Apenas o número 1771 tem um número par de dígitos.

    Encontrar vendedor com maior valor de venda (seller) (arquivo json)
    Dado um array de vendedores, cada um representado por um objeto com o nome do vendedor name e o valor de suas vendas amount, crie uma função para encontrar e retornar o vendedor que obteve o maior valor de venda.

    Utilize o seguinte arquivo para o problema: seller.json

    Quadrados de um array ordenado (sorted-square) (Leetcode)
    Dado um array de números inteiros nums ordenado em ordem crescente, retorne um array com os quadrados de cada número, também ordenado de forma crescente.

    Exemplo 1:

    entrada: nums = [-4,-1,0,3,10]
    saída: [0,1,9,16,100]
    Explicação: Após elevar ao quadrado, temos como resultado o array [16, 1, 0, 9, 100]. Em seguida, após ordenar os valores do array, temos [0, 1, 9, 16, 100].

    Exemplo 2:

    entrada: nums = [-7,-3,2,3,11]
    saída: [4,9,9,49,121]
    Duplicar zeros (duplicate-zeros) (Leetcode)
    Dado um array de inteiros arr, duplique cada ocorrência de zero, deslocando os elementos restantes para a direita (shifting right).

    Observe que elementos além do tamanho do array original não são escritos.

    Utilize a abordagem "In-place" na qual a modificação é feita diretamente no array.

    Exemplo 1:

    entrada: nums = [1,0,2,3,0,4,5,0]
    saída: [1,0,0,2,3,0,0,4]
    Exemplo 2:

    entrada: nums = [1,2,3]
    saída: [1,2,3]
    Merge arrays (merge-arrays) (Leetcode)
    Dado dois arrays de números inteiros nums1 e nums2, ordenados em ordem crescente, sendo m e n seus tamanhos, respectivamente.

    Junte os arrays nums1 e nums2 em um único array ordenado de forma crescente.

    O array final ordenado não deve ser retornado pela função, mas sim armazenado dentro do array nums1. Para acomodar os elementos, nums1 possui um comprimento de m + n.

    Exemplo 1:

    entrada: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    saída: [1,2,2,3,5,6]
    Explicação: Os arrays que queremos juntar são [1,2,3] e [2,5,6]. O resultado ao fazer o merge é [1,2,2,3,5,6]

    Exemplo 2:

    entrada: nums1 = [1], m = 1, nums2 = [], n = 0
    saída: [1]
    Explicação: Os arrays que queremos juntar são [1] e []. O resultado ao fazer o merge é [1]

    Exemplo 3:

    entrada: nums1 = [0], m = 0, nums2 = [1], n = 1
    saída: [1]
    Explicação: Os arrays que queremos juntar são [] e [1]. O resultado ao fazer o merge é [1]

    Contém valores duplicados (contains-duplicate) (Leetcode)
    Dado um array de números inteiros nums, retorne true se houver valores repetidos ou false se não houver repetição de valores no array.

    Exemplo 1:

    entrada: nums = [1,2,3,1]
    saída: true
    Exemplo 2:

    entrada: nums = [1,2,3,4]
    saída: false
    Exemplo 3:

    entrada: nums = [1,1,1,3,3,4,3,2,4,2]
    saída: true
     */

    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }

    public static int ScalarProduct(int[] nums1, int[] nums2) {
        int result = 0;
        for (int i = 0; i < nums1.length; i++)
            result += nums1[i] * nums2[i];
        return result;
    }

    public static void main(String[] ignoredArgs) {
        int[] nums = {1, 1, 0, 1, 1, 1};
        System.out.println(findMaxConsecutiveOnes(nums));

        int[] nums1 = {1, 0, 1, 1, 0, 1};
        System.out.println(findMaxConsecutiveOnes(nums1));


        // Exemplo 1
        int[] nums1_ex1 = {1, 0, 0, 2, 3};
        int[] nums2_ex1 = {0, 3, 0, 4, 0};
        System.out.println("Exemplo 1:");
        System.out.println("Entrada: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]");
        System.out.println("Saída: " + ScalarProduct(nums1_ex1, nums2_ex1));
        System.out.println("Explicação: (1*0) + (0*3) + (0*0) + (2*4) + (3*0) = 8\n");

        // Exemplo 2
        int[] nums1_ex2 = {0, 1, 0, 0, 0};
        int[] nums2_ex2 = {0, 1, 0, 0, 0};
        System.out.println("Exemplo 2:");
        System.out.println("Entrada: nums1 = [0,1,0,0,0], nums2 = [0,1,0,0,0]");
        System.out.println("Saída: " + ScalarProduct(nums1_ex2, nums2_ex2));
        System.out.println("Explicação: (0*0) + (1*1) + (0*0) + (0*0) + (0*0) = 1\n");

        // Exemplo 3
        int[] nums1_ex3 = {0, 1, 0, 0, 2, 0, 0};
        int[] nums2_ex3 = {1, 0, 0, 0, 3, 0, 4};
        System.out.println("Exemplo 3:");
        System.out.println("Entrada: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]");
        System.out.println("Saída: " + ScalarProduct(nums1_ex3, nums2_ex3));
        System.out.println("Explicação: (0*1) + (1*0) + (0*0) + (0*0) + (2*3) + (0*0) + (0*4) = 6");
    }
}
