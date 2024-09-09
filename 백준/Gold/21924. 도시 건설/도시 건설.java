import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long total;
    static List<Edge>[] edges;
    static boolean[] visited;
    static long[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N];
        visited = new boolean[N];
        memo = new long[N];
        Arrays.fill(memo, Long.MAX_VALUE);
        for (int n = 0; n < N; n++)
            edges[n] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
            total += w;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        memo[0] = 0;
        long answer = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (visited[s.e])
                continue;
            visited[s.e] = true;
            answer += s.w;
            if (++count == N)
                break;
            for (Edge e : edges[s.e]) {
                if (visited[e.e] || memo[e.e] <= e.w)
                    continue;
                memo[e.e] = e.w;
                pq.offer(new Edge(e.e, e.w));
            }
        }
        System.out.println(count == N ? total - answer : -1);
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