import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R, C;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();
        for (R = 0; R < N; R++)
            for (C = 0; C < M; C++) {
                visited = new boolean[N][M];
                if (dfs(R, C, 0)) {
                    System.out.println("Yes");
                    return;
                }
            }
        System.out.println("No");
    }

    private static boolean dfs(int r, int c, int cnt) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] != map[R][C])
                continue;
            if (R == nr && C == nc) {
                if (cnt > 2)
                    return true;
                continue;
            }
            visited[nr][nc] = true;
            if(dfs(nr, nc, cnt + 1))
                return true;
        }
        return false;
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}