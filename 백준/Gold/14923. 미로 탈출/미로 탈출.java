import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Pos S, E;
    static boolean[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        S = new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, false);
        st = new StringTokenizer(br.readLine());
        E = new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, false);
        map = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                if (st.nextToken().charAt(0) == '1')
                    map[n][m] = true;
        }
        solution();
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void solution() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(S);
        visited = new boolean[2][N][M];
        visited[0][S.r][S.c] = true;
        int time = 0;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                if (E.r == p.r && E.c == p.c) {
                    System.out.println(time);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || (p.w && map[nr][nc]))
                        continue;
                    if (p.w || map[nr][nc]) {
                        if (visited[1][nr][nc] || visited[0][nr][nc])
                            continue;
                        visited[1][nr][nc] = true;
                        q.offer(new Pos(nr, nc, true));
                    } else {
                        if (visited[0][nr][nc])
                            continue;
                        visited[0][nr][nc] = true;
                        q.offer(new Pos(nr, nc, false));
                    }
                }
            }
            time++;
        }
        System.out.println(-1);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Pos {
        int r, c;
        boolean w;

        public Pos(int r, int c, boolean w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
}