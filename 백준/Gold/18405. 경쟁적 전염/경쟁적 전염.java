import java.io.*;
import java.util.*;
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        List<Point> list = new ArrayList<>();
        for(int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 1; y <= N; y++) {
                int v = Integer.parseInt(st.nextToken());
                if (v != 0)
                    list.add(new Point(v,x,y));
            }
        }
        Collections.sort(list, ((o1, o2) -> o1.n - o2.n));
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        Queue<Point> q = new ArrayDeque<>();
        boolean visited[][] = new boolean[N + 1][N + 1];
        int map[][] = new int[N+1][N+1];
        for (Point p : list) {
            map[p.x][p.y] = p.n;
            visited[p.x][p.y] = true;
            q.offer(p);
        }
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int s = 0; s < S; s++) {
            for (int i = 0, size = q.size(); i < size; i++) {
                Point p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if (!isIn(nx, ny) || visited[nx][ny])
                        continue;
                    map[nx][ny] = p.n;
                    visited[nx][ny] = true;
                    q.offer(new Point(p.n, nx, ny));
                }
            }
        }
        System.out.println(map[X][Y]);
    }

    private static boolean isIn(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }

    private static class Point {
        int n, x, y;

        public Point(int n, int x, int y) {
            this.n = n;
            this.x = x;
            this.y = y;
        }
    }
}