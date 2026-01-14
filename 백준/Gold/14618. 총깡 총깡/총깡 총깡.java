import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int J = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[] A = new int[K];
        st = new StringTokenizer(br.readLine());
        int i = 0;
        while (i < K) A[i++] = Integer.parseInt(st.nextToken());

        int[] B = new int[K];
        st = new StringTokenizer(br.readLine());
        i = 0;
        while (i < K) B[i++] = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[u].add(new Edge(v, w));
            edges[v].add(new Edge(u, w));
        }

        int[] memo = dijkstra(J);

        int minA = Integer.MAX_VALUE;
        for (int node : A) minA = Math.min(minA, memo[node]);

        int minB = Integer.MAX_VALUE;
        for (int node : B) minB = Math.min(minB, memo[node]);

        if (minA == Integer.MAX_VALUE && minB == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else if (minA <= minB) {
            System.out.println("A");
            System.out.println(minA);
        } else {
            System.out.println("B");
            System.out.println(minB);
        }
    }

    private static int[] dijkstra(int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] memo = new int[N + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        
        memo[s] = 0;
        pq.offer(new Edge(s, 0));

        while (!pq.isEmpty()) {
            Edge u = pq.poll();
            
            if (u.w > memo[u.n]) continue;

            for (Edge v : edges[u.n]) {
                int nw = u.w + v.w;
                if (memo[v.n] <= nw) continue;
                
                memo[v.n] = nw;
                pq.offer(new Edge(v.n, nw));
            }
        }
        return memo;
    }

    private static class Edge implements Comparable<Edge> {
        int n, w;

        public Edge(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}