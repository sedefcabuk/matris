package matrix;

import java.util.Random;

public class Matrix {
    static final int SIZE = 5;

    public static void main(String[] args) {
        int[][] A = new int[SIZE][SIZE];
        int[][] B = new int[SIZE][SIZE];
        int[][] C = new int[SIZE][SIZE];

        Random random = new Random();

        // Matrisleri random sayılarla oluşturuyoruz.
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                A[i][j] = random.nextInt(10); // 0-9 arası rastgele sayı
                B[i][j] = random.nextInt(10); // 0-9 arası rastgele sayı
            }
        }

        Thread[] threads = new Thread[SIZE];

        // Her bir satır için 5 thread oluşturuyoruz.
        for (int i = 0; i < SIZE; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < SIZE; j++) {
                    C[row][j] = 0;
                    for (int k = 0; k < SIZE; k++) {
                        C[row][j] += A[row][k] * B[k][j];
                    }
                }
            });
            threads[i].start();
        }

        // Ana thread diğer threadlerin bitmesini join() ile bekliyor.
        for (int i = 0; i < SIZE; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Matris A:");
        print(A);

        System.out.println("Matris B:");
        print(B);

        System.out.println("Sonuç matrisi C:");
        print(C);
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
