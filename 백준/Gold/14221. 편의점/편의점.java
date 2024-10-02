import java.io.*;
import java.util.*;

public class Main {
    static int N, M, P, Q;
    static int[] memo;
    static boolean[] store, visited;
    static PriorityQueue<Edge> pq;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N];
        store = new boolean[N];
        visited = new boolean[N];
        memo = new int[N];
        Arrays.fill(memo, Integer.MAX_VALUE);
        for (int n = 0; n < N; n++)
            edges[n] = new ArrayList<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, w, 0));
            edges[b].add(new Edge(a, w, 0));
        }
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken()) - 1;
            memo[v] = 0;
            pq.offer(new Edge(v, 0, v + 1));
        }
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken()) - 1;
            store[v] = true;
        }

        solution();

    }

    private static void solution() {
        while (!pq.isEmpty()) {
            Edge s = pq.poll();
            if (visited[s.e])
                continue;
            visited[s.e] = true;
            if (store[s.e]) {
                System.out.println(s.h);
                return;
            }
            for (Edge e : edges[s.e]) {
                if (visited[e.e] || memo[e.e] < memo[s.e] + e.w)
                    continue;
                memo[e.e] = memo[s.e] + e.w;
                pq.offer(new Edge(e.e, memo[e.e], s.h));
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int e, w, h;

        public Edge(int e, int w, int h) {
            this.e = e;
            this.w = w;
            this.h = h;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w != o.w
                    ? this.w - o.w
                    : this.h - o.h;
        }
    }
}