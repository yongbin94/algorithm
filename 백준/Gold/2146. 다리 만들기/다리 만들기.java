import java.io.*;
import java.util.*;

public class Main {
    static int N, idx, answer;
    static int[][] map;
    static boolean[][] visited;
    static List<Queue<Pos>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        answer = Integer.MAX_VALUE;
        map = new int[N][N];
        list.add(null);
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                if (st.nextToken().equals("1"))
                    map[r][c] = Integer.MAX_VALUE;
            }
        }
        solution();
    }

    private static void solution() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == Integer.MAX_VALUE)
                    bfs1(new Pos(r, c));
            }
        }
        for (int i = 1, size = list.size(); i < size; i++)
            bfs2(list.get(i));
        System.out.println(answer);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    private static void bfs1(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        list.add(new ArrayDeque<>());
        q.offer(pos);
        map[pos.r][pos.c] = ++idx;
        while (!q.isEmpty()) {
            Pos p = q.poll();
            boolean flag = false;
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (!isIn(nr, nc))
                    continue;
                if (map[nr][nc] == 0)
                    flag = true;
                if (map[nr][nc] != Integer.MAX_VALUE)
                    continue;
                q.offer(new Pos(nr, nc));
                map[nr][nc] = idx;
            }
            if (flag)
                list.get(idx).offer(p);
        }
    }

    private static void bfs2(Queue<Pos> q) {
        int v = map[q.peek().r][q.peek().c];
        visited = new boolean[N][N];
        int time = 0;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc))
                        continue;
                    if (map[nr][nc] != 0 && map[nr][nc] != v) {
                        answer = time;
                        return;
                    }
                    if (map[nr][nc] != 0 || visited[nr][nc])
                        continue;
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
            if (++time >= answer)
                return;
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}