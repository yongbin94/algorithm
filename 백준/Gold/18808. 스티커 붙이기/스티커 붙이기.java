import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            Sticker sticker = new Sticker(R, C);
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    if (st.nextToken().charAt(0) == '0') continue;
                    sticker.list.add(r * C + c);
                }
            }
            outer:
            for (int d = 0; d < 4; d++) {
                for (int n = 0; n < N; n++) {
                    for (int m = 0; m < M; m++) {
                        if (sticker.check(n, m, d)) break outer;
                    }
                }
            }
        }
        int answer = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m]) answer++;
            }
        }
        System.out.println(answer);
    }

    static int[][] dr = {{1, 0, 0, 0}, {0, 1, 0, 0}, {-1, 0, 1, 0}, {0, -1, 0, 1}};
    static int[][] dc = {{0, 1, 0, 0}, {-1, 0, 1, 0}, {0, -1, 0, 1}, {1, 0, 0, 0}};

    private static class Sticker {
        int r, c;
        List<Integer> list;

        public Sticker(int r, int c) {
            this.r = r;
            this.c = c;
            list = new ArrayList<>();
        }

        public boolean check(int n, int m, int d) {
            for (int v : list) {
                int nr = n + v / c * dr[d][0] + v % c * dr[d][1] + (r - 1) * dr[d][2] + (c - 1) * dr[d][3];
                int nc = m + v / c * dc[d][0] + v % c * dc[d][1] + (r - 1) * dc[d][2] + (c - 1) * dc[d][3];
                if (!isIn(nr, nc) || map[nr][nc]) return false;
            }
            for (int v : list) {
                int nr = n + v / c * dr[d][0] + v % c * dr[d][1] + (r - 1) * dr[d][2] + (c - 1) * dr[d][3];
                int nc = m + v / c * dc[d][0] + v % c * dc[d][1] + (r - 1) * dc[d][2] + (c - 1) * dc[d][3];
                map[nr][nc] = true;
            }
            return true;
        }

        private boolean isIn(int r, int c) {
            return r >= 0 && r < N && c >= 0 && c < M;
        }
    }
}