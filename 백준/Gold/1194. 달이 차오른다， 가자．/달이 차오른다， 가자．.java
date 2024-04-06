import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    static Queue<Pos> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        visited = new boolean[1 << 6][N][M];
        q = new ArrayDeque<>();
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();
        find();
        bfs();
    }

    private static void find() {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m] == '0') {
                    q.offer(new Pos(0, n, m));
                    visited[0][n][m] = true;
                    return;
                }
            }
        }
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void bfs() {
        int time = 0;
        while (!q.isEmpty()) {
            time++;
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    int nk = p.k;
                    if (!isIn(nr, nc) || visited[p.k][nr][nc] || map[nr][nc] == '#')
                        continue;
                    int v = map[nr][nc];
                    if (v == '1') {
                        System.out.println(time);
                        return;
                    } else if (v >= 'a')
                        nk = p.k | (1 << (v - 'a'));
                    else if (v >= 'A')
                        if ((p.k >> (v - 'A') & 1) == 0)
                            continue;

                    visited[nk][nr][nc] = true;
                    q.offer(new Pos(nk, nr, nc));
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Pos {
        int k, r, c;

        public Pos(int k, int r, int c) {
            this.k = k;
            this.r = r;
            this.c = c;
        }
    }
}