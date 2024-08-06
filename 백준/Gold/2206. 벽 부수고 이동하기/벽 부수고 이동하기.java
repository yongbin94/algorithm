import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static boolean[][] map, visitedA, visitedB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        visitedA = new boolean[N][M];
        visitedB = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++)
                map[n][m] = input.charAt(m) == '1';
        }
        result = -1;
        bfs();
        System.out.println(result);
    }

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0, false));
        visitedA[0][0] = true;
        visitedB[0][0] = true;
        int time = 0;
        while (!q.isEmpty()) {
            time++;
            for (int idx = 0, size = q.size(); idx < size; idx++) {
                Pos pos = q.poll();
                if (pos.r == N - 1 && pos.c == M - 1) {
                    result = time;
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = pos.r + dr[d];
                    int nc = pos.c + dc[d];
                    if (!isIn(nr, nc))
                        continue;
                    if (map[nr][nc]) {
                        if (pos.destroyed)
                            continue;
                        visitedB[nr][nc] = true;
                        q.offer(new Pos(nr, nc, true));
                    } else {
                        if (visitedA[nr][nc])
                            continue;
                        if (pos.destroyed) {
                            if (visitedB[nr][nc])
                                continue;
                            visitedB[nr][nc] = true;
                        } else {
                            visitedA[nr][nc] = true;
                            visitedB[nr][nc] = true;
                        }
                        q.offer(new Pos(nr, nc, pos.destroyed));
                    }
                }
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }


    static class Pos {
        int r;
        int c;
        boolean destroyed;

        public Pos(int r, int c, boolean destroyed) {
            this.r = r;
            this.c = c;
            this.destroyed = destroyed;
        }
    }
}