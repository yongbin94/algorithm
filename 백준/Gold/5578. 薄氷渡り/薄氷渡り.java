import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        M = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                map[n][m] = st.nextToken().charAt(0) == '1';
        }
        for (int n = 0; n < N; n++)
            for (int m = 0; m < M; m++)
                if (map[n][m])
                    dfs(n, m, 1);
        System.out.println(answer);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void dfs(int r, int c, int cnt) {
        map[r][c] = false;
        answer = Math.max(answer, cnt);
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isIn(nr, nc) || !map[nr][nc])
                continue;
            dfs(nr, nc, cnt + 1);
        }
        map[r][c] = true;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}