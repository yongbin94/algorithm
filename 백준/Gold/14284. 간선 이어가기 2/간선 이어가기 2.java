import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge>[] edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] memo = new int[N + 1];
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        pq.offer(new Edge(S, 0));
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[S] = 0;
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (s.e == T) {
                System.out.println(s.w);
                return;
            }
            if (s.w > memo[s.e]) continue;
            for (Edge e : edges[s.e]) {
                int nw = s.w + e.w;
                if (memo[e.e] <= nw) continue;
                memo[e.e] = nw;
                pq.offer(new Edge(e.e, nw));
            }
        }
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