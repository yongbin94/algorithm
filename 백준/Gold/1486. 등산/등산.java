import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static int N, M, T, D, answer;
    static int[][] map, A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        A = new int[N][M];
        B = new int[N][M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                char ch = input.charAt(m);
                map[n][m] = ch < 'a' ? ch - 'A' : ch - 'a' + 26;
            }
            Arrays.fill(A[n], INF);
            Arrays.fill(B[n], INF);
        }
        dijkstra1(A, false);
        dijkstra1(B, true);
        System.out.println(answer);
    }


    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void dijkstra1(int[][] memo, boolean isReturning) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, 0));
        memo[0][0] = 0;
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (p.w > memo[p.r][p.c]) continue;
            if (isReturning && A[p.r][p.c] + p.w <= D) {
                answer = Math.max(answer, map[p.r][p.c]);
            }
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc)) continue;
                int diff = isReturning ? map[p.r][p.c] - map[nr][nc] : map[nr][nc] - map[p.r][p.c];
                int nw = p.w + (diff > 0 ? diff * diff : 1);
                if (Math.abs(diff) > T || memo[nr][nc] <= nw) continue;
                pq.offer(new Pos(nr, nc, nw));
                memo[nr][nc] = nw;
            }
        }
    }


    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Pos implements Comparable<Pos> {
        int r, c, w;

        public Pos(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Pos o) {
            return this.w - o.w;
        }
    }
}