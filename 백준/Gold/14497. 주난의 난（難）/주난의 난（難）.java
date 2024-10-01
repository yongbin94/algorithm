import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static Pos P;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        st = new StringTokenizer(br.readLine());
        P = new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1);
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();
        solution();
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};


    private static void solution() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(P);
        visited[P.r][P.c] = true;
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (map[p.r][p.c] == '#') {
                System.out.println(p.t);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || visited[nr][nc])
                    continue;
                visited[nr][nc] = true;
                pq.offer(new Pos(nr, nc, map[nr][nc] == '1' ? p.t + 1 : p.t));
            }
        }

    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Pos implements Comparable<Pos> {
        int r, c, t;

        public Pos(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public int compareTo(Pos o) {
            return this.t - o.t;
        }
    }
}