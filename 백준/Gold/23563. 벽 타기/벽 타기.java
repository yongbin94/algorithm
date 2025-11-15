import java.io.*;
import java.util.*;

public class Main {
    static int H, W;
    static char[][] map;
    static boolean[][] hasWall;
    static int[][] memo;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H + 2][W + 2];
        hasWall = new boolean[H + 2][W + 2];
        memo = new int[H + 2][W + 2];
        Arrays.stream(memo).forEach(v -> Arrays.fill(v, 100_000_000));
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        for (int h = 1; h <= H; h++) {
            String input = br.readLine();
            for (int w = 1; w <= W; w++) {
                char ch = input.charAt(w - 1);
                map[h][w] = ch;
                if (ch == '#') {
                    memo[h][w] = -1;
                    for (int d = 0; d < 4; d++) {
                        hasWall[h + dr[d]][w + dc[d]] = true;
                    }
                } else if (ch == 'S') {
                    memo[h][w] = 0;
                    pq.offer(new Pos(h, w, 0));
                }
            }
        }
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (map[p.r][p.c] == 'E') {
                System.out.println(p.w);
                return;
            }
            if (memo[p.r][p.c] < p.w) continue;
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                int nw = p.w + ((hasWall[p.r][p.c] && hasWall[nr][nc]) ? 0 : 1);
                if (memo[nr][nc] <= nw) continue;
                memo[nr][nc] = nw;
                pq.offer(new Pos(nr, nc, nw));
            }
        }
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