import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static boolean[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new boolean[R + 2][C + 2];
        for (int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= C; c++) {
                map[r][c] = st.nextToken().charAt(0) == '1';
            }
        }
        solution();
    }

    static int[] dr = {-1, -1, 0, 0, 1, 1};
    static int[] dc = {-1, 0, -1, 1, -1, 0, 0, 1, -1, 1, 0, 1};

    private static void solution() {
        int res = 0;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));
        boolean[][] outer = new boolean[R + 2][C + 2];
        outer[0][0] = true;
        while (!q.isEmpty()) {
            Pos p = q.poll();
            for (int d = 0; d < 6; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d + (p.r % 2 * 6)];
                if (!isIn(nr, nc) || outer[nr][nc]) continue;
                if (map[nr][nc]) res++;
                else {
                    q.offer(new Pos(nr, nc));
                    outer[nr][nc] = true;
                }
            }
        }
        System.out.println(res);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < R + 2 && c >= 0 && c < C + 2;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}