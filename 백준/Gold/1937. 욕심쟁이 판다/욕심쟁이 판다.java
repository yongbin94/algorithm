import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = 0;
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[][] memo = new int[N][N];
        PriorityQueue<Pos> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int v = Integer.parseInt(st.nextToken());
                map[r][c] = v;
                pq.offer(new Pos(r, c, v));
            }
        }
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || map[p.r][p.c] >= map[nr][nc])
                    continue;
                int v = memo[p.r][p.c] + 1;
                if (v <= memo[nr][nc])
                    continue;
                memo[nr][nc] = v;
                answer = Math.max(answer, v);
            }
        }
        System.out.println(answer + 1);
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Pos {
        int r, c, w;

        public Pos(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
}
