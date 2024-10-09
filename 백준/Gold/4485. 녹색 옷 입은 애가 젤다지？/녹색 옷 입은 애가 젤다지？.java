import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int[][] map, memo;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;
            map = new int[N][];
            memo = new int[N][N];
            Arrays.stream(memo).forEach(r -> Arrays.fill(r, Integer.MAX_VALUE));
            visited = new boolean[N][N];
            for (int n = 0; n < N; n++)
                map[n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            start();
            sb.append("Problem ").append(tc++).append(": ").append(memo[N-1][N-1]).append("\n");
        }
        System.out.println(sb);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void start() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (p.r == N - 1 && p.c == N - 1)
                break;
            visited[p.r][p.c] = true;
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || visited[nr][nc])
                    continue;
                if (p.w + map[nr][nc] < memo[nr][nc]) {
                    memo[nr][nc] = p.w + map[nr][nc];
                    pq.offer(new Pos(nr, nc, memo[nr][nc]));
                }
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Pos implements Comparable<Pos> {
        int r, c, w;

        public Pos(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.w, o.w);
        }
    }
}