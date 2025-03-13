import java.util.Random;
import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {


        int[] vetor = new int[10];

        carregarVetor(vetor);
        printarVetor(vetor);

    }

    public static void carregarVetor(int[] vetor) {
        Random random = new Random();
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random.nextInt(15);
        }
    }

    public static void printarVetor(int[] vetor) {
        for (int i : vetor) {
            System.out.println(i);
        }
    }
}
