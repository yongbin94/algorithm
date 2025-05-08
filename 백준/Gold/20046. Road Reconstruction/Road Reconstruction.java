import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

    static int[] dn = {0, 1, 0, -1};
    static int[] dm = {1, 0, -1, 0};

    private static int solution() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        int[][] memo = new int[N][M];
        Arrays.stream(memo).forEach(v -> Arrays.fill(v, Integer.MAX_VALUE));
        pq.offer(new Pos(-1, 0, 0));
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (p.n == N - 1 && p.m == M - 1) return p.w;
            for (int d = 0; d < 4; d++) {
                int nn = p.n + dn[d];
                int nm = p.m + dm[d];
                if (!isIn(nn, nm) || map[nn][nm] == -1) continue;
                int nw = p.w + map[nn][nm];
                if (memo[nn][nm] <= nw) continue;
                pq.offer(new Pos(nn, nm, nw));
                memo[nn][nm] = nw;
            }
        }
        return -1;
    }

    private static boolean isIn(int n, int m) {
        return n >= 0 && n < N && m >= 0 && m < M;
    }

    private static class Pos implements Comparable<Pos> {
        int n, m, w;

        public Pos(int n, int m, int w) {
            this.n = n;
            this.m = m;
            this.w = w;
        }

        @Override
        public int compareTo(Pos o) {
            return this.w - o.w;
        }
    }
}