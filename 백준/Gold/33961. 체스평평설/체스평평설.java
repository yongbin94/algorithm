import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        if (N < 3) {
            System.out.println("NO");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int[][] A = {{0, 2, 1, 0, 2, 1}, {0, 1, 3, 2, 1, 0, 2, 3}, {0, 1, 3, 4, 2, 0, 1, 2, 4, 3}};
        int n = 1;
        if (N % 3 == 1) {
            for (int i = 0; i < 8; i++) {
                sb.append(i % 2 == 0 ? 1 : 2).append(" ").append(A[1][i] + n).append("\n");
            }
            n = 5;
        } else if (N % 3 == 2) {
            for (int i = 0; i < 10; i++) {
                sb.append(i % 2 == 0 ? 1 : 2).append(" ").append(A[2][i] + n).append("\n");
            }
            n = 6;
        }
        while (n <= N) {
            for (int i = 0; i < 6; i++) {
                sb.append(i % 2 == 0 ? 1 : 2).append(" ").append(A[0][i] + n).append("\n");
            }
            n += 3;
        }
        System.out.println("YES");
        System.out.println(sb);
    }
}