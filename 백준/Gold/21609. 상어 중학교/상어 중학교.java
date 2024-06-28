import java.io.*;
import java.util.*;

public class Main {
    static int N, M, score;
    static int[][] map;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            if (solution())
                break;
            rotate();
        }
        System.out.println(score);
    }


    private static boolean solution() {
        visited = new int[N][N];
        Pos maxPos = null;
        int maxValue = 0;
        int maxRainbow = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] <= 0 || visited[r][c] != 0)
                    continue;
                int[] v = bfs(new Pos(r, c));
                if (v[0] >= maxValue) {
                    if (v[0] == maxValue && v[1] < maxRainbow)
                        continue;
                    maxPos = new Pos(r, c);
                    maxValue = v[0];
                    maxRainbow = v[1];
                }
            }
        }
        if (maxValue <= 1)
            return true;

        remove(maxPos);
        gravity();
        return false;
    }


    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    private static int[] bfs(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.r][pos.c] = map[pos.r][pos.c];
        int res = 0, rainbow = 0;
        while (!q.isEmpty()) {
            Pos p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || (map[nr][nc] != 0 ? (visited[nr][nc] != 0 || map[pos.r][pos.c] != map[nr][nc]) : (visited[nr][nc] == map[pos.r][pos.c])))
                    continue;
                visited[nr][nc] = map[pos.r][pos.c];
                if (map[nr][nc] == 0)
                    rainbow++;
                q.offer(new Pos(nr, nc));
            }
            res++;
        }
        return new int[]{res, rainbow};
    }

    private static void remove(Pos pos) {
        int v = map[pos.r][pos.c], cnt = 0;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        map[pos.r][pos.c] = -2;
        while (!q.isEmpty()) {
            Pos p = q.poll();
            cnt++;
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || (map[nr][nc] != 0 && map[nr][nc] != v))
                    continue;
                map[nr][nc] = -2;
                q.offer(new Pos(nr, nc));
            }
        }
        score += cnt * cnt;
    }


    private static void gravity() {
        for (int r = N - 2; r >= 0; r--) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] >= 0) {
                    int nr = r + 1;
                    while (nr < N && map[nr][c] == -2) {
                        map[nr][c] = map[nr - 1][c];
                        map[nr - 1][c] = -2;
                        nr++;
                    }
                }
            }
        }
    }

    private static void rotate() {
        int tmp;
        for (int r = 0; r < N / 2; r++) {
            for (int c = r; c < N - r - 1; c++) {
                tmp = map[r][c];
                map[r][c] = map[c][N - r - 1];
                map[c][N - r - 1] = map[N - r - 1][N - c - 1];
                map[N - r - 1][N - c - 1] = map[N - c - 1][r];
                map[N - c - 1][r] = tmp;
            }
        }
        gravity();
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}