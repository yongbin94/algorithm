import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S;
    static List<Edge>[] edges;
    static int[] A;
    static int[][] dist;
    static boolean[] B;
    static int[] idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            edges = new ArrayList[N];
            Arrays.setAll(edges, v -> new ArrayList<>());
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                edges[x].add(new Edge(y, d));
                edges[y].add(new Edge(x, d));
            }
            S = Integer.parseInt(br.readLine()) + 1;
            A = new int[S];
            B = new boolean[N];
            B[0] = true;
            idx = new int[N];
            dist = new int[S][S];
            Arrays.stream(dist).forEach(v -> Arrays.fill(v, Integer.MAX_VALUE));
            for (int s = 1; s < S; s++) {
                A[s] = Integer.parseInt(br.readLine());
                B[A[s]] = true;
                idx[A[s]] = s;
            }
            for (int i = 0; i < S; i++) {
                dijkstra(i);
            }
            System.out.println(solution());
        }
    }

    private static void dijkstra(int i) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(A[i], 0));
        int[] memo = new int[N];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[A[i]] = 0;
        while (!pq.isEmpty()) {
            Edge u = pq.poll();
            for (Edge v : edges[u.v]) {
                int nw = u.w + v.w;
                if (memo[v.v] <= nw) continue;
                memo[v.v] = nw;
                if (B[v.v]) {
                    dist[i][idx[v.v]] = Math.min(dist[i][idx[v.v]], nw);
                }
                pq.offer(new Edge(v.v, nw));
            }
        }
    }

    private static int solution() {
        PriorityQueue<EdgeBit> pq = new PriorityQueue<>();
        pq.offer(new EdgeBit(0, 0, 1));
        int[][] memo = new int[S][1 << S];
        Arrays.stream(memo).forEach(v -> Arrays.fill(v, Integer.MAX_VALUE));
        memo[0][1] = 0;
        while (!pq.isEmpty()) {
            EdgeBit u = pq.poll();
            if (memo[u.v][u.b] < u.w) continue;
            for (int v = 1; v < S; v++) {
                if (u.v == v) continue;
                int nb = u.b | (1 << v);
                int nw = u.w + dist[u.v][v];
                if (memo[v][nb] <= nw) continue;
                memo[v][nb] = nw;
                pq.offer(new EdgeBit(v, nw, nb));
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < S; i++) {
            res = Math.min(res, memo[i][(1 << S) - 1] + dist[0][i]);
        }
        return res;
    }

    private static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    private static class EdgeBit extends Edge {
        int b;

        public EdgeBit(int v, int w, int b) {
            super(v, w);
            this.b = b;
        }
    }
}