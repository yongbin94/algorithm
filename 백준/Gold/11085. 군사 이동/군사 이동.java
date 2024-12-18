import java.io.*;
import java.util.*;

public class Main {
    static int P, W, C, V;
    static List<Edge>[] edges;
    static int[] memo;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        edges = new ArrayList[P];
        Arrays.setAll(edges, v -> new ArrayList<>());
        memo = new int[P];
        visited = new boolean[P];
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        while(W-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
        solution();
    }

    private static void solution() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(C, Integer.MAX_VALUE));
        memo[C] = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            Edge s = pq.poll();
            if(s.e == V) {
                System.out.println(s.w);
                return;
            }
            if(visited[s.e])
                continue;
            visited[s.e] = true;
            for(Edge e : edges[s.e]) {
                int nw = Math.min(s.w, e.w);
                if(visited[e.e] || memo[e.e] >= nw)
                    continue;
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
            return o.w - this.w;
        }
    }
}