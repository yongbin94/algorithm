import java.util.*;

public class Main {
    static int N;
    static boolean[][] map;

    public static void main(String[] args) {
        N = new Scanner(System.in).nextInt();
        map = new boolean[N][N];
        recur(N, 0, 0, false);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(map).forEach(row -> {
            for(boolean f : row){
                sb.append(f ? " " : "*");
            }
            sb.append("\n");
        });
        System.out.println(sb);
    }

    private static void recur(int n, int sr, int sc, boolean f) {
        if (n == 1) {
            map[sr][sc] = f;
            return;
        }
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int nr = sr + r * n / 3;
                int nc = sc + c * n / 3;
                recur(n / 3, nr, nc, f || r == 1 && c == 1);
            }
        }
    }
}