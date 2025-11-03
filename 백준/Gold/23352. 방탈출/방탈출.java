import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cnt, answer;
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
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m] == 0) continue;
                bfs(n, m);
            }
        }
        System.out.println(answer);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void bfs(int r, int c) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(r, c));
        boolean[][] visited = new boolean[N][M];
        visited[r][c] = true;
        for (int time = 0; !q.isEmpty(); time++) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                if (cnt <= time) {
                    int v = map[r][c] + map[p.r][p.c];
                    if (cnt < time) {
                        cnt = time;
                        answer = v;
                    } else {
                        answer = Math.max(answer, v);
                    }
                }
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}