import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] map;
    static int[][] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N + 2][M + 2];
        for (int r = 1; r <= N; r++) {
            String input = br.readLine();
            for (int c = 1; c <= M; c++) {
                map[r][c] = input.charAt(c - 1) == '1';
            }
        }
        solution();
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int v = S[r1][c1] + S[r2][c2] - S[r1][c2] - S[r2][c1];
            sb.append(v == 0 ? "Yes\n" : String.format("No %d\n", v));
        }
        System.out.println(sb);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void solution() {
        S = new int[N + 2][M + 2];
        Arrays.stream(S).forEach(v -> Arrays.fill(v, 1));
        S[0][0] = 0;
        map[0][0] = true;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));
        while (!q.isEmpty()) {
            Pos p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || map[nr][nc]) continue;
                S[nr][nc] = 0;
                map[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }

        for (int r = 1; r <= N + 1; r++) {
            for (int c = 1; c <= M + 1; c++) {
                S[r][c] += S[r][c - 1] + S[r - 1][c] - S[r - 1][c - 1];
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r <= N + 1 && c >= 0 && c <= M + 1;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}