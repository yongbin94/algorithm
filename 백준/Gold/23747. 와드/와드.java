import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N, M;
    static Pos H;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = input.charAt(c) - 'a';
            }
        }
        st = new StringTokenizer(br.readLine());
        H = new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        String cmd = br.readLine();
        for (char c : cmd.toCharArray()) {
            if (c == 'W')
                ward();
            else {
                move(c);
            }
        }
        watch();
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++)
                sb.append(visited[r][c] ? '.' : '#');
            sb.append("\n");
        }
        System.out.println(sb);
    }


    static int[] dr = {0, 1, 0, -1, 0};
    static int[] dc = {1, 0, -1, 0, 0};

    private static void ward() {
        int v = map[H.r][H.c];
        visited[H.r][H.c] = true;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(H.r, H.c));
        while (!q.isEmpty()) {
            Pos p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] != v)
                    continue;
                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
    }


    private static void move(char c) {
        int d = c == 'R' ? 0
                : c == 'D' ? 1
                : c == 'L' ? 2
                : 3;
        H.r += dr[d];
        H.c += dc[d];
    }

    private static void watch() {
        for (int d = 0; d < 5; d++) {
            int nr = H.r + dr[d];
            int nc = H.c + dc[d];
            if (!isIn(nr, nc))
                continue;
            visited[nr][nc] = true;
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
