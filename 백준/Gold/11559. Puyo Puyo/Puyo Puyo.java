import java.io.*;
import java.util.*;

public class Main {
    static final int N = 12, M = 6;
    static boolean[][] visited;
    static char[][] map = new char[N][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();
        int time = 0;
        while (play()) {
            time++;
        }
        System.out.println(time);
    }

    private static boolean play() {
        boolean ret = false;
        visited = new boolean[N][M];
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (map[r][c] != '.' && !visited[r][c])
                    if (bfs(new Pos(r, c)))
                        ret = true;

        move();
        return ret;
    }

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static boolean bfs(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        Queue<Pos> q1 = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.r][pos.c] = true;
        while (!q.isEmpty()) {
            Pos p = q.poll();
            q1.offer(p);
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || visited[nr][nc] || map[pos.r][pos.c] != map[nr][nc])
                    continue;
                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
        if(q1.size() >= 4){
            while(!q1.isEmpty()) {
                Pos p = q1.poll();
                map[p.r][p.c] = '.';
            }
            return true;
        }
        return false;
    }

    private static void move() {
        for (int r = N - 1; r >= 0; r--)
            for (int c = 0; c < M; c++) {
                if(map[r][c] != '.') {
                    int nr = r + 1;
                    if (nr < N && map[nr][c] == '.') {
                        char ch = map[r][c];
                        map[r][c] = '.';
                        while (nr + 1 < N && map[nr + 1][c] == '.')
                            nr++;
                        map[nr][c] = ch;
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
