import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        int[] max = new int[N];
        for (int n = 0; n < N; n++) {
            int v = Integer.parseInt(br.readLine());
            pq.offer(new Pos(n, v));
            max[n] = v;
        }
        int[][] map = new int[N][N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < N; m++)
                map[n][m] = Integer.parseInt(st.nextToken());
        }

        int count = 0, sum = 0;
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (visited[p.n])
                continue;
            visited[p.n] = true;
            sum += p.w;
            if(++count == N)
                break;
            for (int n = 0; n < N; n++) {
                if (n == p.n || visited[n] || max[n] <= map[p.n][n])
                    continue;
                pq.offer(new Pos(n, map[p.n][n]));
            }
        }
        System.out.println(sum);
    }

    private static class Pos implements Comparable<Pos> {
        int n, w;

        public Pos(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Pos o) {
            return this.w - o.w;
        }
    }
}
