import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Pos> A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        A = new ArrayDeque<>();
        B = new ArrayDeque<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    A.offer(new Pos(r, c));
                    visited[r][c] = true;
                } else if (map[r][c] == 2) {
                    B.offer(new Pos(r, c));
                    visited[r][c] = true;
                } else if (map[r][c] == -1) {
                    visited[r][c] = true;
                }
            }
        }
        solution();
        int[] res = new int[4];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] > 0) res[map[r][c]]++;
            }
        }
        System.out.printf("%d %d %d\n", res[1], res[2], res[3]);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void solution() {
        while (!A.isEmpty() || !B.isEmpty()) {
            for (int i = 0, size = A.size(); i < size; i++) {
                Pos p = A.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || map[nr][nc] != 0) continue;
                    map[nr][nc] += 1;
                    A.offer(new Pos(nr, nc));
                }
            }
            for (int i = 0, size = B.size(); i < size; i++) {
                Pos p = B.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || visited[nr][nc]) continue;
                    if (map[nr][nc] == 0) B.offer(new Pos(nr, nc));
                    map[nr][nc] += 2;
                    visited[nr][nc] = true;
                }
            }
            for (int i = 0, size = A.size(); i < size; i++) {
                Pos p = A.poll();
                if (visited[p.r][p.c]) continue;
                visited[p.r][p.c] = true;
                A.offer(p);
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