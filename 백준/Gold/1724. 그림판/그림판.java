import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] line, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        line = new boolean[N * 2 + 1][M * 2 + 1];
        visited = new boolean[N][M];
        Arrays.fill(line[0], true);
        Arrays.fill(line[N * 2], true);
        for (int r = 0; r <= N * 2; r++) {
            line[r][0] = true;
            line[r][M * 2] = true;
        }
        for (int T = Integer.parseInt(br.readLine()); T-- > 0; ) {
            draw(new StringTokenizer(br.readLine()));
        }
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (visited[r][c]) continue;
                int v = bfs(r, c);
                max = Math.max(max, v);
                min = Math.min(min, v);

            }
        }
        System.out.printf("%d\n%d\n", max, min);
    }

    private static void draw(StringTokenizer st) {
        int sx = Integer.parseInt(st.nextToken()) * 2;
        int sy = Integer.parseInt(st.nextToken()) * 2;
        int ex = Integer.parseInt(st.nextToken()) * 2;
        int ey = Integer.parseInt(st.nextToken()) * 2;
        sx = sx + ex - (ex = Math.max(sx, ex));
        sy = sy + ey - (ey = Math.max(sy, ey));
        for (int x = sx; x <= ex; x++) {
            for (int y = sy; y <= ey; y++) {
                line[x][y] = true;
            }
        }
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};


    private static int bfs(int r, int c) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(r, c));
        visited[r][c] = true;
        int res = 1;
        while (!q.isEmpty()) {
            Pos p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                int lr = p.r * 2 + 1 + dr[d];
                int lc = p.c * 2 + 1 + dc[d];
                if (!isIn(nr, nc) || visited[nr][nc] || line[lr][lc]) continue;
                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
                res++;
            }
        }
        return res;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}