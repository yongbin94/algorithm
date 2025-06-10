import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m) - '0';
            }
        }
        System.out.println(solution());
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static int solution() {
        int res = 0;
        for (int i = 1; i <= 9; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (map[r][c] != i) continue;
                    map[r][c]++;
                    Queue<Pos> q = new ArrayDeque<>();
                    q.offer(new Pos(r, c));
                    boolean flag = true;
                    int cnt = 0;
                    while (!q.isEmpty()) {
                        Pos p = q.poll();
                        cnt++;
                        for (int d = 0; d < 4; d++) {
                            int nr = p.r + dr[d];
                            int nc = p.c + dc[d];
                            if (!isIn(nr, nc)) {
                                flag = false;
                                continue;
                            }
                            if (map[nr][nc] != i) continue;
                            map[nr][nc]++;
                            q.offer(new Pos(nr, nc));
                        }
                    }
                    if (flag) {
                        res += cnt;
                    }
                }
            }
        }
        return res;
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