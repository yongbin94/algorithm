import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] map;
    static boolean[][][] visited;
    static Pos S, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = st.nextToken().charAt(0) == '1';
            }
        }
        // 북 동 남 서
        ///동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4
        int[] D = {0, 1, 3, 2, 0};
        st = new StringTokenizer(br.readLine());
        S = new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, D[Integer.parseInt(st.nextToken())]);
        st = new StringTokenizer(br.readLine());
        E = new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, D[Integer.parseInt(st.nextToken())]);
        visited = new boolean[N][M][4];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(S);
        visited[S.r][S.c][S.d] = true;
        int time = 0;
        while (true) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                if (p.equals(E)) {
                    System.out.println(time);
                    return;
                }
                if (!visited[p.r][p.c][(p.d + 1) % 4]) {
                    q.offer(new Pos(p.r, p.c, (p.d + 1) % 4));
                    visited[p.r][p.c][(p.d + 1) % 4] = true;
                }
                if (!visited[p.r][p.c][(p.d + 3) % 4]) {
                    q.offer(new Pos(p.r, p.c, (p.d + 3) % 4));
                    visited[p.r][p.c][(p.d + 3) % 4] = true;
                }
                int nr = p.r + dr[p.d];
                int nc = p.c + dc[p.d];
                int k = 0;
                while (isIn(nr, nc) && !map[nr][nc] && k++ < 3) {
                    if (!visited[nr][nc][p.d]) {
                        q.offer(new Pos(nr, nc, p.d));
                        visited[nr][nc][p.d] = true;
                    }
                    nr += dr[p.d];
                    nc += dc[p.d];
                }
            }
            time++;
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }


    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static class Pos {
        int r, c, d;

        public Pos(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public boolean equals(Pos o) {
            return this.r == o.r && this.c == o.c && this.d == o.d;
        }
    }
}