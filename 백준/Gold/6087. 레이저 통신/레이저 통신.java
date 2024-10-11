import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        Queue<Pos> q = new ArrayDeque<>();
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                char ch = map[r][c];
                if (ch == 'C') {
                    if (q.isEmpty()) {
                        visited[r][c] = true;
                        map[r][c] = '.';
                        q.offer(new Pos(r, c));
                    }
                }
            }
        }
        int count = 0;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    while (isIn(nr, nc) && map[nr][nc] != '*') {
                        if (!visited[nr][nc]) {
                            visited[nr][nc] = true;
                            q.offer(new Pos(nr, nc));
                        }
                        if (map[nr][nc] == 'C') {
                            System.out.println(count);
                            return;
                        }
                        nr += dr[d];
                        nc += dc[d];
                    }
                }
            }
            count++;
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