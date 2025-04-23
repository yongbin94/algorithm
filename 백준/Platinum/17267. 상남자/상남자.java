import java.io.*;
import java.util.*;

public class Main {

    static int N, M, L, R;
    static Area[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new Area[N + 1][M + 1];
        Queue<Move> q = new ArrayDeque<>();
        for (int n = 1; n <= N; n++) {
            String input = br.readLine();
            for (int m = 1; m <= M; m++) {
                if (input.charAt(m - 1) == '1') continue;
                Area area = map[n - 1][m] == null ? new Area() : map[n - 1][m].get();
                map[n][m] = area;
                Area left = map[n][m - 1];
                if (left != null) {
                    area.ls.add(left);
                    left.rs.add(area);
                }
                if (input.charAt(m - 1) == '2') {
                    area.visited = true;
                    q.offer(new Move(area, 0, 0));
                }
            }
        }
        int res = 0;
        while (!q.isEmpty()) {
            Move move = q.poll();
            res += move.area.size;
            if (move.l < L) {
                for (Area next : move.area.ls) {
                    if (next.visited) continue;
                    next.visited = true;
                    q.offer(new Move(next, move.l + 1, move.r));
                }
            }
            if (move.r < R) {
                for (Area next : move.area.rs) {
                    if (next.visited) continue;
                    next.visited = true;
                    q.offer(new Move(next, move.l, move.r + 1));
                }
            }
        }
        System.out.println(res);
    }

    private static class Area {
        int size;
        Set<Area> ls, rs;
        boolean visited;

        public Area() {
            size = 1;
            this.ls = new HashSet<>();
            this.rs = new HashSet<>();
            visited = false;
        }

        public Area get() {
            size++;
            return this;
        }
    }

    private static class Move {
        Area area;
        int l, r;

        public Move(Area area, int l, int r) {
            this.area = area;
            this.l = l;
            this.r = r;
        }
    }
}