import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][][] map = new int[3][N][N];
        Arrays.stream(map).forEach(v -> Arrays.stream(v).forEach(w -> Arrays.fill(w, -1)));
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < N; m++) {
                int v = Integer.parseInt(st.nextToken());
                for (int i = 0; i < 3; i++) {
                    map[i][n][m] = Math.max(n == 0 ? 0 : map[i][n - 1][m], m == 0 ? 0 : map[i][n][m - 1]);
                }
                if (v == 0 && map[v][n][m] == 0) {
                    map[v][n][m] = 1;
                    continue;
                }
                if (n != 0 && map[(v + 2) % 3][n - 1][m] != 0) {
                    map[v][n][m] = Math.max(map[v][n][m], map[(v + 2) % 3][n - 1][m] + 1);
                }
                if (m != 0 && map[(v + 2) % 3][n][m - 1] != 0) {
                    map[v][n][m] = Math.max(map[v][n][m], map[(v + 2) % 3][n][m - 1] + 1);
                }
            }
        }
        System.out.println(Math.max(map[0][N - 1][N - 1], Math.max(map[1][N - 1][N - 1], map[2][N - 1][N - 1])));
    }
}