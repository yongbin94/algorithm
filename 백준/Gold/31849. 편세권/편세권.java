import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R, C, answer;
    static int[][] map;
    static boolean[][] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        Queue<StringTokenizer> queue = new ArrayDeque<>();
        while (R-- > 0) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = Integer.parseInt(st.nextToken());
        }
        answer = Integer.MAX_VALUE;
        bfs();
        System.out.println(answer);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void bfs() throws IOException {
        Queue<Pos> q = new ArrayDeque<>();
        int cnt = 0;
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            visited[r][c] = true;
            q.offer(new Pos(r, c));
        }
        while (!q.isEmpty()) {
            cnt++;
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || visited[nr][nc])
                        continue;
                    visited[nr][nc] = true;
                    if (map[nr][nc] != 0)
                        answer = Math.min(answer, map[nr][nc] * cnt);
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