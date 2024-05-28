import java.io.*;
import java.util.*;

public class Main {
    static int N = 8;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][];
        for (int r = 0; r < N; r++)
            map[r] = br.readLine().toCharArray();

        int[] dr = {0, 1, 0, -1, 1, 1, -1, -1};
        int[] dc = {1, 0, -1, 0, 1, -1, 1, -1};
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(N - 1, 0));
        int time = 0;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                if (map[p.r][p.c] == '#')
                    continue;
                if (p.r == 0 && p.c == N - 1) {
                    System.out.println(1);
                    return;
                }
                q.offer(p);
                for (int d = 0; d < 8; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || map[nr][nc] == '#')
                        continue;
                    q.offer(new Pos(nr, nc));
                }
            }
            if (time < 8) {
                for (int n = N - 1; n >= 1; n--)
                    map[n] = map[n - 1];
                map[0] = "........".toCharArray();
                time++;
            }
        }
        System.out.println(0);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}