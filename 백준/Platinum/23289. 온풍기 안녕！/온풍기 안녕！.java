import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map, temp;
    static List<Pos> A, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N * 2 + 1][M * 2 + 1];
        temp = new int[N * 2 + 1][M * 2 + 1];
        A = new ArrayList<>();
        H = new ArrayList<>();
        for (int r = 1; r < N * 2; r += 2) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c < M * 2; c += 2) {
                int v = Integer.parseInt(st.nextToken());
                if (v == 5) A.add(new Pos(r, c, -1));
                else if (v > 0) H.add(new Pos(r, c, v - 1));
            }
        }
        Arrays.fill(map[0], -1);
        Arrays.fill(map[N * 2], -1);
        for (int r = 1; r < N * 2; r++) {
            map[r][0] = -1;
            map[r][M * 2] = -1;
        }
        int W = Integer.parseInt(br.readLine());
        while (W-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if (t == 0) map[(r - 1) * 2][c * 2 - 1] = -1;
            else map[r * 2 - 1][c * 2] = -1;
        }
        int res = 0;
        while (res <= 100) {
            run();
            spread();
            cool();
            res++;
            if (check()) break;
        }
        System.out.println(res);
    }

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] sd = {{2, 3}, {2, 3}, {1, 0}, {1, 0}};

    private static void run() {
        Queue<Pos> q = new ArrayDeque<>();
        for (Pos h : H) {
            boolean[][] visited = new boolean[N * 2 + 1][M * 2 + 1];
            int sr = h.r + dr[h.d] * 2;
            int sc = h.c + dc[h.d] * 2;
            if (!isIn(sr, sc)) continue;
            map[sr][sc] += 5;
            q.offer(new Pos(sr, sc, h.d));
            for (int v = 4; v > 0; v--) {
                for (int i = 0, size = q.size(); i < size; i++) {
                    Pos p = q.poll();
                    int nr = p.r + dr[p.d];
                    int nc = p.c + dc[p.d];
                    if (map[nr][nc] != -1) {
                        nr += dr[p.d];
                        nc += dc[p.d];
                        if (!visited[nr][nc]) {
                            visited[nr][nc] = true;
                            map[nr][nc] += v;
                            if (v > 1) q.offer(new Pos(nr, nc, p.d));
                        }
                    }
                    for (int d : sd[p.d]) {
                        nr = p.r + dr[d];
                        nc = p.c + dc[d];
                        if (map[nr][nc] == -1) continue;
                        nr += dr[d] + dr[p.d];
                        nc += dc[d] + dc[p.d];
                        if (!isIn(nr, nc) || map[nr][nc] == -1) continue;
                        nr += dr[p.d];
                        nc += dc[p.d];
                        if (visited[nr][nc]) continue;
                        visited[nr][nc] = true;
                        map[nr][nc] += v;
                        if (v > 1) q.offer(new Pos(nr, nc, p.d));
                    }
                }
            }
        }
    }

    private static void spread() {
        for (int r = 1; r < N * 2; r += 2) {
            for (int c = 1; c < M * 2; c += 2) {
                int v = map[r][c];
                if (v == 0) continue;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (map[nr][nc] == -1) continue;
                    nr += dr[d];
                    nc += dc[d];
                    if (v <= map[nr][nc]) continue;
                    int diff = (v - map[nr][nc]) / 4;
                    temp[r][c] -= diff;
                    temp[nr][nc] += diff;
                }
            }
        }

        for (int r = 1; r < N * 2; r += 2) {
            for (int c = 1; c <= M * 2; c += 2) {
                map[r][c] += temp[r][c];
                temp[r][c] = 0;
            }
        }
    }

    private static void cool() {
        for (int r = 1; r < N * 2; r += 2) {
            if (map[r][1] > 0) map[r][1]--;
            if (map[r][M * 2 - 1] > 0) map[r][M * 2 - 1]--;
        }

        for (int c = 3; c < M * 2 - 2; c += 2) {
            if (map[1][c] > 0) map[1][c]--;
            if (map[N * 2 - 1][c] > 0) map[N * 2 - 1][c]--;
        }
    }

    private static boolean check() {
        for (Pos p : A) {
            if (map[p.r][p.c] < K) return false;
        }
        return true;
    }


    private static boolean isIn(int r, int c) {
        return r >= 0 && r <= N * 2 && c >= 0 && c <= M * 2;
    }

    private static class Pos {
        int r, c, d;

        public Pos(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}