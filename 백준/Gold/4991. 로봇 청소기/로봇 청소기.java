import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L;
    static int[][] map;
    static int[][] dist;
    static List<Pos> list;
    static int[] count;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        outer:
        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) {
                System.out.println(sb);
                return;
            }
            map = new int[N][M];
            list = new ArrayList<>();
            list.add(new Pos(0, 0));
            int idx = 1;
            for (int n = 0; n < N; n++) {
                String input = br.readLine();
                for (int m = 0; m < M; m++) {
                    int ch = input.charAt(m);
                    if (ch == 'o') {
                        list.set(0, new Pos(n, m));
                    } else if (ch == 'x')
                        map[n][m] = -1;
                    else if (ch == '*') {
                        map[n][m] = idx++;
                        list.add(new Pos(n, m));
                    }
                }
            }
            dist = new int[idx][idx];
            count = new int[idx];
            Arrays.fill(count, idx - 1);
            for (int i = 0; i < idx; i++)
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            for (int i = 0; i < idx; i++) {
                bfs(i);
                if (count[i] != 0) {
                    sb.append(-1).append("\n");
                    continue outer;
                }
            }
            solution();
            sb.append(answer).append("\n");
        }
    }

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void bfs(int I) {
        Pos pos = list.get(I);
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited = new boolean[N][M];
        visited[pos.r][pos.c] = true;
        int time = 0;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                if (map[p.r][p.c] != 0 && map[p.r][p.c] != I) {
                    int idx = map[p.r][p.c];
                    if (dist[I][idx] == Integer.MAX_VALUE) {
                        dist[I][idx] = time;
                        dist[idx][I] = time;
                        count[idx]--;
                        if (--count[I] == 0)
                            return;
                    }
                }
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == -1)
                        continue;
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
            time++;
        }
    }

    static int answer;
    static int[] order;
    static boolean[] used;

    private static void solution() {
        answer = Integer.MAX_VALUE;
        order = new int[list.size() - 1];
        used = new boolean[list.size()];
        recur(0);
    }

    private static void recur(int cnt) {
        if (cnt == list.size() - 1) {
            int sum = dist[0][order[0]];
            for (int i = 0; i < list.size() - 2; i++)
                sum += dist[order[i]][order[i + 1]];
            answer = Math.min(answer, sum);
        }
        for (int i = 1; i < list.size(); i++) {
            if (used[i])
                continue;
            used[i] = true;
            order[cnt] = i;
            recur(cnt + 1);
            used[i] = false;
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
