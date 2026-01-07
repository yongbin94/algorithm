import java.io.*;
import java.util.*;

public class Main {
    static List<Edge>[] edges;
    static List<Integer>[] T;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        cnt = new int[N + 1];
        edges = new ArrayList[1101];
        T = new ArrayList[1101];
        for (int i = 0; i < 1101; i++) {
            edges[i] = new ArrayList<>();
            T[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
            for (int j = 1; j < cnt[i]; j++) {
                int u = i * 100 + j;
                int v = i * 100 + j + 1;
                edges[u].add(new Edge(v, 1));
                edges[v].add(new Edge(u, 1));
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken()), p2 = Integer.parseInt(st.nextToken());
            int q1 = Integer.parseInt(st.nextToken()), q2 = Integer.parseInt(st.nextToken());
            int u = p1 * 100 + p2;
            int v = q1 * 100 + q2;
            T[u].add(v);
            T[v].add(u);
        }

        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            sb.append(dijkstra(a, b, v)).append("\n");
        }
        System.out.print(sb);
    }

    static int dijkstra(int s, int e, int w) {
        int[] memo = new int[1101];
        Arrays.fill(memo, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        memo[s] = 0;
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node u = pq.poll();

            if (u.w > memo[u.n]) continue;
            if (u.n == e) return memo[e];

            for (Edge v : edges[u.n]) {
                if (memo[v.v] > memo[u.n] + v.w) {
                    memo[v.v] = memo[u.n] + v.w;
                    pq.add(new Node(v.v, memo[v.v]));
                }
            }

            for (int v : T[u.n]) {
                if (memo[v] > memo[u.n] + w) {
                    memo[v] = memo[u.n] + w;
                    pq.add(new Node(v, memo[v]));
                }
            }
        }
        return memo[e];
    }

    private static class Node implements Comparable<Node> {
        int n, w;

        Node(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    private static class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}