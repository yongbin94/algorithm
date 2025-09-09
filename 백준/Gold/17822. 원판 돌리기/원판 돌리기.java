import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T, total, cnt;
    static int[][] map;
    static int[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        D = new int[N];
        cnt = N * M;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
                total += map[n][m];
            }
        }
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (d == 1) k = -k;
            for (int i = x - 1; i < N; i += x) {
                D[i] = (D[i] + k + M) % M;
            }
            solution();
        }
        System.out.println(total);
    }


    private static void solution() {
        int prev = cnt;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m] == 0) continue;
                bfs(n, m);
            }
        }
        if (prev == cnt) {
            float avg = (float) total / cnt;
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (map[n][m] == 0) continue;
                    if (map[n][m] > avg) {
                        map[n][m]--;
                        total--;
                    } else if (map[n][m] < avg) {
                        map[n][m]++;
                        total++;
                    }
                }
            }
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void bfs(int n, int m) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(n, m));
        int v = map[n][m];
        map[n][m] = 0;
        boolean flag = false;
        while (!q.isEmpty()) {
            Pos p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                if (nr < 0 || nr >= N) continue;
                int nc = (p.c + dc[d] + (D[p.r] - D[nr]) + M) % M;
                if (v != map[nr][nc]) continue;
                map[nr][nc] = 0;
                total -= v;
                cnt--;
                q.offer(new Pos(nr, nc));
                flag = true;
            }
        }
        if (flag) {
            total -= v;
            cnt--;
            return;
        }
        map[n][m] = v;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}