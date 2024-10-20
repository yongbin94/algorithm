import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T;
    static char[][] map;
    static Queue<Pos> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        q = new ArrayDeque<>();
        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            for (int m = 0; m < M; m++) {
                char c = str.charAt(m);
                if (c == '@')
                    q.offer(new Pos(n, m));
                if (c == '*' || c == '#')
                    T++;
                map[n][m] = c;
            }
        }
        solution();
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void solution() {
        int answer = 0;
        Pos p = q.poll();
        for (int d = 0; d < 4; d++) {
            for (int i = 1; i <= 2; i++) {
                int nr = p.r + dr[d] * i;
                int nc = p.c + dc[d] * i;
                if (!isIn(nr, nc) || map[nr][nc] == '|')
                    break;
                if (map[nr][nc] == '*') {
                    map[nr][nc] = '.';
                    answer++;
                    q.offer(new Pos(nr, nc));
                } else if (map[nr][nc] == '#') {
                    map[nr][nc] = '*';
                }
            }
        }
        while (!q.isEmpty()) {
            p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || map[nr][nc] == '|')
                    continue;
                if (map[nr][nc] == '*') {
                    map[nr][nc] = '.';
                    answer++;
                    q.offer(new Pos(nr, nc));
                } else if (map[nr][nc] == '#') {
                    map[nr][nc] = '*';
                }
            }
        }
        System.out.println(answer + " " + (T - answer));
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