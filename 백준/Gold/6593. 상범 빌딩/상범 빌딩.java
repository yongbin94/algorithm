import java.io.*;
import java.util.*;

public class Main {
    static int L, R, C;
    static char[][][] map;
    static boolean[][][] visited;
    static Pos S;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0) {
                System.out.println(sb);
                return;
            }
            map = new char[L][R][C];
            visited = new boolean[L][R][C];
            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String str = br.readLine();
                    map[l][r] = str.toCharArray();
                    if (str.indexOf('S') != -1)
                        S = new Pos(l, r, str.indexOf('S'));
                }
                br.readLine();
            }
            bfs();
        }

    }

    static int[] dl = {0, 0, 0, 0, 1, -1};
    static int[] dr = {1, 0, -1, 0, 0, 0};
    static int[] dc = {0, 1, 0, -1, 0, 0};

    private static void bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(S);
        visited[S.l][S.r][S.c] = true;
        int time = 0;
        while (!q.isEmpty()) {
            time++;
            for (int i = 0, size = q.size(); i < size; i++) {
                Pos p = q.poll();
                for (int d = 0; d < 6; d++) {
                    int nl = p.l + dl[d];
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (!isIn(nl, nr, nc) || visited[nl][nr][nc] || map[nl][nr][nc] == '#')
                        continue;
                    if(map[nl][nr][nc] == 'E') {
                        sb.append("Escaped in ").append(time).append(" minute(s).\n");
                        return;
                    }
                    visited[nl][nr][nc] = true;
                    q.offer(new Pos(nl,nr,nc));
                }
            }
        }
        sb.append("Trapped!\n");
    }

    private static boolean isIn(int l, int r, int c) {
        return l >= 0 && l < L && r >= 0 && r < R && c >= 0 && c < C;
    }

    private static class Pos {
        int l, r, c;

        public Pos(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }
}
