import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Pos S, F;
    static boolean[][] garbage, border;
    static Info[][] memo;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        garbage = new boolean[N][M];
        border = new boolean[N][M];
        memo = new Info[N][M];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                memo[r][c] = new Info();
                switch (input.charAt(c)) {
                    case '.':
                        break;
                    case 'g':
                        garbage[r][c] = true;
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (!isIn(nr, nc))
                                continue;
                            border[nr][nc] = true;
                        }
                        break;
                    case 'S':
                        S = new Pos(r, c, 0, 0);
                        break;
                    case 'F':
                        F = new Pos(r, c, 0, 0);
                }
            }
        }
        memo[S.r][S.c] = new Info(0, 0);
        border[F.r][F.c] = false;
        solution();
    }

    private static void solution() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(S);
        memo[S.r][S.c] = new Info(0, 0);
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (F.equals(p)) {
                System.out.println(p.g + " " + p.b);
                return;
            }
            if (memo[p.r][p.c].compareTo(p) > 0)
                continue;
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc))
                    continue;
                int ng = p.g + (garbage[nr][nc] ? 1 : 0);
                int nb = p.g != ng ? p.b : border[nr][nc] ? p.b + 1 : p.b;
                if (ng != memo[nr][nc].g ? memo[nr][nc].g < ng : memo[nr][nc].b <= nb)
                    continue;
                memo[nr][nc] = new Info(ng, nb);
                pq.offer(new Pos(nr, nc, ng, nb));
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Pos implements Comparable<Pos> {
        int r, c, g, b;

        public Pos(int r, int c, int g, int b) {
            this.r = r;
            this.c = c;
            this.g = g;
            this.b = b;
        }

        @Override
        public int compareTo(Pos o) {
            return this.g == o.g ? this.b - o.b : this.g - o.g;
        }

        public boolean equals(Pos o) {
            return this.r == o.r && this.c == o.c;
        }
    }

    private static class Info {
        int g, b;

        public Info() {
            this.g = Integer.MAX_VALUE;
        }

        public Info(int g, int b) {
            this.g = g;
            this.b = b;
        }

        public int compareTo(Pos o) {
            return this.g == o.g ? this.b - o.b : this.g - o.g;
        }
    }
}