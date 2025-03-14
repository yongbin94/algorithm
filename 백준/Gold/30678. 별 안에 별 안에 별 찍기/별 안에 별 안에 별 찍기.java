import java.util.*;

public class Main {
    static int N;
    static boolean[][] map;

    public static void main(String[] args) {
        N = (int) Math.pow(5, new Scanner(System.in).nextInt());
        map = new boolean[N][N];
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

    private static void recur(int n, int sr, int sc) {
        if (n == 1) {
            map[sr][sc] = true;
            return;
        }

        recur(n / 5, sr, sc + n / 5 * 2);
        recur(n / 5, sr + n / 5, sc + n / 5 * 2);
        for (int i = 0; i < 5; i++) recur(n / 5, sr + n / 5 * 2, sc + n / 5 * i);
        for (int i = 1; i < 4; i++) recur(n / 5, sr + n / 5 * 3, sc + n / 5 * i);
        recur(n / 5, sr + n / 5 * 4, sc + n / 5);
        recur(n / 5, sr + n / 5 * 4, sc + n / 5 * 3);
    }
}