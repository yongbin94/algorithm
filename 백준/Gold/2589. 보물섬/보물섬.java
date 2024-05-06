import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();
        int answer = 0;
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (map[r][c] == 'L')
                    answer = Math.max(answer, bfs(new Pos(r, c)));
        System.out.println(answer);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static int bfs(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited = new boolean[N][M];
        visited[pos.r][pos.c] = true;
        int time = -1;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == 'W')
                        continue;
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));

                }
            }
            time++;
        }
        return time;
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