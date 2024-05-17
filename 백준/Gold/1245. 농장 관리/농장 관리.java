import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                map[n][m] = Integer.parseInt(st.nextToken());
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!visited[r][c])
                    if (bfs(r, c))
                        answer++;
            }
        }
        System.out.println(answer);
    }

    static int[] dr = {0, 1, 0, -1, 1, 1, -1, -1};
    static int[] dc = {1, 0, -1, 0, 1, -1, 1, -1};

    private static boolean bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        visited[r][c] = true;
        boolean result = true;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 8; d++) {
                int nr = p.x + dr[d];
                int nc = p.y + dc[d];
                if (!isIn(nr, nc))
                    continue;
                if (map[nr][nc] > map[r][c]) {
                    result = false;
                    continue;
                }
                if (visited[nr][nc] || map[nr][nc] != map[r][c])
                    continue;
                visited[nr][nc] = true;
                q.offer(new Point(nr, nc));
            }
        }
        return result;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}