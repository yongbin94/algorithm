import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 2];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int l = 1, r = 1_000_000;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static boolean check(int v) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));
        boolean[][] visited = new boolean[N + 1][M + 2];
        visited[0][0] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            Pos p = q.poll();
            if (map[p.r][p.c] != 0) cnt++;
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] > v) continue;
                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
        return cnt >= K;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r <= N && c >= 0 && c <= M + 1;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}