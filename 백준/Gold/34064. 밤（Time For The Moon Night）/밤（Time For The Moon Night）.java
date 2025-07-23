import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long answer;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 9;
        }
        for (int i = 1; i <= 2; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());
            for (int r = sr; r <= er; r++) {
                for (int c = sc; c <= ec; c++) {
                    if (map[r][c] == 9) continue;
                    map[r][c] += i;
                }
            }
        }
        solution();
        System.out.println(answer);
    }

    private static void solution() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (visited[r][c] || map[r][c] == 9) continue;
                bfs(new Pos(r, c));
            }
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void bfs(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        long[] cnt = new long[4];
        cnt[map[pos.r][pos.c]]++;
        visited[pos.r][pos.c] = true;
        while (!q.isEmpty()) {
            Pos p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == 9) continue;
                q.offer(new Pos(nr, nc));
                cnt[map[nr][nc]]++;
                visited[nr][nc] = true;
            }
        }
        answer += (cnt[1] + cnt[3]) * (cnt[2] + cnt[3]);
    }

    private static boolean isIn(int r, int c) {
        return r >= 1 && r <= N && c >= 1 && c <= M;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}