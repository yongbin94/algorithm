import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] max;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = -1;
            map[b][a] = -1;
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    map[i][j] = 0;
                    continue;
                }
                map[i][j] = v;
            }
        }
        solution();
    }

    private static void solution() {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 1, 0));
        visited = new boolean[N];
        max = new int[N];
        Arrays.fill(max, Integer.MAX_VALUE);
        int count = 1;
        int res = 0, K = 0;
        visited[0] = true;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if(visited[s.e])
                continue;
            visited[s.e] = true;
            if(s.w > 0) {
                res += s.w;
                sb.append(s.s + 1).append(" ").append(s.e + 1).append("\n");
                K++;
            }
            if (++count == N) {
                System.out.println(res + " " + K);
                System.out.println(sb);
                return;
            }
            for (int i = 0; i < N; i++) {
                if (visited[i] || i == s.e || max[i] <= map[s.e][i])
                    continue;
                pq.offer(new Edge(s.e, i, map[s.e][i]));
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}
