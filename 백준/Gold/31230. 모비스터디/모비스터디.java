import java.io.*;
import java.util.*;

public class Main {
    static final long INF = 100_000_000_000_000_000L;
    static int N, M, A, B;
    static List<Edge>[] E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        E = new ArrayList[N + 1];
        Arrays.setAll(E, e -> new ArrayList<>());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            E[a].add(new Edge(b, w));
            E[b].add(new Edge(a, w));
        }
        long[] am = new long[N + 1];
        long[] bm = new long[N + 1];
        Arrays.fill(am, INF);
        Arrays.fill(bm, INF);
        dijkstra(am, A, B);
        dijkstra(bm, B, A);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = 1; i <= N; i++) {
            if(am[i] + bm[i] == am[B]) {
                count++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(count);
        System.out.println(sb);
    }

    private static void dijkstra(long[] memo, int a, int b) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(a, 0));
        memo[a] = 0;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (s.e == b)
                return;
            if(memo[s.e] < s.w)
                continue;
            for (Edge e : E[s.e]) {
                if (memo[e.e] <= memo[s.e] + e.w)
                    continue;
                memo[e.e] = memo[s.e] + e.w;
                pq.offer(new Edge(e.e, s.w + e.w));
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int e;
        long w;

        public Edge(int e, long w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.w, o.w);
        }
    }
}