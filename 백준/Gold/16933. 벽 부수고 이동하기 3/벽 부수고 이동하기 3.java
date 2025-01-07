import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static boolean[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++)
                map[n][m] = input.charAt(m) == '1';
        }
        visited = new boolean[K + 1][N][M];
        solution();
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void solution() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0, 0));
        visited[0][0][0] = true;
        int time = 0;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                if (p.r == N - 1 && p.c == M - 1) {
                    System.out.println(time + 1);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc))
                        continue;
                    if (map[nr][nc] && (time % 2 == 1 || p.k == K))
                        continue;
                    int nk = p.k + (map[nr][nc] ? 1 : 0);
                    if (visited[nk][nr][nc])
                        continue;
                    q.offer(new Pos(nr, nc, nk));
                    visited[nk][nr][nc] = true;
                }
                if (time % 2 == 1)
                    q.offer(p);
            }
            time++;
        }
        System.out.println(-1);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }


    private static class Pos {
        int r, c, k;

        public Pos(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
    }
}