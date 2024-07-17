import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Pos> sandCastle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        sandCastle = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) != '.')
                    map[i][j] = input.charAt(j) - '0';
            }
        }
        init();
        int answer = 0;
        while (!sandCastle.isEmpty()) {
            wave();
            answer++;
        }
        System.out.println(answer);
    }

    private static void wave() {
        for (int i = 0, size = sandCastle.size(); i < size; i++) {
            Pos p = sandCastle.poll();
            for (int d = 0; d < 8; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || map[nr][nc] == 0)
                    continue;
                if (--map[nr][nc] == 0)
                    sandCastle.offer(new Pos(nr, nc));
            }
        }
    }

    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

    private static void init() {
        Queue<Pos> tmp = new ArrayDeque<>();
        visited = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0)
                    tmp.offer(new Pos(r, c));
            }
        }
        while (!tmp.isEmpty()) {
            Pos p = tmp.poll();
            for (int d = 0; d < 8; d++) {
                visited[p.r][p.c] = true;
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc) || map[nr][nc] == 0)
                    continue;
                if (--map[nr][nc] == 0)
                    sandCastle.offer(new Pos(nr, nc));
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
