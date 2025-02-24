import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] map, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        outer:
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new boolean[N][M];
            visited = new boolean[N][M];
            for (int n = 0; n < N; n++) {
                String input = br.readLine();
                for (int m = 0; m < M; m++) {
                    map[n][m] = input.charAt(m) == '#';
                }
            }
            boolean flag = false;
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (!map[n][m] || visited[n][m]) continue;
                    if (!check(new Pos(n, m)) || flag) {
                        sb.append("0\n");
                        continue outer;
                    }
                    flag = true;
                }
            }
            sb.append(flag ? "1\n" : "0\n");
        }
        System.out.println(sb);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static boolean check(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.r][pos.c] = true;
        Pos minA = new Pos(10, 10);
        Pos maxA = new Pos(0, 0);
        while (!q.isEmpty()) {
            Pos p = q.poll();
            minA.setMin(p.r, p.c);
            maxA.setMax(p.r, p.c);
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || !map[nr][nc] || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
        if (maxA.r - minA.r != maxA.c - minA.c)
            return false;

        Pos minB = new Pos(10, 10);
        Pos maxB = new Pos(0, 0);
        int cnt = 0;
        for (int r = minA.r; r <= maxA.r; r++) {
            for (int c = minA.c; c <= maxA.c; c++) {
                if (!visited[r][c]) {
                    minB.setMin(r, c);
                    maxB.setMax(r, c);
                    cnt++;
                }
            }
        }
        int n = maxB.r - minB.r + 1;
        int m = maxB.c - minB.c + 1;
        return n == m && n * m == cnt && (!visited[maxA.r][maxA.c] || !visited[maxA.r][minA.c] || !visited[minA.r][maxA.c] || !visited[minA.r][minA.c]);
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

        public void setMin(int r, int c) {
            this.r = Math.min(this.r, r);
            this.c = Math.min(this.c, c);
        }

        public void setMax(int r, int c) {
            this.r = Math.max(this.r, r);
            this.c = Math.max(this.c, c);
        }
    }
}