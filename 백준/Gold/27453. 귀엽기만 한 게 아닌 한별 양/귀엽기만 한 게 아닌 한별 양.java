import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static int N, M, K;
    static int[][] map;
    static int[][][] memo;
    static Pos S, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        memo = new int[4][N][M];
        Arrays.stream(memo).forEach(v -> Arrays.stream(v).forEach(w -> Arrays.fill(w, INF)));
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                char ch = input.charAt(c);
                if (ch == 'S') {
                    S = new Pos(r, c, 0);
                } else if (ch == 'H') {
                    H = new Pos(r, c, 0);
                } else {
                    map[r][c] = ch == 'X' ? -1 : ch - '0';
                }
            }
        }
        solution();
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void solution() {
        Queue<Pos> q = new ArrayDeque<>();
        for (int d = 0; d < 4; d++) {
            int nr = S.r + dr[d];
            int nc = S.c + dc[d];
            if (!isIn(nr, nc) || map[nr][nc] == -1)
                continue;
            q.offer(new Pos(nr, nc, d));
            memo[d][S.r][S.c] = 0;
            memo[d][nr][nc] = map[nr][nc];
        }
        int time = 1;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                if (p.r == H.r && p.c == H.c) {
                    System.out.println(time);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    if (d == (p.d + 2) % 4)
                        continue;
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || map[nr][nc] == -1 || memo[d][nr][nc] <= map[p.r][p.c] + map[nr][nc])
                        continue;
                    if (K < memo[p.d][p.r][p.c] + map[nr][nc])
                        continue;
                    memo[d][nr][nc] = map[p.r][p.c] + map[nr][nc];
                    q.offer(new Pos(nr, nc, d));
                }
            }
            time++;
        }
        System.out.println(-1);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
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