import java.io.*;
import java.util.*;

public class Main {
    static int K, N, M;
    static boolean[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                map[n][m] = st.nextToken().equals("0");
        }
        visited = new boolean[K +1][N][M];
        solution();
    }

    static int[] dr = {1, 0, -1, 0, 2, 1, -1, -2, -2, -1, 1, 2};
    static int[] dc = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1};

    private static void solution() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0, 0));
        setVisited(0,0,0);
        int time = 0;
        while (!q.isEmpty()) {
            for(int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                if (p.r == N - 1 && p.c == M - 1) {
                    System.out.println(time);
                    return;
                }
                for (int d = 0; d < 12; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    int nk = p.k + (d >= 4 ? 1 : 0);
                    if(nk > K)
                        break;
                    if (!isIn(nr, nc) || visited[nk][nr][nc] || !map[nr][nc])
                        continue;
                    setVisited(nk, nr, nc);
                    q.offer(new Pos(nk, nr, nc));
                }
            }
            time++;
        }
        System.out.println(-1);
    }

    private static void setVisited(int k, int r, int c) {
        for (; k <= K; k++)
            visited[k][r][c] = true;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Pos {
        int k, r, c;

        public Pos(int k, int r, int c) {
            this.k = k;
            this.r = r;
            this.c = c;
        }
    }
}
