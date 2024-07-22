import java.io.*;
import java.util.*;

public class Main {
    static int K, N, M;
    static int A[];
    static int[][] map;
    static Pos E;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            A = new int[26];
            visited = new boolean[N][M];
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                A[st.nextToken().charAt(0) - 'A'] = Integer.parseInt(st.nextToken());
            }
            map = new int[N][M];
            for (int n = 0; n < N; n++) {
                String input = br.readLine();
                for (int m = 0; m < M; m++) {
                    map[n][m] = A[input.charAt(m) - 'A'];
                    if (input.charAt(m) == 'E')
                        E = new Pos(n, m, 0);
                }
            }
            sb.append(solution()).append("\n");
        }
        System.out.println(sb);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static int solution() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(E);
        visited[E.r][E.c] = true;
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc))
                    return p.w;
                if (visited[nr][nc])
                    continue;
                pq.offer(new Pos(nr, nc, p.w + map[nr][nc]));
                visited[nr][nc] = true;
            }
        }
        return -1;
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