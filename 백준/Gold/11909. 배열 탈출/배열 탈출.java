import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        memo = new int[N][N];
        for (int n = 0; n < N; n++) {
            map[n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(memo[n], Integer.MAX_VALUE);
        }
        solution();
    }

    private static void solution() {
        memo[0][0] = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (c < N - 1) {
                    int v = map[r][c + 1] - map[r][c] + 1;
                    memo[r][c + 1] = Math.min(memo[r][c + 1], memo[r][c] + Math.max(v, 0));
                }
                if (r < N - 1) {
                    int v = map[r + 1][c] - map[r][c] + 1;
                    memo[r + 1][c] = Math.min(memo[r + 1][c], memo[r][c] + Math.max(v, 0));
                }
            }
        }
        System.out.println(memo[N - 1][N - 1]);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
