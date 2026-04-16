import java.io.*;
import java.util.*;

public class Main {
    static int N, M, res = 0;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (N < 2 && M < 2) {
            System.out.println(0);
            return;
        }

        recur(0, 0);
        System.out.println(res);
    }

    static void recur(int i, int v) {
        if (i == N * M) {
            res = Math.max(res, v);
            return;
        }

        int r = i / M;
        int c = i % M;

        if (!visited[r][c]) {
            if (c - 1 >= 0 && r + 1 < N && !visited[r][c - 1] && !visited[r + 1][c]) {
                visited[r][c] = visited[r][c - 1] = visited[r + 1][c] = true;
                recur(i + 1, v + (map[r][c] * 2) + map[r][c - 1] + map[r + 1][c]);
                visited[r][c] = visited[r][c - 1] = visited[r + 1][c] = false;
            }
            if (r - 1 >= 0 && c - 1 >= 0 && !visited[r - 1][c] && !visited[r][c - 1]) {
                visited[r][c] = visited[r - 1][c] = visited[r][c - 1] = true;
                recur(i + 1, v + (map[r][c] * 2) + map[r - 1][c] + map[r][c - 1]);
                visited[r][c] = visited[r - 1][c] = visited[r][c - 1] = false;
            }
            if (r - 1 >= 0 && c + 1 < M && !visited[r - 1][c] && !visited[r][c + 1]) {
                visited[r][c] = visited[r - 1][c] = visited[r][c + 1] = true;
                recur(i + 1, v + (map[r][c] * 2) + map[r - 1][c] + map[r][c + 1]);
                visited[r][c] = visited[r - 1][c] = visited[r][c + 1] = false;
            }
            if (c + 1 < M && r + 1 < N && !visited[r][c + 1] && !visited[r + 1][c]) {
                visited[r][c] = visited[r][c + 1] = visited[r + 1][c] = true;
                recur(i + 1, v + (map[r][c] * 2) + map[r][c + 1] + map[r + 1][c]);
                visited[r][c] = visited[r][c + 1] = visited[r + 1][c] = false;
            }
        }
        
        recur(i + 1, v);
    }
}