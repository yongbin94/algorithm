import java.util.*;

public class Main {
    static int N;
    static boolean[][] map;

    public static void main(String[] args) {
        N = new Scanner(System.in).nextInt();
        map = new boolean[N][N * 2];
        recur(N, 0, 0);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(map).forEach(row -> {
            for (boolean f : row) {
                sb.append(f ? "*" : " ");
            }
            sb.append("\n");
        });
        System.out.println(sb);

    }

    private static void recur(int n, int r, int c) {
        if (n == 3) {
            map[r][c + 2] = true;
            map[r + 1][c + 1] = true;
            map[r + 1][c + 3] = true;
            for (int i = 0; i < 5; i++) map[r + 2][c + i] = true;
            return;
        }
        recur(n / 2, r, c + n / 2);
        recur(n / 2, r + n / 2, c);
        recur(n / 2, r + n / 2, c + n);
    }
}