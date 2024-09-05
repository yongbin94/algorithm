import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Edge>[] edges;
    static long[] memo;
    static long[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N];
        memo = new long[N];
        res = new long[N];
        for (int n = 0; n < N; n++)
            edges[n] = new ArrayList<>();
        Arrays.fill(memo, Long.MAX_VALUE);
        Arrays.fill(res, Long.MAX_VALUE);
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
        solution();
        System.out.println(Arrays.stream(res).sum());
    }

    private static void solution() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        memo[0] = 0;
        res[0] = 0;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (memo[s.e] < s.w)
                continue;
            for (Edge e : edges[s.e]) {
                long nw = memo[s.e] + e.w;
                if (memo[e.e] < nw)
                    continue;
                res[e.e] = memo[e.e] == nw ? Math.min(res[e.e], memo[s.e] * 9 / 10 + e.w) : memo[s.e] * 9 / 10 + e.w;
                if (memo[e.e] > nw) {
                    memo[e.e] = nw;
                    pq.offer(new Edge(e.e, nw));
                }
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