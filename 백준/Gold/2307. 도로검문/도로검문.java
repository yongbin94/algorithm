import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, org, max;
    static List<Edge>[] list;
    static int[] memo, prev;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        prev = new int[N];
        prev[0] = -1;
        list = new ArrayList[N];
        
        for (int i = 0; i < N; i++)
            list[i] = new ArrayList<>();
        
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new Edge(b, w));
            list[b].add(new Edge(a, w));
        }
        
        dijkstra(null);
        
        for (int a = N - 1; a >= 0; a = prev[a])
            dijkstra(new Edge(a, prev[a]));
        
        System.out.println(max == INF ? -1 : max);
    }

    private static void dijkstra(Edge rm) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        memo = new int[N];
        Arrays.fill(memo, Integer.MAX_VALUE);
        visited = new boolean[N];
        
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (visited[s.e]) continue;
            visited[s.e] = true;
            for (Edge e : list[s.e]) {
                if (rm != null && ((s.e == rm.e || s.e == rm.w) && (e.e == rm.e || e.e == rm.w))) {
                    continue;
                }
                int w = s.w + e.w;
                if (visited[e.e] || memo[e.e] <= w) continue;
                memo[e.e] = w;
                if (rm == null) {
                    prev[e.e] = s.e;
                }
                pq.offer(new Edge(e.e, w));
            }
        }
        
        if (rm == null) org = memo[N - 1];
        else max = Math.max(max, memo[N - 1] == INF ? INF : memo[N - 1] - org);

    }

    private static class Edge implements Comparable<Edge> {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}