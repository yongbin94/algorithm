import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] map;
    static List<Pos> D = new ArrayList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < N; c++) {
                char ch = input.charAt(c);
                if (ch == '#')
                    D.add(new Pos(r, c, 0, 0));
                map[r][c] = ch;
            }
        }
        solution();
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void solution() {
        Pos S = D.get(0);
        Pos E = D.get(1);
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(S.r, S.c, -4, 0));
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (p.r == E.r && p.c == E.c) {
                System.out.println(p.t - 1);
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (d == (p.d + 2) % 4)
                    continue;
                while (isIn(nr, nc) && map[nr][nc] != '*' && !visited[nr][nc]) {
                    if (map[nr][nc] != '.') {
                        pq.offer(new Pos(nr, nc, d, p.t + (p.d == d ? 0 : 1)));
                        visited[nr][nc] = true;
                        break;
                    }
                    nr += dr[d];
                    nc += dc[d];
                }
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Pos implements Comparable<Pos> {
        int r, c, d, t;

        public Pos(int r, int c, int d, int t) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.t = t;
        }

        @Override
        public int compareTo(Pos o) {
            return this.t - o.t;
        }
    }
}
