import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] visited;
    static boolean[][] route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1][N * 2 + 1];
        route = new boolean[N + 1][N * 2 + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            visited[y][x] = true;
        }
        if (visited[0][N * 2]) {
            System.out.println(-1);
            return;
        }
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0));
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (p.y < N * 2 - p.x && !visited[p.y + 1][p.x + 1]) {
                visited[p.y + 1][p.x + 1] = true;
                route[p.y + 1][p.x + 1] = true;
                pq.offer(new Pos(p.y + 1, p.x + 1));
            }
            if (p.y > 0 && !visited[p.y - 1][p.x + 1]) {
                visited[p.y - 1][p.x + 1] = true;
                route[p.y - 1][p.x + 1] = false;
                pq.offer(new Pos(p.y - 1, p.x + 1));
            }
        }
        if (!visited[0][N * 2]) {
            System.out.println(-1);
            return;
        }
        int y = 0;
        int x = N * 2;
        int answer = 0;
        while (x != 0) {
            if (route[y][x])
                y--;
            else
                answer = Math.max(answer, ++y);
            x--;
        }
        System.out.println(answer);
    }

    private static class Pos implements Comparable<Pos> {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Pos o) {
            return this.x != o.x
                    ? this.x - o.x
                    : o.y - this.y;
        }
    }
}